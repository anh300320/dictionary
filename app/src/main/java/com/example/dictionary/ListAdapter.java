package com.example.dictionary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import myinterfaces.ItemClickListener;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<String> dataList;
    ItemClickListener itemClickListener;

    public static class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvHashtag;
        ItemClickListener itemClickListener;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.tvHashtag = itemView.findViewById(R.id.list_hashtag);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ListAdapter(List<String> dList){
        dataList = dList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hashtag_view, parent, false);
        ListViewHolder vh = new ListViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.tvHashtag.setText(dataList.get(position));
        holder.setItemClickListener(itemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
