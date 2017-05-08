// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.Log;
import android.content.SharedPreferences;
import android.content.SharedPreferences$Editor;
import android.content.Context;

public class NetflixPreference
{
    private static final String PREFS_NAME = "nfxpref";
    private static final String TAG = "nfxpref";
    private Context mContext;
    private SharedPreferences$Editor mEditor;
    private SharedPreferences mPrefs;
    
    public NetflixPreference(final Context mContext) {
        this.mContext = mContext;
        this.load();
    }
    
    private boolean load() {
        try {
            this.mPrefs = this.mContext.getSharedPreferences("nfxpref", 0);
            this.mEditor = this.mPrefs.edit();
            return true;
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to get preferences!", t);
            return false;
        }
    }
    
    private boolean validate(final String s) {
        if (StringUtils.isEmpty(s)) {
            Log.w("nfxpref", "Name is null!");
            return false;
        }
        return true;
    }
    
    public boolean commit() {
        try {
            return this.mEditor.commit();
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to save to preferences!", t);
            return false;
        }
    }
    
    public boolean containsPref(final String s) {
        if (!this.validate(s)) {
            return false;
        }
        try {
            return this.mPrefs.contains(s);
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to get preferences!", t);
            return false;
        }
    }
    
    public boolean getBooleanPref(final String s, final boolean b) {
        if (!this.validate(s)) {
            return b;
        }
        try {
            return this.mPrefs.getBoolean(s, b);
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to get preferences!", t);
            return b;
        }
    }
    
    public float getFloatPref(final String s, final float n) {
        if (!this.validate(s)) {
            return n;
        }
        try {
            return this.mPrefs.getFloat(s, n);
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to get preferences!", t);
            return n;
        }
    }
    
    public int getIntPref(final String s, final int n) {
        if (!this.validate(s)) {
            return n;
        }
        try {
            return this.mPrefs.getInt(s, n);
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to get preferences!", t);
            return n;
        }
    }
    
    public long getLongPref(final String s, final long n) {
        if (!this.validate(s)) {
            return n;
        }
        try {
            return this.mPrefs.getLong(s, n);
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to get preferences!", t);
            return n;
        }
    }
    
    public String getStringPref(String string, final String s) {
        if (!this.validate(string)) {
            return s;
        }
        try {
            string = this.mPrefs.getString(string, s);
            return string;
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to get preferences!", t);
            return s;
        }
    }
    
    public boolean putBooleanPref(final String s, final boolean b) {
        if (!this.validate(s)) {
            return false;
        }
        try {
            this.mEditor.putBoolean(s, b);
            return true;
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to save to preferences!", t);
            return false;
        }
    }
    
    public boolean putIntPref(final String s, final int n) {
        if (!this.validate(s)) {
            return false;
        }
        try {
            this.mEditor.putInt(s, n);
            return true;
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to save to preferences!", t);
            return false;
        }
    }
    
    public void putLongPref(final String s, final float n) {
        if (!this.validate(s)) {
            return;
        }
        this.mEditor.putFloat(s, n);
    }
    
    public boolean putLongPref(final String s, final long n) {
        if (!this.validate(s)) {
            return false;
        }
        try {
            this.mEditor.putLong(s, n);
            return true;
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to save to preferences!", t);
            return false;
        }
    }
    
    public boolean putStringPref(final String s, final String s2) {
        if (!this.validate(s)) {
            return false;
        }
        try {
            this.mEditor.putString(s, s2);
            return true;
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to save to preferences!", t);
            return false;
        }
    }
    
    public boolean removePref(final String s) {
        if (!this.validate(s)) {
            return false;
        }
        try {
            this.mEditor.remove(s);
            return true;
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to save to preferences!", t);
            return false;
        }
    }
}
