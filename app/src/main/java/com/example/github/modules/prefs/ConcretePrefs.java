package com.example.github.modules.prefs;

import android.content.Context;
import android.content.SharedPreferences;

class ConcretePrefs implements IPrefs {
    private static final String PREFS_NAME = "GitAppPrefs";

    private final SharedPreferences sharedPreferences;

    ConcretePrefs(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void putStringValue(String tag, String value) {
        sharedPreferences.edit().putString(tag, value).apply();
    }

    @Override
    public String getStringValue(String tag) {
        return sharedPreferences.getString(tag, null);
    }
}
