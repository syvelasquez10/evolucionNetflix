// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener;

public class AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerWrapper implements AccessibilityManager$AccessibilityStateChangeListener
{
    Object mListener;
    AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerBridge mListenerBridge;
    
    public AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerWrapper(final Object mListener, final AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerBridge mListenerBridge) {
        this.mListener = mListener;
        this.mListenerBridge = mListenerBridge;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerWrapper accessibilityManagerCompatIcs$AccessibilityStateChangeListenerWrapper = (AccessibilityManagerCompatIcs$AccessibilityStateChangeListenerWrapper)o;
            if (this.mListener != null) {
                return this.mListener.equals(accessibilityManagerCompatIcs$AccessibilityStateChangeListenerWrapper.mListener);
            }
            if (accessibilityManagerCompatIcs$AccessibilityStateChangeListenerWrapper.mListener != null) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        if (this.mListener == null) {
            return 0;
        }
        return this.mListener.hashCode();
    }
    
    public void onAccessibilityStateChanged(final boolean b) {
        this.mListenerBridge.onAccessibilityStateChanged(b);
    }
}
