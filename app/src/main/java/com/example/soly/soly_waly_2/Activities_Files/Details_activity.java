package com.example.soly.soly_waly_2.Activities_Files;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import com.example.soly.soly_waly_2.Fragment_Files.Details_fragment;
import com.example.soly.soly_waly_2.Model.Movie_Model_Class;
import com.example.soly.soly_waly_2.R;

/**
 * Created by Soliman Waly on 16/09/2016.
 */
public class Details_activity extends Activity {
FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_fragment) ;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Movie_Model_Class movie = (Movie_Model_Class) bundle.getSerializable("movie");
        manager = getFragmentManager();

       Details_fragment details_fragment = (Details_fragment) manager.findFragmentById(R.id.detailsFragment);
        details_fragment.chage(movie);



    }

}
