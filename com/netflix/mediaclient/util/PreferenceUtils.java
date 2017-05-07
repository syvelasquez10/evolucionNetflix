// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import com.netflix.mediaclient.javabridge.error.Signal;
import com.netflix.mediaclient.javabridge.error.CrashReport;
import com.netflix.mediaclient.Log;
import android.content.Context;

public final class PreferenceUtils
{
    private static final String PREFS_NAME = "nfxpref";
    
    public static boolean containsPref(final Context context, final String s) {
        if (!validate(context, s)) {
            return false;
        }
        try {
            return context.getSharedPreferences("nfxpref", 0).contains(s);
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to get preferences!", t);
            return false;
        }
    }
    
    public static boolean getBooleanPref(final Context context, final String s, final boolean b) {
        if (!validate(context, s)) {
            return b;
        }
        try {
            return context.getSharedPreferences("nfxpref", 0).getBoolean(s, b);
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to get preferences!", t);
            return b;
        }
    }
    
    public static CrashReport getCrashReport(final Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("nfxpref", 0);
        final int int1 = sharedPreferences.getInt("NF_CrashReport.signal", -1);
        final long long1 = sharedPreferences.getLong("NF_CrashReport.sigNumber", -1L);
        final long long2 = sharedPreferences.getLong("NF_CrashReport.errorNumber", -1L);
        final long long3 = sharedPreferences.getLong("NF_CrashReport.errorCode", -1L);
        final long long4 = sharedPreferences.getLong("NF_CrashReport.ts", -1L);
        final String string = sharedPreferences.getString("NF_CrashReport.movieId", "");
        final String string2 = sharedPreferences.getString("NF_CrashReport.episodeId", "");
        final int int2 = sharedPreferences.getInt("NF_CrashReport.trackId", 0);
        final int int3 = sharedPreferences.getInt("NF_CrashReport.pid", -1);
        if (int1 == -1) {
            removeCrashReport(sharedPreferences);
            return null;
        }
        final Signal signal = Signal.toSignal(int1);
        if (signal == null) {
            removeCrashReport(sharedPreferences);
            throw new IllegalArgumentException("Unknown signal " + int1);
        }
        return new CrashReport(signal, long1, long2, long3, string, string2, int2, long4, int3);
    }
    
    public static float getFloatPref(final Context context, final String s, final float n) {
        if (!validate(context, s)) {
            return n;
        }
        try {
            return context.getSharedPreferences("nfxpref", 0).getFloat(s, n);
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to get preferences!", t);
            return n;
        }
    }
    
    public static int getIntPref(final Context context, final String s, final int n) {
        if (!validate(context, s)) {
            return n;
        }
        try {
            return context.getSharedPreferences("nfxpref", 0).getInt(s, n);
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to get preferences!", t);
            return n;
        }
    }
    
    public static long getLongPref(final Context context, final String s, final long n) {
        if (!validate(context, s)) {
            return n;
        }
        try {
            return context.getSharedPreferences("nfxpref", 0).getLong(s, n);
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to get preferences!", t);
            return n;
        }
    }
    
    public static String getStringPref(final Context context, final String s, final String s2) {
        if (!validate(context, s)) {
            return s2;
        }
        try {
            return context.getSharedPreferences("nfxpref", 0).getString(s, s2);
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to get preferences!", t);
            return s2;
        }
    }
    
    public static boolean putBooleanPref(final Context context, final String s, final boolean b) {
        if (!validate(context, s)) {
            return false;
        }
        try {
            final SharedPreferences$Editor edit = context.getSharedPreferences("nfxpref", 0).edit();
            edit.putBoolean(s, b);
            edit.commit();
            return true;
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to save to preferences!", t);
            return false;
        }
    }
    
    public static boolean putIntPref(final Context context, final String s, final int n) {
        if (!validate(context, s)) {
            return false;
        }
        try {
            final SharedPreferences$Editor edit = context.getSharedPreferences("nfxpref", 0).edit();
            edit.putInt(s, n);
            edit.commit();
            return true;
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to save to preferences!", t);
            return false;
        }
    }
    
    public static void putLongPref(final Context context, final String s, final float n) {
        if (!validate(context, s)) {
            return;
        }
        final SharedPreferences$Editor edit = context.getSharedPreferences("nfxpref", 0).edit();
        edit.putFloat(s, n);
        edit.commit();
    }
    
    public static boolean putLongPref(final Context context, final String s, final long n) {
        if (!validate(context, s)) {
            return false;
        }
        try {
            final SharedPreferences$Editor edit = context.getSharedPreferences("nfxpref", 0).edit();
            edit.putLong(s, n);
            edit.commit();
            return true;
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to save to preferences!", t);
            return false;
        }
    }
    
    public static boolean putStringPref(final Context context, final String s, final String s2) {
        if (!validate(context, s)) {
            return false;
        }
        try {
            final SharedPreferences$Editor edit = context.getSharedPreferences("nfxpref", 0).edit();
            edit.putString(s, s2);
            edit.commit();
            return true;
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to save to preferences!", t);
            return false;
        }
    }
    
    private static void removeCrashReport(final SharedPreferences sharedPreferences) {
        final SharedPreferences$Editor edit = sharedPreferences.edit();
        edit.remove("NF_CrashReport.signal");
        edit.remove("NF_CrashReport.sigNumber");
        edit.remove("NF_CrashReport.errorCode");
        edit.remove("NF_CrashReport.errorNumber");
        edit.remove("NF_CrashReport.ts");
        edit.remove("NF_CrashReport.movieId");
        edit.remove("NF_CrashReport.episodeId");
        edit.remove("NF_CrashReport.trackId");
        edit.remove("NF_CrashReport.pid");
        edit.commit();
    }
    
    public static boolean removeCrashReport(final Context context) {
        try {
            removeCrashReport(context.getSharedPreferences("nfxpref", 0));
            return true;
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to remove crash report from preferences!", t);
            return false;
        }
    }
    
    public static boolean removePref(final Context context, final String s) {
        if (!validate(context, s)) {
            return false;
        }
        SharedPreferences sharedPreferences;
        try {
            sharedPreferences = context.getSharedPreferences("nfxpref", 0);
            if (sharedPreferences == null) {
                Log.e("nfxpref", "Prefs null, not expected!");
                return false;
            }
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to save to preferences!", t);
            return false;
        }
        final SharedPreferences$Editor edit = sharedPreferences.edit();
        if (edit == null) {
            Log.e("nfxpref", "Editor null, not expected!");
            return false;
        }
        edit.remove(s);
        edit.commit();
        return true;
    }
    
    public static boolean saveCrashReport(final Context context, final CrashReport crashReport) {
        try {
            final SharedPreferences$Editor edit = context.getSharedPreferences("nfxpref", 0).edit();
            edit.putInt("NF_CrashReport.signal", crashReport.getSignal().getNumber());
            edit.putLong("NF_CrashReport.sigNumber", crashReport.getSigNumber());
            edit.putLong("NF_CrashReport.errorCode", crashReport.getErrorCode());
            edit.putLong("NF_CrashReport.errorNumber", crashReport.getErrorNumber());
            edit.putLong("NF_CrashReport.ts", crashReport.getTimestamp());
            edit.putString("NF_CrashReport.movieId", crashReport.getMovieId());
            edit.putString("NF_CrashReport.episodeId", crashReport.getEpisodeId());
            edit.putInt("NF_CrashReport.trackId", crashReport.getTrkId());
            edit.commit();
            return true;
        }
        catch (Throwable t) {
            Log.e("nfxpref", "Failed to save to preferences!", t);
            return false;
        }
    }
    
    private static boolean validate(final Context context, final String s) {
        if (context == null) {
            Log.w("nfxpref", "Context is null!");
            return false;
        }
        if (s == null) {
            Log.w("nfxpref", "Name is null!");
            return false;
        }
        return true;
    }
}
