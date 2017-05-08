// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.IOException;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

public final class h
{
    public boolean a;
    public boolean b;
    public boolean c;
    public int d;
    
    public h(final Context context) {
        this.a = false;
        this.b = false;
        this.c = false;
        this.d = 10;
        if (a(context).exists()) {
            this.c = true;
        }
    }
    
    public h(JSONObject jsonObject) {
        this.a = false;
        this.b = false;
        this.c = false;
        this.d = 10;
        if (!jsonObject.has("net")) {
            return;
        }
        try {
            jsonObject = jsonObject.getJSONObject("net");
            this.a = jsonObject.optBoolean("enabled", false);
            this.b = jsonObject.optBoolean("persist", false);
            this.c = jsonObject.optBoolean("kill", false);
            this.d = jsonObject.optInt("interval", 10);
        }
        catch (JSONException ex) {}
    }
    
    public static File a(final Context context) {
        return new File(context.getFilesDir().getAbsolutePath() + "/.crittercism.apm.disabled.");
    }
    
    public static void b(final Context context) {
        try {
            a(context).createNewFile();
        }
        catch (IOException ex) {
            dw.a("Unable to kill APM: " + ex.getMessage());
        }
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof h)) {
                return false;
            }
            final h h = (h)o;
            if (this.c != h.c) {
                return false;
            }
            if (this.a != h.a) {
                return false;
            }
            if (this.b != h.b) {
                return false;
            }
            if (this.d != h.d) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        int n = 1231;
        int n2;
        if (this.c) {
            n2 = 1231;
        }
        else {
            n2 = 1237;
        }
        int n3;
        if (this.a) {
            n3 = 1231;
        }
        else {
            n3 = 1237;
        }
        if (!this.b) {
            n = 1237;
        }
        return ((n3 + (n2 + 31) * 31) * 31 + n) * 31 + this.d;
    }
    
    @Override
    public final String toString() {
        return "OptmzConfiguration [\nisSendTaskEnabled=" + this.a + "\n, shouldPersist=" + this.b + "\n, isKilled=" + this.c + "\n, statisticsSendInterval=" + this.d + "]";
    }
}
