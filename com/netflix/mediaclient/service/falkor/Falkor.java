// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.Log;
import com.netflix.falkor.BranchNode;

public class Falkor
{
    public static final boolean ENABLE_VERBOSE_LOGGING;
    public static final BranchNode NULL_ROOT;
    
    static {
        if (Log.isLoggable(null, 2)) {}
        ENABLE_VERBOSE_LOGGING = false;
        NULL_ROOT = null;
    }
}
