// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.app.Activity;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.view.View;
import android.view.View$OnClickListener;

class OldSlidingMenu$1 implements View$OnClickListener
{
    final /* synthetic */ OldSlidingMenu this$0;
    
    OldSlidingMenu$1(final OldSlidingMenu this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.activity.startActivity(KidsUtils.createExitKidsIntent(this.this$0.activity, UIViewLogging$UIViewCommandName.slidingMenuKidsExit));
    }
}
