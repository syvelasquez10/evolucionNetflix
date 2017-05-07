// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Locale;
import java.math.BigInteger;
import android.text.TextUtils;
import android.content.Context;

@ez
public final class gf
{
    private static final Object uf;
    private static String we;
    
    static {
        uf = new Object();
    }
    
    public static String a(final Context context, final String s, final String s2) {
        synchronized (gf.uf) {
            if (gf.we == null && !TextUtils.isEmpty((CharSequence)s)) {
                b(context, s, s2);
            }
            return gf.we;
        }
    }
    
    private static void b(final Context context, final String s, final String s2) {
        BigInteger bigInteger;
        try {
            final ClassLoader classLoader = context.createPackageContext(s2, 3).getClassLoader();
            final Class<?> forName = Class.forName("com.google.ads.mediation.MediationAdapter", false, classLoader);
            bigInteger = new BigInteger(new byte[1]);
            final String[] split = s.split(",");
            BigInteger setBit;
            for (int i = 0; i < split.length; ++i, bigInteger = setBit) {
                setBit = bigInteger;
                if (gj.a(classLoader, forName, split[i])) {
                    setBit = bigInteger.setBit(i);
                }
            }
        }
        catch (Throwable t) {
            gf.we = "err";
            return;
        }
        gf.we = String.format(Locale.US, "%X", bigInteger);
    }
    
    public static String dj() {
        synchronized (gf.uf) {
            return gf.we;
        }
    }
}
