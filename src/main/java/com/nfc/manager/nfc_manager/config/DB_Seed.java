package com.nfc.manager.nfc_manager.config;

import com.nfc.manager.nfc_manager.entity.UserEntity;
import com.nfc.manager.nfc_manager.entity.UserRoleEnum;
import com.nfc.manager.nfc_manager.entity.UserRoles;
import com.nfc.manager.nfc_manager.entity.UserURL_TEST;
import com.nfc.manager.nfc_manager.repositories.UserRepo;
import com.nfc.manager.nfc_manager.repositories.UserRolesRepo;
import com.nfc.manager.nfc_manager.repositories.UserURLRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class DB_Seed implements CommandLineRunner {
    private final UserRepo userRepo;
    private final UserRolesRepo userRolesRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserURLRepo userURLRepo;

    public DB_Seed(UserRepo userRepo, UserRolesRepo userRolesRepo, PasswordEncoder passwordEncoder, UserURLRepo userURLRepo) {
        this.userRepo = userRepo;
        this.userRolesRepo = userRolesRepo;
        this.passwordEncoder = passwordEncoder;
        this.userURLRepo = userURLRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepo.count() == 0){
            if (userRepo.count() > 0) {
                return;
            }
            UserRoles adminRole = new UserRoles().setUserRoleEnum(UserRoleEnum.ADMIN);
            UserRoles userRole = new UserRoles().setUserRoleEnum(UserRoleEnum.USER);

            userRolesRepo.saveAll(List.of(adminRole, userRole));
        }
        if (userRepo.count() == 0) {
            UserRoles role = userRolesRepo.findUserRolesByUserRoleEnum(UserRoleEnum.ADMIN)
                    .orElseThrow(() -> new IllegalArgumentException("No such Role"));
            UserEntity userEntity = new UserEntity().setCompany("Megaprint Transfers")
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("bulgariavarna"))
                    .setContactEmail("edisikav@gmail.com")
                    .setCountry("BG")
                    .setSoftDeleted(false)
                    .setUserRoles(List.of(role));
            userRepo.save(userEntity);
        }

        seedDummyUsersTEST();

//        if (userRepo.findUserByUsername("user").orElse(null) == null){
//            UserEntity userEntity = new UserEntity().setCompany("Megaprint Transfers")
//                    .setUsername("user")
//                    .setPassword(passwordEncoder.encode("bulgariavarna"))
//                    .setUserRoles(List.of(new UserRoles().setUserRoleEnum(UserRoleEnum.USER)));
//            if (userURLRepo.count() == 0){
//                UserURL_TEST userURLTEST = new UserURL_TEST().setUrl("http://192.168.1.109/url/" + UUID.randomUUID());
//                userEntity.setUserURL(userURLTEST);
//                userURLRepo.save(userURLTEST);
//            }
//            userRepo.save(userEntity);
//        }

    }

    private void seedDummyUsersTEST() {
        for (int i = 0; i < 50; i++) {
            UserRoles role = userRolesRepo.findUserRolesByUserRoleEnum(UserRoleEnum.USER)
                    .orElseThrow(() -> new IllegalArgumentException("No such Role"));
            UserEntity userEntity = new UserEntity().setCompany("Megaprint Transfers - " + i)
                    .setUsername(String.format("user_%d", i))
                    .setFirstName("Spas " + i)
                    .setLastName("Delev")
                    .setCountry("BG")
                    .setAddress("Beniyu Dubavitski nomer 8"+i)
                    .setCity("City Mall")
                    .setPostCode("7356")
                    .setPhoneNumber("+345 5145 847256")
                    .setContactEmail(String.format("mygreatcontactemail%d@email.com", i))
                    .setRegistrationDate(LocalDateTime.now())
                    .setPassword(passwordEncoder.encode("L1e2n3n4y5"))
                    .setSoftDeleted(false)
                    .setUserRoles(List.of(role));
            userRepo.save(userEntity);
        }
    }
}
