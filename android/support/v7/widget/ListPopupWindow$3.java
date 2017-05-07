// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.support.v7.appcompat.R$attr;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.internal.widget.ListViewCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.view.View$OnTouchListener;
import android.widget.PopupWindow$OnDismissListener;
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
import android.util.Log;
import android.graphics.Rect;
import android.widget.PopupWindow;
import android.database.DataSetObserver;
import android.widget.AdapterView$OnItemClickListener;
import android.os.Handler;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.widget.ListAdapter;
import java.lang.reflect.Method;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemSelectedListener;

class ListPopupWindow$3 implements AdapterView$OnItemSelectedListener
{
    final /* synthetic */ ListPopupWindow this$0;
    
    ListPopupWindow$3(final ListPopupWindow this$0) {
        this.this$0 = this$0;
    }
    
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (n != -1) {
            final ListPopupWindow$DropDownListView access$600 = this.this$0.mDropDownList;
            if (access$600 != null) {
                access$600.mListSelectionHidden = false;
            }
        }
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
    }
}
