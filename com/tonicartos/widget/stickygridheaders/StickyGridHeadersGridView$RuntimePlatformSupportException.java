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
import android.view.View;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView$OnItemLongClickListener;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AbsListView$OnScrollListener;
import android.widget.GridView;

class StickyGridHeadersGridView$RuntimePlatformSupportException extends RuntimeException
{
    private static final long serialVersionUID = -6512098808936536538L;
    final /* synthetic */ StickyGridHeadersGridView this$0;
    
    public StickyGridHeadersGridView$RuntimePlatformSupportException(final StickyGridHeadersGridView this$0, final Exception ex) {
        this.this$0 = this$0;
        super(StickyGridHeadersGridView.ERROR_PLATFORM, ex);
    }
}
