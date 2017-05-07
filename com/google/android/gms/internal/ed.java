// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public final class ed
{
    public static String D(String string) {
        if (string == null) {
            return null;
        }
        try {
            string = new JSONObject(string).getString("developerPayload");
            return string;
        }
        catch (JSONException ex) {
            gs.W("Fail to parse purchase data");
            return null;
        }
    }
    
    public static String E(String string) {
        if (string == null) {
            return null;
        }
        try {
            string = new JSONObject(string).getString("purchaseToken");
            return string;
        }
        catch (JSONException ex) {
            gs.W("Fail to parse purchase data");
            return null;
        }
    }
    
    public static int b(final Bundle bundle) {
        final Object value = bundle.get("RESPONSE_CODE");
        if (value == null) {
            gs.W("Bundle with null response code, assuming OK (known issue)");
            return 0;
        }
        if (value instanceof Integer) {
            return (int)value;
        }
        if (value instanceof Long) {
            return (int)(long)value;
        }
        gs.W("Unexpected type for intent response code. " + ((Long)value).getClass().getName());
        return 5;
    }
    
    public static int d(final Intent intent) {
        final Object value = intent.getExtras().get("RESPONSE_CODE");
        if (value == null) {
            gs.W("Intent with no response code, assuming OK (known issue)");
            return 0;
        }
        if (value instanceof Integer) {
            return (int)value;
        }
        if (value instanceof Long) {
            return (int)(long)value;
        }
        gs.W("Unexpected type for intent response code. " + ((Long)value).getClass().getName());
        return 5;
    }
    
    public static String e(final Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getStringExtra("INAPP_PURCHASE_DATA");
    }
    
    public static String f(final Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getStringExtra("INAPP_DATA_SIGNATURE");
    }
}
