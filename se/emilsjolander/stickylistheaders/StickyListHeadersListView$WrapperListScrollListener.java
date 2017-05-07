// 
// Decompiled by Procyon v0.5.30
// 

package se.emilsjolander.stickylistheaders;

import android.widget.AbsListView$RecyclerListener;
import android.view.View$OnTouchListener;
import android.widget.AdapterView$OnItemLongClickListener;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View$OnCreateContextMenuListener;
import android.widget.AbsListView$MultiChoiceModeListener;
import android.widget.SectionIndexer;
import android.database.DataSetObserver;
import android.widget.ListAdapter;
import android.view.View$BaseSavedState;
import android.os.Parcelable;
import android.widget.ListView;
import android.util.SparseBooleanArray;
import android.annotation.TargetApi;
import android.view.View$OnClickListener;
import android.annotation.SuppressLint;
import android.view.ViewGroup$MarginLayoutParams;
import android.util.Log;
import android.view.View$MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.graphics.Canvas;
import android.os.Build$VERSION;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.AbsListView;
import android.widget.AbsListView$OnScrollListener;

class StickyListHeadersListView$WrapperListScrollListener implements AbsListView$OnScrollListener
{
    final /* synthetic */ StickyListHeadersListView this$0;
    
    private StickyListHeadersListView$WrapperListScrollListener(final StickyListHeadersListView this$0) {
        this.this$0 = this$0;
    }
    
    public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
        if (this.this$0.mOnScrollListenerDelegate != null) {
            this.this$0.mOnScrollListenerDelegate.onScroll(absListView, n, n2, n3);
        }
        this.this$0.updateOrClearHeader(this.this$0.mList.getFixedFirstVisibleItem());
    }
    
    public void onScrollStateChanged(final AbsListView absListView, final int n) {
        if (this.this$0.mOnScrollListenerDelegate != null) {
            this.this$0.mOnScrollListenerDelegate.onScrollStateChanged(absListView, n);
        }
    }
}
