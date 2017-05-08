// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

class StaggeredGridLayoutManager$1 implements Runnable
{
    final /* synthetic */ StaggeredGridLayoutManager this$0;
    
    StaggeredGridLayoutManager$1(final StaggeredGridLayoutManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.checkForGaps();
    }
}
