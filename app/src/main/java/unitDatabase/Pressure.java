package unitDatabase;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Pressure extends LitePalSupport {

    @Column(unique = true, defaultValue = "unknown")
    private String unitName;
    @Column(unique = true, defaultValue = "unknown")
    private String zhName;
    @Column(defaultValue = "")
    private String symbol;
    private double factor;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }
}
