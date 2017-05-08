// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.SharedPreferences;
import org.json.JSONException;
import android.content.Context;
import org.json.JSONObject;

public final class bf
{
    public boolean a;
    public int b;
    public int c;
    public JSONObject d;
    
    bf() {
        this.a = false;
        this.b = 10;
        this.c = 3600000;
        this.d = new JSONObject();
    }
    
    public bf(final JSONObject jsonObject) {
        this.a = false;
        this.b = 10;
        this.c = 3600000;
        this.d = new JSONObject();
        this.a = jsonObject.optBoolean("enabled", false);
        this.b = jsonObject.optInt("interval", 10);
        this.c = jsonObject.optInt("defaultTimeout", 3600000);
        this.d = jsonObject.optJSONObject("transactions");
        if (this.d == null) {
            this.d = new JSONObject();
        }
    }
    
    public static bf a(Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("com.crittercism.txn.config", 0);
        context = (Context)new bf();
        ((bf)context).a = sharedPreferences.getBoolean("enabled", false);
        ((bf)context).b = sharedPreferences.getInt("interval", 10);
        ((bf)context).c = sharedPreferences.getInt("defaultTimeout", 3600000);
        final String string = sharedPreferences.getString("transactions", (String)null);
        ((bf)context).d = new JSONObject();
        if (string == null) {
            return (bf)context;
        }
        try {
            ((bf)context).d = new JSONObject(string);
            return (bf)context;
        }
        catch (JSONException ex) {
            return (bf)context;
        }
    }
    
    public final long a(final String s) {
        final JSONObject optJSONObject = this.d.optJSONObject(s);
        if (optJSONObject != null) {
            return optJSONObject.optLong("timeout", (long)this.c);
        }
        return this.c;
    }
}
