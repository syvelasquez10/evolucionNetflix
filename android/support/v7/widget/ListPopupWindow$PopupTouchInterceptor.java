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
import android.view.ViewParent;
import android.view.ViewGroup;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.AbsListView$OnScrollListener;
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
import android.content.Context;
import android.widget.ListAdapter;
import java.lang.reflect.Method;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

class ListPopupWindow$PopupTouchInterceptor implements View$OnTouchListener
{
    final /* synthetic */ ListPopupWindow this$0;
    
    private ListPopupWindow$PopupTouchInterceptor(final ListPopupWindow this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        final int n = (int)motionEvent.getX();
        final int n2 = (int)motionEvent.getY();
        if (action == 0 && this.this$0.mPopup != null && this.this$0.mPopup.isShowing() && n >= 0 && n < this.this$0.mPopup.getWidth() && n2 >= 0 && n2 < this.this$0.mPopup.getHeight()) {
            this.this$0.mHandler.postDelayed((Runnable)this.this$0.mResizePopupRunnable, 250L);
        }
        else if (action == 1) {
            this.this$0.mHandler.removeCallbacks((Runnable)this.this$0.mResizePopupRunnable);
        }
        return false;
    }
}
