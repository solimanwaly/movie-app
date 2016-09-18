package com.example.soly.soly_waly_2.Model;

import java.io.Serializable;

/**
 * Created by Soliman Waly on 16/09/2016.
 */
public class Movie_Model_Class implements Serializable {
    public String Movie_posterURL;
    public  String title;
    public String Movie_releaseDate;
    public  int Movie_time;
    public String Movie_overview;
    public int Movie_id;
    public double vote_average;



    public void setDetails(int id,String Poster_path, String title, String overview, String releaseDate, int time, double vote_average) {
        this.Movie_id=id;
        this.Movie_posterURL =Poster_path;
        this.title = title;
        this.Movie_overview = overview;
        this.Movie_releaseDate = releaseDate;
        this.Movie_time = time;
        this.vote_average = vote_average;
    }

    public void setURL(String posterURL, int id) {
        this.Movie_posterURL = posterURL;
        this.Movie_id = id;
    }


}
