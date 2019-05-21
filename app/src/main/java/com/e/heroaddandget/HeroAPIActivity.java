package com.e.heroaddandget;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_api);
        etHeroname =findViewById(R.id.etHeroname);
        etDesc = findViewById(R.id.etDesc);
        IV = findViewById(R.id.IV);
        LoadFormURl();
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });
    }

    private void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy= new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy)   ;
    }
    private void LoadFormURl() {
        StrictMode();
        try {
                String imgURL = "https://softwarica.edu.np/wp-content/uploads/2019/02/Kiran-Rana.jpg";
                URL url = new URL(imgURL);
                IV.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
        } catch (IOException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();;
        }

    }

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

