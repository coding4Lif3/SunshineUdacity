package com.coding4lif3.sunshine.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.coding4lif3.sunshine.R;

/**
 * Created by federicomonaco on 10/5/14.
 */
public class Utility {

    public static String getPreferenceLocation(Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return preferences.getString(ctx.getString(R.string.preference_location_key), ctx.getString(R.string.preference_location_default));
    }

    public static String getPreferenceTempUnits(Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        return preferences.getString(ctx.getString(R.string.preference_tunits_key), ctx.getString(R.string.preference_tunits_default));
    }
}
