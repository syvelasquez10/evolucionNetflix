// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import android.content.Context;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.os.Handler;
import com.netflix.mediaclient.android.app.Status;

class ServiceAgent$2 implements Runnable
{
    final /* synthetic */ ServiceAgent this$0;
    
    ServiceAgent$2(final ServiceAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.initCallback.onInitComplete(this.this$0, this.this$0.initErrorResult);
    }
}