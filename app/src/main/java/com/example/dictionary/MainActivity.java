package com.example.dictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Objects.AccessToken;
import Objects.LoginToken;
import Objects.LoginUser;
import bottom.fragments.BookmarkFragment;
import bottom.fragments.DictionaryFragment;
import bottom.fragments.UserFragment;
import bottom.fragments.VocabularyFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import Retrofit.RetrofitService;
import Retrofit.RetrofitBuilder;

public class MainActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    Button btnSignUp;

    RetrofitService retrofitService = RetrofitBuilder.build("https://nhqt-dict.herokuapp.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        setClickListener();
    }


    private void findViewById(){
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    private void setClickListener(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<LoginToken> loginUser = retrofitService.login(new LoginUser(etEmail.getText().toString(), etPassword.getText().toString(), true));
                loginUser.enqueue(new Callback<LoginToken>() {
                    @Override
                    public void onResponse(Call<LoginToken> call, Response<LoginToken> response) {
                        String accessToken = response.body().getAccessToken();
                        //Context context = getBaseContext();
                        //SharedPreferences sharedPreferences = context.getSharedPreferences("com.dictionary.USER_ACCESS", Context.MODE_PRIVATE);
                       // SharedPreferences.Editor editor = sharedPreferences.edit();
                        //editor.putString("access_token", accessToken);
                        //editor.commit();

                        //Intent intent = new Intent(getBaseContext(), Main2Activity.class);
                        //startActivity(intent);

                        AccessToken.setAccessToken(accessToken);
                        finish();
                        Log.d("hehehe", "Login");
                    }

                    @Override
                    public void onFailure(Call<LoginToken> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                        Log.d("hehehe", "onFailure: ");
                    }
                });
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SignUpActitvity.class);
                startActivity(intent);
            }
        });
    }
}
