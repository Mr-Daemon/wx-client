package com.example.wx_client.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.wx_client.database.DataBaseHandler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListenService extends Service {
    private static final String TAG = "ListenService";
    public static ListenService instance;
    private static ServerSocket socket;

    static {
        try {
            socket = new ServerSocket(1234);
        } catch (IOException e) {
            Log.e(TAG, "static initializer", e);
        }
    }

    private String username;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        instance = this;
        username = intent.getExtras().getString("username");
        new Thread(() -> {
            try {
                while (true) {
                    Socket conn = socket.accept();
                    acceptData(conn);
                }
            } catch (IOException e) {
                Log.e(TAG, "onStartCommand", e);
            }
        }).start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    void acceptData(Socket socket) {
        new Thread(() -> {
            try {
                DataInputStream stream = new DataInputStream(socket.getInputStream());
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(stream));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    content.append(line).append("\n");
                }
                Log.d(TAG, "acceptData() called: data = [" + content.toString() + "]");
                List<String> rList = new ArrayList<>();
                Matcher matcher = Pattern.compile("\"(.*?)\"").matcher(content);
                while (matcher.find()) {
                    rList.add(matcher.group(1));
                }
                String msg = new String(rList.get(3).getBytes(), StandardCharsets.UTF_8);
                Log.d(TAG, "msg: " + msg);
                DataBaseHandler.insertRecord(username, rList.get(1), "[" + rList.get(1) + "]: " + msg);
            } catch (IOException e) {
                Log.e(TAG, "acceptData", e);
            }
        }).start();
    }
}
