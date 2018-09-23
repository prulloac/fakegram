package com.example.fakegram.controller;

import com.example.fakegram.dto.NewUserDataDTO;
import com.example.fakegram.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("users")
@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getBasicUserData(
            @RequestParam("username") String username
    ) {
        try {
            return new ResponseEntity<>(userService.getBasicUserDataFromUsername(username), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "fulldata", method = RequestMethod.GET)
    public ResponseEntity getUserData(
            @RequestParam("username") String username
    ) {
        try {
            return new ResponseEntity<>(userService.getUserDataFromUsername(username), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createUser(
            @RequestBody NewUserDataDTO newUserData
    ) {
        try {
            return new ResponseEntity<>(userService.createUser(newUserData), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "follow", method = RequestMethod.POST)
    public ResponseEntity followUser(
            @RequestParam("id") String id,
            @RequestParam("userToFollow") String username
    ) {
        try {
            return new ResponseEntity<>(userService.follow(id, username), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity login(
            @RequestParam("usernameOrEmail") String usernameOrEmail,
            @RequestParam("password") String password
    ) {
        try {
            return new ResponseEntity<>(userService.login(usernameOrEmail, password), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
