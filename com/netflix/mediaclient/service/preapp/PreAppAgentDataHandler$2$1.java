// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.preappservice.PDiskDataRepository;
import com.netflix.mediaclient.util.data.DataRepository$DataSavedCallback;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import android.content.Intent;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.preappservice.PService;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;

class PreAppAgentDataHandler$2$1 implements Runnable
{
    final /* synthetic */ PreAppAgentDataHandler$2 this$1;
    
    PreAppAgentDataHandler$2$1(final PreAppAgentDataHandler$2 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.notifyOthers(PreAppAgentDataHandler.mContext, this.this$1.val$updateType);
    }
}
