package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //參數定義（按鈕、賬戶名、密碼、前往註冊頁面按鈕）
    Button bLogin;
    EditText regUsername, regPassword;
    TextView tvregisterLink;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Button login = this.findViewById(R.id.bLogin);
        regUsername = (EditText)findViewById(R.id.regUsername);
        regPassword = (EditText)findViewById(R.id.regPassword);
        bLogin = (Button)findViewById(R.id.bLogin);

        tvregisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvregisterLink.setOnClickListener(this);

        UserLocalStore userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogin:
                String username = regUsername.getText().toString();
                String password = regPassword.getText().toString();

                User user = new User(null, null);

                authenticate(user);

                break;

            case R.id.tvRegisterLink:

                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }

    }

    private void authenticate(User user){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnUser) {
                if(returnUser == null){
                showErrorMessage();
                }else{

                }
            }
        });
    }

    //副函式：錯誤資訊顯示
    private void showErrorMessage(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
        dialogBuilder.setMessage("INCORRECT USER DETAILS!");
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser){
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        startActivity(new Intent(this, UserConfigActivity.class));
    }
}

//          login.setOnClickListener(new View.OnClickListener() {
//          @Override
//                  public void onClick(View view){
//                      EditText account = LoginActivity.this.findViewById(R.id.regUsername);
//                      EditText password = LoginActivity.this.findViewById(R.id.regPassword);
//
//                      if(account.getText().toString().equals("iclab") && password.getText().toString().equals("aa"))
//                      {
//                          Toast.makeText(LoginActivity.this,"登入成功！歡迎您！中心人員！", Toast.LENGTH_SHORT).show();
//                          startActivity(new Intent(LoginActivity.this, MenuActivity.class));
//                      }
//                      else
//                      {
//                          Toast.makeText(LoginActivity.this,"賬戶或密碼錯誤！請重新輸入！", Toast.LENGTH_SHORT).show();
//                      }
//          }
//      });

