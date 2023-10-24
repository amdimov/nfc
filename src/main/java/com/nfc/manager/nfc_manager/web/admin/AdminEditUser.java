package com.nfc.manager.nfc_manager.web.admin;

import com.nfc.manager.nfc_manager.entity.DTO.UserEditDTO;
import com.nfc.manager.nfc_manager.entity.views.UserView;
import com.nfc.manager.nfc_manager.repositories.UserRepo;
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
public class AdminEditUser {

    private final UserService userService;

    public AdminEditUser(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}/edit-user")
    public String getEditUserModel(@PathVariable String username, Model model){
        UserEditDTO userEditDTO = userService.getUserEditDTOByUsername(username);
        System.out.println(userEditDTO);
        model.addAttribute("userEditDTO", userEditDTO);
        model.addAttribute("username", username);
        return "admin/edit-user";
    }

    @PostMapping("/{username}/edit-user")
    public String editUser(@PathVariable String username,
                           @Valid UserEditDTO userEditDTO, BindingResult bindingResult
            , RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userEditDTO", userEditDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userEditDTO"
                    , bindingResult);
            return "redirect:/admin/" + username + "/edit-user";
        }
        userService.editUser(username, userEditDTO);
        return "redirect:/admin/" + username + "/edit-user";
    }
}
