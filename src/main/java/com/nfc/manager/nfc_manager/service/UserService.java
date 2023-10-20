package com.nfc.manager.nfc_manager.service;

import com.nfc.manager.nfc_manager.entity.DTO.UserDTO;
import com.nfc.manager.nfc_manager.entity.views.NFC_View;
import com.nfc.manager.nfc_manager.entity.views.UserView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    void registerUser(UserDTO userDTO);
    Boolean usernameExists(String username);

    Page<UserView> fetchPagebleUsers(Integer pageNo, Integer pageSize, String sortBy);

    UserView getUserByUsername(String username);

}
