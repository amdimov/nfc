package com.nfc.manager.nfc_manager.web.admin;

import com.nfc.manager.nfc_manager.entity.DTO.NFC_DTO;
import com.nfc.manager.nfc_manager.entity.views.UserView;
import com.nfc.manager.nfc_manager.service.NFCService;
import com.nfc.manager.nfc_manager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminAddNFC {
    private final UserService userService;
    private final NFCService nfcService;

    public AdminAddNFC(UserService userService, NFCService nfcService) {
        this.userService = userService;
        this.nfcService = nfcService;
    }

    @GetMapping("/{username}/add-nfc")
    public String addNfcView(@PathVariable(name = "username") String username, Model model){
        UserView user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "admin/nfc/add-nfc";
    }

    @PostMapping("/{username}/add-nfc")
    public String addNFC(@PathVariable(name = "username") String username,
                         @Valid NFC_DTO nfc, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model){
        System.out.println(bindingResult.hasErrors());
        System.out.println(nfc);
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("nfc", nfc);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.nfc"
                    , bindingResult);
            return "redirect:/admin/" + username + "/add-nfc";
        }
        nfcService.addNFC(username, nfc);
        System.out.println(nfc);
        return "redirect:/admin/" + username + "/add-nfc";
    }

    @ModelAttribute("nfc")
    public NFC_DTO renderNFC_DTO(){
        return nfcService.buildNFC_DTO();
    }

}
