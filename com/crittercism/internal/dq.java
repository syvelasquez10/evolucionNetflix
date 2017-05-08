// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.UUID;
import android.content.SharedPreferences$Editor;
import android.content.Context;
import android.content.SharedPreferences;

public final class dq
{
    private SharedPreferences a;
    private SharedPreferences b;
    private Context c;
    
    public dq(final Context c) {
        if (c == null) {
            throw new NullPointerException("context was null");
        }
        this.c = c;
        this.a = c.getSharedPreferences("com.crittercism.usersettings", 0);
        this.b = c.getSharedPreferences("com.crittercism.prefs", 0);
        if (this.a == null) {
            throw new NullPointerException("prefs were null");
        }
        if (this.b == null) {
            throw new NullPointerException("legacy prefs were null");
        }
    }
    
    private boolean a(final String s) {
        final SharedPreferences$Editor edit = this.a.edit();
        edit.putString("hashedDeviceID", s);
        return edit.commit();
    }
    
    private static String b() {
        try {
            return UUID.randomUUID().toString();
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return null;
        }
    }
    
    public final String a() {
        String string;
        if ((string = this.a.getString("hashedDeviceID", (String)null)) == null) {
            final String string2 = this.b.getString("com.crittercism.prefs.did", (String)null);
            if ((string = string2) != null) {
                string = string2;
                if (this.a(string2)) {
                    final SharedPreferences$Editor edit = this.b.edit();
                    edit.remove("com.crittercism.prefs.did");
                    edit.commit();
                    string = string2;
                }
            }
        }
        String b;
        if ((b = string) == null) {
            b = b();
            this.a(b);
        }
        return b;
    }
}
