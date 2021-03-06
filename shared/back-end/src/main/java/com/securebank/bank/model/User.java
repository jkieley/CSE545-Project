package com.securebank.bank.model;

import com.securebank.bank.services.errors.ApplicationValidationError;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.security.*;

public class User {

    @Id
    private String id;
    private String type;// tier1,tier2,administrator, merchant, consumer
    private String name;
    private String address;
    private String phoneNumber;
    private String request;
    private String otpToken;
    private long otpTokenExpires;

    @Indexed(unique = true)
    private String username;
    private String password;
    private String email;


    public User() {}

    public User(String id, String type, String name, String address, String phoneNumber, String username, String password, String email, String request) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.email = email;
        this.request = request;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void generateOtpToken() {
        String token = new String();
        token += new Double(Math.floor(Math.random() * 10)).intValue();
        while (token.length() < 6) {
            token += new Double(Math.floor(Math.random() * 10)).intValue();
        }
        this.otpToken = token;
        this.otpTokenExpires = (System.currentTimeMillis() / 1000) + 600;

    }

    public String getOtpToken() {
        return this.otpToken;
    }

    public void setOtpToken(String token)  { this.otpToken = token; }

    public Boolean isOtpValid(String token) {
        long curTime = System.currentTimeMillis() / 1000;
        return (token.equals(this.otpToken) && (curTime <= this.otpTokenExpires));
    }

    public void extendOtpExpiration() {
        long curTime = System.currentTimeMillis() / 1000;
        this.otpTokenExpires = curTime + 600;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
