// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.hardware.display;

import android.view.Display;
import android.content.Context;
import android.view.WindowManager;

class DisplayManagerCompat$LegacyImpl extends DisplayManagerCompat
{
    private final WindowManager mWindowManager;
    
    public DisplayManagerCompat$LegacyImpl(final Context context) {
        this.mWindowManager = (WindowManager)context.getSystemService("window");
    }
    
    @Override
    public Display getDisplay(final int n) {
        final Display defaultDisplay = this.mWindowManager.getDefaultDisplay();
        if (defaultDisplay.getDisplayId() == n) {
            return defaultDisplay;
        }
        return null;
    }
    
    @Override
    public Display[] getDisplays() {
        return new Display[] { this.mWindowManager.getDefaultDisplay() };
    }
    
    @Override
    public Display[] getDisplays(final String s) {
        if (s == null) {
            return this.getDisplays();
        }
        return new Display[0];
    }
}
