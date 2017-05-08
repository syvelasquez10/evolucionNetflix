// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class Snackbar$5$1 implements Runnable
{
    final /* synthetic */ Snackbar$5 this$1;
    
    Snackbar$5$1(final Snackbar$5 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.onViewHidden(3);
    }
}
