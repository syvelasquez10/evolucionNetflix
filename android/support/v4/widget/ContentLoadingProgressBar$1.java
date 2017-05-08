// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

class ContentLoadingProgressBar$1 implements Runnable
{
    final /* synthetic */ ContentLoadingProgressBar this$0;
    
    ContentLoadingProgressBar$1(final ContentLoadingProgressBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mPostedHide = false;
        this.this$0.mStartTime = -1L;
        this.this$0.setVisibility(8);
    }
}
