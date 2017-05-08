// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.storage;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

public class AsyncStorageErrorUtil
{
    static WritableMap getDBError(final String s) {
        return getError(s, "Database Error");
    }
    
    static WritableMap getError(final String s, final String s2) {
        final WritableMap map = Arguments.createMap();
        map.putString("message", s2);
        if (s != null) {
            map.putString("key", s);
        }
        return map;
    }
    
    static WritableMap getInvalidKeyError(final String s) {
        return getError(s, "Invalid key");
    }
    
    static WritableMap getInvalidValueError(final String s) {
        return getError(s, "Invalid Value");
    }
}
