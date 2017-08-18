package com.fernandez.kenneth.pickme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_GALLERY=1;
    private static final int URL_TO_SHARE = 1;
    private ImageView imageViewer;
    private Button buttShare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findView();





    }

    private void findView() {
        imageViewer = (ImageView) findViewById(R.id.pic);
        buttShare = (Button) findViewById(R.id.button);



    }
    public void choose(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_TEXT,URL_TO_SHARE);
        startActivity(Intent.createChooser(intent,"share this on"));



    }
    public void upload(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_TEXT,URL_TO_SHARE);
        startActivityForResult(Intent.createChooser(intent,"Select Image From"),REQUEST_CODE_GALLERY);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_GALLERY && data != null){
            Uri uri=data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                imageViewer.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();

            }
            imageViewer.setOnClickListener(null);
            buttShare.setVisibility(View.VISIBLE);

        }



    }



}
