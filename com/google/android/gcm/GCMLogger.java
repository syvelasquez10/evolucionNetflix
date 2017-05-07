// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gcm;

import android.util.Log;

class GCMLogger
{
    private final String mLogPrefix;
    private final String mTag;
    
    GCMLogger(final String mTag, final String mLogPrefix) {
        this.mTag = mTag;
        this.mLogPrefix = mLogPrefix;
    }
    
    protected void log(final int n, String format, final Object... array) {
        if (Log.isLoggable(this.mTag, n)) {
            format = String.format(format, array);
            Log.println(n, this.mTag, this.mLogPrefix + format);
        }
    }
}
