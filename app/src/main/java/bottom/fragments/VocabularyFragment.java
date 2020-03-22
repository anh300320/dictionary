package bottom.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.HashtagActivity;
import com.example.dictionary.ListAdapter;
import com.example.dictionary.R;

import java.util.List;

import Objects.AccessToken;
import Objects.ResponseTags;
import Retrofit.RetrofitBuilder;
import Retrofit.RetrofitService;
import myinterfaces.ItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VocabularyFragment extends Fragment {

    private RecyclerView recyclerList;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //SharedPreferences sharedPreferences;
    String token;
    List<String> listTag;

    RetrofitService retrofitService = RetrofitBuilder.build("https://nhqt-dict.herokuapp.com/");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_vocab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerList = view.findViewById(R.id.recycler_view_list_hashtag);

        //sharedPreferences = getContext().getSharedPreferences("com.dictionary.USER_ACCESS", Context.MODE_PRIVATE);
        //token = sharedPreferences.getString("access_token", "null");
        token = "Bearer " + AccessToken.getAccessToken();

        Call<ResponseTags> showList = retrofitService.showList(token);
        showList.enqueue(new Callback<ResponseTags>() {
            @Override
            public void onResponse(Call<ResponseTags> call, Response<ResponseTags> response) {
                ResponseTags responseTags = response.body();
                //int size = responseTags.getTags().size();


                listTag = responseTags.getTags();

                if (listTag != null) {

                    recyclerList.hasFixedSize();
                    layoutManager = new LinearLayoutManager(getActivity());
                    recyclerList.setLayoutManager(layoutManager);
                    mAdapter = new ListAdapter(listTag);
                    mAdapter.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position) {

                            String tag = listTag.get(position);
                            Intent intent = new Intent(getContext(), HashtagActivity.class);
                            intent.putExtra("tag", tag);
                            intent.putExtra("token", token);
                            startActivity(intent);
                        }
                    });

                    recyclerList.setAdapter(mAdapter);

                }
            }
            @Override
            public void onFailure(Call<ResponseTags> call, Throwable t) {
            }
        });



        super.onViewCreated(view, savedInstanceState);
    }
}
