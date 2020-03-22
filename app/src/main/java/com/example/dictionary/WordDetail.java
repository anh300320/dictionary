package com.example.dictionary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
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
import android.widget.TextView;

import Objects.AccessToken;
import Objects.Word;
import Retrofit.RetrofitBuilder;
import Retrofit.RetrofitService;
import myinterfaces.WordModifier;

public class WordDetail extends AppCompatActivity {

    Bundle bundle;
    Intent intent;

    TextView tvKey;
    TextView tvPronun;
    TextView tvType;
    TextView tvMeaning;
    Button btnMark;

    Word word;

    //SharedPreferences sharedPreferences;
    String token;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);
        intent = getIntent();
        //bundle = intent.getExtras();
        word = (Word) intent.getSerializableExtra("word");


        findViewById();

        //getActionBar().setTitle(word.getKey());
        tvKey.setText(word.getKey());
        tvType.setText(WordModifier.modify(word.getType()));
        if(word.getMeaning().size()==0) tvMeaning.setText("-Không rõ nghĩa");
            else{
                String meaning = "";
                int size = word.getMeaning().size();
                for(int i=0; i<size;i++)
                    meaning = meaning + word.getMeaning().get(i)+ "\n";
                tvMeaning.setText(meaning);
        }
            tvPronun.setText(word.getTrait().get(0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.word_detail_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_word_detail_star);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

               // sharedPreferences = getBaseContext().getSharedPreferences("com.dictionary.USER_ACCESS", Context.MODE_PRIVATE);
               // token = sharedPreferences.getString("access_token", "null");

                token = AccessToken.getAccessToken();

                if(token == null) {
                    startActivity(new Intent(getBaseContext(), LoginPopUp.class));
                } else {
                    String oid = word.getId();
                    Intent intent = new Intent(getBaseContext(), MarkActivity.class);
                    intent.putExtra("oid", oid);
                    intent.putExtra("key", word.getKey());
                    startActivity(intent);
                }
                return  true;
            }
        });
        return true;
    }

    protected void findViewById(){
        tvKey = findViewById(R.id.word_detail_key);
        tvPronun = findViewById(R.id.word_detail_pronunciation);
        tvType = findViewById(R.id.word_detail_type);
        tvMeaning = findViewById(R.id.word_detail_meaning);
    }
}
