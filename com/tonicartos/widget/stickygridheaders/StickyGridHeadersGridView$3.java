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
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.view.View$MeasureSpec;
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

class StickyGridHeadersGridView$3 implements Runnable
{
    final /* synthetic */ StickyGridHeadersGridView this$0;
    final /* synthetic */ View val$header;
    final /* synthetic */ StickyGridHeadersGridView$PerformHeaderClick val$performHeaderClick;
    
    StickyGridHeadersGridView$3(final StickyGridHeadersGridView this$0, final View val$header, final StickyGridHeadersGridView$PerformHeaderClick val$performHeaderClick) {
        this.this$0 = this$0;
        this.val$header = val$header;
        this.val$performHeaderClick = val$performHeaderClick;
    }
    
    @Override
    public void run() {
        this.this$0.mMotionHeaderPosition = -1;
        this.this$0.mTouchModeReset = null;
        this.this$0.mTouchMode = -1;
        this.val$header.setPressed(false);
        this.this$0.setPressed(false);
        this.val$header.invalidate();
        this.this$0.invalidate(0, this.val$header.getTop(), this.this$0.getWidth(), this.val$header.getHeight());
        if (!this.this$0.mDataChanged) {
            this.val$performHeaderClick.run();
        }
    }
}
