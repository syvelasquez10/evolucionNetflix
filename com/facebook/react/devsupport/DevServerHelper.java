// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.devsupport;

public class DevServerHelper
{
    private static final String SOURCE_MAP_URL_FORMAT;
    
    static {
        SOURCE_MAP_URL_FORMAT = "http://%s/%s.bundle?platform=android&dev=%s&hot=%s&minify=%s".replaceFirst("\\.bundle", ".map");
    }
}
