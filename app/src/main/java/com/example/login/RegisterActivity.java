package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    Spinner regLabGroupSpinner;
    EditText regName, regUsername, regPassword;
    TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //EditText
        regName = (EditText) findViewById(R.id.regName);
        regUsername = (EditText) findViewById(R.id.regUsername);
        regPassword = (EditText) findViewById(R.id.regPassword);
        //Spinner
        regLabGroupSpinner = (Spinner) findViewById(R.id.regLabGroupSpinner);
        //Button
        bRegister = (Button) findViewById(R.id.bRegister);
        //TextView
        tvLoginLink = (TextView)findViewById(R.id.tvLoginLink);

        bRegister.setOnClickListener(this);
        tvLoginLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bRegister:    //點擊「完成註冊」之動作
                String name = regName.getText().toString();
                String username = regUsername.getText().toString();
                String password = regPassword.getText().toString();
                String labgroup = regLabGroupSpinner.toString();

                //丟入Class:User
                User user = new User(name, labgroup, username, password);
                //呼叫副函式registerUser
                registerUser(user);

                break;

            case R.id.tvLoginLink:  //點擊「返回登入畫面」之動作
                startActivity(new Intent(this, LoginActivity.class));
        }
    }


    private void registerUser(User user){
        //Class:ServerRequests
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnUser) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}