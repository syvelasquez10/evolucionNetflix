// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaWrap
{
    NO_WRAP(0), 
    WRAP(1);
    
    private int mIntValue;
    
    private YogaWrap(final int mIntValue) {
        this.mIntValue = mIntValue;
    }
    
    public int intValue() {
        return this.mIntValue;
    }
}
