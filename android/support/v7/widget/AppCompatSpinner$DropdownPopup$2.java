// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow$OnDismissListener;
import android.graphics.drawable.Drawable;
import android.widget.SpinnerAdapter;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v4.view.ViewCompat;
import android.widget.AdapterView$OnItemClickListener;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.widget.ListAdapter;
import android.view.View;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class AppCompatSpinner$DropdownPopup$2 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ AppCompatSpinner$DropdownPopup this$1;
    
    AppCompatSpinner$DropdownPopup$2(final AppCompatSpinner$DropdownPopup this$1) {
        this.this$1 = this$1;
    }
    
    public void onGlobalLayout() {
        if (!this.this$1.isVisibleToUser((View)this.this$1.this$0)) {
            this.this$1.dismiss();
            return;
        }
        this.this$1.computeContentWidth();
        this.this$1.show();
    }
}
