// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public final class bv$t implements bu
{
    private Integer a;
    
    public bv$t() {
        this.a = null;
        int orientation;
        if ((orientation = bv.b.getResources().getConfiguration().orientation) == 0) {
            final Display defaultDisplay = ((WindowManager)bv.b.getSystemService("window")).getDefaultDisplay();
            if (defaultDisplay.getWidth() == defaultDisplay.getHeight()) {
                orientation = 3;
            }
            else if (defaultDisplay.getWidth() > defaultDisplay.getHeight()) {
                orientation = 2;
            }
            else {
                orientation = 1;
            }
        }
        this.a = orientation;
    }
    
    @Override
    public final String a() {
        return "orientation";
    }
}
