// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import java.util.HashSet;
import com.netflix.mediaclient.util.data.DataRepository$DataSavedCallback;
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
import com.netflix.mediaclient.Log;
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

class PreAppAgentDataHandler$6$1 implements Runnable
{
    final /* synthetic */ PreAppAgentDataHandler$6 this$1;
    
    PreAppAgentDataHandler$6$1(final PreAppAgentDataHandler$6 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.notifyOthers(PreAppAgentDataHandler.mContext, this.this$1.val$updateType);
    }
}
