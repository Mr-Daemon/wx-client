
package com.example.wx_client.network;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ResponseBody {

    @SerializedName("code")
    private Long mCode;
    @SerializedName("list")
    private List<String> mList;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("token")
    private int mToken;
    @SerializedName("type")
    private String mType;

    public Long getCode() {
        return mCode;
    }

    public void setCode(Long code) {
        mCode = code;
    }

    public List<String> getList() {
        return mList;
    }

    public void setList(List<String> list) {
        mList = list;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

    public int getToken() {
        return mToken;
    }

    public void setToken(int token) {
        mToken = token;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
