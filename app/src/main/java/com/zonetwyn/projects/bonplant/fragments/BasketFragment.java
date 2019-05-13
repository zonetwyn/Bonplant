package com.zonetwyn.projects.bonplant.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zonetwyn.projects.bonplant.R;
import com.zonetwyn.projects.bonplant.adapters.RecordAdapter;
import com.zonetwyn.projects.bonplant.models.Record;

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends BottomSheetDialogFragment {

    private Context context;

    private RecyclerView recyclerView;
    private TextView totalPrice;
    private Button checkout;

    private RecordAdapter adapter;

    public BasketFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_basket, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        totalPrice = rootView.findViewById(R.id.totalPrice);
        checkout = rootView.findViewById(R.id.checkout);

        initButtons();

        // applying typeface
        totalPrice.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/poppins/Poppins-SemiBold.otf"));

        initRecyclerView();

        return rootView;
    }

    private void initButtons() {
        // apply fonts
        checkout.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/poppins/Poppins-SemiBold.otf"));
        checkout.setTransformationMethod(null);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initRecyclerView() {
        List<Record> records = new ArrayList<>();
        records.add(new Record());
        records.add(new Record());

        adapter = new RecordAdapter(context, records);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

}
