// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.MotionEvent;
import android.os.Build$VERSION;

public class MotionEventCompat
{
    public static final int ACTION_HOVER_ENTER = 9;
    public static final int ACTION_HOVER_EXIT = 10;
    public static final int ACTION_HOVER_MOVE = 7;
    public static final int ACTION_MASK = 255;
    public static final int ACTION_POINTER_DOWN = 5;
    public static final int ACTION_POINTER_INDEX_MASK = 65280;
    public static final int ACTION_POINTER_INDEX_SHIFT = 8;
    public static final int ACTION_POINTER_UP = 6;
    public static final int ACTION_SCROLL = 8;
    static final MotionEventVersionImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 5) {
            IMPL = (MotionEventVersionImpl)new EclairMotionEventVersionImpl();
            return;
        }
        IMPL = (MotionEventVersionImpl)new BaseMotionEventVersionImpl();
    }
    
    public static int findPointerIndex(final MotionEvent motionEvent, final int n) {
        return MotionEventCompat.IMPL.findPointerIndex(motionEvent, n);
    }
    
    public static int getActionIndex(final MotionEvent motionEvent) {
        return (motionEvent.getAction() & 0xFF00) >> 8;
    }
    
    public static int getActionMasked(final MotionEvent motionEvent) {
        return motionEvent.getAction() & 0xFF;
    }
    
    public static int getPointerCount(final MotionEvent motionEvent) {
        return MotionEventCompat.IMPL.getPointerCount(motionEvent);
    }
    
    public static int getPointerId(final MotionEvent motionEvent, final int n) {
        return MotionEventCompat.IMPL.getPointerId(motionEvent, n);
    }
    
    public static float getX(final MotionEvent motionEvent, final int n) {
        return MotionEventCompat.IMPL.getX(motionEvent, n);
    }
    
    public static float getY(final MotionEvent motionEvent, final int n) {
        return MotionEventCompat.IMPL.getY(motionEvent, n);
    }
    
    static class BaseMotionEventVersionImpl implements MotionEventVersionImpl
    {
        @Override
        public int findPointerIndex(final MotionEvent motionEvent, final int n) {
            if (n == 0) {
                return 0;
            }
            return -1;
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
    
    static class EclairMotionEventVersionImpl implements MotionEventVersionImpl
    {
        @Override
        public int findPointerIndex(final MotionEvent motionEvent, final int n) {
            return MotionEventCompatEclair.findPointerIndex(motionEvent, n);
        }
        
        @Override
        public int getPointerCount(final MotionEvent motionEvent) {
            return MotionEventCompatEclair.getPointerCount(motionEvent);
        }
        
        @Override
        public int getPointerId(final MotionEvent motionEvent, final int n) {
            return MotionEventCompatEclair.getPointerId(motionEvent, n);
        }
        
        @Override
        public float getX(final MotionEvent motionEvent, final int n) {
            return MotionEventCompatEclair.getX(motionEvent, n);
        }
        
        @Override
        public float getY(final MotionEvent motionEvent, final int n) {
            return MotionEventCompatEclair.getY(motionEvent, n);
        }
    }
    
    interface MotionEventVersionImpl
    {
        int findPointerIndex(final MotionEvent p0, final int p1);
        
        int getPointerCount(final MotionEvent p0);
        
        int getPointerId(final MotionEvent p0, final int p1);
        
        float getX(final MotionEvent p0, final int p1);
        
        float getY(final MotionEvent p0, final int p1);
    }
}
