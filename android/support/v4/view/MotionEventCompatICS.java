// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.MotionEvent;
import android.annotation.TargetApi;

@TargetApi(14)
class MotionEventCompatICS
{
    public static int getButtonState(final MotionEvent motionEvent) {
        return motionEvent.getButtonState();
    }
}
