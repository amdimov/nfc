package com.nfc.manager.nfc_manager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@Service
public interface S3Manager {
    URL uploadImage(MultipartFile multipartFile);
    void deleteImage(URL url);
    URL stringToUrl(String urlString);
    URL replaceImageByURL(String urlToDelete, MultipartFile multipartFile);
}
