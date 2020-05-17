package com.example.wx_client.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wx_client.BaseActivity;
import com.example.wx_client.R;

public class ChatActivity extends BaseActivity {
    private String username;
    private String friendname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_chat);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        friendname = bundle.getString("friendname");
        getSupportActionBar().setTitle(friendname);
    }
}
