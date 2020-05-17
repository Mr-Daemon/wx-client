package com.example.wx_client.main;

import java.util.List;

public interface Main {
    interface View {
        void initRV(List<String> list);
    }

    interface Presenter {
        void fetchFriendList(int token, String username);
    }
}
