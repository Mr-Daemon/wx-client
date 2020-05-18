package com.example.wx_client.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.wx_client.BaseActivity;
import com.example.wx_client.R;
import com.example.wx_client.network.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements Main.View {
    RecyclerView recyclerView;
    String username;
    int token;
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        token = bundle.getInt("token");
        getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
        OfflineNetwork.offline(new OfflineNetwork.RequestBody(username)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: " + username + " offline");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
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
        MainAdapter adapter = new MainAdapter(this, username, token, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
