// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.widget.TextView;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.support.design.R$anim;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewCompat;
import android.os.Build$VERSION;
import android.support.design.R$layout;
import android.view.LayoutInflater;
import android.os.Handler$Callback;
import android.os.Looper;
import android.view.ViewGroup;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;

class Snackbar$8 extends ViewPropertyAnimatorListenerAdapter
{
    final /* synthetic */ Snackbar this$0;
    
    Snackbar$8(final Snackbar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.this$0.onViewHidden();
    }
    
    @Override
    public void onAnimationStart(final View view) {
        this.this$0.mView.animateChildrenOut(0, 180);
    }
}
