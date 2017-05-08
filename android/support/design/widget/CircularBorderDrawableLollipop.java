// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.graphics.Outline;
import android.annotation.TargetApi;

@TargetApi(21)
class CircularBorderDrawableLollipop extends CircularBorderDrawable
{
    public void getOutline(final Outline outline) {
        this.copyBounds(this.mRect);
        outline.setOval(this.mRect);
    }
}
