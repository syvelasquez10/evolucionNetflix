// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;

class EdgeEffectCompat$EdgeEffectIcsImpl implements EdgeEffectCompat$EdgeEffectImpl
{
    @Override
    public boolean draw(final Object o, final Canvas canvas) {
        return EdgeEffectCompatIcs.draw(o, canvas);
    }
    
    @Override
    public void finish(final Object o) {
        EdgeEffectCompatIcs.finish(o);
    }
    
    @Override
    public boolean isFinished(final Object o) {
        return EdgeEffectCompatIcs.isFinished(o);
    }
    
    @Override
    public Object newEdgeEffect(final Context context) {
        return EdgeEffectCompatIcs.newEdgeEffect(context);
    }
    
    @Override
    public boolean onAbsorb(final Object o, final int n) {
        return EdgeEffectCompatIcs.onAbsorb(o, n);
    }
    
    @Override
    public boolean onPull(final Object o, final float n) {
        return EdgeEffectCompatIcs.onPull(o, n);
    }
    
    @Override
    public boolean onRelease(final Object o) {
        return EdgeEffectCompatIcs.onRelease(o);
    }
    
    @Override
    public void setSize(final Object o, final int n, final int n2) {
        EdgeEffectCompatIcs.setSize(o, n, n2);
    }
}
