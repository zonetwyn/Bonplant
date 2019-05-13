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

public class Filters extends LinearLayout {

    private Context context;

    private TextView filtersLabel;
    private Button apply;

    public Filters(Context context) {
        super(context);
        this.context = context;
    }

    public Filters(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init();
    }

    private void init() {
        // binding views
        View rootView = LayoutInflater.from(context).inflate(R.layout.filters, this, true);
        filtersLabel = rootView.findViewById(R.id.filtersLabel);
        apply = rootView.findViewById(R.id.apply);


        // applying fonts
        filtersLabel.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/poppins/Poppins-SemiBold.otf"));

        // initializations
        initButtons();
    }

    private void initButtons() {
        apply.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/poppins/Poppins-SemiBold.otf"));
        apply.setTransformationMethod(null);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

