package com.nfc.manager.nfc_manager.web;

import com.nfc.manager.nfc_manager.entity.views.NFCGeneralStatisticsView;
import com.nfc.manager.nfc_manager.entity.views.NFC_View;
import com.nfc.manager.nfc_manager.service.NFCService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MyNFController {
    private final NFCService nfcService;

    public MyNFController(NFCService nfcService) {
        this.nfcService = nfcService;
    }

    @GetMapping("/my-nfc")
    public String getMyNFCpage(@RequestParam(required = false) Optional<Integer> pageNo,
                               @RequestParam(name = "pageSize", defaultValue = "9", required = false) Integer pageSize,
                               @RequestParam(name = "sortBy", defaultValue = "createdDateTime", required = false) String sortBy,
                               @RequestParam(name = "searchNFC", defaultValue = "", required = false) String searchNFC,
                               Model model, Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Page<NFC_View> allNfcOfUser = nfcService.getAllNfcOfUser(pageNo.orElse(0), pageSize, sortBy, username, searchNFC);
            model.addAttribute("username", username);
            model.addAttribute("allNfcOfUser", allNfcOfUser);
            int totalPages = allNfcOfUser.getTotalPages();
            model.addAttribute("currentPageSize", pageSize);
            if (totalPages > 0){
                List<Integer> pageNumbers = IntStream.range(1, totalPages+1)
                        .boxed().collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);

            }

            model.addAttribute("searchNFC", searchNFC);
            return "my-nfc";
        }
        return null;
    }
}
