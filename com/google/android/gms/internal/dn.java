// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Locale;
import java.math.BigInteger;
import android.text.TextUtils;
import android.content.Context;

public final class dn
{
    private static final Object px;
    private static String qX;
    
    static {
        px = new Object();
    }
    
    public static String b(final Context context, final String s, final String s2) {
        synchronized (dn.px) {
            if (dn.qX == null && !TextUtils.isEmpty((CharSequence)s)) {
                c(context, s, s2);
            }
            return dn.qX;
        }
    }
    
    public static String bx() {
        synchronized (dn.px) {
            return dn.qX;
        }
    }
    
    private static void c(final Context context, final String s, final String s2) {
        BigInteger bigInteger;
        try {
            final ClassLoader classLoader = context.createPackageContext(s2, 3).getClassLoader();
            final Class<?> forName = Class.forName("com.google.ads.mediation.MediationAdapter", false, classLoader);
            bigInteger = new BigInteger(new byte[1]);
            final String[] split = s.split(",");
            BigInteger setBit;
            for (int i = 0; i < split.length; ++i, bigInteger = setBit) {
                setBit = bigInteger;
                if (dq.a(classLoader, forName, split[i])) {
                    setBit = bigInteger.setBit(i);
                }
            }
        }
        catch (Throwable t) {
            dn.qX = "err";
            return;
        }
        dn.qX = String.format(Locale.US, "%X", bigInteger);
    }
}
