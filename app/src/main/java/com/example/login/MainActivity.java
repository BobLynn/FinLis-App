package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    Button cameraPage;
    Button logout;
    Button mlkitOCR;
    Button tesseractOCR;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initByXml();
        logoutButtonAct();
        ocrButtonAct();
    }

    //activity_camera button initial
    void initByXml(){

        cameraPage = (Button) findViewById(R.id.cameraPage);
        View.OnClickListener cameraOnclick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoNewClass_1 = new Intent();
                gotoNewClass_1.setClass(MainActivity.this, CameraActivity.class);
                startActivity(gotoNewClass_1);
                finish();

            }
        };
        cameraPage.setOnClickListener(cameraOnclick);
    }

    //往Tesseract4Android演算法之Activity
//    void ocrButtonAct()
//    {
//        ocrismagic = (Button) findViewById(R.id.ocrTime);
//        View.OnClickListener ocrOc = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent gogogo = new Intent();
//                gogogo.setClass(MenuActivity.this, TexxMain.class);
//                startActivity(gogogo);
//                finish();
//
//            }
//        };
//        ocrismagic.setOnClickListener(ocrOc);
//
//    }

    //往ML Kit演算法之Activity
    void ocrButtonAct()
    {
        mlkitOCR = (Button) findViewById(R.id.mlkitOCR);
        View.OnClickListener ocrOnclick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoNewClass_2 = new Intent();
                gotoNewClass_2.setClass(MainActivity.this, OCRActivity.class);
                startActivity(gotoNewClass_2);
                finish();

            }
        };
        mlkitOCR.setOnClickListener(ocrOnclick);

    }

    void logoutButtonAct(){
        logout = (Button) findViewById(R.id.logoutBtn);
        View.OnClickListener logoutOnclick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoNewClass_3 = new Intent();
                gotoNewClass_3.setClass(MainActivity.this, LoginActivity.class);
                startActivity(gotoNewClass_3);
                Toast.makeText(MainActivity.this,"中心人員，您已退出登入！", Toast.LENGTH_SHORT).show();
                finish();
            }
        };
        logout.setOnClickListener(logoutOnclick);

    }
    
}