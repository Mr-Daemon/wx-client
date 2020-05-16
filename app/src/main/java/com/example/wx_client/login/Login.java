package com.example.wx_client.login;

import com.example.wx_client.network.ResponseBody;

public interface Login {
    interface View {
        void startMain(ResponseBody body);
    }

    interface Presenter {
        void login(String username, String password);
    }
}
