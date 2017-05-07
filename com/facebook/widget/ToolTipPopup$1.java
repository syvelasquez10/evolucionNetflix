// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.view.View$OnClickListener;
import android.view.View$MeasureSpec;
import android.app.Activity;
import com.facebook.android.R$drawable;
import com.facebook.android.R$id;
import android.widget.TextView;
import android.widget.PopupWindow;
import android.content.Context;
import android.view.View;
import java.lang.ref.WeakReference;
import android.view.ViewTreeObserver$OnScrollChangedListener;

class ToolTipPopup$1 implements ViewTreeObserver$OnScrollChangedListener
{
    final /* synthetic */ ToolTipPopup this$0;
    
    ToolTipPopup$1(final ToolTipPopup this$0) {
        this.this$0 = this$0;
    }
    
    public void onScrollChanged() {
        if (this.this$0.mAnchorViewRef.get() != null && this.this$0.mPopupWindow != null && this.this$0.mPopupWindow.isShowing()) {
            if (!this.this$0.mPopupWindow.isAboveAnchor()) {
                this.this$0.mPopupContent.showTopArrow();
                return;
            }
            this.this$0.mPopupContent.showBottomArrow();
        }
    }
}
