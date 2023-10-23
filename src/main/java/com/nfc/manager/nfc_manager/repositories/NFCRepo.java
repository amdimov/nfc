package com.nfc.manager.nfc_manager.repositories;

import com.nfc.manager.nfc_manager.entity.NFC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NFCRepo extends JpaRepository<NFC, Long> {
    @Query("SELECT nfc FROM UserEntity u JOIN u.nfcList nfc WHERE u.username = :username")
    Page<NFC> getAllNfcOfUser(String username, Pageable pageable);
}
