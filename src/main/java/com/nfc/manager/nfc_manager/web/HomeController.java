package com.nfc.manager.nfc_manager.web;

import com.nfc.manager.nfc_manager.entity.views.NFCGeneralStatisticsView;
import com.nfc.manager.nfc_manager.service.NFCService;
import com.nfc.manager.nfc_manager.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final UserService userService;
    private final NFCService nfcService;

    public HomeController(UserService userService, NFCService nfcService) {
        this.userService = userService;
        this.nfcService = nfcService;
    }

    @GetMapping("/home")
    public String homePage(Model model, Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            NFCGeneralStatisticsView generalStatistics = nfcService.getGeneralStatisticsOfUser(username);
            model.addAttribute("generalStatistics", generalStatistics);
        }

        return "home";
    }

//    @PostMapping("/url")
//    public String postURL(@RequestParam String tempURL){
//        System.out.println(tempURL);
//        UserEntity user = userRepo.findUserByUsername("user").get();
//        Optional<UserURL_TEST> userURL = userURLRepo.findById(1L);
//        userURL.get().setTempURL(tempURL);
//        userURLRepo.save(userURL.get());
//        user.setUserURL(userURL.get());
//        userRepo.save(user);
//        return "redirect:/home";
//    }
//
//    @GetMapping("/url/70ddb984-3772-416f-9623-415ad0964b7c")
//    public String redirectToTemp(){
//        UserEntity user = userRepo.findUserByUsername("user").get();
//        return "redirect:" + user.getUserURL().getTempURL();
//    }

}
