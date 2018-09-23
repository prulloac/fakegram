package com.example.fakegram.service;

import com.example.fakegram.dao.UserDAO;
import com.example.fakegram.dto.BasicUserDataDTO;
import com.example.fakegram.dto.NewUserDataDTO;
import com.example.fakegram.dto.UserDataDTO;
import com.example.fakegram.mapper.AddressMapper;
import com.example.fakegram.mapper.UserMapper;
import com.example.fakegram.model.Address;
import com.example.fakegram.model.GeographicZone;
import com.example.fakegram.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final GeographicZoneService geographicZoneService;

    @Autowired
    public UserService(UserDAO userDAO, GeographicZoneService geographicZoneService) {
        this.userDAO = userDAO;
        this.geographicZoneService = geographicZoneService;
    }

    public User getUserFromUsername(String username) {
        return userDAO.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
    }

    public Optional<User> getOptionalUserFromUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public BasicUserDataDTO getBasicUserDataFromUsername(String username) {
        return new UserMapper().toBasicUserDataDTO(getUserFromUsername(username));
    }

    public UserDataDTO getUserDataFromUsername(String username) {
        return new UserMapper().toUserDataDTO(getUserFromUsername(username));
    }

    public BasicUserDataDTO createUser(NewUserDataDTO newUserData) {
        User user = new UserMapper().fromNewUserDataDTO(newUserData);
        if (null != newUserData.getAddress()) {
            Address address = new AddressMapper().fromAddressDTO(newUserData.getAddress());
            GeographicZone geographicZone = geographicZoneService
                    .getFromLocationString(newUserData.getAddress().getLocation());
            address.setLocation(geographicZone);
            user.setAddress(address);
        }
        userDAO.saveAndFlush(user);
        return getBasicUserDataFromUsername(user.getUsername());
    }

    public boolean follow(String id, String username) {
        try {
            User user = userDAO.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
            User userToFollow = userDAO.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("user not found"));
            user.addFollow(userToFollow);
            userDAO.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public UserDataDTO login(String usernameOrEmail, String password) {
        return new UserMapper().toUserDataDTO(userDAO
                .findByUsernameOrEmailAndPassword(usernameOrEmail, usernameOrEmail, password)
                .orElseThrow(() -> new RuntimeException("user not found")));
    }

    public Optional<User> findById(String userId) {
        return userDAO.findById(userId);
    }
}
