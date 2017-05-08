// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.widget.Button;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.support.design.R$anim;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.os.Build$VERSION;
import android.support.design.R$layout;
import android.view.LayoutInflater;
import android.os.Handler$Callback;
import android.os.Looper;
import android.view.ViewGroup;
import android.content.Context;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class Snackbar$7 implements Animation$AnimationListener
{
    final /* synthetic */ Snackbar this$0;
    
    Snackbar$7(final Snackbar this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animation animation) {
        if (this.this$0.mCallback != null) {
            this.this$0.mCallback.onShown(this.this$0);
        }
        SnackbarManager.getInstance().onShown(this.this$0.mManagerCallback);
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}
