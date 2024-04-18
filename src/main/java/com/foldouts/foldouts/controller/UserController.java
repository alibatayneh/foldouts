package com.foldouts.foldouts.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

//    @Secured({"ADMIN"})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public String helloUserController(){
        return "User Access level";
    }
}
