package com.zonetwyn.projects.bonplant.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zonetwyn.projects.bonplant.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpPersonalFragment extends Fragment {


    public SignUpPersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_personal, container, false);
    }

}
