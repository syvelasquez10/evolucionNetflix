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

class Snackbar$4 implements SwipeDismissBehavior$OnDismissListener
{
    final /* synthetic */ Snackbar this$0;
    
    Snackbar$4(final Snackbar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onDismiss(final View view) {
        this.this$0.dismiss();
    }
    
    @Override
    public void onDragStateChanged(final int n) {
        switch (n) {
            default: {}
            case 1:
            case 2: {
                SnackbarManager.getInstance().cancelTimeout(this.this$0.mManagerCallback);
            }
            case 0: {
                SnackbarManager.getInstance().restoreTimeout(this.this$0.mManagerCallback);
            }
        }
    }
}
