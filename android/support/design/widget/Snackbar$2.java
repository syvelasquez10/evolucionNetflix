// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.widget.Button;
import android.text.TextUtils;
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
import android.view.View$OnClickListener;

class Snackbar$2 implements View$OnClickListener
{
    final /* synthetic */ Snackbar this$0;
    final /* synthetic */ View$OnClickListener val$listener;
    
    Snackbar$2(final Snackbar this$0, final View$OnClickListener val$listener) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
    }
    
    public void onClick(final View view) {
        this.val$listener.onClick(view);
        this.this$0.dispatchDismiss(1);
    }
}
