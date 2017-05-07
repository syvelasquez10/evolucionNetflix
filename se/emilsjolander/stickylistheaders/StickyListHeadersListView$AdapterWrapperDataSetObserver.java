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
import android.widget.AbsListView$OnScrollListener;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.database.DataSetObserver;

class StickyListHeadersListView$AdapterWrapperDataSetObserver extends DataSetObserver
{
    final /* synthetic */ StickyListHeadersListView this$0;
    
    private StickyListHeadersListView$AdapterWrapperDataSetObserver(final StickyListHeadersListView this$0) {
        this.this$0 = this$0;
    }
    
    public void onChanged() {
        this.this$0.clearHeader();
    }
    
    public void onInvalidated() {
        this.this$0.clearHeader();
    }
}
