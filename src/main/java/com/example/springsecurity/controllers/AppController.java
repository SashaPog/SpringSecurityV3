package com.example.springsecurity.controllers;

import com.example.springsecurity.models.Application;
import com.example.springsecurity.models.User;
import com.example.springsecurity.services.AppService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/apps")
@AllArgsConstructor
public class AppController {

    private AppService appService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the unprotected page";
    }

    @GetMapping("/all-apps")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Application> allApplications(){
        return appService.allAplication();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Application applicationByID (@PathVariable int id){
        return appService.applicationByID(id);
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody User user) {
        appService.addUser(user);
        return "User is saved";
    }
}
