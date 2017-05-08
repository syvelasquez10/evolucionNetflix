// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;

class BottomSheetBehavior$1 implements Runnable
{
    final /* synthetic */ BottomSheetBehavior this$0;
    final /* synthetic */ View val$child;
    final /* synthetic */ int val$state;
    
    BottomSheetBehavior$1(final BottomSheetBehavior this$0, final View val$child, final int val$state) {
        this.this$0 = this$0;
        this.val$child = val$child;
        this.val$state = val$state;
    }
    
    @Override
    public void run() {
        this.this$0.startSettlingAnimation(this.val$child, this.val$state);
    }
}
