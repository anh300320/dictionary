package com.example.dictionary;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Objects.AccessToken;
import Objects.ResponseTags;
import Objects.WordPost;
import Retrofit.RetrofitBuilder;
import Retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkActivity extends AppCompatActivity {

    TextView tvKey;
    EditText etHashtag;
    Button btnMark;
    String accessToken;

    RetrofitService retrofitService = RetrofitBuilder.build("https://nhqt-dict.herokuapp.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);
        findViewById();

        String oid = getIntent().getStringExtra("oid");
        String key = getIntent().getStringExtra("key");

        //SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("com.dictionary.USER_ACCESS", Context.MODE_PRIVATE);
        //accessToken = sharedPreferences.getString("access_token", "null");

        accessToken = "Bearer " + AccessToken.getAccessToken();

        btnMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WordPost wordPost = new WordPost(oid, "en_vi", etHashtag.getText().toString());
                Call<ResponseTags> mark = retrofitService.mark(accessToken, wordPost);
                mark.enqueue(new Callback<ResponseTags>() {
                    @Override
                    public void onResponse(Call<ResponseTags> call, Response<ResponseTags> response) {
                        ResponseTags responseTags = response.body();
                        Log.d("hehehe", "marking " + responseTags.getMes());
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseTags> call, Throwable t) {
                        Log.d("hehehe", "onFailure: mark");
                    }
                });
            }
        });
    }

    protected void findViewById(){
        etHashtag = findViewById(R.id.word_mark_hashtag);
        tvKey = findViewById(R.id.word_mark_key);
        btnMark = findViewById(R.id.word_mark_button);
    }
}
