package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginTask extends AsyncTask<String, Void, Integer> {
    private static final String TAG = "LoginTask";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final Gson gson = new GsonBuilder().setLenient().create();

    private final String username;
    private final String password;

    public LoginTask(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected Integer doInBackground(String... params) {
        String url = params[0];
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(gson.toJson(new LoginRequest(username, password)), JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.code();
            } else {
                Log.e(TAG, "Error response code: " + response.code());
                return -1;
            }
        } catch (IOException e) {
            Log.e(TAG, "Error executing request", e);
            return -1;
        }
    }

    public static class LoginRequest {
        public final String username;
        public final String password;

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
