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
import android.support.v4.view.ViewCompat;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.appcompat.R$attr;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.ScrollerCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;

class ActionBarOverlayLayout$2 extends ViewPropertyAnimatorListenerAdapter
{
    final /* synthetic */ ActionBarOverlayLayout this$0;
    
    ActionBarOverlayLayout$2(final ActionBarOverlayLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationCancel(final View view) {
        this.this$0.mCurrentActionBarBottomAnimator = null;
        this.this$0.mAnimatingForFling = false;
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.this$0.mCurrentActionBarBottomAnimator = null;
        this.this$0.mAnimatingForFling = false;
    }
}
