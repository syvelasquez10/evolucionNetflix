// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.widget.ScrollView;
import android.view.accessibility.AccessibilityEvent;
import android.view.View;
import android.support.v4.view.AccessibilityDelegateCompat;

class NestedScrollView$AccessibilityDelegate extends AccessibilityDelegateCompat
{
    @Override
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        final NestedScrollView nestedScrollView = (NestedScrollView)view;
        accessibilityEvent.setClassName((CharSequence)ScrollView.class.getName());
        final AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(accessibilityEvent);
        record.setScrollable(nestedScrollView.getScrollRange() > 0);
        record.setScrollX(nestedScrollView.getScrollX());
        record.setScrollY(nestedScrollView.getScrollY());
        record.setMaxScrollX(nestedScrollView.getScrollX());
        record.setMaxScrollY(nestedScrollView.getScrollRange());
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        final NestedScrollView nestedScrollView = (NestedScrollView)view;
        accessibilityNodeInfoCompat.setClassName(ScrollView.class.getName());
        if (nestedScrollView.isEnabled()) {
            final int scrollRange = nestedScrollView.getScrollRange();
            if (scrollRange > 0) {
                accessibilityNodeInfoCompat.setScrollable(true);
                if (nestedScrollView.getScrollY() > 0) {
                    accessibilityNodeInfoCompat.addAction(8192);
                }
                if (nestedScrollView.getScrollY() < scrollRange) {
                    accessibilityNodeInfoCompat.addAction(4096);
                }
            }
        }
    }
    
    @Override
    public boolean performAccessibilityAction(final View view, int n, final Bundle bundle) {
        if (super.performAccessibilityAction(view, n, bundle)) {
            return true;
        }
        final NestedScrollView nestedScrollView = (NestedScrollView)view;
        if (!nestedScrollView.isEnabled()) {
            return false;
        }
        switch (n) {
            default: {
                return false;
            }
            case 4096: {
                n = Math.min(nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom() - nestedScrollView.getPaddingTop() + nestedScrollView.getScrollY(), nestedScrollView.getScrollRange());
                if (n != nestedScrollView.getScrollY()) {
                    nestedScrollView.smoothScrollTo(0, n);
                    return true;
                }
                return false;
            }
            case 8192: {
                n = nestedScrollView.getHeight();
                n = Math.max(nestedScrollView.getScrollY() - (n - nestedScrollView.getPaddingBottom() - nestedScrollView.getPaddingTop()), 0);
                if (n != nestedScrollView.getScrollY()) {
                    nestedScrollView.smoothScrollTo(0, n);
                    return true;
                }
                return false;
            }
        }
    }
}
