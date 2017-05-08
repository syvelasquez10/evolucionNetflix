// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Rect;
import android.view.View;

class ViewCompat$JbMr2ViewCompatImpl extends ViewCompat$JbMr1ViewCompatImpl
{
    @Override
    public Rect getClipBounds(final View view) {
        return ViewCompatJellybeanMr2.getClipBounds(view);
    }
    
    @Override
    public boolean isInLayout(final View view) {
        return ViewCompatJellybeanMr2.isInLayout(view);
    }
    
    @Override
    public void setClipBounds(final View view, final Rect rect) {
        ViewCompatJellybeanMr2.setClipBounds(view, rect);
    }
}
