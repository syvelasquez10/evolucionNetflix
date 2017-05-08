// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;
import android.view.View$OnClickListener;

class BottomSheetDialog$1 implements View$OnClickListener
{
    final /* synthetic */ BottomSheetDialog this$0;
    
    BottomSheetDialog$1(final BottomSheetDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (this.this$0.mCancelable && this.this$0.isShowing() && this.this$0.shouldWindowCloseOnTouchOutside()) {
            this.this$0.cancel();
        }
    }
}
