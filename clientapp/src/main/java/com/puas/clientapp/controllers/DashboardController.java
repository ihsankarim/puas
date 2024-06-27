package com.puas.clientapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

  @GetMapping
  public String dashboard(Model model) {
    return "dashboard/dashboard";
  }

  @GetMapping("/categories")
  public String category(Model model) {
    return "dashboard/category";
  }

  @GetMapping("/pengaduan")
  public String pengaduan(Model model) {
    return "dashboard/pengaduan";
  }

  @GetMapping("/role")
  public String role(Model model) {
    return "dashboard/role";
  }
  @GetMapping("/users")
  public String user(Model model) {
    return "dashboard/user";
  }

}
