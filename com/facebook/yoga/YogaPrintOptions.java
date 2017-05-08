// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaPrintOptions
{
    CHILDREN(4), 
    LAYOUT(1), 
    STYLE(2);
    
    private int mIntValue;
    
    private YogaPrintOptions(final int mIntValue) {
        this.mIntValue = mIntValue;
    }
}
