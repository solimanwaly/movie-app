package com.example.soly.soly_waly_2.Holders;

import android.view.View;
import android.widget.TextView;

import com.example.soly.soly_waly_2.R;

/**
 * Created by Soliman Waly on 16/09/2016.
 */
public class Review_Holder {


    public TextView content;
    public  TextView authorTextView;
    public Review_Holder(View v){
        content = (TextView) v.findViewById(R.id.review_txt);
        authorTextView = (TextView) v.findViewById(R.id.author_txt);

    }
}
