package com.nfc.manager.nfc_manager.web;

import com.nfc.manager.nfc_manager.service.NFCService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NFCRedirect {
    private final NFCService nfcService;

    public NFCRedirect(NFCService nfcService) {
        this.nfcService = nfcService;
    }

    @GetMapping("/nfc/{nfcCode}/{staticCode}")
    public String redirectNFC(@PathVariable String nfcCode,
                              @PathVariable String staticCode){
        String destination = nfcService.redirectNFC(nfcCode, staticCode);
        String redirect = "redirect:" + destination;
        return "redirect:" + destination;
    }
}
