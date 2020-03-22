package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import Objects.Word;
import Objects.WordPost;
import Retrofit.RetrofitBuilder;
import Retrofit.RetrofitService;
import myinterfaces.ItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HashtagActivity extends AppCompatActivity {

    String tag;
    String token;
    Intent intent;

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashtag);
        intent = getIntent();
        tag = intent.getStringExtra("tag");
        token = intent.getStringExtra("token");
        //token = "Bearer "+ token;

        recyclerView = findViewById(R.id.hashtag_list_word);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);

        RetrofitService retrofitService = RetrofitBuilder.build("https://nhqt-dict.herokuapp.com/");
        WordPost wordPost = new WordPost(null, "en_vi", tag);


        Call<List<List<Word>>> getWords = retrofitService.getWords(token, wordPost);
        getWords.enqueue(new Callback<List<List<Word>>>() {
            @Override
            public void onResponse(Call<List<List<Word>>> call, Response<List<List<Word>>> response) {
                List<List<Word>> list0 = response.body();
                List<Word> list = new ArrayList<>();
                int size = list0.size();
                for(int i=0; i<size; i+=2)
                    list.add(list0.get(i).get(0));
                mAdapter = new MyAdapter(list);
                mAdapter.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Word word = list.get(position);
                        Intent intent = new Intent(getBaseContext(), WordDetail.class);
                        intent.putExtra("word", word);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(Call<List<List<Word>>> call, Throwable t) {
                Log.d("hehehe", "onFailure: ");
            }
        });
    }
}
