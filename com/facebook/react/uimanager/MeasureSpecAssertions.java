// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.View$MeasureSpec;

public class MeasureSpecAssertions
{
    public static final void assertExplicitMeasureSpec(int mode, int mode2) {
        mode = View$MeasureSpec.getMode(mode);
        mode2 = View$MeasureSpec.getMode(mode2);
        if (mode == 0 || mode2 == 0) {
            throw new IllegalStateException("A catalyst view must have an explicit width and height given to it. This should normally happen as part of the standard catalyst UI framework.");
        }
    }
}
