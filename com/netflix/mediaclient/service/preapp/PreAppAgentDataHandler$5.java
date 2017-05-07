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
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.pservice.PDiskData;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;

class PreAppAgentDataHandler$5 extends LoggingResourceFetcherCallback
{
    private int mCount;
    final /* synthetic */ PreAppAgentDataHandler this$0;
    final /* synthetic */ PDiskData val$newData;
    final /* synthetic */ PreAppAgentEventType val$updateType;
    final /* synthetic */ int val$urlFetchCount;
    
    PreAppAgentDataHandler$5(final PreAppAgentDataHandler this$0, final int val$urlFetchCount, final PDiskData val$newData, final PreAppAgentEventType val$updateType) {
        this.this$0 = this$0;
        this.val$urlFetchCount = val$urlFetchCount;
        this.val$newData = val$newData;
        this.val$updateType = val$updateType;
        this.mCount = this.val$urlFetchCount;
    }
    
    @Override
    public void onResourceFetched(final String s, final String s2, final Status status) {
        Log.d("nf_preappagentdatahandler", String.format("onResourceFetched mCount: %d, reqUrl: %s, localUrl: %s", this.mCount, s, s2));
        this.val$newData.urlMap.put(s, s2);
        final int mCount = this.mCount - 1;
        this.mCount = mCount;
        if (mCount <= 0) {
            this.this$0.storeAndNotify(this.val$newData, this.val$updateType);
        }
    }
}