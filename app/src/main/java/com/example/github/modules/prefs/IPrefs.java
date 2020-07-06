package com.example.github.modules.prefs;

public interface IPrefs {
    void putStringValue(String tag, String value);
    String getStringValue(String tag);
}
