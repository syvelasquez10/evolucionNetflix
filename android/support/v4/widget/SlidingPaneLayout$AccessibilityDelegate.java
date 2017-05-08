// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.ViewGroup;
import android.view.ViewParent;
import android.support.v4.view.ViewCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.View;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.graphics.Rect;
import android.support.v4.view.AccessibilityDelegateCompat;

class SlidingPaneLayout$AccessibilityDelegate extends AccessibilityDelegateCompat
{
    private final Rect mTmpRect;
    final /* synthetic */ SlidingPaneLayout this$0;
    
    SlidingPaneLayout$AccessibilityDelegate(final SlidingPaneLayout this$0) {
        this.this$0 = this$0;
        this.mTmpRect = new Rect();
    }
    
    private void copyNodeInfoNoChildren(final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
        final Rect mTmpRect = this.mTmpRect;
        accessibilityNodeInfoCompat2.getBoundsInParent(mTmpRect);
        accessibilityNodeInfoCompat.setBoundsInParent(mTmpRect);
        accessibilityNodeInfoCompat2.getBoundsInScreen(mTmpRect);
        accessibilityNodeInfoCompat.setBoundsInScreen(mTmpRect);
        accessibilityNodeInfoCompat.setVisibleToUser(accessibilityNodeInfoCompat2.isVisibleToUser());
        accessibilityNodeInfoCompat.setPackageName(accessibilityNodeInfoCompat2.getPackageName());
        accessibilityNodeInfoCompat.setClassName(accessibilityNodeInfoCompat2.getClassName());
        accessibilityNodeInfoCompat.setContentDescription(accessibilityNodeInfoCompat2.getContentDescription());
        accessibilityNodeInfoCompat.setEnabled(accessibilityNodeInfoCompat2.isEnabled());
        accessibilityNodeInfoCompat.setClickable(accessibilityNodeInfoCompat2.isClickable());
        accessibilityNodeInfoCompat.setFocusable(accessibilityNodeInfoCompat2.isFocusable());
        accessibilityNodeInfoCompat.setFocused(accessibilityNodeInfoCompat2.isFocused());
        accessibilityNodeInfoCompat.setAccessibilityFocused(accessibilityNodeInfoCompat2.isAccessibilityFocused());
        accessibilityNodeInfoCompat.setSelected(accessibilityNodeInfoCompat2.isSelected());
        accessibilityNodeInfoCompat.setLongClickable(accessibilityNodeInfoCompat2.isLongClickable());
        accessibilityNodeInfoCompat.addAction(accessibilityNodeInfoCompat2.getActions());
        accessibilityNodeInfoCompat.setMovementGranularities(accessibilityNodeInfoCompat2.getMovementGranularities());
    }
    
    public boolean filter(final View view) {
        return this.this$0.isDimmed(view);
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName((CharSequence)SlidingPaneLayout.class.getName());
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(View child, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        final AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat);
        super.onInitializeAccessibilityNodeInfo(child, obtain);
        this.copyNodeInfoNoChildren(accessibilityNodeInfoCompat, obtain);
        obtain.recycle();
        accessibilityNodeInfoCompat.setClassName(SlidingPaneLayout.class.getName());
        accessibilityNodeInfoCompat.setSource(child);
        final ViewParent parentForAccessibility = ViewCompat.getParentForAccessibility(child);
        if (parentForAccessibility instanceof View) {
            accessibilityNodeInfoCompat.setParent((View)parentForAccessibility);
        }
        for (int childCount = this.this$0.getChildCount(), i = 0; i < childCount; ++i) {
            child = this.this$0.getChildAt(i);
            if (!this.filter(child) && child.getVisibility() == 0) {
                ViewCompat.setImportantForAccessibility(child, 1);
                accessibilityNodeInfoCompat.addChild(child);
            }
        }
    }
    
    @Override
    public boolean onRequestSendAccessibilityEvent(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
        return !this.filter(view) && super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }
}
