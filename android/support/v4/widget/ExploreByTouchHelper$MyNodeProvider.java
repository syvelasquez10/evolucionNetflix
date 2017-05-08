// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;

class ExploreByTouchHelper$MyNodeProvider extends AccessibilityNodeProviderCompat
{
    final /* synthetic */ ExploreByTouchHelper this$0;
    
    ExploreByTouchHelper$MyNodeProvider(final ExploreByTouchHelper this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(final int n) {
        return AccessibilityNodeInfoCompat.obtain(this.this$0.obtainAccessibilityNodeInfo(n));
    }
    
    @Override
    public boolean performAction(final int n, final int n2, final Bundle bundle) {
        return this.this$0.performAction(n, n2, bundle);
    }
}
