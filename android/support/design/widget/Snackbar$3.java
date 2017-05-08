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

class Snackbar$3 implements SnackbarManager$Callback
{
    final /* synthetic */ Snackbar this$0;
    
    Snackbar$3(final Snackbar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void dismiss(final int n) {
        Snackbar.sHandler.sendMessage(Snackbar.sHandler.obtainMessage(1, n, 0, (Object)this.this$0));
    }
    
    @Override
    public void show() {
        Snackbar.sHandler.sendMessage(Snackbar.sHandler.obtainMessage(0, (Object)this.this$0));
    }
}
