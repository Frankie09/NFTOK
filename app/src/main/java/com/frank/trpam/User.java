package com.frank.trpam;

public class User {


    private String email;

    private String password;
    private String phone;
    private String username;
    private double money;


    public User( String email, String password , String phone, String username, double money){

        this.email = email;

        this.password = password;
        this.phone = phone;
        this.username = username;
        this.money = money;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
