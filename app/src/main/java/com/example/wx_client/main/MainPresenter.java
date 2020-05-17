package com.example.wx_client.main;

import android.util.Log;

import com.example.wx_client.network.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements Main.Presenter {
    private Main.View view;
    private static final String TAG = "MainPresenter";

    MainPresenter(Main.View view) {
        this.view = view;
    }

    public void fetchFriendList(int token, String username) {
        MainNetwork.RequestBody body = new MainNetwork.RequestBody();
        body.setToken(token);
        body.setUsername(username);
        MainNetwork.fetchFriendsList(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.initRV(response.body().getList());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: error", t);
            }
        });
    }
}
