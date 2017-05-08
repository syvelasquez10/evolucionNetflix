// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.animation.TypeEvaluator;

@TargetApi(14)
class RectEvaluator implements TypeEvaluator<Rect>
{
    private Rect mRect;
    
    public Rect evaluate(final float n, final Rect rect, final Rect rect2) {
        final int n2 = (int)((rect2.left - rect.left) * n) + rect.left;
        final int n3 = (int)((rect2.top - rect.top) * n) + rect.top;
        final int n4 = (int)((rect2.right - rect.right) * n) + rect.right;
        final int n5 = (int)((rect2.bottom - rect.bottom) * n) + rect.bottom;
        if (this.mRect == null) {
            return new Rect(n2, n3, n4, n5);
        }
        this.mRect.set(n2, n3, n4, n5);
        return this.mRect;
    }
}
