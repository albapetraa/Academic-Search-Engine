/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AkademikArama.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 *
 * @author Emree
 */
@Node("User")
public class User {

    @Id @GeneratedValue
    private long userId;

    @Property("userMail")
    private String userMail;

    @Property("userPassword")
    private String userPass;

    public User() {
    }

    public User(String userMail, String userPass) {
        this.userMail = userMail;
        this.userPass = userPass;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

}
