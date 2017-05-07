// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

public class z
{
    private static GoogleAnalytics AT;
    
    public static void T(final String s) {
        final Logger logger = getLogger();
        if (logger != null) {
            logger.error(s);
        }
    }
    
    public static void U(final String s) {
        final Logger logger = getLogger();
        if (logger != null) {
            logger.info(s);
        }
    }
    
    public static void V(final String s) {
        final Logger logger = getLogger();
        if (logger != null) {
            logger.verbose(s);
        }
    }
    
    public static void W(final String s) {
        final Logger logger = getLogger();
        if (logger != null) {
            logger.warn(s);
        }
    }
    
    public static boolean eL() {
        boolean b = false;
        if (getLogger() != null) {
            b = b;
            if (getLogger().getLogLevel() == 0) {
                b = true;
            }
        }
        return b;
    }
    
    private static Logger getLogger() {
        if (z.AT == null) {
            z.AT = GoogleAnalytics.eE();
        }
        if (z.AT != null) {
            return z.AT.getLogger();
        }
        return null;
    }
}
