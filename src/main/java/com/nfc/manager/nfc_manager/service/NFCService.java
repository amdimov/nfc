package com.nfc.manager.nfc_manager.service;

import com.nfc.manager.nfc_manager.entity.DTO.NFC_DTO;
import org.springframework.stereotype.Service;

@Service
public interface NFCService {
    NFC_DTO renderNFCView();

    NFC_DTO buildNFC_DTO();

    Boolean addNFC(String username, NFC_DTO nfc);
}
