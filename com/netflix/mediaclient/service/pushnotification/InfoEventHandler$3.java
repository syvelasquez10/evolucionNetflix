// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.ParcelUtils;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.Log;

class InfoEventHandler$3 implements Runnable
{
    final /* synthetic */ InfoEventHandler this$0;
    
    InfoEventHandler$3(final InfoEventHandler this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.i("nf_push_info", "Refreshing ALL lolomo via runnable");
        if (InfoEventHandler.mService != null) {
            InfoEventHandler.mService.getBrowse().refreshLolomo();
        }
    }
}
