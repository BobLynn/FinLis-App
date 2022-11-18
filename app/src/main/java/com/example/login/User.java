package com.example.login;

public class User {
    String name, username, labgroup, password;


    //情境1:資料皆有輸入
    public User(String name, String labgroup, String username, String password){
        this.name = name;
        this.labgroup = labgroup;
        this.username = username;
        this.password = password;
    }

    //情境2:只輸入賬戶與密碼
    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.labgroup = "";
        this.name = "";
    }
}
