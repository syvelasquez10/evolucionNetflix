// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.widget.ListAdapter;
import android.widget.Adapter;
import android.os.Handler;
import android.widget.AbsListView;
import android.os.Parcelable;
import android.widget.AdapterView;
import java.util.ArrayList;
import android.graphics.Canvas;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.view.View$MeasureSpec;
import android.view.MotionEvent$PointerCoords;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView$OnItemLongClickListener;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AbsListView$OnScrollListener;
import android.widget.GridView;
import android.view.View;

class StickyGridHeadersGridView$PerformHeaderClick extends StickyGridHeadersGridView$WindowRunnable implements Runnable
{
    int mClickMotionPosition;
    final /* synthetic */ StickyGridHeadersGridView this$0;
    
    private StickyGridHeadersGridView$PerformHeaderClick(final StickyGridHeadersGridView this$0) {
        this.this$0 = this$0;
        super(this$0, null);
    }
    
    @Override
    public void run() {
        if (!this.this$0.mDataChanged && this.this$0.mAdapter != null && this.this$0.mAdapter.getCount() > 0 && this.mClickMotionPosition != -1 && this.mClickMotionPosition < this.this$0.mAdapter.getCount() && this.sameWindow()) {
            final View header = this.this$0.getHeaderAt(this.mClickMotionPosition);
            if (header != null) {
                this.this$0.performHeaderClick(header, this.this$0.headerViewPositionToId(this.mClickMotionPosition));
            }
        }
    }
}
