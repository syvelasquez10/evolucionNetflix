// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import android.content.Intent;
import android.os.Bundle;
import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;
import com.google.android.gms.internal.zzgr;

@zzgr
public class zzi
{
    public String zzao(String string) {
        if (string == null) {
            return null;
        }
        try {
            string = new JSONObject(string).getString("developerPayload");
            return string;
        }
        catch (JSONException ex) {
            zzb.zzaH("Fail to parse purchase data");
            return null;
        }
    }
    
    public int zzc(final Bundle bundle) {
        final Object value = bundle.get("RESPONSE_CODE");
        if (value == null) {
            zzb.zzaH("Bundle with null response code, assuming OK (known issue)");
            return 0;
        }
        if (value instanceof Integer) {
            return (int)value;
        }
        if (value instanceof Long) {
            return (int)(long)value;
        }
        zzb.zzaH("Unexpected type for intent response code. " + ((Long)value).getClass().getName());
        return 5;
    }
    
    public int zzd(final Intent intent) {
        if (intent == null) {
            return 5;
        }
        final Object value = intent.getExtras().get("RESPONSE_CODE");
        if (value == null) {
            zzb.zzaH("Intent with no response code, assuming OK (known issue)");
            return 0;
        }
        if (value instanceof Integer) {
            return (int)value;
        }
        if (value instanceof Long) {
            return (int)(long)value;
        }
        zzb.zzaH("Unexpected type for intent response code. " + ((Long)value).getClass().getName());
        return 5;
    }
    
    public String zze(final Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getStringExtra("INAPP_PURCHASE_DATA");
    }
    
    public String zzf(final Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getStringExtra("INAPP_DATA_SIGNATURE");
    }
}
