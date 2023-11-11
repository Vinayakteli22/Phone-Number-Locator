package com.example.phonelocater;

public class Myhelper {
    String name,phone_num,user_name,password;

    public Myhelper() {

    }

    public Myhelper(String name, String phone_num, String user_name, String password) {
        this.name = name;
        this.phone_num = phone_num;
        this.user_name = user_name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
