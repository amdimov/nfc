package com.nfc.manager.nfc_manager.service;

import com.nfc.manager.nfc_manager.entity.DTO.NFC_DTO;
import com.nfc.manager.nfc_manager.entity.DTO.NFC_Edit_DTO;
import com.nfc.manager.nfc_manager.entity.DTO.NFC_Single_Edit_DTO;
import com.nfc.manager.nfc_manager.entity.NFC;
import com.nfc.manager.nfc_manager.entity.UserEntity;
import com.nfc.manager.nfc_manager.entity.views.NFCGeneralStatisticsView;
import com.nfc.manager.nfc_manager.entity.views.NFC_View;
import com.nfc.manager.nfc_manager.entity.views.NFCsingleStatsView;
import com.nfc.manager.nfc_manager.entity.views.UserView;
import com.nfc.manager.nfc_manager.repositories.NFCRepo;
import com.nfc.manager.nfc_manager.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
        String code = randomUUID.substring(0, 10);
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
        List<NFC> nfcList = user.getNfcList();
        nfcList.add(nfcEntity);
        user.setNfcList(nfcList);
        userRepo.save(user);
        return true;
    }

    @Override
    public Page<NFC_View> getAllNfcOfUser(Integer pageNo, Integer pageSize, String sortBy, String username,
                                          String searchNFC) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("nfc." + sortBy).descending());
        UserEntity user = userRepo.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Username wasn't during retrieving NFC"));
        if (!searchNFC.isEmpty() || !searchNFC.isBlank()){
            return getAllNfcBySearchTerm(searchNFC.trim(), user, pageable);
        }
        Page<NFC> allNfcOfUser = nfcRepo.getAllNfcOfUser(user.getUsername(), pageable);
        if (allNfcOfUser == null || !allNfcOfUser.hasContent()) {
            return Page.empty(pageable);
        }

        List<NFC_View> nfcViews = allNfcOfUser.stream().map(nfc -> modelMapper.map(nfc, NFC_View.class)).collect(Collectors.toList());
        return new PageImpl<>(nfcViews, pageable, allNfcOfUser.getTotalElements());
    }

    private Page<NFC_View> getAllNfcBySearchTerm(String searchNFC, UserEntity user, Pageable pageable) {
        Page<NFC> allNfcOfUser = nfcRepo.findAllUserNfcBySearchTerm(user.getUsername(), searchNFC, pageable);
        List<NFC_View> nfcViews = allNfcOfUser.stream().map(nfc -> modelMapper.map(nfc, NFC_View.class)).collect(Collectors.toList());
        if (allNfcOfUser == null || !allNfcOfUser.hasContent()) {
            return Page.empty(pageable);
        }
        return new PageImpl<>(nfcViews, pageable, allNfcOfUser.getTotalElements());
    }
    @Override
    public NFC_Edit_DTO getUsersNFCByNfcCode(String username, String nfcCode) {
        NFC nfc = nfcRepo.findByUser_UsernameAndNfcCode(username, nfcCode)
        .orElseThrow(() -> new IllegalArgumentException("Username wasn't during retrieving NFC for editing"));
        return modelMapper.map(nfc, NFC_Edit_DTO.class);
    }

    @Override
    public Boolean editUsersNFC(String username, NFC_Edit_DTO userNfc) {
        UserEntity user = userRepo.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("No user found during NFC edit"));
        Optional<NFC> nfcOpt = user.getNfcList().stream().filter(nfc -> nfc.getNfcCode().equals(userNfc.getNfcCode()))
                .findFirst();
        if (nfcOpt.isPresent()){
            if (!userNfc.getFilePreview().isEmpty()) {
                URL url = s3Manager.replaceImageByURL(nfcOpt.get().getImagePreviewURL(), userNfc.getFilePreview());
                userNfc.setImagePreviewURL(url.toString());
            }
            userNfc.setCreatedDateTime(nfcOpt.get().getCreatedDateTime());
            nfcOpt.ifPresent(nfc -> modelMapper.map(userNfc, nfc));
            System.out.println(userNfc);
            System.out.println(nfcOpt.get());
            nfcRepo.save(nfcOpt.get());
            userRepo.save(user);
            return true;
        }

        return false;
    }

    @Override
    public NFCGeneralStatisticsView getGeneralStatisticsOfUser(String username) {
        UserEntity user = userRepo.findUserByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException("No username found during extracting general statistics"));
        List<NFC> userNfcs = user.getNfcList();
        Integer totalNumberOfNFCs = userNfcs.stream().mapToInt(nfc -> nfc.getNumberOfNFCs()).sum();
        Integer numberOfCampaigns = userNfcs.size();
        Long totalNumberOfViews = userNfcs.stream().mapToLong(nfc -> nfc.getNumberOfViews()).sum();
        return new NFCGeneralStatisticsView().setTotalNumberOfNFCs(totalNumberOfNFCs)
                .setNumberOfCampaigns(numberOfCampaigns)
                .setTotalNumberOfViews(totalNumberOfViews);
    }

    @Override
    public NFCsingleStatsView extractStatisticsOfNFC(String username, String nfcCode) {
        NFC nfc = nfcRepo.findByUser_UsernameAndNfcCode(username, nfcCode).orElseThrow(() ->
                new IllegalArgumentException("No username or nfc found during fetching nfc statics"));
        double numberOfNFCs = (double) nfc.getNumberOfNFCs();
        double numberOfViews = (double) nfc.getNumberOfViews();
        BigDecimal result = BigDecimal.valueOf(numberOfViews / numberOfNFCs * 100)
                .setScale(2, RoundingMode.HALF_UP);
        double percentOfOpenedNFCvsQuantity = result.doubleValue();
        return new NFCsingleStatsView().setNfcQuantity((int) numberOfNFCs)
                .setTotalViews((long) numberOfViews)
                .setPercentOfOpenedNFCVsQuantity(percentOfOpenedNFCvsQuantity);
    }

    @Override
    public Boolean editSingleNFCofUser(String username, String nfcCode, NFC_Single_Edit_DTO nfcEditDto) {
        NFC nfc = nfcRepo.findByUser_UsernameAndNfcCode(username, nfcCode)
                .orElseThrow(() -> new IllegalArgumentException("No NFC found of this user during editing single NFC"));
        nfc.setDynamicNFC_URL(nfcEditDto.getDynamicNFC_URL());
        nfcRepo.save(nfc);
        return true;
    }

    @Override
    public String redirectNFC(String nfcCode, String staticCode) {
        Optional<NFC> nfc = nfcRepo.getByNfcCodeAndStaticNFCURL(nfcCode, staticCode);

        if (nfc.isPresent()){
            //TODO Make view counter as an event
            Long views = nfc.get().getNumberOfViews() + 1L;
            System.out.println();
            nfc.get().setNumberOfViews(views);
            nfcRepo.save(nfc.get());
            return nfc.get().getDynamicNFC_URL();
        }
        return "";
    }

}
