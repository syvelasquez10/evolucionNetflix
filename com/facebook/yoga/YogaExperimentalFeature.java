// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaExperimentalFeature
{
    ROUNDING(0), 
    WEB_FLEX_BASIS(1);
    
    private int mIntValue;
    
    private YogaExperimentalFeature(final int mIntValue) {
        this.mIntValue = mIntValue;
    }
}
