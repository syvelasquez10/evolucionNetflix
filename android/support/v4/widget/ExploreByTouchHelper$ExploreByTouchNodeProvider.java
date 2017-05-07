// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.support.v4.view.ViewParentCompat;
import android.view.MotionEvent;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.view.ViewParent;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.View;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityManager;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;

class ExploreByTouchHelper$ExploreByTouchNodeProvider extends AccessibilityNodeProviderCompat
{
    final /* synthetic */ ExploreByTouchHelper this$0;
    
    private ExploreByTouchHelper$ExploreByTouchNodeProvider(final ExploreByTouchHelper this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(final int n) {
        return this.this$0.createNode(n);
    }
    
    @Override
    public boolean performAction(final int n, final int n2, final Bundle bundle) {
        return this.this$0.performAction(n, n2, bundle);
    }
}
