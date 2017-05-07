// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Paint;
import android.animation.ValueAnimator;
import android.view.View;

class ViewCompatHC
{
    public static int combineMeasuredStates(final int n, final int n2) {
        return View.combineMeasuredStates(n, n2);
    }
    
    public static float getAlpha(final View view) {
        return view.getAlpha();
    }
    
    static long getFrameTime() {
        return ValueAnimator.getFrameDelay();
    }
    
    public static int getLayerType(final View view) {
        return view.getLayerType();
    }
    
    public static int getMeasuredState(final View view) {
        return view.getMeasuredState();
    }
    
    public static float getTranslationX(final View view) {
        return view.getTranslationX();
    }
    
    public static float getTranslationY(final View view) {
        return view.getTranslationY();
    }
    
    public static void jumpDrawablesToCurrentState(final View view) {
        view.jumpDrawablesToCurrentState();
    }
    
    public static int resolveSizeAndState(final int n, final int n2, final int n3) {
        return View.resolveSizeAndState(n, n2, n3);
    }
    
    public static void setActivated(final View view, final boolean activated) {
        view.setActivated(activated);
    }
    
    public static void setAlpha(final View view, final float alpha) {
        view.setAlpha(alpha);
    }
    
    public static void setLayerType(final View view, final int n, final Paint paint) {
        view.setLayerType(n, paint);
    }
    
    public static void setSaveFromParentEnabled(final View view, final boolean saveFromParentEnabled) {
        view.setSaveFromParentEnabled(saveFromParentEnabled);
    }
    
    public static void setTranslationX(final View view, final float translationX) {
        view.setTranslationX(translationX);
    }
    
    public static void setTranslationY(final View view, final float translationY) {
        view.setTranslationY(translationY);
    }
}
