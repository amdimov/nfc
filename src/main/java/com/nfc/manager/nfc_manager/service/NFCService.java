package com.nfc.manager.nfc_manager.service;

import com.nfc.manager.nfc_manager.entity.DTO.NFC_DTO;
import com.nfc.manager.nfc_manager.entity.DTO.NFC_Edit_DTO;
import com.nfc.manager.nfc_manager.entity.NFC;
import com.nfc.manager.nfc_manager.entity.views.NFC_View;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface NFCService {
    NFC_DTO renderNFCView();

    NFC_DTO buildNFC_DTO();

    Boolean addNFC(String username, NFC_DTO nfc);

    Page<NFC_View> getAllNfcOfUser(Integer pageNo, Integer pageSize, String sortBy, String username, String searchNFC);

    NFC_Edit_DTO getUsersNFCByNfcCode(String username, String nfcCode);

    Boolean editUsersNFC(String username, NFC_Edit_DTO userNfc);
}
