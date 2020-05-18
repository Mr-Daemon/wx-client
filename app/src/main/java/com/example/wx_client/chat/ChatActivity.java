package com.example.wx_client.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wx_client.BaseActivity;
import com.example.wx_client.R;
import com.example.wx_client.database.DataBaseHandler;
import com.example.wx_client.network.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends BaseActivity {
    private String username;
    private String friendname;
    private int token;
    private EditText editText;
    private Button sendBtn;
    private RecyclerView chatList;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBtn();
        initRV();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_chat);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        friendname = bundle.getString("friendname");
        token = bundle.getInt("token");
        getSupportActionBar().setTitle(friendname);
        editText = findViewById(R.id.input_text);
        sendBtn = findViewById(R.id.send_btn);
        chatList = findViewById(R.id.chat_list);
    }

    private void initBtn() {
        Context context = this;
        sendBtn.setOnClickListener(v -> {
            String msg = editText.getText().toString();
            ChatNetwork.RequestBody body = new ChatNetwork.RequestBody();
            body.setMsg(msg);
            body.setUsername(username);
            body.setWho(friendname);
            body.setToken(token);
            ChatNetwork.sendMsg(body).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body().getCode() == 0) {
                        String message = "[" + username + "]: " + msg;
                        adapter.addText(message);
                        DataBaseHandler.insertRecord(username, friendname, message);
                    } else {
                        Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
            editText.getText().clear();
        });
    }

    private void initRV() {
        adapter = new ChatAdapter(this, username, friendname);
        chatList.setAdapter(adapter);
        chatList.setLayoutManager(new LinearLayoutManager(this));
    }
}
