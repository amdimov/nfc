package com.nfc.manager.nfc_manager.service;

import com.nfc.manager.nfc_manager.entity.DTO.UserDTO;
import com.nfc.manager.nfc_manager.entity.UserEntity;
import com.nfc.manager.nfc_manager.entity.UserRoleEnum;
import com.nfc.manager.nfc_manager.entity.UserRoles;
import com.nfc.manager.nfc_manager.entity.views.UserView;
import com.nfc.manager.nfc_manager.repositories.UserRepo;
import com.nfc.manager.nfc_manager.repositories.UserRolesRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserRolesRepo userRolesRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, UserRolesRepo userRolesRepo,
                           ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userRolesRepo = userRolesRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        UserEntity user = modelMapper.map(userDTO, UserEntity.class);
        UserRoles userRole = userRolesRepo
                .findUserRolesByUserRoleEnum(UserRoleEnum.USER)
                .orElseThrow(() -> new IllegalArgumentException("This role doesn't exist"));
        user.setUserRoles(List.of(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setSoftDeleted(false);
        userRepo.save(user);
    }

    @Override
    public Boolean usernameExists(String username) {
        return userRepo.existsByUsernameIgnoreCase(username);
    }

    @Override
    public Page<UserView> fetchPagebleUsers(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        System.out.println(pageable);
        Page<UserEntity> all = userRepo.findAll(pageable);
        System.out.println(all);
        return all.map(e -> modelMapper.map(e, UserView.class));
    }
}
