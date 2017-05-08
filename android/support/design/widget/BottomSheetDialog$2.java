// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.support.v4.view.AccessibilityDelegateCompat;

class BottomSheetDialog$2 extends AccessibilityDelegateCompat
{
    final /* synthetic */ BottomSheetDialog this$0;
    
    BottomSheetDialog$2(final BottomSheetDialog this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        if (this.this$0.mCancelable) {
            accessibilityNodeInfoCompat.addAction(1048576);
            accessibilityNodeInfoCompat.setDismissable(true);
            return;
        }
        accessibilityNodeInfoCompat.setDismissable(false);
    }
    
    @Override
    public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
        if (n == 1048576 && this.this$0.mCancelable) {
            this.this$0.cancel();
            return true;
        }
        return super.performAccessibilityAction(view, n, bundle);
    }
}
