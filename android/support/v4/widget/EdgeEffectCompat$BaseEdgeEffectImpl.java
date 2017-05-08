// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;

class EdgeEffectCompat$BaseEdgeEffectImpl implements EdgeEffectCompat$EdgeEffectImpl
{
    @Override
    public boolean draw(final Object o, final Canvas canvas) {
        return false;
    }
    
    @Override
    public void finish(final Object o) {
    }
    
    @Override
    public boolean isFinished(final Object o) {
        return true;
    }
    
    @Override
    public Object newEdgeEffect(final Context context) {
        return null;
    }
    
    @Override
    public boolean onAbsorb(final Object o, final int n) {
        return false;
    }
    
    @Override
    public boolean onPull(final Object o, final float n) {
        return false;
    }
    
    @Override
    public boolean onPull(final Object o, final float n, final float n2) {
        return false;
    }
    
    @Override
    public boolean onRelease(final Object o) {
        return false;
    }
    
    @Override
    public void setSize(final Object o, final int n, final int n2) {
    }
}
