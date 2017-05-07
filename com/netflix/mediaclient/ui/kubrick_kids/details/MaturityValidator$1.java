// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.support.design.widget.Snackbar;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import android.view.View$OnClickListener;

class MaturityValidator$1 implements View$OnClickListener
{
    final /* synthetic */ MaturityValidator this$0;
    
    MaturityValidator$1(final MaturityValidator this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.activity.onBackPressed();
    }
}
