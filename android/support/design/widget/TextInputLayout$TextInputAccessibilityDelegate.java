// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.text.TextUtils;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.View;
import android.support.v4.view.AccessibilityDelegateCompat;

class TextInputLayout$TextInputAccessibilityDelegate extends AccessibilityDelegateCompat
{
    final /* synthetic */ TextInputLayout this$0;
    
    TextInputLayout$TextInputAccessibilityDelegate(final TextInputLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName((CharSequence)TextInputLayout.class.getSimpleName());
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setClassName(TextInputLayout.class.getSimpleName());
        final CharSequence text = this.this$0.mCollapsingTextHelper.getText();
        if (!TextUtils.isEmpty(text)) {
            accessibilityNodeInfoCompat.setText(text);
        }
        if (this.this$0.mEditText != null) {
            accessibilityNodeInfoCompat.setLabelFor((View)this.this$0.mEditText);
        }
        CharSequence text2;
        if (this.this$0.mErrorView != null) {
            text2 = this.this$0.mErrorView.getText();
        }
        else {
            text2 = null;
        }
        if (!TextUtils.isEmpty(text2)) {
            accessibilityNodeInfoCompat.setContentInvalid(true);
            accessibilityNodeInfoCompat.setError(text2);
        }
    }
    
    @Override
    public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(view, accessibilityEvent);
        final CharSequence text = this.this$0.mCollapsingTextHelper.getText();
        if (!TextUtils.isEmpty(text)) {
            accessibilityEvent.getText().add(text);
        }
    }
}
