package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.repository.UserRepository;
import com.example.springbootthymeleaftw.service.SecurityService;
import com.example.springbootthymeleaftw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.access.event.AuthenticationCredentialsNotFoundEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.util.Optional;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final SecurityService securityService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping()
    public String open(Model model, String error, String logout){
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @PostMapping("")
    public String login(Model model, String error, String logout, @RequestParam("username") String email, @RequestParam("password") String password){

        Optional<UserEntity> user = userService.findByEmailService(email);
        if (user.isPresent()) {
            UserEntity presentUser = user.get();
            //if (bCryptPasswordEncoder.matches(password,presentUser.getPassword())) {
            switch (presentUser.getRole().getName()) {
                case "Client":
                    return "redirect:/clientPage";
                case "B2B":
                    return "redirect:/B2BPage";
                case "B2C":
                    return "redirect:/B2CPage";

            }
            //}
        }
        model.addAttribute("error", "Your login credentials are invalid.");
        return "login";
    }


    @GetMapping("/error")
    public String error(Model model, String error, String logout){
        return "login";
    }

}
