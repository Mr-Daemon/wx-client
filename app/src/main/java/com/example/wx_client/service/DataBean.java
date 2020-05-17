package com.example.wx_client.service;

import com.google.gson.annotations.SerializedName;

public class DataBean {
    static class Message {

        @SerializedName("from")
        private String mFrom;
        @SerializedName("msg")
        private String mMsg;

        public String getFrom() {
            return mFrom;
        }

        public void setFrom(String from) {
            mFrom = from;
        }

        public String getMsg() {
            return mMsg;
        }

        public void setMsg(String msg) {
            mMsg = msg;
        }

    }
}
