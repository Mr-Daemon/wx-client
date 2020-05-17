package com.example.wx_client.main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wx_client.R;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter {
    private ArrayList<String> friendList = new ArrayList<>();
    private Context context;
    private static final String TAG = "MainAdapter";

    public MainAdapter(Context context, List<String> list) {
        this.context = context;
        friendList.addAll(list);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = new MainHolder(LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MainHolder mainHolder = (MainHolder) holder;
        Log.d(TAG, "onBindViewHolder() called with: text = [" + friendList.get(position) + "], position = [" + position + "]");
        mainHolder.textView.setText(friendList.get(position));
        mainHolder.itemView.setOnClickListener(v -> {
        });
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    static class MainHolder extends RecyclerView.ViewHolder {
        TextView textView;

        MainHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_friend_name);
        }
    }
}
