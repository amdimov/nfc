package com.nfc.manager.nfc_manager.service;

import com.nfc.manager.nfc_manager.config.s3.S3Bucket;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

@Service
public class S3ManagerImpl implements S3Manager {
    private final S3Client s3Client;

    public S3ManagerImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    /**
     * Upload a file to AWS S3 to BUCKET_1 only
     * @param multipartFile
     * @return URL of the S3 uploaded file
     */
    @Override
    public URL uploadImage(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()){
            throw new IllegalArgumentException("File is missing in uploadImage s3");
        }
        String tempFileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
        File tempFile = null;
        try {
            tempFile = File.createTempFile(tempFileName, multipartFile.getOriginalFilename());
            multipartFile.transferTo(tempFile);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(S3Bucket.BUCKET_1)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .key(tempFileName)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromFile(tempFile));

        URL url = s3Client.utilities().getUrl(builder -> builder.bucket(S3Bucket.BUCKET_1).key(tempFileName));
        tempFile.delete();
        return url;
    }

    @Override
    public void deleteImage(URL fileUrl) {
        String key = fileUrl.getPath().substring(fileUrl.getPath().indexOf('/') + 1);
        // remove leading '/'

        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(S3Bucket.BUCKET_1)
                .key(key)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
    }
    @Override
    public URL stringToUrl(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL: " + urlString, e);
        }
    }

    /**
     * Deleted the existing url and returns an new URL from the uploaded MultipartFile
     * @param urlToDelete
     * @param multipartFile
     * @return the new URL from uploaded file
     */
    @Override
    public URL replaceImageByURL(String urlToDelete, MultipartFile multipartFile){
        URL deletedUrl = stringToUrl(urlToDelete);
        deleteImage(deletedUrl);
        return uploadImage(multipartFile);
    }
}
