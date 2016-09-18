package com.example.soly.soly_waly_2.Fragment_Files;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soly.soly_waly_2.Adapters.ReviewAdapter;
import com.example.soly.soly_waly_2.DataBase_Files.DataBaseConnection;
import com.example.soly.soly_waly_2.Fetch_json.FetchTrailers;
import com.example.soly.soly_waly_2.Fetch_json.Fetch_Details;
import com.example.soly.soly_waly_2.Model.Movie_Model_Class;
import com.example.soly.soly_waly_2.Model.Review_Model_Class;
import com.example.soly.soly_waly_2.Model.Trailer_Model_Class;
import com.example.soly.soly_waly_2.R;
import com.linearlistview.LinearListView;
import com.squareup.picasso.Picasso;
import com.example.soly.soly_waly_2.Fetch_json.FetchReviews;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Soliman Waly on 16/09/2016.
 */
public class Details_fragment extends Fragment implements View.OnClickListener, LinearListView.OnItemClickListener {
    TextView title;
    ImageView poster;
    TextView releasedTime;
    TextView time;
    Button favourite;
    TextView overview;
    Movie_Model_Class movie;
    TextView voteAverage;
    LinearListView trailers;
    LinearListView reviewsListView;
    List<Trailer_Model_Class> trailerList;
    List<Review_Model_Class> reviewList;
    DataBaseConnection db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_movie_details, container, false);
        title = (TextView) v.findViewById(R.id.Title_movie_txt);
        releasedTime = (TextView) v.findViewById(R.id.ReleasedTime_movie_txt);
        time = (TextView) v.findViewById(R.id.Time_movie_txt);
        overview = (TextView) v.findViewById(R.id.Overview_movie_txt);
        poster = (ImageView) v.findViewById(R.id.image_movie_txt);
        favourite = (Button) v.findViewById(R.id.favourite_Btn);
        favourite.setOnClickListener(this);
        trailers = (LinearListView) v.findViewById(R.id.trailersList_movie);
        reviewsListView = (LinearListView) v.findViewById(R.id.reviewsList_movie);
        voteAverage = (TextView) v.findViewById(R.id.VoteAverage_movie_txt);
        db = new DataBaseConnection(getActivity());
        return v;
    }


    public void chage(Movie_Model_Class movie) {

       if(movie == null){
           title.setText("");
           releasedTime.setText("");
           time.setText("");
           overview.setText("");
           voteAverage.setText("");
           trailers.setAdapter(null);
           reviewsListView.setAdapter(null);
           poster.setImageBitmap(null);
           return;
       }
        int flag = db.searchMovie(movie.Movie_id);
        if (flag == 1) {
            favourite.setBackgroundColor(Color.parseColor("#2ED3Ff"));
            favourite.setText("Make as Unfavourite");
        }else{
            favourite.setText("Make as favourite");
            favourite.setBackgroundColor(Color.parseColor("#4c7bff"));
        }


        Fetch_Details fetch_movies = new Fetch_Details();
        try {
            movie = fetch_movies.execute(movie.Movie_id + "").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        this.movie = movie;
        title.setText(movie.title);
        releasedTime.setText(movie.Movie_releaseDate.substring(0, 4));
        time.setText(movie.Movie_time + " min");
        overview.setText(movie.Movie_overview);
        voteAverage.setText(movie.vote_average + " / 10 ");
        Picasso.with(getActivity()).load(movie.Movie_posterURL).into(poster);
        FetchTrailers fetchTrailers = new FetchTrailers();
        trailerList = null;
        try {
            trailerList = fetchTrailers.execute(movie.Movie_id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<String> trailersNames = new ArrayList<>();
        for (Trailer_Model_Class t : trailerList) {
            trailersNames.add(t.name);
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.trailer_item, R.id.trailer_txt, trailersNames);
        trailers.setAdapter(adapter);
        trailers.setOnItemClickListener(this);
        FetchReviews fetchReviews = new FetchReviews();
        reviewList = null;
        try {
            reviewList = fetchReviews.execute(movie.Movie_id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<Review_Model_Class> reviews = new ArrayList<>();

        for (Review_Model_Class r : reviewList) {
            reviews.add(new Review_Model_Class(r.author, r.content));
        }


        ReviewAdapter reviewAdapter = new ReviewAdapter(getActivity(), reviews);
        reviewsListView.setAdapter(reviewAdapter);
        reviewsListView.setOnItemClickListener(this);
    }


    public void onItemClick(LinearListView parent, View view, int position, long id) {
        if (parent.getId() == R.id.trailersList_movie) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerList.get(position).url));
            intent = Intent.createChooser(intent, "");
            startActivity(intent);
        }
    }

    MenuItem item;

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.favourite_Btn) {

            int flag = db.searchMovie(movie.Movie_id);


            if (flag == 0) {
                long id = db.insertMovie(movie.Movie_id, movie.Movie_posterURL);
                if (id < 0) {
                    Toast.makeText(getActivity(), "error Not inserted ", Toast.LENGTH_SHORT).show();
                } else {
                }
                favourite.setText("Make as Unfavourite");
                favourite.setBackgroundColor(Color.parseColor("#2ED3Ff"));
            } else if (flag == 1) {
                favourite.setText("Make as favourite");
                db.deleteMovie(movie.Movie_id);
                favourite.setBackgroundColor(Color.parseColor("#4c7bff"));
            }
        }
    }

}


