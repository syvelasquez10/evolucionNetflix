// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;
import android.view.View$OnClickListener;

class Snackbar$1 implements View$OnClickListener
{
    final /* synthetic */ Snackbar this$0;
    final /* synthetic */ View$OnClickListener val$listener;
    
    Snackbar$1(final Snackbar this$0, final View$OnClickListener val$listener) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
    }
    
    public void onClick(final View view) {
        this.val$listener.onClick(view);
        this.this$0.dispatchDismiss(1);
    }
}
