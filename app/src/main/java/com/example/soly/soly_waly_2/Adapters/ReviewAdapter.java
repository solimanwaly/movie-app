package com.example.soly.soly_waly_2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.soly.soly_waly_2.Holders.Review_Holder;
import com.example.soly.soly_waly_2.Model.Review_Model_Class;
import com.example.soly.soly_waly_2.Fragment_Files.Home_Fragment;
import com.example.soly.soly_waly_2.R;

import java.util.List;

/**
 * Created by Soliman Waly on 16/09/2016.
 */
public class ReviewAdapter extends BaseAdapter {

    Context context;
    List<Review_Model_Class> reviewList;

    public ReviewAdapter(Context context, List<Review_Model_Class> reviewList) {
        this.reviewList = reviewList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return reviewList.size();
    }

    @Override
    public Object getItem(int position) {
        return reviewList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Home_Fragment movie_fragment = new Home_Fragment();
        Review_Holder holder = null;
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.review_item, parent, false);
            holder = new Review_Holder(v);
            v.setTag(holder);
        } else {
            holder = (Review_Holder) convertView.getTag();
        }
        holder.authorTextView.setText(reviewList.get(position).author);
        holder.content.setText(reviewList.get(position).content);

        return v;
    }


}
