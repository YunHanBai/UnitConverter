package com.bai.unitconverter;

public class TemperatureUnit extends Unit {


    public TemperatureUnit(){
        super();
    }

    /**
     * Constructor
     * @param name
     * @param symbol
     * @param factor Used as type (Kelvin: 1, Fahrenheit: 2, Celsius: 3)
     */
    public TemperatureUnit(String name, String symbol, double factor){
        super(name, symbol, factor);
    }

    @Override
    public double convertTo(Unit unit){
        double from = getFactor();
        double to = unit.getFactor();

        if ((from == Math.floor(from)) && (to == Math.floor(to))) {
            if(from == to){
                return getValue();
            } else if (from == 3){
                // From Celsius
                if (to == 2){
                    // To Fahrenheit
                    return 32 + (getValue() * 9.0 / 5.0);
                }
                if (to == 1){
                    // To Kelvin
                    return getValue() + 273.15;
                }
            } else if (from == 2){
                // From Fahrenheit
                if (to == 3){
                    // To Celsius
                    return (getValue() - 32) * (5.0 / 9.0);
                }
                if (to == 1){
                    // To Kelvin
                    return (getValue() - 32) * (5.0 / 9.0) + 273.15;
                }
            } else if (from == 1){
                // From Kelvin
                if (to == 3){
                    // To Celsius
                    return getValue() - 273.15;
                }
                if (to == 2){
                    // To Fahrenheit
                    return 32 + ((getValue() - 273.15) * 9.0 / 5.0);
                }
            }
        }

        throw new NumberFormatException("Temperature Factor Invalid");
    }
}
