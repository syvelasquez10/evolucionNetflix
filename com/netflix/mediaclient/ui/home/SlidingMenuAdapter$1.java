// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.app.Activity;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.view.View;
import android.view.View$OnClickListener;

class SlidingMenuAdapter$1 implements View$OnClickListener
{
    final /* synthetic */ SlidingMenuAdapter this$0;
    
    SlidingMenuAdapter$1(final SlidingMenuAdapter this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.activity.startActivity(KidsUtils.createExitKidsIntent(this.this$0.activity, UIViewLogging$UIViewCommandName.slidingMenuKidsExit));
    }
}
