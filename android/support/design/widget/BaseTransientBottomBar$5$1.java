// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

class BaseTransientBottomBar$5$1 implements Runnable
{
    final /* synthetic */ BaseTransientBottomBar$5 this$1;
    
    BaseTransientBottomBar$5$1(final BaseTransientBottomBar$5 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.onViewHidden(3);
    }
}
