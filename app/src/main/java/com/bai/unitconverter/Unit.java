package com.bai.unitconverter;

import android.util.Log;

public class Unit {

    private String unitName;
    private String symbol;
    private double factor;
    private double value;

    public Unit(){
        unitName = "";
        symbol = "";
        factor = 1;
        value = 0;
    }

    public Unit(String name, String symbol, double factor){
        unitName = name;
        this.symbol = symbol;
        this.factor = factor;
        value = 0;
    }

    public double convertTo(Unit targetUnit){
        Log.d("Unit", "convertTo");
        return value * (targetUnit.getFactor() / factor);
    }

    public String getUnitName() {
        return unitName;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getFactor() {
        return factor;
    }

    public double getValue() {
        return value;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
