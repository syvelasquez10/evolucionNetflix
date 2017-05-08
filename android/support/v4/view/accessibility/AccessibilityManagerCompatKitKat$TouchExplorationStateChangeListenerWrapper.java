// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager$TouchExplorationStateChangeListener;

public class AccessibilityManagerCompatKitKat$TouchExplorationStateChangeListenerWrapper implements AccessibilityManager$TouchExplorationStateChangeListener
{
    final Object mListener;
    final AccessibilityManagerCompatKitKat$TouchExplorationStateChangeListenerBridge mListenerBridge;
    
    public AccessibilityManagerCompatKitKat$TouchExplorationStateChangeListenerWrapper(final Object mListener, final AccessibilityManagerCompatKitKat$TouchExplorationStateChangeListenerBridge mListenerBridge) {
        this.mListener = mListener;
        this.mListenerBridge = mListenerBridge;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final AccessibilityManagerCompatKitKat$TouchExplorationStateChangeListenerWrapper accessibilityManagerCompatKitKat$TouchExplorationStateChangeListenerWrapper = (AccessibilityManagerCompatKitKat$TouchExplorationStateChangeListenerWrapper)o;
            if (this.mListener != null) {
                return this.mListener.equals(accessibilityManagerCompatKitKat$TouchExplorationStateChangeListenerWrapper.mListener);
            }
            if (accessibilityManagerCompatKitKat$TouchExplorationStateChangeListenerWrapper.mListener != null) {
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
    
    public void onTouchExplorationStateChanged(final boolean b) {
        this.mListenerBridge.onTouchExplorationStateChanged(b);
    }
}
