package com.nfc.manager.nfc_manager.repositories;

import com.nfc.manager.nfc_manager.entity.NFC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NFCRepo extends JpaRepository<NFC, Long> {
}
