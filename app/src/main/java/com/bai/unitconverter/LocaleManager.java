package com.bai.unitconverter;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LocaleManager {


    public static Context setLocale(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return updateResources(context,
                prefs.getString("language", Locale.getDefault().getDisplayLanguage()));
    }

    public static Context setNewLocale(Context context, String language){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        // Uses commit() instead of apply()
        // because apply() could be prevent from finish by killing the process
        prefs.edit().putString("language", language).commit();
        return updateResources(context, language);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResources(Context context, String language){
        Locale locale = new Locale(language);
        locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            // Deprecation
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }
}