// 
// Decompiled by Procyon v0.5.30
// 

package se.emilsjolander.stickylistheaders;

import android.widget.AbsListView$RecyclerListener;
import android.view.View$OnTouchListener;
import android.view.View$OnCreateContextMenuListener;
import android.widget.SectionIndexer;
import android.database.DataSetObserver;
import android.widget.ListAdapter;
import android.view.View$BaseSavedState;
import android.os.Parcelable;
import android.widget.ListView;
import android.annotation.TargetApi;
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
import android.widget.AbsListView$OnScrollListener;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.view.View;
import android.view.View$OnClickListener;

class StickyListHeadersListView$1 implements View$OnClickListener
{
    final /* synthetic */ StickyListHeadersListView this$0;
    
    StickyListHeadersListView$1(final StickyListHeadersListView this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (this.this$0.mOnHeaderClickListener != null) {
            this.this$0.mOnHeaderClickListener.onHeaderClick(this.this$0, this.this$0.mHeader, this.this$0.mHeaderPosition, this.this$0.mHeaderId, true);
        }
    }
}
