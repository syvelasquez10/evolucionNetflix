// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.view.View;
import android.os.Build$VERSION;

public class AccessibilityNodeInfoCompat$CollectionInfoCompat
{
    final Object mInfo;
    
    private AccessibilityNodeInfoCompat$CollectionInfoCompat(final Object mInfo) {
        this.mInfo = mInfo;
    }
    
    public static AccessibilityNodeInfoCompat$CollectionInfoCompat obtain(final int n, final int n2, final boolean b, final int n3) {
        return new AccessibilityNodeInfoCompat$CollectionInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionInfo(n, n2, b, n3));
    }
}
