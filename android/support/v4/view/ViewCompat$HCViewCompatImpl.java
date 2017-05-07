// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Paint;
import android.view.View;

class ViewCompat$HCViewCompatImpl extends ViewCompat$GBViewCompatImpl
{
    @Override
    long getFrameTime() {
        return ViewCompatHC.getFrameTime();
    }
    
    @Override
    public int getMeasuredState(final View view) {
        return ViewCompatHC.getMeasuredState(view);
    }
    
    @Override
    public float getTranslationY(final View view) {
        return ViewCompatHC.getTranslationY(view);
    }
    
    @Override
    public void jumpDrawablesToCurrentState(final View view) {
        ViewCompatHC.jumpDrawablesToCurrentState(view);
    }
    
    @Override
    public int resolveSizeAndState(final int n, final int n2, final int n3) {
        return ViewCompatHC.resolveSizeAndState(n, n2, n3);
    }
    
    @Override
    public void setAlpha(final View view, final float n) {
        ViewCompatHC.setAlpha(view, n);
    }
    
    @Override
    public void setLayerType(final View view, final int n, final Paint paint) {
        ViewCompatHC.setLayerType(view, n, paint);
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
}
