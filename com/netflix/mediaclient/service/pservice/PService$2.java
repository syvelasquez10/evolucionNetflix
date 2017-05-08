// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import android.os.IBinder;
import android.os.SystemClock;
import java.util.Iterator;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Context;
import android.app.PendingIntent;
import com.netflix.mediaclient.Log;
import android.app.AlarmManager;
import android.content.Intent;
import java.util.ArrayList;
import android.app.Service;

class PService$2 implements PServiceAgent$AgentContext
{
    final /* synthetic */ PService this$0;
    
    PService$2(final PService this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public PServiceAgent$PServiceFetchAgentInterface getFetchAgent() {
        return this.this$0.mFetchAgent;
    }
    
    @Override
    public PServiceAgent$PServicePartnerFetchInterface getPartnerFetch() {
        return this.this$0.mFetchAgent;
    }
    
    @Override
    public PService getService() {
        return this.this$0;
    }
    
    @Override
    public PServiceAgent$PServiceWidgetAgentInterface getWidgetAgent() {
        return this.this$0.mWidgetAgent;
    }
}
