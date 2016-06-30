package com.qf.my.image;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView iv ;
    Button btn1;
    ImageCompress compress;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.activity_iv);
        btn1 = (Button) findViewById(R.id.activity_btn1);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bitmap bitmap = (Bitmap) msg.obj;
                iv.setImageBitmap(bitmap);
            }
        };
        compress = new ImageCompress();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        byte[] bs = HttpUtiles.getByte("http://img2.91.com/uploads/allimg/160601/57-160601145616-50.jpg");
                        if(bs!=null){
                            Log.i("12345","新的大小"+bs.length);
                         Bitmap bitmap =  compress.compress(bs,100,100);
                            Message msg = handler.obtainMessage();
                            msg.obj = bitmap;
                            handler.sendMessage(msg);
                        }
                    }
                }.start();
            }
        });


    }
}
