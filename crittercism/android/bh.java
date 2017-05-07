// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.SharedPreferences;
import org.json.JSONException;
import android.content.Context;
import org.json.JSONObject;

public final class bh
{
    public boolean a;
    public int b;
    public int c;
    public JSONObject d;
    
    bh() {
        this.a = false;
        this.b = 10;
        this.c = 3600000;
        this.d = new JSONObject();
    }
    
    public bh(final JSONObject jsonObject) {
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
    
    public static bh a(Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("com.crittercism.txn.config", 0);
        context = (Context)new bh();
        ((bh)context).a = sharedPreferences.getBoolean("enabled", false);
        ((bh)context).b = sharedPreferences.getInt("interval", 10);
        ((bh)context).c = sharedPreferences.getInt("defaultTimeout", 3600000);
        final String string = sharedPreferences.getString("transactions", (String)null);
        ((bh)context).d = new JSONObject();
        if (string == null) {
            return (bh)context;
        }
        try {
            ((bh)context).d = new JSONObject(string);
            return (bh)context;
        }
        catch (JSONException ex) {
            return (bh)context;
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
