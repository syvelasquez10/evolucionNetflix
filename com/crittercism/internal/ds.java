// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.Context;
import android.content.SharedPreferences;

public final class ds
{
    public SharedPreferences a;
    
    protected ds() {
    }
    
    public ds(final Context context) {
        this.a = context.getSharedPreferences("com.crittercism.ratemyapp", 0);
    }
    
    public final int a() {
        return this.a.getInt("numAppLoads", 0);
    }
    
    public final void a(final boolean b) {
        this.a.edit().putBoolean("rateMyAppEnabled", b).commit();
    }
    
    public final String b() {
        return this.a.getString("rateAppMessage", "Would you mind taking a second to rate my app?  I would really appreciate it!");
    }
    
    public final String c() {
        return this.a.getString("rateAppTitle", "Rate My App");
    }
}
