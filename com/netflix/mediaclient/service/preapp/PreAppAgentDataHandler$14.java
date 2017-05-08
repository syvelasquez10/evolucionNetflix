// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import java.util.HashSet;
import com.netflix.mediaclient.util.data.DataRepository$DataSavedCallback;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.pservice.PServiceWidgetAgent;
import com.netflix.mediaclient.service.pservice.PDiskData$ImageType;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.pservice.PService;
import android.content.Intent;
import com.netflix.model.branches.FalkorVideo;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.service.pservice.PVideo;
import java.util.Iterator;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.service.pservice.PDiskData$ListType;
import java.util.Set;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import java.util.List;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.pservice.PDiskData;
import com.netflix.mediaclient.service.pservice.PDiskDataRepository$LoadCallback;

class PreAppAgentDataHandler$14 extends PDiskDataRepository$LoadCallback
{
    final /* synthetic */ PreAppAgentDataHandler this$0;
    final /* synthetic */ PreAppAgentEventType val$updateType;
    
    PreAppAgentDataHandler$14(final PreAppAgentDataHandler this$0, final PreAppAgentEventType val$updateType) {
        this.this$0 = this$0;
        this.val$updateType = val$updateType;
    }
    
    @Override
    public void onDataLoaded(final PDiskData pDiskData) {
        if (pDiskData == null) {
            Log.d("nf_preappagentdatahandler", "data on disk is null, first time");
        }
        PreAppAgentDataHandler.mServiceAgent.getMainHandler().post((Runnable)new PreAppAgentDataHandler$14$1(this, pDiskData));
    }
}
