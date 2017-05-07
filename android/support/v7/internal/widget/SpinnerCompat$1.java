// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.os.Parcelable;
import android.content.DialogInterface;
import android.view.View$MeasureSpec;
import android.graphics.drawable.Drawable;
import android.widget.SpinnerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.content.DialogInterface$OnClickListener;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.support.v7.widget.ListPopupWindow$ForwardingListener;

class SpinnerCompat$1 extends ListPopupWindow$ForwardingListener
{
    final /* synthetic */ SpinnerCompat this$0;
    final /* synthetic */ SpinnerCompat$DropdownPopup val$popup;
    
    SpinnerCompat$1(final SpinnerCompat this$0, final View view, final SpinnerCompat$DropdownPopup val$popup) {
        this.this$0 = this$0;
        this.val$popup = val$popup;
        super(view);
    }
    
    @Override
    public ListPopupWindow getPopup() {
        return this.val$popup;
    }
    
    public boolean onForwardingStarted() {
        if (!this.this$0.mPopup.isShowing()) {
            this.this$0.mPopup.show();
        }
        return true;
    }
}
