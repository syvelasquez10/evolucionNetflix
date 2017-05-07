// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.app.AlertDialog$Builder;
import android.view.WindowManager$LayoutParams;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.ViewGroup;
import android.widget.TextView;
import android.text.Layout;
import android.view.View$OnClickListener;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.content.Context;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class CopyrightView$1 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ CopyrightView this$0;
    final /* synthetic */ Context val$context;
    
    CopyrightView$1(final CopyrightView this$0, final Context val$context) {
        this.this$0 = this$0;
        this.val$context = val$context;
    }
    
    public void onGlobalLayout() {
        ViewUtils.removeGlobalLayoutListener((View)this.this$0.copyrightTextView, (ViewTreeObserver$OnGlobalLayoutListener)this);
        final Layout layout = this.this$0.copyrightTextView.getLayout();
        this.this$0.expandedYOffset = this.this$0.copyrightViewGroup.getMeasuredHeight() * 3;
        if (layout != null && layout.getEllipsisCount(0) > 0) {
            this.this$0.copyrightTextView.setOnClickListener((View$OnClickListener)new CopyrightView$1$1(this));
        }
    }
}
