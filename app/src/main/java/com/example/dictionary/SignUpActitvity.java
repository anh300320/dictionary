package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Objects.LoginToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import Retrofit.RetrofitService;
import Retrofit.RetrofitBuilder;

public class SignUpActitvity extends AppCompatActivity {

    EditText etName;
    EditText etEmail;
    EditText etPassword;
    EditText etPasswordCf;
    Button btnSignUp;
    RetrofitService retrofitService = RetrofitBuilder.build("https://nhqt-dict.herokuapp.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_actitvity);
        findViewById();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String passwordcf = etPasswordCf.getText().toString();

                if(!password.equals(passwordcf))
                    Toast.makeText(SignUpActitvity.this, "password confirmation is not correct", Toast.LENGTH_SHORT).show();
                        else if(!checkEmail(email))
                    Toast.makeText(SignUpActitvity.this, "email is invalid", Toast.LENGTH_SHORT).show();
                        else {
                            Call<LoginToken> createUser = retrofitService.createUser(name, email, password, passwordcf);
                            createUser.enqueue(new Callback<LoginToken>() {
                                @Override
                                public void onResponse(Call<LoginToken> call, Response<LoginToken> response) {
                                    String message = response.body().getMessage();
                                    Toast.makeText(SignUpActitvity.this, "Sign up successfully!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<LoginToken> call, Throwable t) {
                                    Log.d("hehehe", "onFailure: ");
                                }
                            });
                        }
            }
        });
    }

        public boolean checkEmail(String email){
        String dotcom = ".com";
        int counter = 0;
        int length = email.length();
        for(int i = length - 1; i>=length-4;i--)
            if (email.charAt(i) != dotcom.charAt(i - length + 4)) return false;
        for(int i= length-5;i>=0;i--)
            if(email.charAt(i) == '@')
                if(i==0) return false; else counter++;
                return (counter==1);
    }

    private void findViewById(){
        etName = findViewById(R.id.et_signup_name);
        etEmail = findViewById(R.id.et_signup_email);
        etPassword = findViewById(R.id.et_signup_password);
        etPasswordCf = findViewById(R.id.et_signup_password_cf);
        btnSignUp = findViewById(R.id.btn_signup);
    }
}
