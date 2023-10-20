package com.nfc.manager.nfc_manager.service;

import com.nfc.manager.nfc_manager.entity.DTO.NFC_DTO;
import com.nfc.manager.nfc_manager.entity.NFC;
import com.nfc.manager.nfc_manager.entity.UserEntity;
import com.nfc.manager.nfc_manager.entity.views.NFC_View;
import com.nfc.manager.nfc_manager.entity.views.UserView;
import com.nfc.manager.nfc_manager.repositories.NFCRepo;
import com.nfc.manager.nfc_manager.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
                .setUser(user)
                .setDisabled(false);
        nfcRepo.save(nfcEntity);
        user.setNfcList(List.of(nfcEntity));
        userRepo.save(user);
        return true;
    }

    @Override
    @Transactional
    public Page<NFC_View> getAllNfcOfUser(Integer pageNo, Integer pageSize, String sortBy, String username) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        UserEntity user = userRepo.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Username wasn't found in retrieving NFC"));
        Optional<Page<List<NFC>>> allNfcOfUser1 = Optional.of(nfcRepo.getAllNfcOfUser(user.getUsername(), pageable));

        //TODO
        List<NFC_View> nfcViews = allNfcOfUser1.get().stream().map(nfc -> modelMapper.map(nfc, NFC_View.class)).toList();
        return new PageImpl<>(nfcViews, pageable, allNfcOfUser1.get().getTotalElements());
    }


}
