// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Build$VERSION;

class ViewUtils
{
    static final ValueAnimatorCompat$Creator DEFAULT_ANIMATOR_CREATOR;
    private static final ViewUtils$ViewUtilsImpl IMPL;
    
    static {
        DEFAULT_ANIMATOR_CREATOR = new ViewUtils$1();
        if (Build$VERSION.SDK_INT >= 21) {
            IMPL = new ViewUtils$ViewUtilsImplLollipop(null);
            return;
        }
        IMPL = new ViewUtils$ViewUtilsImplBase(null);
    }
    
    static ValueAnimatorCompat createAnimator() {
        return ViewUtils.DEFAULT_ANIMATOR_CREATOR.createAnimator();
    }
}
