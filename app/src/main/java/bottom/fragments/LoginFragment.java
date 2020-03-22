package bottom.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dictionary.MainActivity;
import com.example.dictionary.R;

import myinterfaces.LoginListener;

public class LoginFragment extends Fragment {

    Button btnLogin;
    LoginListener loginListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void setLoginListener(LoginListener loginListener){
        this.loginListener = loginListener;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        btnLogin = view.findViewById(R.id.login_fragment_btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                getActivity().startActivity(intent);
                loginListener.login();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}
