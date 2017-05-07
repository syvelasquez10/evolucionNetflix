// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.preappservice.PDiskDataRepository;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import android.content.Intent;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.preappservice.PService;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.data.DataRepository$DataSavedCallback;

class PreAppAgentDataHandler$2 implements DataRepository$DataSavedCallback
{
    final /* synthetic */ PreAppAgentDataHandler this$0;
    final /* synthetic */ UpdateEventType val$updateType;
    
    PreAppAgentDataHandler$2(final PreAppAgentDataHandler this$0, final UpdateEventType val$updateType) {
        this.this$0 = this$0;
        this.val$updateType = val$updateType;
    }
    
    @Override
    public void onDataSaved(final String s) {
        Log.d("nf_preapp_datahandler", "onDataSaved");
        PreAppAgentDataHandler.mServiceAgent.getMainHandler().post((Runnable)new PreAppAgentDataHandler$2$1(this));
    }
}
