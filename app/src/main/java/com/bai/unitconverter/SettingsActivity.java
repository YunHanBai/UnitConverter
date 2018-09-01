package com.bai.unitconverter;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

public class SettingsActivity extends BaseActivity {

    private CharSequence[] languageItems = {"中文", "English"};
    private CharSequence[] qualifier = {"zh", "en"};
    private CharSequence[] decimalItems;
    private CharSequence[] decimalValues;
    private CharSequence[] orderItems;
    private CharSequence[] orderValues;
    private int lang_pref = 0, decimal_pref = 0, order_pref = 0;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Action bar back arrow
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Initializing the arrays of items as entries of dropdowns
        decimalItems = getResources().getStringArray(R.array.pref_unit_format_entries);
        orderItems = getResources().getStringArray(R.array.pref_order_entries);
        orderValues = getResources().getStringArray(R.array.pref_order_values);

        // Language dropdown

        findViewById(R.id.language_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsActivity.this);
                dialog.setTitle(getString(R.string.pref_language_title));
                dialog.setSingleChoiceItems(languageItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lang_pref = which;
                    }
                }).setPositiveButton(getString(R.string.submit), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CharSequence choice = qualifier[lang_pref];
                        if (prefs.getString("language", "en") != choice){
                            setNewLocale(choice.toString());
                        }
                    }
                });
                AlertDialog alert = dialog.create();
                alert.show();
            }
        });

        // Decimal point drop down

        findViewById(R.id.decimal_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsActivity.this);

                // Initialize the decimal string value list with scientific notation preference
                if(prefs.getBoolean("scientific_notation", true)){
                    decimalValues = getResources().getStringArray(R.array.pref_unit_format_values);
                } else {
                    decimalValues = getResources().getStringArray(R.array.pref_unit_format_values_no_E);
                }
                dialog.setTitle(getString(R.string.pref_format_title));
                dialog.setSingleChoiceItems(decimalItems, prefs.getInt("format_value",
                        7), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        decimal_pref = which;
                    }
                }).setPositiveButton(getString(R.string.submit), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CharSequence choice = decimalValues[decimal_pref];
                        if (prefs.getString("format", "#.##########E0") != choice){
                            prefs.edit().putString("format", choice.toString()).apply();
                            prefs.edit().putInt("format_value", decimal_pref).apply();
                        }
                    }
                });
                AlertDialog alert = dialog.create();
                alert.show();
            }
        });

        // About activity button

        findViewById(R.id.about_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        // Scientific notation switch

        final SwitchCompat switcher = findViewById(R.id.science_switch);
        // Set the state of the switch, checked by default
        switcher.setChecked(prefs.getBoolean("scientific_notation", true));
        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String format = prefs.getString("format", "#.##########E0");
                if (isChecked){
                    // Change to scientific notation
                    if(prefs.getBoolean("scientific_notation", true) != true){
                        prefs.edit().putBoolean("scientific_notation", true).apply();

                        // Manipulate format preference
                        prefs.edit().putString("format", addE(format)).apply();
                    }
                } else {
                    // Change to no scientific notation
                    if(prefs.getBoolean("scientific_notation", true) == true){
                        prefs.edit().putBoolean("scientific_notation", false).apply();
                        // Manipulate format preference
                        prefs.edit().putString("format", removeE(format)).apply();
                    }
                }
            }
        });

        // Unit list order drop down

        findViewById(R.id.unit_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsActivity.this);

                dialog.setTitle(getString(R.string.pref_unit_order_title));
                dialog.setSingleChoiceItems(orderItems, prefs.getInt("order_value",
                        0), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        order_pref = which;
                    }
                }).setPositiveButton(getString(R.string.submit), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CharSequence choice = orderValues[order_pref];
                        if (prefs.getString("sorting_order", "") != choice){
                            prefs.edit().putString("sorting_order", choice.toString()).apply();
                            prefs.edit().putInt("order_value", order_pref).apply();
                        }
                    }
                });
                AlertDialog alert = dialog.create();
                alert.show();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        return true;
    }

    private void setNewLocale(String language){
        LocaleManager.setNewLocale(this, language);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    private String removeE(String format){
        if(format.endsWith("E0")){
            return (format.subSequence(0, format.length() - 2)).toString();
        } else
            return format;
    }

    private String addE(String format){
        if(!format.endsWith("E")){
            return format + "E0";
        } else {
            return format;
        }
    }
}
