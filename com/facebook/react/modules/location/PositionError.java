// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.location;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

public class PositionError
{
    public static int PERMISSION_DENIED;
    public static int POSITION_UNAVAILABLE;
    public static int TIMEOUT;
    
    static {
        PositionError.PERMISSION_DENIED = 1;
        PositionError.POSITION_UNAVAILABLE = 2;
        PositionError.TIMEOUT = 3;
    }
    
    public static WritableMap buildError(final int n, final String s) {
        final WritableMap map = Arguments.createMap();
        map.putInt("code", n);
        if (s != null) {
            map.putString("message", s);
        }
        return map;
    }
}
