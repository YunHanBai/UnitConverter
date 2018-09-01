package com.bai.unitconverter;

import android.util.Log;
import android.widget.Toast;

import unitDatabase.Fuel;

public class FuelUnit extends Unit{

    public FuelUnit(){
        super();
    }

    /**
     * Constructor
     * @param name
     * @param symbol
     * @param factor Used as type
     */
    public FuelUnit(String name, String symbol, double factor){
        super(name, symbol, factor);
    }

    @Override
    public double convertTo(Unit targetUnit){
        // L/100km is considered type 0
        // Other units are considered type 1, e.g. km/L, mi/gal
        int fromType = (this.getFactor() == 100)? 0 : 1;
        int toType = (targetUnit.getFactor() == 100)? 0 : 1;

        if (fromType == toType) {
            Log.d("FuelUnitConvertion", "SAME TYPE");
            return convertToSameType(this.getValue(), this.getFactor(), targetUnit.getFactor());
        } else if (fromType < toType) {
            // From L/100km
            double toKmPL = 100/this.getValue();
            Log.d("FuelUnitConvertion", "1.convert to km/L = " + toKmPL);
            return convertToSameType(toKmPL, 1, targetUnit.getFactor());
        } else {
            // To L/100km
            double toKmPL = convertToSameType(this.getValue(), this.getFactor(), 1);
            Log.d("FuelUnitConvertion", "2.convert to km/L = " + toKmPL);
            return 100/toKmPL;
        }
    }

    public double convertToSameType(double value, double factorFrom, double factorTo) {
        return value * (factorTo/factorFrom);
    }
}
