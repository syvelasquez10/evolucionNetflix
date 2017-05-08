// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.MotionEvent;

class MotionEventCompat$GingerbreadMotionEventVersionImpl extends MotionEventCompat$EclairMotionEventVersionImpl
{
    @Override
    public int getSource(final MotionEvent motionEvent) {
        return MotionEventCompatGingerbread.getSource(motionEvent);
    }
}
