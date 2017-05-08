// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.MotionEvent;

class MotionEventCompat$BaseMotionEventVersionImpl implements MotionEventCompat$MotionEventVersionImpl
{
    @Override
    public int findPointerIndex(final MotionEvent motionEvent, final int n) {
        if (n == 0) {
            return 0;
        }
        return -1;
    }
    
    @Override
    public float getAxisValue(final MotionEvent motionEvent, final int n) {
        return 0.0f;
    }
    
    @Override
    public int getPointerCount(final MotionEvent motionEvent) {
        return 1;
    }
    
    @Override
    public int getPointerId(final MotionEvent motionEvent, final int n) {
        if (n == 0) {
            return 0;
        }
        throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
    }
    
    @Override
    public int getSource(final MotionEvent motionEvent) {
        return 0;
    }
    
    @Override
    public float getX(final MotionEvent motionEvent, final int n) {
        if (n == 0) {
            return motionEvent.getX();
        }
        throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
    }
    
    @Override
    public float getY(final MotionEvent motionEvent, final int n) {
        if (n == 0) {
            return motionEvent.getY();
        }
        throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
    }
}
