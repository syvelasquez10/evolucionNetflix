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

class StickyGridHeadersGridView$CheckForHeaderLongPress extends StickyGridHeadersGridView$WindowRunnable implements Runnable
{
    final /* synthetic */ StickyGridHeadersGridView this$0;
    
    private StickyGridHeadersGridView$CheckForHeaderLongPress(final StickyGridHeadersGridView this$0) {
        this.this$0 = this$0;
        super(this$0, null);
    }
    
    @Override
    public void run() {
        final View header = this.this$0.getHeaderAt(this.this$0.mMotionHeaderPosition);
        if (header != null) {
            final long access$400 = this.this$0.headerViewPositionToId(this.this$0.mMotionHeaderPosition);
            if (!this.sameWindow() || this.this$0.mDataChanged || !this.this$0.performHeaderLongPress(header, access$400)) {
                this.this$0.mTouchMode = 2;
                return;
            }
            this.this$0.mTouchMode = -2;
            this.this$0.setPressed(false);
            header.setPressed(false);
        }
    }
}
