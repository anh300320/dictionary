package bottom.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.MyAdapter;
import com.example.dictionary.R;
import com.example.dictionary.WordDetail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import Objects.Word;
import myinterfaces.ItemClickListener;

public class DictionaryFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    Activity activity;
    TextView tvLookup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dictionary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.list_search_word);
        tvLookup = view.findViewById(R.id.tvLookup);
        activity = getActivity();
    }

    public void searchingWord(List<Word> listWords){
        tvLookup.setVisibility(View.GONE);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(listWords);
        mAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Word word = listWords.get(position);
                /*Bundle bundle = new Bundle();
                bundle.putParcelable("word", word);
                Intent intent = new Intent(getActivity(), WordDetail.class);
                intent.putExtra("word", word);
                activity.startActivity(intent);*/

                Intent intent = new Intent(getActivity(), WordDetail.class);
                intent.putExtra("word", word);
                activity.startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }
}
