// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import java.util.List;
import android.view.accessibility.AccessibilityManager;
import android.os.Build$VERSION;

public abstract class AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat
{
    final Object mListener;
    
    public AccessibilityManagerCompat$AccessibilityStateChangeListenerCompat() {
        this.mListener = AccessibilityManagerCompat.IMPL.newAccessiblityStateChangeListener(this);
    }
    
    public abstract void onAccessibilityStateChanged(final boolean p0);
}
