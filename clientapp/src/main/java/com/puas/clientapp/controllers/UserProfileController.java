package com.puas.clientapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.puas.clientapp.models.User;
import com.puas.clientapp.services.UserProfileService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping
    public String getProfile(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userProfileService.getUserByUsername(username);
        model.addAttribute("isActive", "profile");
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/user")
    public String getAllUsers(Model model) {
        List<User> users = userProfileService.getAllUser();
        model.addAttribute("users", users);
        model.addAttribute("isActive", "user");
        return "dashboard/user";
    }
}

// @GetMapping(value = "/update", produces = "text/html")
// public String updateProfileView(Model model, Authentication authentication) {
// String username = authentication.getName();
// ProfileEmployee updateForm = employeeService.getEmployeeByUsername(username);
// model.addAttribute("updateForm", updateForm);
// return "profile/update-form";
// }

// @PutMapping("/update/{username}")
// public String updateProfile(@PathVariable("username") String username,
// @ModelAttribute("updateForm") @RequestBody ProfileEmployee employeeRequest,
// Authentication authentication) {
// username = authentication.getName();
// employeeService.updateEmployee(username, employeeRequest);
// return "redirect:/profile";
// }
