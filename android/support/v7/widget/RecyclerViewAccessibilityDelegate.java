// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.View;
import android.support.v4.view.AccessibilityDelegateCompat;

public class RecyclerViewAccessibilityDelegate extends AccessibilityDelegateCompat
{
    final AccessibilityDelegateCompat mItemDelegate;
    final RecyclerView mRecyclerView;
    
    public RecyclerViewAccessibilityDelegate(final RecyclerView mRecyclerView) {
        this.mItemDelegate = new RecyclerViewAccessibilityDelegate$1(this);
        this.mRecyclerView = mRecyclerView;
    }
    
    AccessibilityDelegateCompat getItemDelegate() {
        return this.mItemDelegate;
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName((CharSequence)RecyclerView.class.getName());
        if (view instanceof RecyclerView) {
            final RecyclerView recyclerView = (RecyclerView)view;
            if (recyclerView.getLayoutManager() != null) {
                recyclerView.getLayoutManager().onInitializeAccessibilityEvent(accessibilityEvent);
            }
        }
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setClassName(RecyclerView.class.getName());
        if (this.mRecyclerView.getLayoutManager() != null) {
            this.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat);
        }
    }
    
    @Override
    public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
        return super.performAccessibilityAction(view, n, bundle) || (this.mRecyclerView.getLayoutManager() != null && this.mRecyclerView.getLayoutManager().performAccessibilityAction(n, bundle));
    }
}
