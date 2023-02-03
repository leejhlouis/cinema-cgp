package com.cinemacgp.utils;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.cinemacgp.models.Movie;
import com.cinemacgp.models.Response;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class APIService {
    private static final String API_KEY = "9970a3271dde526ba6195cdbd10c36fb";

    public static ArrayList<Movie> getMoviesNowPlaying(){
        String raw = call("https://api.themoviedb.org/3/movie/now_playing?api_key=" + API_KEY);
        Response response = parseJson(raw);
        return response.getMovies();
    }

    private static String call(String request) {
        String response = "";

        try {
            URL url = new URL(request);
            URLConnection connection = url.openConnection();

            response = toString(connection.getInputStream());

        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        }

        return response;
    }

    private static Response parseJson(String raw){
        Gson gson = new Gson();
        return gson.fromJson(raw, Response.class);
    }

    private static String toString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();

        try {
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();
    }
}
