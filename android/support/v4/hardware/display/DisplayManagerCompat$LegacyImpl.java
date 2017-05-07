// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.hardware.display;

import android.content.Context;
import android.view.WindowManager;

class DisplayManagerCompat$LegacyImpl extends DisplayManagerCompat
{
    private final WindowManager mWindowManager;
    
    public DisplayManagerCompat$LegacyImpl(final Context context) {
        this.mWindowManager = (WindowManager)context.getSystemService("window");
    }
}
