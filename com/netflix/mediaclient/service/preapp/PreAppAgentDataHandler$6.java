// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import java.util.HashSet;
import android.content.Intent;
import com.netflix.mediaclient.service.pservice.PService;
import java.util.Map;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.pservice.PDiskDataRepository$LoadCallback;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;
import java.util.Iterator;
import com.netflix.mediaclient.service.pservice.PVideo;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.service.pservice.PDiskData$ListName;
import java.util.Set;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;
import com.netflix.mediaclient.service.pservice.PDiskData;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.data.DataRepository$DataSavedCallback;

class PreAppAgentDataHandler$6 implements DataRepository$DataSavedCallback
{
    final /* synthetic */ PreAppAgentDataHandler this$0;
    final /* synthetic */ PreAppAgentEventType val$updateType;
    
    PreAppAgentDataHandler$6(final PreAppAgentDataHandler this$0, final PreAppAgentEventType val$updateType) {
        this.this$0 = this$0;
        this.val$updateType = val$updateType;
    }
    
    @Override
    public void onDataSaved(final String s) {
        Log.d("nf_preappagentdatahandler", "onDataSaved");
        PreAppAgentDataHandler.mServiceAgent.getMainHandler().post((Runnable)new PreAppAgentDataHandler$6$1(this));
    }
}
