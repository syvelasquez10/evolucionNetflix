// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;

class Snackbar$7 extends ViewPropertyAnimatorListenerAdapter
{
    final /* synthetic */ Snackbar this$0;
    
    Snackbar$7(final Snackbar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.this$0.onViewShown();
    }
    
    @Override
    public void onAnimationStart(final View view) {
        this.this$0.mView.animateChildrenIn(70, 180);
    }
}
