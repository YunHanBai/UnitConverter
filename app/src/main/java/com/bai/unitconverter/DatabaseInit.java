package com.bai.unitconverter;

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

public class DatabaseInit {

    static void initializeDatabase(){
        initializeSpeed();
        initializeLength();
        initializeArea();
        initializeVolume();
        initializeTemp();
        initializeFuel();
        initializeFrequency();
        initializeAngle();
        initializePressure();
        initializeTime();
        initializeMass();
        initializeStorage();
    }

    private static void initializeLength(){
        Length mile = new Length();
        mile.setUnitName("Mile");
        mile.setZhName("英里");
        mile.setFactor(6.213711922373E-4);
        mile.setSymbol("mi");
        mile.save();

        Length km = new Length();
        km.setUnitName("Kilometer");
        km.setZhName("千米");
        km.setFactor(.001);
        km.setSymbol("km");
        km.save();

        Length meter = new Length();
        meter.setUnitName("Meter");
        meter.setZhName("米");
        meter.setFactor(1);
        meter.setSymbol("m");
        meter.save();

        Length yard = new Length();
        yard.setUnitName("Yard");
        yard.setZhName("码");
        yard.setFactor(1.09361);
        yard.setSymbol("yd");
        yard.save();

        Length foot = new Length();
        foot.setUnitName("Foot");
        foot.setZhName("英尺");
        foot.setFactor(3.280839895E0);
        foot.setSymbol("ft");
        foot.save();

        Length dm = new Length();
        dm.setUnitName("Decimeter");
        dm.setZhName("分米");
        dm.setFactor(10);
        dm.setSymbol("dm");
        dm.save();

        Length inch = new Length();
        inch.setUnitName("Inch");
        inch.setZhName("英寸");
        inch.setFactor(3.937007874E1);
        inch.setSymbol("in");
        inch.save();

        Length cm = new Length();
        cm.setUnitName("Centimeter");
        cm.setZhName("厘米");
        cm.setFactor(100);
        cm.setSymbol("cm");
        cm.save();

        Length mm = new Length();
        mm.setUnitName("Millimeter");
        mm.setZhName("毫米");
        mm.setFactor(1000);
        mm.setSymbol("mm");
        mm.save();

        Length micro = new Length();
        micro.setUnitName("Micrometer");
        micro.setZhName("微米");
        micro.setFactor(1E6);
        micro.setSymbol("μm");
        micro.save();

        Length nm = new Length();
        nm.setUnitName("Nanometer");
        nm.setZhName("纳米");
        nm.setFactor(1E9);
        nm.setSymbol("nm");
        nm.save();

        Length seaMile = new Length();
        seaMile.setUnitName("Nautical Mile");
        seaMile.setZhName("海里");
        seaMile.setFactor(5.399568035E-4);
        seaMile.setSymbol("nmi");
        seaMile.save();
    }

    private static void initializeSpeed(){
        Speed mps = new Speed();
        mps.setUnitName("Meter/Second");
        mps.setZhName("米/秒");
        mps.setFactor(1);
        mps.setSymbol("m/s");
        mps.save();

        Speed kmph = new Speed();
        kmph.setUnitName("Kilometer/Hour");
        kmph.setZhName("千米/小时");
        kmph.setFactor(3.6);
        kmph.setSymbol("km/h");
        kmph.save();

        Speed mph = new Speed();
        mph.setUnitName("Miles/Hour");
        mph.setZhName("英里/小时");
        mph.setFactor(2.236936);
        mph.setSymbol("mph");
        mph.save();

        Speed ftps = new Speed();
        ftps.setUnitName("Foot/Second");
        ftps.setZhName("英尺/秒");
        ftps.setFactor(3.280840);
        ftps.setSymbol("ft/s");
        ftps.save();

        Speed inps = new Speed();
        inps.setUnitName("Inch/Second");
        inps.setZhName("英寸/秒");
        inps.setFactor(39.370079);
        inps.setSymbol("in/s");
        inps.save();

        Speed knot = new Speed();
        knot.setUnitName("Knot");
        knot.setZhName("节");
        knot.setFactor(1.943844);
        knot.setSymbol("kn");
        knot.save();

        Speed mach = new Speed();
        mach.setUnitName("Mach");
        mach.setZhName("马赫");
        mach.setFactor(0.00291545);
        mach.setSymbol("Ma");
        mach.save();

        Speed ls = new Speed();
        ls.setUnitName("Lightspeed");
        ls.setZhName("光速");
        ls.setFactor(3.335641E-9);
        ls.setSymbol("c");
        ls.save();
    }

    private static void initializeArea(){
        Area sqm = new Area();
        sqm.setUnitName("Square Mile");
        sqm.setZhName("平方英里");
        sqm.setFactor(3.8610216E-7);
        sqm.setSymbol("mi²");
        sqm.save();

        Area km2 = new Area();
        km2.setUnitName("Square Kilometer");
        km2.setZhName("平方千米");
        km2.setFactor(1E-6);
        km2.setSymbol("km²");
        km2.save();

        Area hec = new Area();
        hec.setUnitName("Hectare");
        hec.setZhName("公顷");
        hec.setFactor(1E-4);
        hec.setSymbol("ha");
        hec.save();

        Area are = new Area();
        are.setUnitName("Are");
        are.setZhName("公亩");
        are.setFactor(0.01);
        are.setSymbol("are");
        are.save();

        Area acre = new Area();
        acre.setUnitName("Acre");
        acre.setZhName("英亩");
        acre.setFactor(0.000247105);
        acre.setSymbol("acre");
        acre.save();

        Area m2 = new Area();
        m2.setUnitName("Square Meter");
        m2.setZhName("平方米");
        m2.setFactor(1);
        m2.setSymbol("m²");
        m2.save();

        Area sqyd = new Area();
        sqyd.setUnitName("Square Yard");
        sqyd.setZhName("平方码");
        sqyd.setFactor(1.19599);
        sqyd.setSymbol("yd²");
        sqyd.save();

        Area sqft = new Area();
        sqft.setUnitName("Square Foot");
        sqft.setZhName("平方英尺");
        sqft.setFactor(10.7639104);
        sqft.setSymbol("ft²");
        sqft.save();

        Area sqin = new Area();
        sqin.setUnitName("Square Inch");
        sqin.setZhName("平方英寸");
        sqin.setFactor(1550.0031);
        sqin.setSymbol("in²");
        sqin.save();

        Area sqcm = new Area();
        sqcm.setUnitName("Square Centimeter");
        sqcm.setZhName("平方厘米");
        sqcm.setFactor(10000);
        sqcm.setSymbol("cm²");
        sqcm.save();

        Area sqmm = new Area();
        sqmm.setUnitName("Square Millimeter");
        sqmm.setZhName("平方毫米");
        sqmm.setFactor(1000000);
        sqmm.setSymbol("mm²");
        sqmm.save();
    }

    private static void initializeVolume(){
        Volume m3 = new Volume();
        m3.setUnitName("Cubic Meter");
        m3.setZhName("立方米");
        m3.setFactor(1);
        m3.setSymbol("m³");
        m3.save();

        Volume cm3 = new Volume();
        cm3.setUnitName("Cubic Centimeter");
        cm3.setZhName("立方厘米");
        cm3.setFactor(1000000);
        cm3.setSymbol("cm³");
        cm3.save();

        Volume mm3 = new Volume();
        mm3.setUnitName("Cubic Millimeter");
        mm3.setZhName("立方毫米");
        mm3.setFactor(1000000000);
        mm3.setSymbol("mm³");
        mm3.save();

        Volume liter = new Volume();
        liter.setUnitName("Liter");
        liter.setZhName("升");
        liter.setFactor(1000);
        liter.setSymbol("L");
        liter.save();

        Volume ml = new Volume();
        ml.setUnitName("Milliliter");
        ml.setZhName("毫升");
        ml.setFactor(1000000);
        ml.setSymbol("ml");
        ml.save();

        Volume ft3 = new Volume();
        ft3.setUnitName("Cubic Foot");
        ft3.setZhName("立方英尺");
        ft3.setFactor(35.31467);
        ft3.setSymbol("ft³");
        ft3.save();

        Volume usLGallon = new Volume();
        usLGallon.setUnitName("US Liquid Gallon");
        usLGallon.setZhName("美制液体加仑");
        usLGallon.setFactor(264.17205124156);
        usLGallon.setSymbol("gal");
        usLGallon.save();

        Volume usDGallon = new Volume();
        usDGallon.setUnitName("US Dry Gallon");
        usDGallon.setZhName("美制固体加仑");
        usDGallon.setFactor(227.02074456538);
        usDGallon.setSymbol("gal");
        usDGallon.save();

        Volume ukGallon = new Volume();
        ukGallon.setUnitName("Imperial Gallon");
        ukGallon.setZhName("英制加仑");
        ukGallon.setFactor(219.96924829909);
        ukGallon.setSymbol("gal");
        ukGallon.save();

        Volume dm3 = new Volume();
        dm3.setUnitName("Cubic Decimeter");
        dm3.setZhName("立方分米");
        dm3.setFactor(1000);
        dm3.setSymbol("dm³");
        dm3.save();

        Volume uslq = new Volume();
        uslq.setUnitName("US Liquid Quart");
        uslq.setZhName("美制液体夸脱");
        uslq.setFactor(1056.688);
        uslq.setSymbol("qt");
        uslq.save();

        Volume usdq = new Volume();
        usdq.setUnitName("US Dry Quart");
        usdq.setZhName("美制固体加仑");
        usdq.setFactor(908.083);
        usdq.setSymbol("qt");
        usdq.save();

        Volume ukq = new Volume();
        ukq.setUnitName("Imperial Quart");
        ukq.setZhName("英制夸脱");
        ukq.setFactor(879.877);
        ukq.setSymbol("qt");
        ukq.save();

        Volume usfo = new Volume();
        usfo.setUnitName("US Fluid Ounce");
        usfo.setZhName("美制液体盎司");
        usfo.setFactor(33814);
        usfo.setSymbol("fl oz");
        usfo.save();

        Volume ukfo = new Volume();
        ukfo.setUnitName("Imperial Fluid Ounce");
        ukfo.setZhName("英制液体盎司");
        ukfo.setFactor(35195.1);
        ukfo.setSymbol("fl oz");
        ukfo.save();
    }

    private static void initializeTemp(){
        Temperature cel = new Temperature();
        cel.setUnitName("Celsius");
        cel.setZhName("摄氏度");
        cel.setFactor(3);
        cel.setSymbol("°C");
        cel.save();

        Temperature fah = new Temperature();
        fah.setUnitName("Fahrenheit");
        fah.setZhName("华氏度");
        fah.setFactor(2);
        fah.setSymbol("°F");
        fah.save();

        Temperature kel = new Temperature();
        kel.setUnitName("Kelvin");
        kel.setZhName("开尔文");
        kel.setFactor(1);
        kel.setSymbol("K");
        kel.save();
    }

    private static void initializeFuel(){
        Fuel usMile = new Fuel();
        usMile.setUnitName("Miles/Gallon(US)");
        usMile.setZhName("英里/美制加仑");
        usMile.setFactor(2.35215);
        usMile.setSymbol("mi/gal");
        usMile.save();

        Fuel lp100km = new Fuel();
        lp100km.setUnitName("Liter/100 Kilometer");
        lp100km.setZhName("升/百公里");
        lp100km.setFactor(100);
        lp100km.setSymbol("L/100km");
        lp100km.save();

        Fuel kmpl = new Fuel();
        kmpl.setUnitName("Kilometer/Liter");
        kmpl.setZhName("千米/升");
        kmpl.setFactor(1);
        kmpl.setSymbol("km/L");
        kmpl.save();

        Fuel ukMile = new Fuel();
        ukMile.setUnitName("Miles/Gallon(Imp.)");
        ukMile.setZhName("英里/英制加仑");
        ukMile.setFactor(2.82481);
        ukMile.setSymbol("mi/gal");
        ukMile.save();
    }

    private static void initializeFrequency(){
        Frequency hertz = new Frequency();
        hertz.setUnitName("Hertz");
        hertz.setZhName("赫兹");
        hertz.setFactor(1);
        hertz.setSymbol("Hz");
        hertz.save();

        Frequency kHz = new Frequency();
        kHz.setUnitName("Kilohertz");
        kHz.setZhName("千赫");
        kHz.setFactor(0.001);
        kHz.setSymbol("kHz");
        kHz.save();

        Frequency mHz = new Frequency();
        mHz.setUnitName("Megahertz");
        mHz.setZhName("兆赫");
        mHz.setFactor(1E-6);
        mHz.setSymbol("MHz");
        mHz.save();

        Frequency gHz = new Frequency();
        gHz.setUnitName("GigaHertz");
        gHz.setZhName("千兆赫");
        gHz.setFactor(1E-9);
        gHz.setSymbol("GHz");
        gHz.save();

        Frequency tHz = new Frequency();
        tHz.setUnitName("Terahertz");
        tHz.setZhName("太赫");
        tHz.setFactor(1E-12);
        tHz.setSymbol("THz");
        tHz.save();

        Frequency milliHz = new Frequency();
        milliHz.setUnitName("MilliHertz");
        milliHz.setZhName("毫赫");
        milliHz.setFactor(1E3);
        milliHz.setSymbol("mHz");
        milliHz.save();

        Frequency microHz = new Frequency();
        microHz.setUnitName("MicroHertz");
        microHz.setZhName("微赫");
        microHz.setFactor(1E6);
        microHz.setSymbol("µHz");
        microHz.save();
    }

    private static void initializeAngle(){
        Angle degree = new Angle();
        degree.setUnitName("Degree");
        degree.setZhName("度");
        degree.setFactor(1);
        degree.setSymbol("°");
        degree.save();

        Angle radian = new Angle();
        radian.setUnitName("Radian");
        radian.setZhName("弧度");
        radian.setFactor(0.0174533);
        radian.setSymbol("rad");
        radian.save();

        Angle gradian = new Angle();
        gradian.setUnitName("Gradian");
        gradian.setZhName("百分度");
        gradian.setFactor(10/9.0);
        gradian.setSymbol("ᵍ");
        gradian.save();

        Angle minArc = new Angle();
        minArc.setUnitName("Minute of Arc");
        minArc.setZhName("弧分");
        minArc.setFactor(60);
        minArc.setSymbol("′");
        minArc.save();

        Angle secAre = new Angle();
        secAre.setUnitName("Second of Arc");
        secAre.setZhName("弧秒");
        secAre.setFactor(3600);
        secAre.setSymbol("″");
        secAre.save();
    }

    private static void initializePressure(){
        Pressure pascal = new Pressure();
        pascal.setUnitName("Pascal");
        pascal.setZhName("帕斯卡");
        pascal.setFactor(1);
        pascal.setSymbol("Pa, N/m², kg/m·s²");
        pascal.save();

        Pressure psi = new Pressure();
        psi.setUnitName("Pound/Square Inch");
        psi.setZhName("磅/平方英寸");
        psi.setFactor(0.000145038);
        psi.setSymbol("psi");
        psi.save();

        Pressure mbar = new Pressure();
        mbar.setUnitName("Millibar");
        mbar.setZhName("毫巴");
        mbar.setFactor(0.01);
        mbar.setSymbol("mbar, hPa");
        mbar.save();

        Pressure kPa = new Pressure();
        kPa.setUnitName("Kilopascal");
        kPa.setZhName("千帕");
        kPa.setFactor(0.001);
        kPa.setSymbol("kPa");
        kPa.save();

        Pressure mmHg = new Pressure();
        mmHg.setUnitName("Millimeter of Mercury");
        mmHg.setZhName("毫米汞柱");
        mmHg.setFactor(0.007502);
        mmHg.setSymbol("mmHg");
        mmHg.save();

        Pressure atm = new Pressure();
        atm.setUnitName("Atmosphere");
        atm.setZhName("标准大气压");
        atm.setFactor(9.8692E-6);
        atm.setSymbol("atm");
        atm.save();

        Pressure bar = new Pressure();
        bar.setUnitName("Bar");
        bar.setZhName("巴");
        bar.setFactor(1.0000018082621e-5);
        bar.setSymbol("bar");
        bar.save();
    }

    private static void initializeTime(){
        Time hour = new Time();
        hour.setUnitName("Hour");
        hour.setZhName("小时");
        hour.setFactor(1);
        hour.setSymbol("hr");
        hour.save();

        Time minute = new Time();
        minute.setUnitName("Minute");
        minute.setZhName("分钟");
        minute.setFactor(60);
        minute.setSymbol("min");
        minute.save();

        Time second = new Time();
        second.setUnitName("Second");
        second.setZhName("秒");
        second.setFactor(3600);
        second.setSymbol("s");
        second.save();

        Time day = new Time();
        day.setUnitName("Day");
        day.setZhName("天");
        day.setFactor(0.0416667);
        day.setSymbol("day");
        day.save();

        Time ms = new Time();
        ms.setUnitName("Millisecond");
        ms.setZhName("毫秒");
        ms.setFactor(3.6E6);
        ms.setSymbol("ms");
        ms.save();

        Time micros = new Time();
        micros.setUnitName("Microsecond");
        micros.setZhName("微秒");
        micros.setFactor(3.6E9);
        micros.setSymbol("μs");
        micros.save();

        Time ns = new Time();
        ns.setUnitName("NanoSecond");
        ns.setZhName("纳秒");
        ns.setFactor(3.6E12);
        ns.setSymbol("ns");
        ns.save();
    }

    private static void initializeMass(){
        Mass kg = new Mass();
        kg.setUnitName("Kilogram");
        kg.setZhName("千克");
        kg.setFactor(1);
        kg.setSymbol("kg");
        kg.save();

        Mass lb = new Mass();
        lb.setUnitName("Pound");
        lb.setZhName("磅");
        lb.setFactor(2.204623);
        lb.setSymbol("lb");
        lb.save();

        Mass mTon = new Mass();
        mTon.setUnitName("Metric Ton");
        mTon.setZhName("吨(公制)");
        mTon.setFactor(.001);
        mTon.setSymbol("t");
        mTon.save();

        Mass usTon = new Mass();
        usTon.setUnitName("US Ton");
        usTon.setZhName("吨(美制)");
        usTon.setFactor(0.00110231);
        usTon.setSymbol("t");
        usTon.save();

        Mass gram = new Mass();
        gram.setUnitName("Gram");
        gram.setZhName("克");
        gram.setFactor(1000);
        gram.setSymbol("g");
        gram.save();

        Mass mg = new Mass();
        mg.setUnitName("Milligram");
        mg.setZhName("毫克");
        mg.setFactor(1E6);
        mg.setSymbol("mg");
        mg.save();

        Mass microg = new Mass();
        microg.setUnitName("Microgram");
        microg.setZhName("微克");
        microg.setFactor(1E9);
        microg.setSymbol("μg");
        microg.save();

        Mass ounce = new Mass();
        ounce.setUnitName("Ounce");
        ounce.setZhName("盎司");
        ounce.setFactor(35.27396);
        ounce.setSymbol("oz");
        ounce.save();

        Mass stone = new Mass();
        stone.setUnitName("Stone");
        stone.setZhName("斯通");
        stone.setFactor(0.157473);
        stone.setSymbol("st");
        stone.save();
    }

    private static void initializeStorage(){
        Storage bit = new Storage();
        bit.setUnitName("Bit");
        bit.setZhName("比特");
        bit.setFactor(8);
        bit.setSymbol("bit");
        bit.save();

        Storage byt = new Storage();
        byt.setUnitName("Byte");
        byt.setZhName("字节");
        byt.setFactor(1);
        byt.setSymbol("B");
        byt.save();

        Storage kB = new Storage();
        kB.setUnitName("Kilobyte");
        kB.setZhName("千字节");
        kB.setFactor(0.001);
        kB.setSymbol("kbit");
        kB.save();

        Storage mB = new Storage();
        mB.setUnitName("Megabyte");
        mB.setZhName("兆字节");
        mB.setFactor(1E-6);
        mB.setSymbol("MB");
        mB.save();

        Storage gB = new Storage();
        gB.setUnitName("Gigabyte");
        gB.setZhName("吉字节");
        gB.setFactor(1E-9);
        gB.setSymbol("GB");
        gB.save();

        Storage tB = new Storage();
        tB.setUnitName("Terabyte");
        tB.setZhName("太字节");
        tB.setFactor(1E-12);
        tB.setSymbol("TB");
        tB.save();

        Storage pB = new Storage();
        pB.setUnitName("Petabyte");
        pB.setZhName("拍字节");
        pB.setFactor(1E-15);
        pB.setSymbol("PB");
        pB.save();
    }
}
