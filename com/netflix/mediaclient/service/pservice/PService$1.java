// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import android.os.IBinder;
import com.netflix.mediaclient.util.AndroidUtils;
import android.os.SystemClock;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Context;
import android.app.PendingIntent;
import android.app.AlarmManager;
import android.content.Intent;
import android.app.Service;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
import java.util.ArrayList;

class PService$1 implements PServiceAgent$InitCallback
{
    private final ArrayList<PServiceAgent> agentsToInit;
    final /* synthetic */ PService this$0;
    
    PService$1(final PService this$0) {
        this.this$0 = this$0;
        this.agentsToInit = new PService$1$1(this);
    }
    
    @Override
    public void onInitComplete(final PServiceAgent pServiceAgent, final Status status) {
        ThreadUtils.assertOnMain();
        if (status.isError()) {
            if (Log.isLoggable()) {
                Log.e("nf_preapp_service", String.format("PService init failed with PServiceAgent: %s, statusCode=%s", pServiceAgent.getClass().getSimpleName(), status.getStatusCode()));
            }
            this.this$0.initCompleted();
            this.this$0.stopSelf();
        }
        else {
            if (Log.isLoggable()) {
                Log.i("nf_preapp_service", String.format("PService successfully inited PServiceAgent: %s", pServiceAgent.getClass().getSimpleName()));
            }
            if (pServiceAgent == this.this$0.mFetchAgent) {
                final Iterator<PServiceAgent> iterator = this.agentsToInit.iterator();
                while (iterator.hasNext()) {
                    iterator.next().init(this.this$0.agentContext, this);
                }
            }
            else {
                this.agentsToInit.remove(pServiceAgent);
                if (this.agentsToInit.isEmpty()) {
                    Log.i("nf_preapp_service", "PService successfully inited all PServiceAgents ");
                    this.this$0.initCompleted();
                }
            }
        }
    }
}
