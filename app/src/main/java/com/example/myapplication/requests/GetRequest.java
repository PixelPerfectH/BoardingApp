package com.example.myapplication.requests;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetRequest extends AsyncTask<String, Void, String> {
    private static final String TAG = "GetRequest";
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        String decodedUrl = URLDecoder.decode(url); // декодируем URL, чтобы избежать проблем с URL-кодированием
        Request request = new Request.Builder()
                .url(decodedUrl)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                Log.e(TAG, "Error response code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            Log.e(TAG, "Error executing request", e);
            return null;
        }
    }
}
