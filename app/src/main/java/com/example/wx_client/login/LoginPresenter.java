package com.example.wx_client.login;

import android.util.Log;
import android.widget.Toast;

import com.example.wx_client.network.ResponseBody;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements Login.Presenter {
    private Login.View view;

    LoginPresenter(Login.View view) {
        this.view = view;
    }

    @Override
    public void login(String username, String password) {
        LoginNetwork.RequestBody body = new LoginNetwork.RequestBody();
        body.setUsername(username);
        body.setPassword(password);
        LoginNetwork.loginCall(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.startMain(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
