// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.app.AlertDialog$Builder;
import android.view.WindowManager$LayoutParams;
import android.app.AlertDialog;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.ViewGroup;
import android.widget.TextView;
import android.text.Layout;
import android.view.View$OnClickListener;
import android.view.View;
import android.content.Context;
import android.view.View$OnLayoutChangeListener;

class CopyrightView$1 implements View$OnLayoutChangeListener
{
    final /* synthetic */ CopyrightView this$0;
    final /* synthetic */ Context val$context;
    
    CopyrightView$1(final CopyrightView this$0, final Context val$context) {
        this.this$0 = this$0;
        this.val$context = val$context;
    }
    
    public void onLayoutChange(final View view, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.this$0.copyrightTextView.removeOnLayoutChangeListener((View$OnLayoutChangeListener)this);
        final Layout layout = this.this$0.copyrightTextView.getLayout();
        this.this$0.expandedYOffset = 3 * this.this$0.copyrightViewGroup.getMeasuredHeight();
        if (layout != null && layout.getEllipsisCount(0) > 0) {
            this.this$0.copyrightTextView.setOnClickListener((View$OnClickListener)new CopyrightView$1$1(this));
        }
    }
}
