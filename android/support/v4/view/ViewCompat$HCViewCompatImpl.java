// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Paint;
import android.graphics.Matrix;
import android.view.View;

class ViewCompat$HCViewCompatImpl extends ViewCompat$BaseViewCompatImpl
{
    @Override
    public int combineMeasuredStates(final int n, final int n2) {
        return ViewCompatHC.combineMeasuredStates(n, n2);
    }
    
    @Override
    public float getAlpha(final View view) {
        return ViewCompatHC.getAlpha(view);
    }
    
    @Override
    long getFrameTime() {
        return ViewCompatHC.getFrameTime();
    }
    
    @Override
    public int getLayerType(final View view) {
        return ViewCompatHC.getLayerType(view);
    }
    
    @Override
    public Matrix getMatrix(final View view) {
        return ViewCompatHC.getMatrix(view);
    }
    
    @Override
    public int getMeasuredHeightAndState(final View view) {
        return ViewCompatHC.getMeasuredHeightAndState(view);
    }
    
    @Override
    public int getMeasuredState(final View view) {
        return ViewCompatHC.getMeasuredState(view);
    }
    
    @Override
    public int getMeasuredWidthAndState(final View view) {
        return ViewCompatHC.getMeasuredWidthAndState(view);
    }
    
    @Override
    public float getPivotX(final View view) {
        return ViewCompatHC.getPivotX(view);
    }
    
    @Override
    public float getPivotY(final View view) {
        return ViewCompatHC.getPivotY(view);
    }
    
    @Override
    public float getRotation(final View view) {
        return ViewCompatHC.getRotation(view);
    }
    
    @Override
    public float getRotationX(final View view) {
        return ViewCompatHC.getRotationX(view);
    }
    
    @Override
    public float getRotationY(final View view) {
        return ViewCompatHC.getRotationY(view);
    }
    
    @Override
    public float getScaleX(final View view) {
        return ViewCompatHC.getScaleX(view);
    }
    
    @Override
    public float getScaleY(final View view) {
        return ViewCompatHC.getScaleY(view);
    }
    
    @Override
    public float getTranslationX(final View view) {
        return ViewCompatHC.getTranslationX(view);
    }
    
    @Override
    public float getTranslationY(final View view) {
        return ViewCompatHC.getTranslationY(view);
    }
    
    @Override
    public float getX(final View view) {
        return ViewCompatHC.getX(view);
    }
    
    @Override
    public float getY(final View view) {
        return ViewCompatHC.getY(view);
    }
    
    @Override
    public void jumpDrawablesToCurrentState(final View view) {
        ViewCompatHC.jumpDrawablesToCurrentState(view);
    }
    
    @Override
    public void offsetLeftAndRight(final View view, final int n) {
        ViewCompatHC.offsetLeftAndRight(view, n);
    }
    
    @Override
    public void offsetTopAndBottom(final View view, final int n) {
        ViewCompatHC.offsetTopAndBottom(view, n);
    }
    
    @Override
    public int resolveSizeAndState(final int n, final int n2, final int n3) {
        return ViewCompatHC.resolveSizeAndState(n, n2, n3);
    }
    
    @Override
    public void setActivated(final View view, final boolean b) {
        ViewCompatHC.setActivated(view, b);
    }
    
    @Override
    public void setAlpha(final View view, final float n) {
        ViewCompatHC.setAlpha(view, n);
    }
    
    @Override
    public void setLayerPaint(final View view, final Paint paint) {
        this.setLayerType(view, this.getLayerType(view), paint);
        view.invalidate();
    }
    
    @Override
    public void setLayerType(final View view, final int n, final Paint paint) {
        ViewCompatHC.setLayerType(view, n, paint);
    }
    
    @Override
    public void setPivotX(final View view, final float n) {
        ViewCompatHC.setPivotX(view, n);
    }
    
    @Override
    public void setPivotY(final View view, final float n) {
        ViewCompatHC.setPivotY(view, n);
    }
    
    @Override
    public void setRotation(final View view, final float n) {
        ViewCompatHC.setRotation(view, n);
    }
    
    @Override
    public void setRotationX(final View view, final float n) {
        ViewCompatHC.setRotationX(view, n);
    }
    
    @Override
    public void setRotationY(final View view, final float n) {
        ViewCompatHC.setRotationY(view, n);
    }
    
    @Override
    public void setSaveFromParentEnabled(final View view, final boolean b) {
        ViewCompatHC.setSaveFromParentEnabled(view, b);
    }
    
    @Override
    public void setScaleX(final View view, final float n) {
        ViewCompatHC.setScaleX(view, n);
    }
    
    @Override
    public void setScaleY(final View view, final float n) {
        ViewCompatHC.setScaleY(view, n);
    }
    
    @Override
    public void setTranslationX(final View view, final float n) {
        ViewCompatHC.setTranslationX(view, n);
    }
    
    @Override
    public void setTranslationY(final View view, final float n) {
        ViewCompatHC.setTranslationY(view, n);
    }
    
    @Override
    public void setX(final View view, final float n) {
        ViewCompatHC.setX(view, n);
    }
    
    @Override
    public void setY(final View view, final float n) {
        ViewCompatHC.setY(view, n);
    }
}
