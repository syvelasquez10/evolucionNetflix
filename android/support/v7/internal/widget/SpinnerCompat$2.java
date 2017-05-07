// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.util.Log;
import android.widget.Adapter;
import android.view.MotionEvent;
import android.os.Parcelable;
import android.content.DialogInterface;
import android.view.View$MeasureSpec;
import android.widget.SpinnerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.view.View;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.appcompat.R$attr;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.ListPopupWindow$ForwardingListener;
import android.content.DialogInterface$OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class SpinnerCompat$2 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ SpinnerCompat this$0;
    
    SpinnerCompat$2(final SpinnerCompat this$0) {
        this.this$0 = this$0;
    }
    
    public void onGlobalLayout() {
        if (!this.this$0.mPopup.isShowing()) {
            this.this$0.mPopup.show();
        }
        final ViewTreeObserver viewTreeObserver = this.this$0.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
        }
    }
}
