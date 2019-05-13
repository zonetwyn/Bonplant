package com.zonetwyn.projects.bonplant.fragments;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.zonetwyn.projects.bonplant.R;
import com.zonetwyn.projects.bonplant.adapters.ProductAdapter;
import com.zonetwyn.projects.bonplant.components.Basket;
import com.zonetwyn.projects.bonplant.components.Filters;
import com.zonetwyn.projects.bonplant.models.Product;
import com.zonetwyn.projects.bonplant.utils.Event;
import com.zonetwyn.projects.bonplant.utils.EventBus;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private HomeViewModel viewModel;

    private Context context;

    private FrameLayout mapView;
    private DrawerLayout drawerLayout;
    private Filters filters;
    private Basket basket;
    private RecyclerView recyclerView;

    private SupportMapFragment mapFragment;
    private GoogleMap map;

    private ProductAdapter adapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context = getContext();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);

        drawerLayout = rootView.findViewById(R.id.drawerLayout);
        mapView = rootView.findViewById(R.id.mapView);
        filters = rootView.findViewById(R.id.filters);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        basket = rootView.findViewById(R.id.basket);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map, mapFragment).commit();
        }
        try {
            mapFragment.getMapAsync(this);
        } catch (Exception e) {}

        switchViews();

        initBasket();

        return rootView;
    }

    private void initBasket() {
        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event = new Event(Event.SUBJECT_MAIN_OPEN_BASKET, "Open Basket");
                EventBus.publish(EventBus.SUBJECT_MAIN_ACTIVITY, event);
            }
        });
    }

    private void switchViews() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                applySwitch();
            }
        }, 10000);
    }

    private void applySwitch() {
        mapView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        basket.setVisibility(View.VISIBLE);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());

        adapter = new ProductAdapter(context, products);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END);
            } else {
                drawerLayout.openDrawer(GravityCompat.END);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng france = new LatLng(48.8499198, 2.6370411);
        map.addMarker(new MarkerOptions().position(france).title("Marker in France"));
        map.moveCamera(CameraUpdateFactory.newLatLng(france));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(france, 15));
        map.getUiSettings().setZoomControlsEnabled(true);
    }
}
