package com.nfc.manager.nfc_manager.repositories;

import com.nfc.manager.nfc_manager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.nfcList WHERE u.username = :username")
    Optional<UserEntity> findUserByUsername(String username);

    Boolean existsByUsernameIgnoreCase(String username);
    Boolean existsByUsernameIgnoreCaseAndIdNot(String username, Long id);
}
