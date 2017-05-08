// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.MotionEvent;

interface MotionEventCompat$MotionEventVersionImpl
{
    int findPointerIndex(final MotionEvent p0, final int p1);
    
    float getAxisValue(final MotionEvent p0, final int p1);
    
    int getPointerCount(final MotionEvent p0);
    
    int getPointerId(final MotionEvent p0, final int p1);
    
    int getSource(final MotionEvent p0);
    
    float getX(final MotionEvent p0, final int p1);
    
    float getY(final MotionEvent p0, final int p1);
}
