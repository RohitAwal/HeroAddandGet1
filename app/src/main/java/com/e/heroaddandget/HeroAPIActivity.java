package com.e.heroaddandget;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import HeroAPI.HeroAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroAPIActivity extends AppCompatActivity {

    EditText etHeroname,etDesc;
    Button btnSave;
    ImageView IV;
    String imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_api);
        etHeroname =findViewById(R.id.etHeroname);
        etDesc = findViewById(R.id.etDesc);
        IV = findViewById(R.id.IV);
       // LoadFormURl();
        IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });
    }
    private void BrowseImage(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK){
            if (data ==null){
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();

            }
        }
        Uri uri = data.getData();
        imagePath = getRealPathFromUri(uri);
        previewImage(imagePath);
    }

    private void previewImage(String imagePath) {
        File imgFile = new File(imagePath);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            IV.setImageBitmap(myBitmap);
        }
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader Loader = new CursorLoader(getApplicationContext(), uri, projection, null,null,null);
        Cursor cursor = Loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }
    //    private void StrictMode(){
//        android.os.StrictMode.ThreadPolicy policy= new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
//        android.os.StrictMode.setThreadPolicy(policy)   ;
//    }
//    private void LoadFormURl() {
//        StrictMode();
//        try {
//                String imgURL = "https://softwarica.edu.np/wp-content/uploads/2019/02/Kiran-Rana.jpg";
//                URL url = new URL(imgURL);
//                IV.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
//        } catch (IOException e) {
//            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();;
//        }
//
//    }

    private void Add(){

        String name = etHeroname.getText().toString();
        String desc = etDesc.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HeroAPI heroAPI = retrofit.create(HeroAPI.class);
        Call<Void> heroesCall = heroAPI.addHero(name,desc);

        heroesCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(HeroAPIActivity.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(HeroAPIActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                etHeroname.setText("");
                etDesc.setText("");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(HeroAPIActivity.this, "Error"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        }
    }

