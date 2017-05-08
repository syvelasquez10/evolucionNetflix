// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.animation;

import android.view.View;

class GingerbreadAnimatorCompatProvider implements AnimatorProvider
{
    @Override
    public void clearInterpolator(final View view) {
    }
    
    @Override
    public ValueAnimatorCompat emptyValueAnimator() {
        return new GingerbreadAnimatorCompatProvider$GingerbreadFloatValueAnimator();
    }
}
