package com.nfc.manager.nfc_manager.service;

import com.nfc.manager.nfc_manager.entity.DTO.UserDTO;
import com.nfc.manager.nfc_manager.entity.DTO.UserEditDTO;
import com.nfc.manager.nfc_manager.entity.views.UserView;
import org.springframework.data.domain.Page;


public interface UserService {
    void registerUser(UserDTO userDTO);
    Boolean usernameExists(String username);

    Page<UserView> fetchPagebleUsers(Integer pageNo, Integer pageSize, String sortBy);

    UserView getUserByUsername(String username);

    UserEditDTO getUserEditDTOByUsername(String username);

    Boolean editUser(String username, UserEditDTO userEditDTO);

    Boolean isUsernameUniqueForUpdate(Long id, String username);

    Boolean isUsernameUniqueForCreate(String username);
}
