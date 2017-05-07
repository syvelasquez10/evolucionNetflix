// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import android.os.IBinder;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import java.util.ArrayList;
import android.app.Service;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;

class PService$1 implements PServiceAgent$InitCallback
{
    final /* synthetic */ PService this$0;
    
    PService$1(final PService this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onInitComplete(final PServiceAgent pServiceAgent, final Status status) {
        ThreadUtils.assertOnMain();
        if (status.isError()) {
            if (Log.isLoggable("nf_preapp_service", 6)) {
                Log.e("nf_preapp_service", String.format("PService init failed with PServiceAgent: %s, statusCode=%s", pServiceAgent.getClass().getSimpleName(), status.getStatusCode()));
            }
            this.this$0.initCompleted();
            this.this$0.stopSelf();
        }
        else {
            if (Log.isLoggable("nf_preapp_service", 6)) {
                Log.i("nf_preapp_service", String.format("PService successfully inited PServiceAgent: %s", pServiceAgent.getClass().getSimpleName()));
            }
            if (pServiceAgent == this.this$0.mFetchAgent) {
                this.this$0.initCompleted();
            }
        }
    }
}
