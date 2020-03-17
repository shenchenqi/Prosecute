package com.micro.root.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * created by kilin on 20-3-17 上午10:25
 */
public abstract class BaseShared {

    protected final Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    protected BaseShared(Context context) {
        this.context = context;
        if (!TextUtils.isEmpty(getCacheName())) {
            sharedPreferences = getSharedPreference();
            editor = getEditor();
        }
    }

    protected abstract String getCacheName();

    private SharedPreferences getSharedPreference() {
        return context.getSharedPreferences(getCacheName(), 0);
    }

    public int getInt(String key, int value) {
        if (sharedPreferences == null) {
            return 0;
        }
        return sharedPreferences.getInt(key, value);
    }

    public String getString(String key, String value) {
        if (sharedPreferences == null) {
            return "";
        }
        return sharedPreferences.getString(key, value);
    }

    public boolean getBoolean(String key, boolean value) {
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.getBoolean(key, value);
    }

    public float getFloat(String key, float value) {
        if (sharedPreferences == null) {
            return 0;
        }
        return sharedPreferences.getFloat(key, value);
    }

    public long getLong(String key, long value) {
        if (sharedPreferences == null) {
            return 0;
        }
        return sharedPreferences.getLong(key, value);
    }

    public Set<String> getSetList(String key, Set<String> value) {
        if (sharedPreferences == null) {
            return new HashSet<>();
        }
        return sharedPreferences.getStringSet(key, value);
    }

    private SharedPreferences.Editor getEditor() {
        return getSharedPreference().edit();
    }

    public void saveString(String key, String value) {
        if (editor == null) {
            return;
        }
        editor.putString(key, value);
        editor.apply();
    }

    public void saveInteger(String key, int value) {
        if (editor == null) {
            return;
        }
        editor.putInt(key, value);
        editor.apply();
    }

    public void saveBoolean(String key, boolean value) {
        if (editor == null) {
            return;
        }
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void saveLong(String key, long value) {
        if (editor == null) {
            return;
        }
        editor.putLong(key, value);
        editor.apply();
    }

    public void saveFloat(String key, float value) {
        if (editor == null) {
            return;
        }
        editor.putFloat(key, value);
        editor.apply();
    }

    public void saveSet(String key, Set<String> value) {
        if (editor == null) {
            return;
        }
        editor.putStringSet(key, value);
        editor.apply();
    }

    public void clear() {
        if (editor == null) {
            return;
        }
        editor.clear();
        editor.apply();
    }

    public void clearKey(String key) {
        if (editor == null) {
            return;
        }
        editor.remove(key);
        editor.apply();
    }
}