// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;

class Snackbar$5 implements Snackbar$SnackbarLayout$OnAttachStateChangeListener
{
    final /* synthetic */ Snackbar this$0;
    
    Snackbar$5(final Snackbar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onViewAttachedToWindow(final View view) {
    }
    
    @Override
    public void onViewDetachedFromWindow(final View view) {
        if (this.this$0.isShownOrQueued()) {
            Snackbar.sHandler.post((Runnable)new Snackbar$5$1(this));
        }
    }
}
