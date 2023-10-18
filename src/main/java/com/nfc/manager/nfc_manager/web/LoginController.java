package com.nfc.manager.nfc_manager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String displayHomePage(){
        return "login";
    }

}
