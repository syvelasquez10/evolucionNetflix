// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.app.Fragment;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class RoleDetailsFrag$ActorRelatedView$1 extends BroadcastReceiver
{
    final /* synthetic */ RoleDetailsFrag$ActorRelatedView this$1;
    
    RoleDetailsFrag$ActorRelatedView$1(final RoleDetailsFrag$ActorRelatedView this$1) {
        this.this$1 = this$1;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$1.this$0.getNetflixActivity()) || intent == null || !this.this$1.this$0.isResumed() || this.this$1.this$0.isHidden()) {
            return;
        }
        final String action = intent.getAction();
        switch (action) {
            default: {}
            case "com.netflix.mediaclient.intent.action.RDP_CLOSE": {
                this.this$1.this$0.getFragmentManager().beginTransaction().hide((Fragment)this.this$1.this$0).commitAllowingStateLoss();
            }
        }
    }
}
