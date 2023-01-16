package com.main.app.controllers;

import com.main.app.models.Role;
import com.main.app.models.User;
import com.main.app.repo.RoleRepository;
import com.main.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class SettingController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/setting")
    public String setting(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "setting/setting";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") long id) {
        userRepository.deleteById(id);
        return "redirect:/setting";
    }

    @GetMapping("/setting/user/add-role/{id}")
    public String addRoleForUser(Model model, @PathVariable(value = "id") long id) {
        User user = userRepository.findById(id).get();
        Iterable<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "setting/setting-add-role";
    }

    @PostMapping("/setting/user/add-role/{id}")
    public String addRoleForUserPost(Model model, @PathVariable(value = "id") long id, @RequestParam String role) {
        User user = userRepository.findById(id).get();
        Role newRole = roleRepository.findByName(role);
        for (Role r : user.getRoles()) {
            if (Objects.equals(r.name, newRole.name)) {
                Iterable<Role> roles = roleRepository.findAll();
                model.addAttribute("roles", roles);
                model.addAttribute("user", user);
                model.addAttribute("error", "Пользователь уже имеет эту роль.");
                return "setting/setting-add-role";
            }
        }
        user.getRoles().clear();
        user.getRoles().add(newRole);
        userRepository.save(user);
        return "redirect:/setting";
    }
}
