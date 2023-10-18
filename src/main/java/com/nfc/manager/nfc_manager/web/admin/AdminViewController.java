package com.nfc.manager.nfc_manager.web.admin;

import com.nfc.manager.nfc_manager.entity.views.UserView;
import com.nfc.manager.nfc_manager.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminViewController {
    public final UserService userService;

    public AdminViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String adminHome(){
        return "admin/home";
    }

    @GetMapping("/all-users")
    public String adminAllUsers(@RequestParam(required = false) Optional<Integer> pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                @RequestParam(name = "sortBy", defaultValue = "id") String sortBy, Model model){
        Page<UserView> pageViews = userService.fetchPagebleUsers(pageNo.orElse(0), pageSize, sortBy);
        System.out.println(pageViews);
        return "admin/all-users";
    }
}
