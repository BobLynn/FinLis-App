package com.example.texxtexx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.texxapp.R;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;

public class TexxMain extends AppCompatActivity {

        private ImageView imgv;
        private TextView tvshow;
        private Button btnocr;
        private Bitmap bmp;

//        //若Android的版本大於API23，路徑取根目錄下的tesseract資料夾，若小於則放在
//        //在mnt/sdcard下面
//        private String datapath= Environment.getExternalStorageDirectory().getAbsolutePath() +
//                File.separator + "tesseract" + File.separator + "tessdata";

//        private String datapath = Environment.getExternalStorageDirectory().getAbsolutePath()
//                + File.separator + "tesseract";

        String datapath = new File(Environment.getExternalStorageDirectory(),
                "tesseract").getAbsolutePath();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.texx_main);

            //呼叫副函式：允許讀取資料
            requestPermission();

            imgv=findViewById(R.id.imgv);
            //讀取要辨識的圖像，放在專案裏
            bmp= BitmapFactory.decodeResource(this.getResources(), R.drawable.testimg);
            imgv.setImageBitmap(bmp);
            imgv.setScaleType(ImageView.ScaleType.FIT_XY);

            tvshow=findViewById(R.id.tvshow);
            btnocr=findViewById(R.id.btnOcr);

            btnocr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        TessBaseAPI mTess=new TessBaseAPI();
                        //存放tessdata的文件路径 就是chi_sim.traineddata文件的位置chi_sim.traineddata
                        //选择语言 chi_sim 简体中文  eng 英文
                        String language="chi_sim";

                        File dirdatapath=new File(datapath);
                        if (!dirdatapath.exists()) {
                            dirdatapath.mkdirs();
                        }

                        File dir=new File(datapath, "tessdata");
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }

                        tvshow.append(datapath +"\r\n");
                        mTess.init(datapath, language);
                        //將圖片匯入mTess進行辨識
                        mTess.setImage(bmp);
                        //获取识别的文字（这里会等一段时间，这里的代码是在主线程的，建议将这部分代码放到子线程）
                        String result=mTess.getUTF8Text();
                        tvshow.append("==>OCR result: \n" + result);
                    } catch (Exception e) {
                        e.printStackTrace();
                        tvshow.append(e.getMessage());
                    }
                }
            });

        }

        void requestPermission() {
            final int REQUEST_CODE=1;
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE);
            }
        }

}