// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge;

import org.json.JSONObject;

public interface NrdpObject
{
    public static final int NEED_FURTHER_HANDLE = 0;
    public static final int NEED_PUBLISH = -1;
    public static final int NO_MORE_HANDLE = 1;
    
    String getName();
    
    String getPath();
    
    int processUpdate(final JSONObject p0);
}
