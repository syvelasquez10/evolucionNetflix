// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.graphics.PorterDuff$Mode;

class ViewUtils
{
    static final ValueAnimatorCompat$Creator DEFAULT_ANIMATOR_CREATOR;
    
    static {
        DEFAULT_ANIMATOR_CREATOR = new ViewUtils$1();
    }
    
    static ValueAnimatorCompat createAnimator() {
        return ViewUtils.DEFAULT_ANIMATOR_CREATOR.createAnimator();
    }
    
    static boolean objectEquals(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    static PorterDuff$Mode parseTintMode(final int n, final PorterDuff$Mode porterDuff$Mode) {
        switch (n) {
            default: {
                return porterDuff$Mode;
            }
            case 3: {
                return PorterDuff$Mode.SRC_OVER;
            }
            case 5: {
                return PorterDuff$Mode.SRC_IN;
            }
            case 9: {
                return PorterDuff$Mode.SRC_ATOP;
            }
            case 14: {
                return PorterDuff$Mode.MULTIPLY;
            }
            case 15: {
                return PorterDuff$Mode.SCREEN;
            }
        }
    }
}
