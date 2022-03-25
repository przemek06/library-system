/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.system.bean;

import com.mycompany.library.system.entity.User;
import com.mycompany.library.system.repository.UserRepository;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

/**
 *
 * @author swiat
 */
@ManagedBean
@SessionScoped
public class RegisterBean {

    String username;
    String email;
    String password;
    String message;
    String redirection;

    @Inject
    UserRepository userRepository;

    public void register(ActionEvent event) {
        if (userAlreadyExists(username)) {
            handleUserAlreadyExists();
            return;
        }
        boolean created = tryRegisterUser(username, password, email);
        handleRegisterResponse(created);
    }

    private void handleRegisterResponse(boolean created) {
        if (created) {
            redirection = "login";
        } else {
            redirection = "register";
            message = "Something went wrong";
        }
    }

    private boolean tryRegisterUser(String username, String password, String email) {
        User createdUser = new User(username, password, email, "USER");
        try {
            return userRepository.addUser(createdUser);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean userAlreadyExists(String username) {
        User existingUser = userRepository.getUserByUsername(username);
        if (existingUser != null) {
            return true;
        }
        return false;
    }

    private void handleUserAlreadyExists() {
        redirection = "register";
        message = "There is an user with this username already.";
    }

    public String redirect() {
        return redirection;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
