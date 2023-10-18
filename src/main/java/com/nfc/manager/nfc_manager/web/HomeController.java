package com.nfc.manager.nfc_manager.web;

import com.nfc.manager.nfc_manager.entity.UserEntity;
import com.nfc.manager.nfc_manager.entity.UserURL_TEST;
import com.nfc.manager.nfc_manager.repositories.UserRepo;
import com.nfc.manager.nfc_manager.repositories.UserURLRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomeController {

    private final UserURLRepo userURLRepo;
    private final UserRepo userRepo;

    public HomeController(UserURLRepo userURLRepo, UserRepo userRepo) {
        this.userURLRepo = userURLRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/home")
    public String homePage(Model model){
        UserEntity user = userRepo.findUserByUsername("user").get();

        model.addAttribute("user", user);
        return "home";
    }

    @PostMapping("/url")
    public String postURL(@RequestParam String tempURL){
        System.out.println(tempURL);
        UserEntity user = userRepo.findUserByUsername("user").get();
        Optional<UserURL_TEST> userURL = userURLRepo.findById(1L);
        userURL.get().setTempURL(tempURL);
        userURLRepo.save(userURL.get());
        user.setUserURL(userURL.get());
        userRepo.save(user);
        return "redirect:/home";
    }

    @GetMapping("/url/70ddb984-3772-416f-9623-415ad0964b7c")
    public String redirectToTemp(){
        UserEntity user = userRepo.findUserByUsername("user").get();
        return "redirect:" + user.getUserURL().getTempURL();
    }

}
