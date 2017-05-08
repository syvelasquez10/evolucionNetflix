// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class BaseTransientBottomBar$3 implements SnackbarManager$Callback
{
    final /* synthetic */ BaseTransientBottomBar this$0;
    
    BaseTransientBottomBar$3(final BaseTransientBottomBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void dismiss(final int n) {
        BaseTransientBottomBar.sHandler.sendMessage(BaseTransientBottomBar.sHandler.obtainMessage(1, n, 0, (Object)this.this$0));
    }
    
    @Override
    public void show() {
        BaseTransientBottomBar.sHandler.sendMessage(BaseTransientBottomBar.sHandler.obtainMessage(0, (Object)this.this$0));
    }
}
