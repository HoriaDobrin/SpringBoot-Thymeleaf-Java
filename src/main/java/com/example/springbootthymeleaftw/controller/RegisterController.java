package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.RoleEntity;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.repository.RoleRepository;
import com.example.springbootthymeleaftw.service.RoleService;
import com.example.springbootthymeleaftw.service.UserService;
import com.example.springbootthymeleaftw.service.UserValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserValidatorService userValidatorService;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final RoleService roleService;

    @GetMapping()
    public String open(Model model){
        System.out.println(model);
        model.addAttribute("userForm", new UserEntity());
        model.addAttribute("role", new RoleEntity());
        return "register";
    }

    @PostMapping()
    public String register(@ModelAttribute("userForm") UserEntity userForm, BindingResult bindingResult, @ModelAttribute("role") RoleEntity role){
        userValidatorService.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "register";
        }


        userForm.setRole(role);
        roleService.saveRole(role);

        userService.save(userForm);
        roleRepository.save(role);
        /*userForm.setRole();*/
        switch(role.getName())
        {
            case "Client" :
                return "login";
            case "B2B" :
                return "redirect:/firmFormular";
            case "B2C" :
                return "redirect:/firmFormular";
        default:
            break;
        }

        userService.login(userForm.getEmail(), userForm.getPassword());
        return "redirect:/login";
    }
}
