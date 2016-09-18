package com.example.soly.soly_waly_2.Holders;

import android.view.View;
import android.widget.ImageView;

import com.example.soly.soly_waly_2.R;

/**
 * Created by Soliman Waly on 16/09/2016.
 */
public class Grid_Holder {
    public ImageView imageView;

    public Grid_Holder(View v) {
        this.imageView = (ImageView) v.findViewById(R.id.Img_movies_item);
    }
}
