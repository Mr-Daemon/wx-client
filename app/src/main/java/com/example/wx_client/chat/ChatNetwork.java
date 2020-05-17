package com.example.wx_client.chat;

import com.example.wx_client.network.BaseNetwork;
import com.example.wx_client.network.ResponseBody;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class ChatNetwork extends BaseNetwork {
    private static SendMsg send = retrofit.create(SendMsg.class);

    Call<ResponseBody> sendMsg(RequestBody body) {
        return send.sendMsg(body);
    }

    interface SendMsg {
        @POST("send-msg")
        Call<ResponseBody> sendMsg(@Body RequestBody body);
    }

    static class RequestBody {
        @SerializedName("msg")
        private String mMsg;
        @SerializedName("token")
        private Long mToken;
        @SerializedName("username")
        private String mUsername;
        @SerializedName("who")
        private String mWho;

        public String getMsg() {
            return mMsg;
        }

        public void setMsg(String msg) {
            mMsg = msg;
        }

        public Long getToken() {
            return mToken;
        }

        public void setToken(Long token) {
            mToken = token;
        }

        public String getUsername() {
            return mUsername;
        }

        public void setUsername(String username) {
            mUsername = username;
        }

        public String getWho() {
            return mWho;
        }

        public void setWho(String who) {
            mWho = who;
        }
    }
}
