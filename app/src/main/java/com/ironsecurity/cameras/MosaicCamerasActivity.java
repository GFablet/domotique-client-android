package com.ironsecurity.cameras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.ironsecurity.R;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//public class MosaicCamerasActivity extends AppCompatActivity {


  //  WebView camera1;
    //WebView camera2;
    //WebView camera3;
    //WebView camera4;

    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
      //  super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mosaic_cameras);

        //camera1 = findViewById(R.id.camera1);
        //camera1.loadUrl("http://admin:@172.16.3.14/video.cgi");

        //camera2 = findViewById(R.id.camera2);
        //camera2.loadUrl("http://admin:@172.16.3.14/video.cgi");

//        camera3 = findViewById(R.id.camera3);
  //      camera3.loadUrl("http://admin:@172.16.3.14/video.cgi");
//
  //      camera4 = findViewById(R.id.camera4);
    //    camera4.loadUrl("http://admin:@172.16.3.14/video.cgi");

        




    public class MosaicCamerasActivity extends AppCompatActivity {

        WebView webView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            webView = findViewById(R.id.webView);




            webView.loadUrl("http://admin:@172.16.3.14/video.cgi");


        }
}