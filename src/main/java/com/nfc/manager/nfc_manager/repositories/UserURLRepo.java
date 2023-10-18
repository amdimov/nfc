package com.nfc.manager.nfc_manager.repositories;

import com.nfc.manager.nfc_manager.entity.UserURL_TEST;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserURLRepo extends JpaRepository<UserURL_TEST, Long> {
}
