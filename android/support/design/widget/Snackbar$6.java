// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;

class Snackbar$6 implements Snackbar$SnackbarLayout$OnLayoutChangeListener
{
    final /* synthetic */ Snackbar this$0;
    
    Snackbar$6(final Snackbar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onLayoutChange(final View view, final int n, final int n2, final int n3, final int n4) {
        this.this$0.mView.setOnLayoutChangeListener(null);
        if (this.this$0.shouldAnimate()) {
            this.this$0.animateViewIn();
            return;
        }
        this.this$0.onViewShown();
    }
}
