package com.nfc.manager.nfc_manager.utils.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageTypeValidator implements ConstraintValidator<ValidImage, MultipartFile> {
    private final static String FILE_JPEG = "image/jpeg";
    private final static String FILE_PNG = "image/png";
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file.isEmpty()){
            return true;
        }
        String contentType = file.getContentType();
        if(!contentType.equals(FILE_JPEG) && !contentType.equals(FILE_PNG)) {
            return false;
        }
        // Verify if the content is indeed an image
        try {
            BufferedImage bi = ImageIO.read(file.getInputStream());
            return bi != null;
        } catch (IOException e) {
            return false;
        }
    }
}
