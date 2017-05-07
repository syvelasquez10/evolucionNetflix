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
import android.view.View;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;

class FloatingActionButtonEclairMr1$1 extends AnimationUtils$AnimationListenerAdapter
{
    final /* synthetic */ FloatingActionButtonEclairMr1 this$0;
    
    FloatingActionButtonEclairMr1$1(final FloatingActionButtonEclairMr1 this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationEnd(final Animation animation) {
        this.this$0.mIsHiding = false;
        this.this$0.mView.setVisibility(8);
    }
    
    @Override
    public void onAnimationStart(final Animation animation) {
        this.this$0.mIsHiding = true;
    }
}
