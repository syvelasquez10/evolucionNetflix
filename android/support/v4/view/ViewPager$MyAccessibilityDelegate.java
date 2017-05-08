// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.View;

class ViewPager$MyAccessibilityDelegate extends AccessibilityDelegateCompat
{
    final /* synthetic */ ViewPager this$0;
    
    ViewPager$MyAccessibilityDelegate(final ViewPager this$0) {
        this.this$0 = this$0;
    }
    
    private boolean canScroll() {
        return this.this$0.mAdapter != null && this.this$0.mAdapter.getCount() > 1;
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName((CharSequence)ViewPager.class.getName());
        final AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(accessibilityEvent);
        record.setScrollable(this.canScroll());
        if (accessibilityEvent.getEventType() == 4096 && this.this$0.mAdapter != null) {
            record.setItemCount(this.this$0.mAdapter.getCount());
            record.setFromIndex(this.this$0.mCurItem);
            record.setToIndex(this.this$0.mCurItem);
        }
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
        accessibilityNodeInfoCompat.setScrollable(this.canScroll());
        if (this.this$0.canScrollHorizontally(1)) {
            accessibilityNodeInfoCompat.addAction(4096);
        }
        if (this.this$0.canScrollHorizontally(-1)) {
            accessibilityNodeInfoCompat.addAction(8192);
        }
    }
    
    @Override
    public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
        if (super.performAccessibilityAction(view, n, bundle)) {
            return true;
        }
        switch (n) {
            default: {
                return false;
            }
            case 4096: {
                if (this.this$0.canScrollHorizontally(1)) {
                    this.this$0.setCurrentItem(this.this$0.mCurItem + 1);
                    return true;
                }
                return false;
            }
            case 8192: {
                if (this.this$0.canScrollHorizontally(-1)) {
                    this.this$0.setCurrentItem(this.this$0.mCurItem - 1);
                    return true;
                }
                return false;
            }
        }
    }
}
