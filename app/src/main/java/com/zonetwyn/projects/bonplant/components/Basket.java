package com.zonetwyn.projects.bonplant.components;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zonetwyn.projects.bonplant.R;

public class Basket extends LinearLayout {

    private Context context;

    private TextView count;

    public Basket(Context context) {
        super(context);
        this.context = context;
    }

    public Basket(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init();
    }

    private void init() {
        // binding views
        View rootView = LayoutInflater.from(context).inflate(R.layout.basket, this, true);
        count = rootView.findViewById(R.id.basketCount);


        // applying fonts
        count.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/poppins/Poppins-SemiBold.otf"));

        // initializations
    }
}
