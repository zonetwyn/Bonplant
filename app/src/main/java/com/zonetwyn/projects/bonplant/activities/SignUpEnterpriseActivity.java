package com.zonetwyn.projects.bonplant.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zonetwyn.projects.bonplant.R;
import com.zonetwyn.projects.bonplant.fragments.SignUpCardFragment;
import com.zonetwyn.projects.bonplant.fragments.SignUpEnterpriseFragment;
import com.zonetwyn.projects.bonplant.fragments.SignUpProfileFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignUpEnterpriseActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button prev;
    private Button next;

    private PageAdapter adapter;

    private int currentPosition = 0;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/poppins/Poppins-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        setContentView(R.layout.activity_sign_up_enterprise);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.enterprise_title);
        toolbarTitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/poppins/Poppins-SemiBold.otf"));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        viewPager = findViewById(R.id.viewPager);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);

        initButtons();
        initViewPager();
        applyPosition();
    }

    private void initButtons() {
        next.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/poppins/Poppins-SemiBold.otf"));
        next.setTransformationMethod(null);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition != 2) {
                    currentPosition++;
                    viewPager.setCurrentItem(currentPosition);
                }
            }
        });

        prev.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/poppins/Poppins-SemiBold.otf"));
        prev.setTransformationMethod(null);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition != 0) {
                    currentPosition--;
                    viewPager.setCurrentItem(currentPosition);
                }
            }
        });
    }

    private void initViewPager() {
        adapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                applyPosition();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void applyPosition() {
        if (currentPosition == 0) {
            prev.setVisibility(View.GONE);
            next.setVisibility(View.VISIBLE);
        } else if (currentPosition == 2) {
            next.setVisibility(View.GONE);
            prev.setVisibility(View.VISIBLE);
        } else {
            next.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private class PageAdapter extends FragmentStatePagerAdapter {

        private static final int NUM_PAGES = 3;
        SparseArray<Fragment> registeredFragments = new SparseArray<>();

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SignUpEnterpriseFragment();
                case 1:
                    return new SignUpProfileFragment();
                case 2:
                    return new SignUpCardFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
