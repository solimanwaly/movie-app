package com.example.soly.soly_waly_2.Activities_Files;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.soly.soly_waly_2.Fragment_Files.Details_fragment;
import com.example.soly.soly_waly_2.InterFace_File.Communicator;
import com.example.soly.soly_waly_2.Model.Movie_Model_Class;
import com.example.soly.soly_waly_2.R;
/**
 * Created by Soliman Waly on 16/09/2016.
 */

public class MainActivity extends AppCompatActivity implements Communicator {
   public FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getFragmentManager();
    }

    @Override
    public void sendDataOfMovies(Movie_Model_Class movie) {
        Details_fragment details_fragment = (Details_fragment) manager.findFragmentById(R.id.detailsFragment);
        if (details_fragment != null ) {
            details_fragment.chage(movie);
        } else {
            Intent intent = new Intent(this, Details_activity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("movie", movie);
            intent.putExtras(bundle);
            startActivity(intent);

        }
    }


}
