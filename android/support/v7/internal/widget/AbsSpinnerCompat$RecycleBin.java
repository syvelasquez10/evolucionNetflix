// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;
import android.view.View$MeasureSpec;
import android.widget.Adapter;
import android.view.ViewGroup$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.database.DataSetObserver;
import android.widget.SpinnerAdapter;
import android.view.View;
import android.util.SparseArray;

class AbsSpinnerCompat$RecycleBin
{
    private final SparseArray<View> mScrapHeap;
    final /* synthetic */ AbsSpinnerCompat this$0;
    
    AbsSpinnerCompat$RecycleBin(final AbsSpinnerCompat this$0) {
        this.this$0 = this$0;
        this.mScrapHeap = (SparseArray<View>)new SparseArray();
    }
    
    void clear() {
        final SparseArray<View> mScrapHeap = this.mScrapHeap;
        for (int size = mScrapHeap.size(), i = 0; i < size; ++i) {
            final View view = (View)mScrapHeap.valueAt(i);
            if (view != null) {
                AbsSpinnerCompat.access$000(this.this$0, view, true);
            }
        }
        mScrapHeap.clear();
    }
    
    View get(final int n) {
        final View view = (View)this.mScrapHeap.get(n);
        if (view != null) {
            this.mScrapHeap.delete(n);
        }
        return view;
    }
    
    public void put(final int n, final View view) {
        this.mScrapHeap.put(n, (Object)view);
    }
}
