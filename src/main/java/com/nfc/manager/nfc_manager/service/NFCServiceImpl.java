package com.nfc.manager.nfc_manager.service;

import com.nfc.manager.nfc_manager.entity.DTO.NFC_DTO;
import com.nfc.manager.nfc_manager.entity.NFC;
import com.nfc.manager.nfc_manager.entity.UserEntity;
import com.nfc.manager.nfc_manager.repositories.NFCRepo;
import com.nfc.manager.nfc_manager.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class NFCServiceImpl implements NFCService {

    private final UserRepo userRepo;
    private final NFCRepo nfcRepo;
    private final ModelMapper modelMapper;
    private final S3Manager s3Manager;

    public NFCServiceImpl(UserRepo userRepo, NFCRepo nfcRepo, ModelMapper modelMapper, S3Manager s3Manager) {
        this.userRepo = userRepo;
        this.nfcRepo = nfcRepo;
        this.modelMapper = modelMapper;
        this.s3Manager = s3Manager;
    }

    @Override
    public NFC_DTO renderNFCView() {
        return null;
    }

    //Set random NFC ID and Static NFC Hash
    @Override
    public NFC_DTO buildNFC_DTO() {
        NFC_DTO nfc = new NFC_DTO();
        String randomUUID = UUID.randomUUID().toString().replace("-", "");
        String code = randomUUID.substring(0, 5);
        UUID hash = UUID.randomUUID();
        return nfc.setStaticNFC_URL(hash.toString().toUpperCase(Locale.ROOT))
                .setNfcCode(code.toUpperCase(Locale.ROOT));
    }

    @Override
    public Boolean addNFC(String username, NFC_DTO nfc) {
        UserEntity user = userRepo.findUserByUsername(username)
                .orElseThrow(()-> new IllegalArgumentException("Can't add NFC. Username wasn't found"));
        System.out.println(nfc);
        NFC nfcEntity = modelMapper.map(nfc, NFC.class);
        if (!nfc.getFilePreview().isEmpty()) {
            URL uploadedImage = s3Manager.uploadImage(nfc.getFilePreview());
            nfcEntity.setImagePreviewURL(uploadedImage.toString());
        }
        nfcEntity.setDeleted(false).setCreatedDateTime(LocalDateTime.now())
                .setDisabled(false);
        nfcRepo.save(nfcEntity);
        user.setNfcList(List.of(nfcEntity));
        userRepo.save(user);
        return true;
    }
}
