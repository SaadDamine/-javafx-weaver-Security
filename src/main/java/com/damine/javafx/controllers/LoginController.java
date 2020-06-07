package com.damine.javafx.controllers;

import com.damine.javafx.entities.User;
import com.damine.javafx.enums.RoleEnum;
import com.damine.javafx.repositories.UserRepository;
import com.damine.javafx.services.UserService;
import com.damine.javafx.utils.BCryptManagerUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FxmlView("../fxml/login.fxml")
public class LoginController implements Initializable {
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (userService.testUser()){
            // Ceci n'est pas à recopier en production
            List<RoleEnum> adminRole = Arrays.asList(RoleEnum.USER, RoleEnum.ADMINISTRATOR);
            User adminUser = new User("admin", "admin", "Admin", "ADMIN", adminRole);
            userRepository.save(adminUser);
        }
    }

    public void login(ActionEvent actionEvent) {
        if(username.getText()==null){
            return;
        }
        if(password.getText()==null){
            return;
        }
        if(userService.loadUserByUsername(username.getText()) == null){
            return;
        }
        if (BCryptManagerUtil.passwordencoder().matches(password.getText(), userService.loadUserByUsername(username.getText()).getPassword())) {
            System.out.println("encoder: true");
        }else{
            System.out.println("encoder: false");
        }
    }
}
