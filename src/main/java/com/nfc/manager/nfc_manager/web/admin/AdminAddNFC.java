package com.nfc.manager.nfc_manager.web.admin;

import com.nfc.manager.nfc_manager.entity.DTO.NFC_DTO;
import com.nfc.manager.nfc_manager.entity.views.NFC_View;
import com.nfc.manager.nfc_manager.entity.views.UserView;
import com.nfc.manager.nfc_manager.service.NFCService;
import com.nfc.manager.nfc_manager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{username}/all-nfc")
    public String allNfcView(@PathVariable(name = "username") String username,
                             @RequestParam(required = false) Optional<Integer> pageNo,
                             @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                             @RequestParam(name = "sortBy", defaultValue = "registrationDate", required = false) String sortBy,
                             Model model){
        Page<NFC_View> allNfcOfUser = nfcService.getAllNfcOfUser(pageNo.orElse(0), pageSize, sortBy, username);
        model.addAttribute("allNfcOfUser", allNfcOfUser);
        System.out.println(allNfcOfUser);
        return "admin/nfc/all-nfc";
    }

    @PostMapping("/{username}/add-nfc")
    public String addNFC(@PathVariable(name = "username") String username,
                         @Valid NFC_DTO nfc, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("nfc", nfc);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.nfc"
                    , bindingResult);
            return "redirect:/admin/" + username + "/add-nfc";
        }
        nfcService.addNFC(username, nfc);
        return "redirect:/admin/" + username + "/all-nfc";
    }

    @ModelAttribute("nfc")
    public NFC_DTO renderNFC_DTO(){
        return nfcService.buildNFC_DTO();
    }

}
