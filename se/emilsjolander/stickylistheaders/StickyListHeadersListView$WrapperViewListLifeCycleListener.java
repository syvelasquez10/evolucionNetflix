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
import android.view.View$OnClickListener;
import android.annotation.SuppressLint;
import android.view.ViewGroup$MarginLayoutParams;
import android.util.Log;
import android.view.View$MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.AbsListView$OnScrollListener;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.os.Build$VERSION;
import android.graphics.Canvas;

class StickyListHeadersListView$WrapperViewListLifeCycleListener implements WrapperViewList$LifeCycleListener
{
    final /* synthetic */ StickyListHeadersListView this$0;
    
    private StickyListHeadersListView$WrapperViewListLifeCycleListener(final StickyListHeadersListView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onDispatchDrawOccurred(final Canvas canvas) {
        if (Build$VERSION.SDK_INT < 8) {
            this.this$0.updateOrClearHeader(this.this$0.mList.getFixedFirstVisibleItem());
        }
        if (this.this$0.mHeader != null) {
            if (!this.this$0.mClippingToPadding) {
                StickyListHeadersListView.access$1300(this.this$0, canvas, this.this$0.mHeader, 0L);
                return;
            }
            canvas.save();
            canvas.clipRect(0, this.this$0.mPaddingTop, this.this$0.getRight(), this.this$0.getBottom());
            StickyListHeadersListView.access$1200(this.this$0, canvas, this.this$0.mHeader, 0L);
            canvas.restore();
        }
    }
}
