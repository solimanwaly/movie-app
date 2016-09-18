package com.example.soly.soly_waly_2.Fetch_json;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.soly.soly_waly_2.APi.Api_key;
import com.example.soly.soly_waly_2.Model.Movie_Model_Class;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Soliman Waly on 16/09/2016.
 */
public class Fetch_Details extends AsyncTask<String, Void, Movie_Model_Class> {


    @Override
    protected Movie_Model_Class doInBackground(String[]  params) {

        String baseUrl = "https://api.themoviedb.org/3/movie/" + params[0] + "?";

        Movie_Model_Class movie = get_json_Details(getJson(baseUrl));


        return movie;

    }

    public String getJson(String base) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonstring = null;
        String api_key = Api_key.getApi_key();
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
            jsonstring = buffer.toString();

            return jsonstring.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

    public Movie_Model_Class get_json_Details(String jsonstring) {
        Movie_Model_Class movie = new Movie_Model_Class();
        try {
            JSONObject jsonObject = new JSONObject(jsonstring);
            String poster = jsonObject.getString("poster_path");
            int id = jsonObject.getInt("id");
            String title = jsonObject.getString("original_title");
            String overview = jsonObject.getString("overview");
            String releaseDate = jsonObject.getString("release_date");
            int time = jsonObject.getInt("runtime");
            double vote_average = jsonObject.getDouble("vote_average");
            movie.setDetails(id, "http://image.tmdb.org/t/p/w320/" + poster, title, overview, releaseDate, time, vote_average);
            return movie;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

}