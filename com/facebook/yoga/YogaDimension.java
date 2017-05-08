// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaDimension
{
    HEIGHT(1), 
    WIDTH(0);
    
    private int mIntValue;
    
    private YogaDimension(final int mIntValue) {
        this.mIntValue = mIntValue;
    }
}
