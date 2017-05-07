// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.PorterDuff$Mode;
import android.view.animation.Animation$AnimationListener;
import android.support.design.R$anim;
import android.graphics.Rect;
import android.content.res.ColorStateList;
import android.view.animation.Animation;
import android.view.View;
import android.graphics.drawable.Drawable;

class FloatingActionButtonEclairMr1$ResetElevationAnimation extends FloatingActionButtonEclairMr1$BaseShadowAnimation
{
    final /* synthetic */ FloatingActionButtonEclairMr1 this$0;
    
    private FloatingActionButtonEclairMr1$ResetElevationAnimation(final FloatingActionButtonEclairMr1 this$0) {
        this.this$0 = this$0;
        super(this$0, null);
    }
    
    @Override
    protected float getTargetShadowSize() {
        return this.this$0.mElevation;
    }
}
