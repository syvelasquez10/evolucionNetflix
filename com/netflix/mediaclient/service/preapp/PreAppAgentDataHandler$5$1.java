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
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.service.pservice.PDiskData$ListName;
import java.util.Set;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import java.util.List;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.pservice.PDiskDataRepository;
import com.netflix.mediaclient.service.pservice.PDiskData;

class PreAppAgentDataHandler$5$1 implements Runnable
{
    final /* synthetic */ PreAppAgentDataHandler$5 this$1;
    final /* synthetic */ PDiskData val$onDiskData;
    
    PreAppAgentDataHandler$5$1(final PreAppAgentDataHandler$5 this$1, final PDiskData val$onDiskData) {
        this.this$1 = this$1;
        this.val$onDiskData = val$onDiskData;
    }
    
    @Override
    public void run() {
        PDiskDataRepository.clearDiskData(PreAppAgentDataHandler.mContext);
        this.this$1.this$0.clearOldImages(this.val$onDiskData, this.this$1.val$updateType);
        Log.d("nf_preappagentdatahandler", "old data on disk cleared");
        this.this$1.this$0.proceedAfterFetchOfImages(this.this$1.this$0.mergeData(this.this$1.val$newData, this.val$onDiskData, this.this$1.val$updateType), this.this$1.val$updateType);
    }
}
