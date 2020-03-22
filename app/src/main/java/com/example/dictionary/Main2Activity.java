package com.example.dictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import Objects.AccessToken;
import Objects.Word;
import Retrofit.RetrofitBuilder;
import Retrofit.RetrofitService;
import bottom.fragments.BookmarkFragment;
import bottom.fragments.DictionaryFragment;
import bottom.fragments.LoginFragment;
import bottom.fragments.UserFragment;
import bottom.fragments.VocabularyFragment;
import myinterfaces.LoginListener;
import myinterfaces.LogoutListener;
import myinterfaces.WordModifier;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    RetrofitService retrofitService = RetrofitBuilder.build("https://nhqt-dict.herokuapp.com/");

    FrameLayout frameLayout;
    DictionaryFragment dictionaryFragment = new DictionaryFragment();
    UserFragment userFragment = new UserFragment();
    VocabularyFragment vocabularyFragment = new VocabularyFragment();
    LoginFragment loginFragment = new LoginFragment();

    String accessToken = null;

    List<Word> listWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String word = "Ính từAnh từ";

        accessToken = AccessToken.getAccessToken();

        findViewById();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DictionaryFragment()).commit();

        Call<List<Word>> getReady = retrofitService.search("a");
        getReady.enqueue(new Callback<List<Word>>() {
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
            }

            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) {
            }
        });

        userFragment.setLogoutListener(new LogoutListener() {
            @Override
            public void logout() {
                bottomNavigationView.setSelectedItemId(R.id.navigation_dict);
            }
        });

        loginFragment.setLoginListener(new LoginListener() {
            @Override
            public void login() {
                bottomNavigationView.setSelectedItemId(R.id.navigation_dict);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;

                switch(menuItem.getItemId()){
                    case R.id.navigation_dict:
                        selectedFragment = dictionaryFragment;
                        break;
                    case R.id.navigation_vocab:
                        if ( AccessToken.getAccessToken() != null) selectedFragment = vocabularyFragment;
                            else selectedFragment = loginFragment;
                        break;
                    case R.id.navigation_bookmark:
                        selectedFragment = new BookmarkFragment();
                        break;
                    case R.id.navigation_user:
                        if( AccessToken.getAccessToken() != null) selectedFragment = userFragment;
                            else selectedFragment = loginFragment;
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.home_menu_search);
        final SearchView searchView =(SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(!dictionaryFragment.isVisible()) bottomNavigationView.setSelectedItemId(R.id.navigation_dict);
                Call<List<Word>> search = retrofitService.search(query);
                search.enqueue(new Callback<List<Word>>() {
                    @Override
                    public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                        listWords = response.body();
                        if(listWords == null)
                            Toast.makeText(Main2Activity.this, "Can't find this word", Toast.LENGTH_SHORT).show();
                        dictionaryFragment.searchingWord(listWords);
                    }

                    @Override
                    public void onFailure(Call<List<Word>> call, Throwable t) {
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!dictionaryFragment.isVisible()) bottomNavigationView.setSelectedItemId(R.id.navigation_dict);
                Call<List<Word>> search = retrofitService.search(newText);
                search.enqueue(new Callback<List<Word>>() {
                    @Override
                    public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                        listWords = response.body();
                        dictionaryFragment.searchingWord(listWords);
                    }

                    @Override
                    public void onFailure(Call<List<Word>> call, Throwable t) {
                    }
                });
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void findViewById(){
        bottomNavigationView = findViewById(R.id.navigation_view);
        frameLayout = findViewById(R.id.fragment_container);
    }
}
