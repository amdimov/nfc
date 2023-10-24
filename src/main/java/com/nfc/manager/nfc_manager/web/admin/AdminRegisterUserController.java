package com.nfc.manager.nfc_manager.web.admin;

import com.nfc.manager.nfc_manager.entity.DTO.UserDTO;
import com.nfc.manager.nfc_manager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin")
public class AdminRegisterUserController {

    private final UserService userService;

    public AdminRegisterUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register-user")
    public String renderPage(){
        return "/admin/register-user";
    }

    @PostMapping("/register-user")
    public String registerUser(@Valid UserDTO userDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userDTO", userDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userDTO"
                            , bindingResult);
            return "redirect:/admin/register-users";
        }
        userDTO.setRegistrationDate(LocalDateTime.now());
        userService.registerUser(userDTO);

        return "redirect:/admin/all-users";
    }

    @ModelAttribute("userDTO")
    public UserDTO modelUserDTO(){
        return new UserDTO();
    }
}
