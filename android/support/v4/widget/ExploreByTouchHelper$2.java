// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.util.SparseArrayCompat;

final class ExploreByTouchHelper$2 implements FocusStrategy$CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat>
{
    @Override
    public AccessibilityNodeInfoCompat get(final SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat, final int n) {
        return sparseArrayCompat.valueAt(n);
    }
    
    @Override
    public int size(final SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat) {
        return sparseArrayCompat.size();
    }
}
