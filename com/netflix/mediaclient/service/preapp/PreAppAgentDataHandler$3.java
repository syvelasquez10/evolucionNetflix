// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
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
import com.netflix.mediaclient.service.preappservice.PDiskDataRepository;
import com.netflix.mediaclient.util.data.DataRepository$DataSavedCallback;

class PreAppAgentDataHandler$3 implements Runnable
{
    final /* synthetic */ PreAppAgentDataHandler this$0;
    final /* synthetic */ DataRepository$DataSavedCallback val$callback;
    final /* synthetic */ PreAppAgentDataHandler$DataToDisk val$newData;
    
    PreAppAgentDataHandler$3(final PreAppAgentDataHandler this$0, final PreAppAgentDataHandler$DataToDisk val$newData, final DataRepository$DataSavedCallback val$callback) {
        this.this$0 = this$0;
        this.val$newData = val$newData;
        this.val$callback = val$callback;
    }
    
    @Override
    public void run() {
        PDiskDataRepository.saveData(PreAppAgentDataHandler.mContext, this.val$newData.toJsonString(), this.val$callback);
    }
}
