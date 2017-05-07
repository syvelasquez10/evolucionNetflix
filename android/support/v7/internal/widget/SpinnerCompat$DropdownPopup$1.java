// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow$OnDismissListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.SpinnerAdapter;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ListAdapter;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;

class SpinnerCompat$DropdownPopup$1 implements AdapterView$OnItemClickListener
{
    final /* synthetic */ SpinnerCompat$DropdownPopup this$1;
    final /* synthetic */ SpinnerCompat val$this$0;
    
    SpinnerCompat$DropdownPopup$1(final SpinnerCompat$DropdownPopup this$1, final SpinnerCompat val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int selection, final long n) {
        this.this$1.this$0.setSelection(selection);
        if (this.this$1.this$0.mOnItemClickListener != null) {
            this.this$1.this$0.performItemClick(view, selection, this.this$1.mAdapter.getItemId(selection));
        }
        this.this$1.dismiss();
    }
}
