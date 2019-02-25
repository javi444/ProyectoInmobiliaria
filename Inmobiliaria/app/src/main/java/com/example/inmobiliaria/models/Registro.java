package com.example.inmobiliaria.models;

public class Registro {

    private String email;
    private String password;
    private String name;
    private String picture;
    private String role;

    public Registro() {
    }

    public Registro(String email, String password, String name, String picture, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.picture = picture;
        this.role = role;
    }

    public Registro(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
