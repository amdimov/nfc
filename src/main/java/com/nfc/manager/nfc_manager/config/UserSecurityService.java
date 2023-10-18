package com.nfc.manager.nfc_manager.config;

import com.nfc.manager.nfc_manager.entity.UserEntity;
import com.nfc.manager.nfc_manager.repositories.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepo userRepo;

    public UserSecurityService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User with this username not found - loadUserByUsername"));
        List<GrantedAuthority> grantedAuthorities = userEntity.getUserRoles()
                .stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getUserRoleEnum().name()))
                .collect(Collectors.toList());
        System.out.println(grantedAuthorities);
        UserSecurity userSecurity = new UserSecurity(userEntity.getUsername(), userEntity.getPassword(), grantedAuthorities);
        return userSecurity;
    }
}
