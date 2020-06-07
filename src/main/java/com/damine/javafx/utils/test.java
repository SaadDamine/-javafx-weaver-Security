package com.damine.javafx.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("encoder:  " + encoder.encode("abel"));
        System.out.println("encoder:  " + encoder.encode("admin"));

        if (BCryptManagerUtil.passwordencoder().matches("abel", BCryptManagerUtil.passwordencoder().encode("abel"))) {
            System.out.println("encoder: true");
        }
    }
}
