package com.example.soly.soly_waly_2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.soly.soly_waly_2.Model.Movie_Model_Class;
import com.example.soly.soly_waly_2.Holders.Grid_Holder;
import com.example.soly.soly_waly_2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Soliman Waly on 16/09/2016.
 */
public class MyAdapter extends BaseAdapter {
    List<Movie_Model_Class> movies;
    Context context;

    public MyAdapter(Context context, List<Movie_Model_Class> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View poster = convertView;
        Grid_Holder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            poster = (ImageView) inflater.inflate(R.layout.movie_grid_item, parent, false);
            holder = new Grid_Holder(poster);
            poster.setTag(holder);
        } else {
            holder = (Grid_Holder) poster.getTag();
        }
        Picasso.with(context).load(movies.get(position).Movie_posterURL).into(holder.imageView);
        return poster;
    }

}
