package com.bai.unitconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.*;

public class MainActivity extends BaseActivity {

    private List<Category> categories = new ArrayList<>();
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the category recycler view
        initializeCategories();

        // Initialize the database
        database = LitePal.getDatabase();
        checkFirstRun();

        CategoryAdapter adapter = new CategoryAdapter(categories);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ImageView setting = findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        ImageView about = findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeCategories(){
        categories.add(new Category(getString(R.string.speed), R.drawable.speed, 1));
        categories.add(new Category(getString(R.string.length), R.drawable.length, 2));
        categories.add(new Category(getString(R.string.area), R.drawable.area, 3));
        categories.add(new Category(getString(R.string.fuel), R.drawable.fuel, 4));
        categories.add(new Category(getString(R.string.temperature), R.drawable.temperature, 5));
        categories.add(new Category(getString(R.string.volume), R.drawable.volume, 6));
        categories.add(new Category(getString(R.string.frequency), R.drawable.frequency, 7));
        categories.add(new Category(getString(R.string.angle), R.drawable.angle, 8));
        categories.add(new Category(getString(R.string.pressure), R.drawable.pressure, 9));
        categories.add(new Category(getString(R.string.storage), R.drawable.storage, 10));
        categories.add(new Category(getString(R.string.mass), R.drawable.mass, 11));
        categories.add(new Category(getString(R.string.time), R.drawable.time, 12));
    }

    private void checkFirstRun() {

        final String PREF_VERSION_CODE_KEY = "version_code";

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, -1);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {
            // This is just a normal run
            return;
        } else if (savedVersionCode == -1) {
            // First run
            DatabaseInit.initializeDatabase();
        } else if (currentVersionCode > savedVersionCode) {
            // Updated
        }
        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }
}
