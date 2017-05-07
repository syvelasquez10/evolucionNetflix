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
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;

class PreAppAgentDataHandler$1 extends LoggingResourceFetcherCallback
{
    private int mCount;
    final /* synthetic */ PreAppAgentDataHandler this$0;
    final /* synthetic */ PreAppAgentDataHandler$DataToDisk val$newData;
    final /* synthetic */ UpdateEventType val$updateType;
    final /* synthetic */ int val$urlFetchCount;
    
    PreAppAgentDataHandler$1(final PreAppAgentDataHandler this$0, final int val$urlFetchCount, final PreAppAgentDataHandler$DataToDisk val$newData, final UpdateEventType val$updateType) {
        this.this$0 = this$0;
        this.val$urlFetchCount = val$urlFetchCount;
        this.val$newData = val$newData;
        this.val$updateType = val$updateType;
        this.mCount = this.val$urlFetchCount;
    }
    
    @Override
    public void onResourceFetched(final String s, final String s2, final Status status) {
        Log.d("nf_preapp_datahandler", String.format("onResourceFetched mCount: %d, reqUrl: %s, localUrl: %s", this.mCount, s, s2));
        this.val$newData.urlMap.put(s, s2);
        final int mCount = this.mCount - 1;
        this.mCount = mCount;
        if (mCount <= 0) {
            this.this$0.storeAndNotify(this.val$newData, this.val$updateType);
        }
    }
}
