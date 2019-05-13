package com.zonetwyn.projects.bonplant.fragments;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zonetwyn.projects.bonplant.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpProfileUpdatedFragment extends Fragment {

    private Context context;

    private Button submit;

    public SignUpProfileUpdatedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sign_up_profile_updated, container, false);

        submit = rootView.findViewById(R.id.submit);

        initButtons();

        return rootView;
    }

    private void initButtons() {
        // apply fonts
        submit.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/poppins/Poppins-SemiBold.otf"));
        submit.setTransformationMethod(null);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
