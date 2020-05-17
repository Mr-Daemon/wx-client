package com.example.wx_client.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wx_client.BaseActivity;
import com.example.wx_client.R;
import com.example.wx_client.main.MainActivity;
import com.example.wx_client.network.ResponseBody;
import com.google.gson.Gson;

public class LoginActivity extends BaseActivity implements Login.View {
    private EditText editText1;
    private EditText editText2;
    private Button button;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private Boolean box1 = false;
    private Boolean box2 = false;
    private String username = "";
    private String password = "";
    private final String NULL_PASSWORD = "Your password can't be Null.";
    private final String NULL_PHONE = "Your phone number can't be Null.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        editText1 = findViewById(R.id.login_et1);
        editText2 = findViewById(R.id.login_et2);
        button = findViewById(R.id.login_b);
        checkBox1 = findViewById(R.id.login_cb1);
        checkBox2 = findViewById(R.id.login_cb2);
        setBoxListener();
        getMessage();
        setButtonListener();
    }

    private void setBoxListener() {
        button.setOnClickListener(v -> {
            username = editText1.getText().toString();
            password = editText2.getText().toString();
            if (username.equals("")) {
                Toast.makeText(this, NULL_PHONE, Toast.LENGTH_SHORT).show();
            } else if (password.equals("")) {
                Toast.makeText(this, NULL_PASSWORD, Toast.LENGTH_SHORT).show();
            } else {
                new LoginPresenter(this).login(username, password);
            }
        });
    }

    private void getMessage() {

    }

    private void setButtonListener() {

    }

    @Override
    public void startMain(ResponseBody body) {
        Toast.makeText(this, new Gson().toJson(body), Toast.LENGTH_SHORT).show();
        if (body.getCode() == 0) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("token", body.getToken());
            startActivity(intent);
        }
    }
}
