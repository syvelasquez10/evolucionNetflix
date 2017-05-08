// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;

class BottomSheetDialog$2 extends BottomSheetBehavior$BottomSheetCallback
{
    final /* synthetic */ BottomSheetDialog this$0;
    
    BottomSheetDialog$2(final BottomSheetDialog this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onSlide(final View view, final float n) {
    }
    
    @Override
    public void onStateChanged(final View view, final int n) {
        if (n == 5) {
            this.this$0.dismiss();
        }
    }
}
