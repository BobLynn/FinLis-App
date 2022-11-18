package com.example.login;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";

    //"SharedPreferences"允許在收機上儲存資料
    SharedPreferences userLocalDatabase;

    //獲取SharedPreferences
    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData (User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("group", user.labgroup);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);

        //在分享資料時要commit!
        spEditor.commit();
    }

    //屬於Class: User的副函式——儲存資料到相應欄位
    public User getLoggedInUser(){
        String name = userLocalDatabase.getString("name","");
        String group = userLocalDatabase.getString("group", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");

        User storedUser = new User(name, group, username, password);
        return storedUser;
    }

    //回傳用戶是否登入狀態(true, false)
    public void setUserLoggedIn (boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    //認證用戶登入狀態(authenticate)
    public boolean getUserLoggedIn(){
        if(userLocalDatabase.getBoolean("loggedIn", false)){
            return true;
        }
        else{
            return false;
        }
    }


    //清除登入資料
    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

}
