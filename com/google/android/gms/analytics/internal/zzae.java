// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

@Deprecated
public class zzae
{
    private static volatile Logger zzOk;
    
    static {
        setLogger(new zzs());
    }
    
    public static Logger getLogger() {
        return zzae.zzOk;
    }
    
    public static void setLogger(final Logger zzOk) {
        zzae.zzOk = zzOk;
    }
    
    public static boolean zzM(final int n) {
        boolean b = false;
        if (getLogger() != null) {
            b = b;
            if (getLogger().getLogLevel() <= n) {
                b = true;
            }
        }
        return b;
    }
    
    public static void zzaE(final String s) {
        final zzaf zzkq = zzaf.zzkq();
        if (zzkq != null) {
            zzkq.zzbb(s);
        }
        else if (zzM(2)) {
            Log.w((String)zzy.zzNa.get(), s);
        }
        final Logger zzOk = zzae.zzOk;
        if (zzOk != null) {
            zzOk.warn(s);
        }
    }
    
    public static void zzf(final String s, final Object o) {
        final zzaf zzkq = zzaf.zzkq();
        if (zzkq != null) {
            zzkq.zze(s, o);
        }
        else if (zzM(3)) {
            String string;
            if (o != null) {
                string = s + ":" + o;
            }
            else {
                string = s;
            }
            Log.e((String)zzy.zzNa.get(), string);
        }
        final Logger zzOk = zzae.zzOk;
        if (zzOk != null) {
            zzOk.error(s);
        }
    }
}
