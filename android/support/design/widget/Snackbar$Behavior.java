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
import android.view.MotionEvent;

final class Snackbar$Behavior extends SwipeDismissBehavior<Snackbar$SnackbarLayout>
{
    final /* synthetic */ Snackbar this$0;
    
    Snackbar$Behavior(final Snackbar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean onInterceptTouchEvent(final CoordinatorLayout coordinatorLayout, final Snackbar$SnackbarLayout snackbar$SnackbarLayout, final MotionEvent motionEvent) {
        if (coordinatorLayout.isPointInChildBounds((View)snackbar$SnackbarLayout, (int)motionEvent.getX(), (int)motionEvent.getY())) {
            switch (motionEvent.getActionMasked()) {
                case 0: {
                    SnackbarManager.getInstance().cancelTimeout(this.this$0.mManagerCallback);
                    break;
                }
                case 1:
                case 3: {
                    SnackbarManager.getInstance().restoreTimeout(this.this$0.mManagerCallback);
                    break;
                }
            }
        }
        return super.onInterceptTouchEvent(coordinatorLayout, snackbar$SnackbarLayout, motionEvent);
    }
}
