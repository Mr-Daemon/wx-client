package com.example.wx_client.login;

import com.example.wx_client.network.BaseNetwork;
import com.example.wx_client.network.ResponseBody;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

class LoginNetwork extends BaseNetwork {
    private static Login login = retrofit.create(Login.class);

    static Call<ResponseBody> loginCall(RequestBody body) {
        return login.login(body);
    }

    interface Login {
        @POST("login")
        Call<ResponseBody> login(@Body RequestBody body);
    }

    static public class RequestBody {

        @SerializedName("password")
        private String mPassword;
        @SerializedName("username")
        private String mUsername;

        public String getPassword() {
            return mPassword;
        }

        public void setPassword(String password) {
            mPassword = password;
        }

        public String getUsername() {
            return mUsername;
        }

        public void setUsername(String username) {
            mUsername = username;
        }

    }
}
