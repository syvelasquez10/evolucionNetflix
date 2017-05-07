// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

class AbsActionBarView$1 implements Runnable
{
    final /* synthetic */ AbsActionBarView this$0;
    
    AbsActionBarView$1(final AbsActionBarView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.showOverflowMenu();
    }
}
