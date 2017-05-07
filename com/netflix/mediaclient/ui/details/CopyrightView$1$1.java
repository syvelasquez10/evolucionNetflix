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
import android.view.View$OnLayoutChangeListener;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import android.view.View$OnClickListener;

class CopyrightView$1$1 implements View$OnClickListener
{
    final /* synthetic */ CopyrightView$1 this$1;
    
    CopyrightView$1$1(final CopyrightView$1 this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final View view) {
        this.this$1.this$0.showExpandedCopyrightPopup(this.this$1.this$0.details, this.this$1.val$context);
    }
}
