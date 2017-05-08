// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaOverflow
{
    HIDDEN(1), 
    SCROLL(2), 
    VISIBLE(0);
    
    private int mIntValue;
    
    private YogaOverflow(final int mIntValue) {
        this.mIntValue = mIntValue;
    }
    
    public int intValue() {
        return this.mIntValue;
    }
}
