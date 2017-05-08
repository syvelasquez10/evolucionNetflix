// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class ReactMarker
{
    private static ReactMarker$MarkerListener sMarkerListener;
    
    static {
        ReactMarker.sMarkerListener = null;
    }
    
    @DoNotStrip
    public static void logMarker(final String s) {
        if (ReactMarker.sMarkerListener != null) {
            ReactMarker.sMarkerListener.logMarker(s);
        }
    }
}
