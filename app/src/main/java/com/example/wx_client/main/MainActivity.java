package com.example.wx_client.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.wx_client.BaseActivity;
import com.example.wx_client.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements Main.View {
    RecyclerView recyclerView;
    String username;
    int token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        token = bundle.getInt("token");
        getData();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
    }

    void getData() {
        new MainPresenter(this).fetchFriendList(token, username);
    }

    @Override
    public void initRV(List<String> list) {
        MainAdapter adapter = new MainAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
