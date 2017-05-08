// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaMeasureMode
{
    AT_MOST(2), 
    EXACTLY(1), 
    UNDEFINED(0);
    
    private int mIntValue;
    
    private YogaMeasureMode(final int mIntValue) {
        this.mIntValue = mIntValue;
    }
}
