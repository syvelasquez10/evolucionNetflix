// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import android.content.Context;

public class nf
{
    private final nn aku;
    private nl akv;
    
    public nf(final Context context, final int n, final String s, final String s2, final a a, final boolean b) {
        int versionCode = 0;
        final String packageName = context.getPackageName();
        while (true) {
            try {
                versionCode = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
                this.akv = new nl(packageName, versionCode, n, s, s2, b);
                this.aku = new nn(context, new nk(a));
            }
            catch (PackageManager$NameNotFoundException ex) {
                Log.wtf("PlayLogger", "This can't happen.");
                continue;
            }
            break;
        }
    }
    
    public void a(final long n, final String s, final byte[] array, final String... array2) {
        this.aku.b(this.akv, new nh(n, s, array, array2));
    }
    
    public void b(final String s, final byte[] array, final String... array2) {
        this.a(System.currentTimeMillis(), s, array, array2);
    }
    
    public void start() {
        this.aku.start();
    }
    
    public void stop() {
        this.aku.stop();
    }
    
    public interface a
    {
        void b(final PendingIntent p0);
        
        void mS();
        
        void mT();
    }
}
