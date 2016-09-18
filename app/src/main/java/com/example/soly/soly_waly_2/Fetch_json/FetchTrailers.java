package com.example.soly.soly_waly_2.Fetch_json;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.soly.soly_waly_2.APi.Api_key;
import com.example.soly.soly_waly_2.Model.Trailer_Model_Class;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Soliman Waly on 16/09/2016.
 */
public class FetchTrailers extends AsyncTask<Integer, Void, List<Trailer_Model_Class>> {

    @Override
    protected List<Trailer_Model_Class> doInBackground(Integer... params) {
        String base = "https://api.themoviedb.org/3/movie/" + params[0] + "/videos?";
        String json = getJSON(base);

        return getTrailers(json);
    }

    private List<Trailer_Model_Class> getTrailers(String json) {
        List<Trailer_Model_Class> trailers = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray results = jsonObject.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject resultsJSONObject = results.getJSONObject(i);
                String key = resultsJSONObject.getString("key");
                String name = resultsJSONObject.getString("name");
                Trailer_Model_Class trailer = new Trailer_Model_Class(name, "https://www.youtube.com/watch?v=" + key);
                trailers.add(trailer);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trailers;
    }

    public String getJSON(String base) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonstr = null;


        String api_key = Api_key.getApi_key();
        int page = 1;
        try {
            String baseUrl = base;

            final String API_param = "api_key";
            Uri builtUri = Uri.parse(baseUrl).buildUpon()
                    .appendQueryParameter(API_param, api_key).build();

            URL url = new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }

            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            jsonstr = buffer.toString();
            Log.d("json", jsonstr.toString());
            return jsonstr.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}