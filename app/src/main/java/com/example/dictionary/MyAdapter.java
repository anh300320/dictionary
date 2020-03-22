package com.example.dictionary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Objects.Word;
import myinterfaces.ItemClickListener;
import myinterfaces.WordModifier;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Word> listWords;
    ItemClickListener itemClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvWord;
        TextView tvType;
        TextView tvMeaning;
        ItemClickListener itemClickListener;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.tvWord = itemView.findViewById(R.id.tv_word);
            this.tvType = itemView.findViewById(R.id.tv_word_type);
            this.tvMeaning = itemView.findViewById(R.id.tv_word_meaning);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }

    public MyAdapter(List<Word> lWords){
        listWords = lWords;
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.word_search_view, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvWord.setText(listWords.get(position).getKey());

        String type = listWords.get(position).getType();

        type = WordModifier.modify(type);

        holder.tvType.setText(type);
        if(listWords.get(position).getMeaning().size()==0) holder.tvMeaning.setText("không rõ nghĩa"); else
        holder.tvMeaning.setText(listWords.get(position).getMeaning().get(0));
        holder.setItemClickListener(itemClickListener);
    }

    @Override
    public int getItemCount() {
        return listWords.size();
    }
}
