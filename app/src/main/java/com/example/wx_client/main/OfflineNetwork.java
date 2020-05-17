package com.example.wx_client.main;

import com.example.wx_client.network.BaseNetwork;
import com.example.wx_client.network.ResponseBody;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public class OfflineNetwork extends BaseNetwork {
    private static Offline offline = retrofit.create(Offline.class);

    public static Call<ResponseBody> offline(RequestBody body) {
        return offline.offline(body);
    }

    interface Offline {
        @POST("offline")
        Call<ResponseBody> offline(@Body RequestBody body);
    }

    static public class RequestBody {

        RequestBody(String username) {
            mUsername = username;
        }

        @SerializedName("username")
        private String mUsername;

        public String getUsername() {
            return mUsername;
        }

        public void setUsername(String username) {
            mUsername = username;
        }

    }
}
