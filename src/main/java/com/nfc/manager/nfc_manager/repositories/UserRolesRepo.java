package com.nfc.manager.nfc_manager.repositories;

import com.nfc.manager.nfc_manager.entity.UserRoleEnum;
import com.nfc.manager.nfc_manager.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRolesRepo extends JpaRepository<UserRoles, Long> {
    Optional<UserRoles> findUserRolesByUserRoleEnum(UserRoleEnum userRoleEnum);
}
