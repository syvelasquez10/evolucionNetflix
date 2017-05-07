// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Paint;
import android.animation.ValueAnimator;
import android.view.View;

class ViewCompatHC
{
    public static float getAlpha(final View view) {
        return view.getAlpha();
    }
    
    static long getFrameTime() {
        return ValueAnimator.getFrameDelay();
    }
    
    public static int getLayerType(final View view) {
        return view.getLayerType();
    }
    
    public static int getMeasuredHeightAndState(final View view) {
        return view.getMeasuredHeightAndState();
    }
    
    public static int getMeasuredState(final View view) {
        return view.getMeasuredState();
    }
    
    public static int getMeasuredWidthAndState(final View view) {
        return view.getMeasuredWidthAndState();
    }
    
    public static int resolveSizeAndState(final int n, final int n2, final int n3) {
        return View.resolveSizeAndState(n, n2, n3);
    }
    
    public static void setLayerType(final View view, final int n, final Paint paint) {
        view.setLayerType(n, paint);
    }
}
