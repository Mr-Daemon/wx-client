package com.example.wx_client.main;

import com.example.wx_client.network.BaseNetwork;
import com.example.wx_client.network.ResponseBody;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class MainNetwork extends BaseNetwork {
    private static FriendList friendList = retrofit.create(FriendList.class);

    static Call<ResponseBody> fetchFriendsList(RequestBody body) {
        return friendList.friendsList(body);
    }

    interface FriendList {
        @POST("friends-list")
        Call<ResponseBody> friendsList(@Body RequestBody body);
    }

    static class RequestBody {
        @SerializedName("token")
        private int mToken;
        @SerializedName("username")
        private String mUsername;

        public int getToken() {
            return mToken;
        }

        public void setToken(int token) {
            mToken = token;
        }

        public String getUsername() {
            return mUsername;
        }

        public void setUsername(String username) {
            mUsername = username;
        }

    }

}
