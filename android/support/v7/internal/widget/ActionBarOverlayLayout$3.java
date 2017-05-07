// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.support.v7.internal.app.WindowCallback;
import android.support.v7.internal.view.menu.y;
import android.view.Menu;
import android.support.v7.internal.VersionUtils;
import android.support.v7.appcompat.R$id;
import android.os.Build$VERSION;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.appcompat.R$attr;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.ScrollerCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.view.View;
import android.support.v4.view.ViewCompat;

class ActionBarOverlayLayout$3 implements Runnable
{
    final /* synthetic */ ActionBarOverlayLayout this$0;
    
    ActionBarOverlayLayout$3(final ActionBarOverlayLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.haltActionBarHideOffsetAnimations();
        this.this$0.mCurrentActionBarTopAnimator = ViewCompat.animate((View)this.this$0.mActionBarTop).translationY(0.0f).setListener(this.this$0.mTopAnimatorListener);
        if (this.this$0.mActionBarBottom != null && this.this$0.mActionBarBottom.getVisibility() != 8) {
            this.this$0.mCurrentActionBarBottomAnimator = ViewCompat.animate((View)this.this$0.mActionBarBottom).translationY(0.0f).setListener(this.this$0.mBottomAnimatorListener);
        }
    }
}
