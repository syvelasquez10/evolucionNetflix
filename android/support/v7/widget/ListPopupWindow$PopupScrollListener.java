// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.widget.PopupWindowCompat;
import android.os.Build$VERSION;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.AdapterView;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent;
import android.widget.ListView;
import android.view.View$OnTouchListener;
import android.view.ViewParent;
import android.view.ViewGroup;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.content.res.TypedArray;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.internal.widget.AppCompatPopupWindow;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.util.Log;
import android.graphics.Rect;
import android.widget.PopupWindow;
import android.database.DataSetObserver;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView$OnItemClickListener;
import android.os.Handler;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.content.Context;
import android.widget.ListAdapter;
import java.lang.reflect.Method;
import android.widget.AbsListView;
import android.widget.AbsListView$OnScrollListener;

class ListPopupWindow$PopupScrollListener implements AbsListView$OnScrollListener
{
    final /* synthetic */ ListPopupWindow this$0;
    
    private ListPopupWindow$PopupScrollListener(final ListPopupWindow this$0) {
        this.this$0 = this$0;
    }
    
    public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
    }
    
    public void onScrollStateChanged(final AbsListView absListView, final int n) {
        if (n == 1 && !this.this$0.isInputMethodNotNeeded() && this.this$0.mPopup.getContentView() != null) {
            this.this$0.mHandler.removeCallbacks((Runnable)this.this$0.mResizePopupRunnable);
            this.this$0.mResizePopupRunnable.run();
        }
    }
}
