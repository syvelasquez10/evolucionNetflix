// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow$OnDismissListener;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.SpinnerAdapter;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ListAdapter;
import android.support.v7.widget.ListPopupWindow;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class SpinnerCompat$DropdownPopup$2 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ SpinnerCompat$DropdownPopup this$1;
    
    SpinnerCompat$DropdownPopup$2(final SpinnerCompat$DropdownPopup this$1) {
        this.this$1 = this$1;
    }
    
    public void onGlobalLayout() {
        this.this$1.computeContentWidth();
        this.this$1.show();
    }
}
