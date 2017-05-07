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
import android.view.View;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView$OnItemLongClickListener;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AbsListView$OnScrollListener;
import android.widget.GridView;

class StickyGridHeadersGridView$WindowRunnable
{
    private int mOriginalAttachCount;
    final /* synthetic */ StickyGridHeadersGridView this$0;
    
    private StickyGridHeadersGridView$WindowRunnable(final StickyGridHeadersGridView this$0) {
        this.this$0 = this$0;
    }
    
    public void rememberWindowAttachCount() {
        this.mOriginalAttachCount = StickyGridHeadersGridView.access$500(this.this$0);
    }
    
    public boolean sameWindow() {
        return this.this$0.hasWindowFocus() && StickyGridHeadersGridView.access$600(this.this$0) == this.mOriginalAttachCount;
    }
}
