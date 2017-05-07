// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text;

import android.os.Build$VERSION;

public class ICUCompat
{
    private static final ICUCompatImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (ICUCompatImpl)new ICUCompatImplIcs();
            return;
        }
        IMPL = (ICUCompatImpl)new ICUCompatImplBase();
    }
    
    public static String addLikelySubtags(final String s) {
        return ICUCompat.IMPL.addLikelySubtags(s);
    }
    
    public static String getScript(final String s) {
        return ICUCompat.IMPL.getScript(s);
    }
    
    interface ICUCompatImpl
    {
        String addLikelySubtags(final String p0);
        
        String getScript(final String p0);
    }
    
    static class ICUCompatImplBase implements ICUCompatImpl
    {
        @Override
        public String addLikelySubtags(final String s) {
            return s;
        }
        
        @Override
        public String getScript(final String s) {
            return null;
        }
    }
    
    static class ICUCompatImplIcs implements ICUCompatImpl
    {
        @Override
        public String addLikelySubtags(final String s) {
            return ICUCompatIcs.addLikelySubtags(s);
        }
        
        @Override
        public String getScript(final String s) {
            return ICUCompatIcs.getScript(s);
        }
    }
}
