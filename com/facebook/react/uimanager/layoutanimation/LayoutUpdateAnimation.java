// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.layoutanimation;

import android.view.animation.Animation;
import android.view.View;

class LayoutUpdateAnimation extends AbstractLayoutAnimation
{
    @Override
    Animation createAnimationImpl(final View view, final int n, final int n2, final int n3, final int n4) {
        boolean b = false;
        boolean b2;
        if (view.getX() != n || view.getY() != n2) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (view.getWidth() != n3 || view.getHeight() != n4) {
            b = true;
        }
        if (!b2 && !b) {
            return null;
        }
        if (!b2 || !b) {}
        return new PositionAndSizeAnimation(view, n, n2, n3, n4);
    }
    
    @Override
    boolean isValid() {
        return this.mDurationMs > 0;
    }
}
