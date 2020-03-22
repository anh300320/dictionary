package bottom.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dictionary.Main2Activity;
import com.example.dictionary.R;

import Objects.AccessToken;
import Objects.LoginToken;
import Retrofit.RetrofitBuilder;
import Retrofit.RetrofitService;
import myinterfaces.LogoutListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class UserFragment extends Fragment {

    Button btnChangepass;
    Button btnLogout;
    Context context = getActivity();
    Activity activity = getActivity();
    LogoutListener logoutListener;

    //SharedPreferences sharedPreferences;
    String token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    public void setLogoutListener(LogoutListener logoutListener){
        this.logoutListener = logoutListener;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnChangepass = view.findViewById(R.id.user_btnChangepass);
        btnLogout = view.findViewById(R.id.user_btnLogout);

        //sharedPreferences = getContext().getSharedPreferences("com.dictionary.USER_ACCESS", Context.MODE_PRIVATE);
        //token = sharedPreferences.getString("access_token", null);
        token = "Bearer " + AccessToken.getAccessToken();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitService retrofitService = new RetrofitBuilder().build("https://nhqt-dict.herokuapp.com/");
                Call<LoginToken> logout = retrofitService.logout(token);
                logout.enqueue(new Callback<LoginToken>() {
                    @Override
                    public void onResponse(Call<LoginToken> call, Response<LoginToken> response) {
                        LoginToken loginToken = response.body();
                        AccessToken.clearAccessToken();
                        logoutListener.logout();
                        Log.d("hehehe", "logout "+loginToken.getMessage());
                    }

                    @Override
                    public void onFailure(Call<LoginToken> call, Throwable t) {
                        Log.d("hehehe", "logout failed");
                    }
                });
                //activity.finish();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
