package com.zonetwyn.projects.bonplant.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zonetwyn.projects.bonplant.R;
import com.zonetwyn.projects.bonplant.fragments.AboutUsFragment;
import com.zonetwyn.projects.bonplant.fragments.AccountFragment;
import com.zonetwyn.projects.bonplant.fragments.BasketFragment;
import com.zonetwyn.projects.bonplant.fragments.BillsFragment;
import com.zonetwyn.projects.bonplant.fragments.HomeFragment;
import com.zonetwyn.projects.bonplant.fragments.OrderHistoryFragment;
import com.zonetwyn.projects.bonplant.fragments.ShopFragment;
import com.zonetwyn.projects.bonplant.utils.CustomTypefaceSpan;
import com.zonetwyn.projects.bonplant.utils.Event;
import com.zonetwyn.projects.bonplant.utils.EventBus;

import io.reactivex.functions.Consumer;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;

    private TextView toolbarTitle;

    private int currentMenu = 0;
    private String[] titles;

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
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.home);
        toolbarTitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/poppins/Poppins-SemiBold.otf"));

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navigationHeader = navigationView.getHeaderView(0);
        TextView picture = navigationHeader.findViewById(R.id.picture);
        picture.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/poppins/Poppins-SemiBold.otf"));

        // init titles;
        titles  = new String[]{
                getString(R.string.home),
                getString(R.string.shop),
                getString(R.string.order_history),
                getString(R.string.bills),
                getString(R.string.account),
                getString(R.string.about_us)
        };

        subscribeToBus();
        setupMenuItemFont();
    }

    private void subscribeToBus() {
        EventBus.subscribe(EventBus.SUBJECT_MAIN_ACTIVITY, this, new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof Event) {
                    Event event = (Event) o;
                    if (event.getData() != null && event.getSubject() != 0) {
                        switch (event.getSubject()) {
                            case Event.SUBJECT_MAIN_OPEN_BASKET:
                                showBasketFragment();
                                break;
                        }
                    }
                }
            }
        });
    }

    private void showBasketFragment() {
        BasketFragment fragment = new BasketFragment();
        fragment.show(getSupportFragmentManager(), fragment.getTag());
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentMenu = 0;
        applyMenuSelected();
    }

    private void setupMenuItemFont() {
        Menu menu = navigationView.getMenu();
        for (int i=0; i<menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);

            SubMenu subMenu = menuItem.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            applyFontToMenuItem(menuItem);
        }
    }

    private void applyFontToMenuItem(MenuItem menuItem) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/poppins/Poppins-Regular.otf");
        SpannableString title = new SpannableString(menuItem.getTitle());
        title.setSpan(new CustomTypefaceSpan("" , font), 0 , title.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        menuItem.setTitle(title);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            currentMenu = 0;
        } else if (id == R.id.nav_shop) {
            currentMenu = 1;
        } else if (id == R.id.nav_order_history) {
            currentMenu = 2;
        } else if (id == R.id.nav_bills) {
            currentMenu = 3;
        } else if (id == R.id.nav_account) {
            currentMenu = 4;
        } else if (id == R.id.nav_about_us) {
            currentMenu = 5;
        }

        applyMenuSelected();

        return true;
    }

    private void applyMenuSelected() {
        Fragment fragment = null;
        switch (currentMenu) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new ShopFragment();
                break;
            case 2:
                fragment = new OrderHistoryFragment();
                break;
            case 3:
                fragment = new BillsFragment();
                break;
            case 4:
                fragment = new AccountFragment();
                break;
            case 5:
                fragment = new AboutUsFragment();
                break;
        }

        if (fragment != null) {
            getSupportFragmentManager().popBackStack();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainFrame, fragment);
            fragmentTransaction.commit();

            // check navigation item
            Menu menu = navigationView.getMenu();
            MenuItem menuItem = menu.getItem(currentMenu);
            menuItem.setChecked(true);

            // uncheck others
            for (int i=0; i<menu.size(); i++) {
                if (i != currentMenu) {
                    MenuItem item = menu.getItem(i);
                    item.setChecked(false);
                }
            }

            toolbarTitle.setText(titles[currentMenu]);
        }

        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.unregister(this);
    }
}
