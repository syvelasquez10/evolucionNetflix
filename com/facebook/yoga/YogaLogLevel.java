// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaLogLevel
{
    DEBUG(3), 
    ERROR(0), 
    INFO(2), 
    VERBOSE(4), 
    WARN(1);
    
    private int mIntValue;
    
    private YogaLogLevel(final int mIntValue) {
        this.mIntValue = mIntValue;
    }
}
