// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.Rect;
import java.util.Comparator;

class FocusStrategy$SequentialComparator<T> implements Comparator<T>
{
    private final FocusStrategy$BoundsAdapter<T> mAdapter;
    private final boolean mIsLayoutRtl;
    private final Rect mTemp1;
    private final Rect mTemp2;
    
    FocusStrategy$SequentialComparator(final boolean mIsLayoutRtl, final FocusStrategy$BoundsAdapter<T> mAdapter) {
        this.mTemp1 = new Rect();
        this.mTemp2 = new Rect();
        this.mIsLayoutRtl = mIsLayoutRtl;
        this.mAdapter = mAdapter;
    }
    
    @Override
    public int compare(final T t, final T t2) {
        final boolean b = true;
        int n = 1;
        final Rect mTemp1 = this.mTemp1;
        final Rect mTemp2 = this.mTemp2;
        this.mAdapter.obtainBounds(t, mTemp1);
        this.mAdapter.obtainBounds(t2, mTemp2);
        if (mTemp1.top >= mTemp2.top) {
            if (mTemp1.top > mTemp2.top) {
                return 1;
            }
            if (mTemp1.left < mTemp2.left) {
                if (!this.mIsLayoutRtl) {
                    n = -1;
                }
                return n;
            }
            if (mTemp1.left > mTemp2.left) {
                if (!this.mIsLayoutRtl) {
                    return 1;
                }
            }
            else if (mTemp1.bottom >= mTemp2.bottom) {
                if (mTemp1.bottom > mTemp2.bottom) {
                    return 1;
                }
                if (mTemp1.right < mTemp2.right) {
                    int n2;
                    if (this.mIsLayoutRtl) {
                        n2 = (b ? 1 : 0);
                    }
                    else {
                        n2 = -1;
                    }
                    return n2;
                }
                if (mTemp1.right <= mTemp2.right) {
                    return 0;
                }
                if (!this.mIsLayoutRtl) {
                    return 1;
                }
            }
        }
        return -1;
    }
}
