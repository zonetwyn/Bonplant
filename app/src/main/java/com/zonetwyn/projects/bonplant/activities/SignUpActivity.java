package com.zonetwyn.projects.bonplant.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.zonetwyn.projects.bonplant.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignUpActivity extends AppCompatActivity {

    private CheckBox enterpriseCbx;
    private CheckBox personalCbx;
    private Button next;

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
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        personalCbx = findViewById(R.id.personalCbx);
        enterpriseCbx = findViewById(R.id.enterpriseCbx);
        next = findViewById(R.id.next);

        initButtons();
    }

    private void initButtons() {
        // apply fonts
        next.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/poppins/Poppins-SemiBold.otf"));
        next.setTransformationMethod(null);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean enterpriseChecked = enterpriseCbx.isChecked();
                boolean personalChecked = personalCbx.isChecked();

                if (enterpriseChecked && personalChecked) {
                    // TODO
                } else {
                    if (enterpriseChecked) {
                        Intent intent = new Intent(SignUpActivity.this, SignUpEnterpriseActivity.class);
                        startActivity(intent);
                    } else if (personalChecked) {
                        Intent intent = new Intent(SignUpActivity.this, SignUpPersonalActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
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
}
