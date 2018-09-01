package com.bai.unitconverter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import unitDatabase.Angle;
import unitDatabase.Area;
import unitDatabase.Frequency;
import unitDatabase.Fuel;
import unitDatabase.Length;
import unitDatabase.Mass;
import unitDatabase.Pressure;
import unitDatabase.Speed;
import unitDatabase.Storage;
import unitDatabase.Temperature;
import unitDatabase.Time;
import unitDatabase.Volume;

public class ConversionActivity extends BaseActivity
        implements MultiSelectionSpinner.OnMultipleItemsSelectedListener{

    private List<Unit> unitsList = new ArrayList<Unit>(), selectedList;
    private List<String> unitNames = new ArrayList<String>();
    private RecyclerView conversionTable;
    private UnitAdapter adapter;
    private EditText inputValue;
    private String category;
    private int order;
    static Unit baseUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        Intent intent = getIntent();
        category = intent.getStringExtra("type");
        order = intent.getIntExtra("order", -1);

        // Reset Activity title
        setTitle(category);

        // Action bar back arrow
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Create a list containing all the units in this category
        initUnits();
        // Initialize the base unit
        if (category.equals(getString(R.string.fuel))){
            baseUnit = new FuelUnit();
        } else if (category.equals(getString(R.string.temperature))){
            baseUnit = new TemperatureUnit();
        } else {
            baseUnit = new Unit();
        }
        final Context context = this;


        // ----- beginning of EditText -----
        inputValue = findViewById(R.id.value_input);
        inputValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(inputValue.getText().length() != 0) {
                    baseUnit.setValue(new Double(inputValue.getText().toString()));
                } else {
                    baseUnit.setValue(0);
                }
                // Update the conversion table
                if(adapter != null)
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Clear button
        ImageView clearText = findViewById(R.id.clear_text);
        clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValue.setText("");
            }
        });


        // ----- beginning of single selection spinner -----

        Spinner singleSpinner = findViewById(R.id.single_spinner);
        // Adjust the popup window of the spinner
        try{
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);
            android.widget.ListPopupWindow popupWindow =
                    (android.widget.ListPopupWindow) popup.get(singleSpinner);
            // Set the vertical displacement of the popup window
            popupWindow.setVerticalOffset(110);
        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e){
            Log.e("Conversion Activity", e.getMessage());
        }

        ArrayAdapter<String>  singleAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, unitNames);
        singleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        singleSpinner.setAdapter(singleAdapter);
        singleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Unit selected = unitsList.get(position);
                baseUnit.setUnitName(selected.getUnitName());
                baseUnit.setSymbol(selected.getUnitName());
                baseUnit.setFactor(selected.getFactor());

                // Update the conversion table
                if(adapter != null)
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String alert = getString(R.string.singleUnitAlert);
                Toast.makeText(context, alert, Toast.LENGTH_SHORT).show();
            }
        });

        // ----- beginning of multiple selection spinner -----
        MultiSelectionSpinner multiSpinner = findViewById(R.id.multi_spinner);
        multiSpinner.setUnits(unitNames);
        multiSpinner.setListener(this);

        // ----- beginning of conversion table -----
        conversionTable = findViewById(R.id.conversion_table);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        conversionTable.setLayoutManager(layoutManager);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        return true;
    }

    /**
     *  Create a list of Units representing all the units in this category
     *  Create a list of Strings of unit names for populating the spinner
     */
    private void initUnits(){
        // Get language preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = prefs.getString("language", Locale.getDefault().getDisplayLanguage());
        String orderType = prefs.getString("sorting_order", "");

        if(orderType.equals("")){
            if(lang.equals("en") || lang.equals("english")){
                initUnitDefaultEn();
            } else if (lang.equals("zh")) {
                initUnitDefaultZh();
            }
        } else {
            if(lang.equals("en") || lang.equals("english")){
                initUnitOrderedEn(orderType);
            } else if (lang.equals("zh")) {
                initUnitOrderedZh(orderType);
            }
        }

    }

    private void initUnitDefaultEn(){
        switch (order) {
            case 1:
                for (Speed unit : LitePal.findAll(Speed.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 2:
                for (Length unit : LitePal.findAll(Length.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 3:
                for (Area unit : LitePal.findAll(Area.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 4:
                for (Fuel unit : LitePal.findAll(Fuel.class)) {
                    if(unit.getFactor() == 100){
                        unitsList.add(new FuelUnit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    } else {
                        unitsList.add(new FuelUnit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    }
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 5:
                for (Temperature unit : LitePal.findAll(Temperature.class)) {
                    unitsList.add(new TemperatureUnit(unit.getUnitName(), unit.getSymbol(),
                            unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 6:
                for (Volume unit : LitePal.findAll(Volume.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 7:
                for (Frequency unit : LitePal.findAll(Frequency.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 8:
                for (Angle unit : LitePal.findAll(Angle.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 9:
                for (Pressure unit : LitePal.findAll(Pressure.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 10:
                for (Storage unit : LitePal.findAll(Storage.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 11:
                for (Mass unit : LitePal.findAll(Mass.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 12:
                for (Time unit : LitePal.findAll(Time.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
        }
    }

    private void initUnitDefaultZh(){
        switch (order) {
            case 1:
                for (Speed unit : LitePal.findAll(Speed.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 2:
                for (Length unit : LitePal.findAll(Length.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 3:
                for (Area unit : LitePal.findAll(Area.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 4:
                for (Fuel unit : LitePal.findAll(Fuel.class)) {
                    if(unit.getFactor() == 100){
                        unitsList.add(new FuelUnit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    } else {
                        unitsList.add(new FuelUnit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    }
                    unitNames.add(unit.getZhName());
                }
                break;
            case 5:
                for (Temperature unit : LitePal.findAll(Temperature.class)) {
                    unitsList.add(new TemperatureUnit(unit.getZhName(), unit.getSymbol(),
                            unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 6:
                for (Volume unit : LitePal.findAll(Volume.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 7:
                for (Frequency unit : LitePal.findAll(Frequency.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 8:
                for (Angle unit : LitePal.findAll(Angle.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 9:
                for (Pressure unit : LitePal.findAll(Pressure.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 10:
                for (Storage unit : LitePal.findAll(Storage.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 11:
                for (Mass unit : LitePal.findAll(Mass.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 12:
                for (Time unit : LitePal.findAll(Time.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
        }
    }

    private void initUnitOrderedEn(String orderType){
        switch (order) {
            case 1:
                for (Speed unit : LitePal.order(orderType).find(Speed.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 2:
                for (Length unit : LitePal.order(orderType).find(Length.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 3:
                for (Area unit : LitePal.order(orderType).find(Area.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 4:
                for (Fuel unit : LitePal.order(orderType).find(Fuel.class)) {
                    if(unit.getFactor() == 100){
                        unitsList.add(new FuelUnit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    } else {
                        unitsList.add(new FuelUnit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    }
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 5:
                for (Temperature unit : LitePal.order(orderType).find(Temperature.class)) {
                    unitsList.add(new TemperatureUnit(unit.getUnitName(), unit.getSymbol(),
                            unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 6:
                for (Volume unit : LitePal.order(orderType).find(Volume.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 7:
                for (Frequency unit : LitePal.order(orderType).find(Frequency.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 8:
                for (Angle unit : LitePal.order(orderType).find(Angle.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 9:
                for (Pressure unit : LitePal.order(orderType).find(Pressure.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 10:
                for (Storage unit : LitePal.order(orderType).find(Storage.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 11:
                for (Mass unit : LitePal.order(orderType).find(Mass.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
            case 12:
                for (Time unit : LitePal.order(orderType).find(Time.class)) {
                    unitsList.add(new Unit(unit.getUnitName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getUnitName());
                }
                break;
        }
    }

    private void initUnitOrderedZh(String orderType){
        switch (order) {
            case 1:
                for (Speed unit : LitePal.order(orderType).find(Speed.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 2:
                for (Length unit : LitePal.order(orderType).find(Length.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 3:
                for (Area unit : LitePal.order(orderType).find(Area.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 4:
                for (Fuel unit : LitePal.order(orderType).find(Fuel.class)) {
                    if(unit.getFactor() == 100){
                        unitsList.add(new FuelUnit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    } else {
                        unitsList.add(new FuelUnit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    }
                    unitNames.add(unit.getZhName());
                }
                break;
            case 5:
                for (Temperature unit : LitePal.order(orderType).find(Temperature.class)) {
                    unitsList.add(new TemperatureUnit(unit.getZhName(), unit.getSymbol(),
                            unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 6:
                for (Volume unit : LitePal.order(orderType).find(Volume.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 7:
                for (Frequency unit : LitePal.order(orderType).find(Frequency.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 8:
                for (Angle unit : LitePal.order(orderType).find(Angle.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 9:
                for (Pressure unit : LitePal.order(orderType).find(Pressure.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 10:
                for (Storage unit : LitePal.order(orderType).find(Storage.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 11:
                for (Mass unit : LitePal.order(orderType).find(Mass.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
            case 12:
                for (Time unit : LitePal.order(orderType).find(Time.class)) {
                    unitsList.add(new Unit(unit.getZhName(), unit.getSymbol(), unit.getFactor()));
                    unitNames.add(unit.getZhName());
                }
                break;
        }
    }

    @Override
    public void selectedIndices(List<Integer> indices) {
        selectedList = new ArrayList<>(indices.size());
        for(Integer i : indices){
            selectedList.add(unitsList.get(i));
        }
        // Retrieve user preferred output format
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String formatPattern = prefs.getString("format", "#.##########E0");
        adapter = new UnitAdapter(selectedList, formatPattern);
        conversionTable.setAdapter(adapter);
    }

    @Override
    public void selectedStrings(List<String> strings) {
        if(strings == null || strings.size() == 0){
            String alert = getString(R.string.atLeastUnitAlert);
            Toast.makeText(this, alert, Toast.LENGTH_SHORT).show();
        }
    }
}
