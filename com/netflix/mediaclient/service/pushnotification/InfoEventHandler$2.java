// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import com.netflix.mediaclient.util.ParcelUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.Log;

class InfoEventHandler$2 implements Runnable
{
    final /* synthetic */ InfoEventHandler this$0;
    
    InfoEventHandler$2(final InfoEventHandler this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.i("nf_push_info", "Refreshing IQ via runnable");
        if (InfoEventHandler.mService != null) {
            InfoEventHandler.mService.getBrowse().refreshIQ();
        }
    }
}
