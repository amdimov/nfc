package com.nfc.manager.nfc_manager.repositories;

import com.nfc.manager.nfc_manager.entity.NFC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NFCRepo extends JpaRepository<NFC, Long> {
    @Query("SELECT nfc FROM UserEntity u JOIN u.nfcList nfc WHERE u.username = :username ")
    Page<NFC> getAllNfcOfUser(String username, Pageable pageable);

    @Query("SELECT nfc FROM UserEntity u JOIN u.nfcList nfc WHERE u.username = :username " +
            "AND nfc.deleted = false " +
            "AND nfc.disabled = false " +
            "AND (" +
            "nfc.nfcTitle LIKE %:searchTerm% " +
            "OR nfc.nfcDescription LIKE %:searchTerm% " +
            "OR nfc.dynamicNFC_URL LIKE %:searchTerm% " +
            "OR nfc.nfcCode LIKE %:searchTerm% " +
            "OR nfc.dynamicURLTitle LIKE %:searchTerm% " +
            "OR nfc.staticNFC_URL LIKE %:searchTerm%" +
            ")")
    Page<NFC> findAllUserNfcBySearchTerm(@Param("username") String username, @Param("searchTerm") String searchTerm, Pageable pageable);

    Optional<NFC> findByUser_UsernameAndNfcCode(String username, String nfcCode);
}
