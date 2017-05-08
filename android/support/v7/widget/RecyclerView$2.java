// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

class RecyclerView$2 implements Runnable
{
    final /* synthetic */ RecyclerView this$0;
    
    RecyclerView$2(final RecyclerView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mItemAnimator != null) {
            this.this$0.mItemAnimator.runPendingAnimations();
        }
        this.this$0.mPostedAnimatorRunner = false;
    }
}
