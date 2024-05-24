package ru.gashev.test.advenjineering.projectmanager.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gashev.test.advenjineering.projectmanager.entity.Authority;
import ru.gashev.test.advenjineering.projectmanager.entity.AuthorityType;
import ru.gashev.test.advenjineering.projectmanager.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ModelAttribute
    public void setAttributes(Model model) {

        model.addAttribute("isAdmin",
                SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                        .contains(new Authority(AuthorityType.ADMIN)));


    }

    @GetMapping
    public String getAdminPage() {
        return "admin";
    }

    @PostMapping("/grant")
    public String grantAdminRole(@RequestParam("username") String username, Model model) {
        System.out.println(username);

        String result = adminService.getResultOfGrantAdminRoleToUser(username);
        model.addAttribute("grantResult", result);

        return "admin";
    }

    @PostMapping("/deprive")
    public String depriveAdminRole(@RequestParam("username") String username, Model model) {
        System.out.println(username);

        String result = adminService.getResultOfDepriveAdminRoleToUser(username);
        model.addAttribute("depriveResult", result);

        return "admin";
    }

}
