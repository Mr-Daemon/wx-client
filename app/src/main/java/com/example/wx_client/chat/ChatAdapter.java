package com.example.wx_client.chat;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wx_client.R;
import com.example.wx_client.database.DataBaseHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ChatAdapter extends RecyclerView.Adapter {
    private static final String TAG = "ChatAdapter";
    private String username;
    private String friendname;
    private Context context;
    private List<String> msgList = new ArrayList<>();

    ChatAdapter(Context context, String username, String friendname) {
        this.context = context;
        this.username = username;
        this.friendname = friendname;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                msgList = DataBaseHandler.fetchRecord(username, friendname);
                ((Activity) context).runOnUiThread(() -> {
                    notifyDataSetChanged();
                });
            }
        }, 0, 100);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = new MsgHolder(LayoutInflater.from(context).inflate(R.layout.item_msg, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MsgHolder msgHolder = (MsgHolder) holder;
        Log.d(TAG, "onBindViewHolder() called with: text = [" + msgList.get(position) + "], position = [" + position + "]");
        msgHolder.textView.setText(msgList.get(position));
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    public void addText(String text) {
        msgList.add(text);
        notifyItemInserted(msgList.size() - 1);
    }

    static class MsgHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MsgHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.msg);
        }
    }
}
