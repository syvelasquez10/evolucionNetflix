// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.graphics.Rect;
import android.view.View;
import android.os.Build$VERSION;

public class AccessibilityNodeInfoCompat$CollectionItemInfoCompat
{
    private final Object mInfo;
    
    private AccessibilityNodeInfoCompat$CollectionItemInfoCompat(final Object mInfo) {
        this.mInfo = mInfo;
    }
    
    public static AccessibilityNodeInfoCompat$CollectionItemInfoCompat obtain(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
        return new AccessibilityNodeInfoCompat$CollectionItemInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionItemInfo(n, n2, n3, n4, b, b2));
    }
}
