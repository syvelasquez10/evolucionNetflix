// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public final class bx$t implements bw
{
    private Integer a;
    
    public bx$t() {
        this.a = null;
        bx.b;
        int orientation;
        if ((orientation = bx.b.getResources().getConfiguration().orientation) == 0) {
            final Display defaultDisplay = ((WindowManager)bx.b.getSystemService("window")).getDefaultDisplay();
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
