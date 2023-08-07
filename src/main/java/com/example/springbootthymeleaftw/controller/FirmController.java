package com.example.springbootthymeleaftw.controller;


import com.example.springbootthymeleaftw.model.entity.FirmEntity;
import com.example.springbootthymeleaftw.model.entity.RoleEntity;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.service.FirmService;
import com.example.springbootthymeleaftw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/firmFormular")
@RequiredArgsConstructor
public class FirmController {

    private final FirmService firmService;
    private final UserService userService;

    @GetMapping()
    public String open(Model model){
        model.addAttribute("firmForm", new FirmEntity());
        model.addAttribute("firmEmail", new String());
        return "firmFormular";
    }


    @PostMapping()
    public String firmForm(@ModelAttribute("firmForm") FirmEntity firmForm, BindingResult bindingResult, @ModelAttribute("firmEmail") String firmEmail){

        if (bindingResult.hasErrors())
            return "firmFormular";

        Optional<UserEntity> user = userService.findByEmailService(firmEmail);

        UserEntity userNonOptional = user.get();

        firmForm.setFirmUser(userNonOptional);

        firmForm.getFirmUser().setEmail(firmEmail);
        firmForm.getFirmUser().setPassword(userNonOptional.getPassword());
        firmForm.getFirmUser().setUsername(userNonOptional.getUsername());

        userNonOptional.setUserFirm(firmForm);
        firmForm.setFirmUser(userNonOptional);

        firmService.saveFirm(firmForm);
        userService.save(userNonOptional);

        userService.login(firmForm.getFirmUser().getEmail(), firmForm.getFirmUser().getPassword());
        return "login";
    }


}
