// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.Context;
import com.netflix.mediaclient.Log;
import android.content.Intent;

public final class IntentUtils
{
    public static final String INTENT_CATEGORY_UI = "LocalIntentNflxUi";
    private static final String TAG;
    public static final int USER_HIGH_PRIORITY = 999;
    public static final int USER_LOW_PRIORITY = -999;
    
    static {
        TAG = IntentUtils.class.getSimpleName();
    }
    
    public static String getIntentActionOrNull(final Intent intent) {
        String action;
        if (intent != null) {
            action = intent.getAction();
        }
        else {
            action = null;
        }
        if (action == null && Log.isLoggable()) {
            Log.e(IntentUtils.TAG, "getIntentActionOrNull intentAction is null");
        }
        return action;
    }
    
    private static int getSafePriority(final int n) {
        int n2;
        if (n < -1000) {
            n2 = -999;
        }
        else if ((n2 = n) > 1000) {
            return 999;
        }
        return n2;
    }
    
    public static boolean registerSafelyBroadcastReceiver(final Context context, final BroadcastReceiver broadcastReceiver, String s, final int n, final String... array) {
        if (context == null) {
            throw new IllegalArgumentException("Context is null");
        }
        if (broadcastReceiver == null) {
            throw new IllegalArgumentException("Receiver is null");
        }
        if (array == null || array.length < 1) {
            throw new IllegalArgumentException("No actions!");
        }
        final IntentFilter intentFilter = new IntentFilter();
        if (s != null) {
            intentFilter.addCategory(s);
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            s = array[i];
            if (StringUtils.isNotEmpty(s)) {
                intentFilter.addAction(s);
            }
        }
        intentFilter.setPriority(getSafePriority(n));
        try {
            context.registerReceiver(broadcastReceiver, intentFilter);
            return true;
        }
        catch (Throwable t) {
            Log.e(IntentUtils.TAG, "Failed to register ", t);
            return false;
        }
    }
    
    public static boolean registerSafelyBroadcastReceiver(final Context context, final BroadcastReceiver broadcastReceiver, final String s, final String... array) {
        return registerSafelyBroadcastReceiver(context, broadcastReceiver, s, 999, array);
    }
    
    public static boolean registerSafelyLocalBroadcastReceiver(final Context context, final BroadcastReceiver broadcastReceiver, final IntentFilter intentFilter) {
        if (context == null) {
            throw new IllegalArgumentException("Context is null");
        }
        if (broadcastReceiver == null) {
            throw new IllegalArgumentException("Receiver is null");
        }
        if (intentFilter == null) {
            throw new IllegalArgumentException("No filter!");
        }
        try {
            LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, intentFilter);
            return true;
        }
        catch (Throwable t) {
            Log.e(IntentUtils.TAG, "Failed to register ", t);
            return false;
        }
    }
    
    public static boolean registerSafelyLocalBroadcastReceiver(final Context context, final BroadcastReceiver broadcastReceiver, String s, int i, final String... array) {
        if (array == null || array.length < 1) {
            throw new IllegalArgumentException("No actions!");
        }
        final IntentFilter intentFilter = new IntentFilter();
        if (s != null) {
            intentFilter.addCategory(s);
        }
        int length;
        for (length = array.length, i = 0; i < length; ++i) {
            s = array[i];
            if (StringUtils.isNotEmpty(s)) {
                intentFilter.addAction(s);
            }
        }
        return registerSafelyLocalBroadcastReceiver(context, broadcastReceiver, intentFilter);
    }
    
    public static boolean registerSafelyLocalBroadcastReceiver(final Context context, final BroadcastReceiver broadcastReceiver, final String s, final String... array) {
        return registerSafelyLocalBroadcastReceiver(context, broadcastReceiver, s, 999, array);
    }
    
    public static boolean unregisterSafelyBroadcastReceiver(final Context context, final BroadcastReceiver broadcastReceiver) {
        if (context == null) {
            Log.e(IntentUtils.TAG, "Context is null");
            return false;
        }
        if (broadcastReceiver == null) {
            Log.e(IntentUtils.TAG, "Receiver is null");
            return false;
        }
        try {
            context.unregisterReceiver(broadcastReceiver);
            return true;
        }
        catch (Throwable t) {
            Log.e(IntentUtils.TAG, "Failed to unregister ", t);
            return false;
        }
    }
    
    public static boolean unregisterSafelyLocalBroadcastReceiver(final Context context, final BroadcastReceiver broadcastReceiver) {
        if (context == null) {
            Log.e(IntentUtils.TAG, "Context is null");
            return false;
        }
        if (broadcastReceiver == null) {
            Log.e(IntentUtils.TAG, "Receiver is null");
            return false;
        }
        try {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
            return true;
        }
        catch (Throwable t) {
            Log.e(IntentUtils.TAG, "Failed to unregister ", t);
            return false;
        }
    }
}
