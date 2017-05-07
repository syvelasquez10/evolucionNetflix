// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;

public final class du
{
    public SharedPreferences a;
    
    protected du() {
    }
    
    public du(final Context context) {
        this.a = context.getSharedPreferences("com.crittercism.ratemyapp", 0);
    }
    
    public final int a() {
        return this.a.getInt("numAppLoads", 0);
    }
    
    public final void a(final boolean b) {
        this.a.edit().putBoolean("rateMyAppEnabled", b).commit();
    }
}
