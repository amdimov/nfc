package com.nfc.manager.nfc_manager.config;

import com.nfc.manager.nfc_manager.entity.*;
import com.nfc.manager.nfc_manager.repositories.NFCRepo;
import com.nfc.manager.nfc_manager.repositories.UserRepo;
import com.nfc.manager.nfc_manager.repositories.UserRolesRepo;
import com.nfc.manager.nfc_manager.repositories.UserURLRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class DB_Seed implements CommandLineRunner {
    private final UserRepo userRepo;
    private final UserRolesRepo userRolesRepo;
    private final PasswordEncoder passwordEncoder;

    private final NFCRepo nfcRepo;
    private final UserURLRepo userURLRepo;

    public DB_Seed(UserRepo userRepo, UserRolesRepo userRolesRepo, PasswordEncoder passwordEncoder, NFCRepo nfcRepo, UserURLRepo userURLRepo) {
        this.userRepo = userRepo;
        this.userRolesRepo = userRolesRepo;
        this.passwordEncoder = passwordEncoder;
        this.nfcRepo = nfcRepo;
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
                    .setPassword(passwordEncoder.encode("1234"))
                    .setContactEmail("edisikav@gmail.com")
                    .setCountry("BG")
                    .setSoftDeleted(false)
                    .setUserRoles(List.of(role));
            userRepo.save(userEntity);
        }


//        seedDummyUsersTEST();
//        seedDummyNFCToUser("user_49");

//        if (userRepo.findUserByUsername("user").orElse(null) == null){
//            UserEntity userEntity = new UserEntity().setCompany("Megaprint Transfers")
//                    .setUsername("user")
//                    .setPassword(passwordEncoder.encode("1234"))
//                    .setUserRoles(List.of(new UserRoles().setUserRoleEnum(UserRoleEnum.USER)));
//            if (userURLRepo.count() == 0){
//                UserURL_TEST userURLTEST = new UserURL_TEST().setUrl("http://192.168.1.109/url/" + UUID.randomUUID());
//                userEntity.setUserURL(userURLTEST);
//                userURLRepo.save(userURLTEST);
//            }
//            userRepo.save(userEntity);
//        }

    }

    private void seedDummyNFCToUser(String username) {
        Optional<UserEntity> user49 = userRepo.findUserByUsername(username);
        List<NFC> usersNFC = user49.get().getNfcList();
        for (int i = 0; i < 100; i++) {
            NFC nfc = new NFC().setDeleted(false).setDisabled(false)
                    .setCreatedDateTime(LocalDateTime.now())
                    .setNfcCode("TESTNFC" + i)
                    .setDynamicNFC_URL("test.com/examplenfc")
                    .setStaticNFC_URL("AF3BFD4D-E61D-44F6-A719-7355EC1ABC2A" + i)
                    .setDynamicURLTitle("Test NFC Title")
                    .setImagePreviewURL("https://capandura-s3-bucket1.s3.eu-central-1.amazonaws.com/dd7ee455-b430-4397-afe1-c9e6cf22bf7e_BRING_ME_BACK.jpg")
                    .setNfcDescription("Description")
                    .setNumberOfNFCs(2000 + i)
                    .setNfcTitle("Nfc Title " + i)
                    .setUser(user49.get());
            usersNFC.add(nfc);
            nfcRepo.save(nfc);
        }
        userRepo.save(user49.get());
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
