// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

class RecyclerView$1 implements Runnable
{
    final /* synthetic */ RecyclerView this$0;
    
    RecyclerView$1(final RecyclerView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (!this.this$0.mFirstLayoutComplete || this.this$0.isLayoutRequested()) {
            return;
        }
        if (!this.this$0.mIsAttached) {
            this.this$0.requestLayout();
            return;
        }
        if (this.this$0.mLayoutFrozen) {
            this.this$0.mLayoutRequestEaten = true;
            return;
        }
        this.this$0.consumePendingUpdateOperations();
    }
}
