package com.nfc.manager.nfc_manager.web.admin;

import com.nfc.manager.nfc_manager.entity.DTO.NFC_Edit_DTO;
import com.nfc.manager.nfc_manager.service.NFCService;
import com.nfc.manager.nfc_manager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminEditNFC {
    private final UserService userService;
    private final NFCService nfcService;

    public AdminEditNFC(UserService userService, NFCService nfcService) {
        this.userService = userService;
        this.nfcService = nfcService;
    }

    @GetMapping("/{username}/{nfcCode}/edit-nfc")
    public String editUsersNFC(@PathVariable(name = "username") String username,
                               @PathVariable(name = "nfcCode") String nfcCode,
                               Model model){
        if (!model.containsAttribute("userNfc")) {
            NFC_Edit_DTO userNfc = nfcService.getUsersNFCByNfcCode(username, nfcCode);
            model.addAttribute("userNfc", userNfc);
        }
        model.addAttribute("username", username);
        return "admin/nfc/edit-nfc";
    }

    @PostMapping("/{username}/{nfcCode}/edit-nfc")
    public String editUserNFC(@PathVariable(name = "username") String username,
                              @PathVariable(name = "nfcCode") String nfcCode,
                              @Valid NFC_Edit_DTO userNfc,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userNfc", userNfc);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userNfc"
                    , bindingResult);
            return "redirect:/admin/"+username + "/" +nfcCode + "/edit-nfc";
        }
        nfcService.editUsersNFC(username, userNfc);
        System.out.println(userNfc);
        return "redirect:/admin/"+username + "/" +nfcCode + "/edit-nfc";
    }

}
