package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class UserConfigActivity extends AppCompatActivity implements View.OnClickListener{

    Button bBackToMain;
    EditText regName, regUsername;
    Spinner regLabGroupSpinner;
    String SpinnerToString;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_config);

        regName = (EditText) findViewById(R.id.regName);
        regUsername = (EditText) findViewById(R.id.regUsername);

        //讀取Spinner裡的文字到字串(String)裡
        regLabGroupSpinner = (Spinner) findViewById(R.id.regLabGroupSpinner);
        SpinnerToString = regLabGroupSpinner.getSelectedItem().toString();

        bBackToMain = (Button) findViewById(R.id.bBackToMain);
        bBackToMain.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true){
            DisplayUserDetails();
        }
        else{
            startActivity(new Intent(UserConfigActivity.this, LoginActivity.class));
        }
    }

    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

    private void DisplayUserDetails(){
        User user = userLocalStore.getLoggedInUser();
        regUsername.setText(user.username);
        regName.setText(user.name);
    }

    //按下按鈕之後的動作
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //回到主選單
            case R.id.bBackToMain:
                userLocalStore.clearUserData(); //清除賬戶資料
                userLocalStore.setUserLoggedIn(false);  //登入狀態爲”非“(false)
                startActivity(new Intent(this, PropertyFormActivity.class));
                break;
        }
    }
}