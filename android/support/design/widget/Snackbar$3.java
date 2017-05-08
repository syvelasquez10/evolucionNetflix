// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

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
