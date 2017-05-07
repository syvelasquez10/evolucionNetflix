// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

public class aa
{
    private static GoogleAnalytics vs;
    
    public static boolean cT() {
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
        if (aa.vs == null) {
            aa.vs = GoogleAnalytics.cM();
        }
        if (aa.vs != null) {
            return aa.vs.getLogger();
        }
        return null;
    }
    
    public static void w(final String s) {
        final Logger logger = getLogger();
        if (logger != null) {
            logger.error(s);
        }
    }
    
    public static void x(final String s) {
        final Logger logger = getLogger();
        if (logger != null) {
            logger.info(s);
        }
    }
    
    public static void y(final String s) {
        final Logger logger = getLogger();
        if (logger != null) {
            logger.verbose(s);
        }
    }
    
    public static void z(final String s) {
        final Logger logger = getLogger();
        if (logger != null) {
            logger.warn(s);
        }
    }
}
