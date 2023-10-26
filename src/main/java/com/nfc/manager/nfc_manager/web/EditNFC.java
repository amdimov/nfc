package com.nfc.manager.nfc_manager.web;

import com.nfc.manager.nfc_manager.config.GlobalVariablesConfig;
import com.nfc.manager.nfc_manager.entity.DTO.NFC_Edit_DTO;
import com.nfc.manager.nfc_manager.entity.DTO.NFC_Single_Edit_DTO;
import com.nfc.manager.nfc_manager.entity.views.NFCGeneralStatisticsView;
import com.nfc.manager.nfc_manager.entity.views.NFCsingleStatsView;
import com.nfc.manager.nfc_manager.service.NFCService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EditNFC {
    private final NFCService nfcService;

    public EditNFC(NFCService nfcService) {
        this.nfcService = nfcService;
    }

    @GetMapping("/{nfcCode}/edit-nfc")
    public String getNFCeditPage(@PathVariable String nfcCode,
                                 Authentication authentication,
                                 Model model){
        //TODO - PreAuthorize for user access other nfcCode in PathVariable
        String username = authentication.getName();
        if (!model.containsAttribute("userNfc")) {
            NFC_Edit_DTO userNfc = nfcService.getUsersNFCByNfcCode(username, nfcCode);
            model.addAttribute("userNfc", userNfc);
        }
        NFCsingleStatsView nfcSingleStats = nfcService.extractStatisticsOfNFC(username, nfcCode);
        model.addAttribute("nfcStats", nfcSingleStats);
        model.addAttribute("BASE_URL", GlobalVariablesConfig.BASE_URL);
        return "edit-nfc";
    }

    @PostMapping("/{nfcCode}/edit-nfc")
    public String editURLofNFC(@PathVariable String nfcCode,
                               @Valid NFC_Single_Edit_DTO nfcEditDto, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Authentication authentication){
        String username = authentication.getName();
        //TODO perAuthorize post request too
        Boolean success = nfcService.editSingleNFCofUser(username, nfcCode, nfcEditDto);
        return "redirect:/" + nfcCode + "/edit-nfc";

    }
}
