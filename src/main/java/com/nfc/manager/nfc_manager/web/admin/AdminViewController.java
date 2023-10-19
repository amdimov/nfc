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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                                @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                @RequestParam(name = "sortBy", defaultValue = "registrationDate", required = false) String sortBy, Model model){
        Page<UserView> userPages = userService.fetchPagebleUsers(pageNo.orElse(0), pageSize, sortBy);
        model.addAttribute("usersPage", userPages);
        int totalPages = userPages.getTotalPages();
        System.out.println(userPages);
        model.addAttribute("currentPageSize", pageSize);
        if (totalPages > 0){
            List<Integer> pageNumbers = IntStream.range(1, totalPages+1)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);

        }
        return "admin/all-users";
    }
}
