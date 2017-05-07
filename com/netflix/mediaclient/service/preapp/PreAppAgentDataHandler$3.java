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
import java.util.Iterator;
import com.netflix.mediaclient.service.pservice.PVideo;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.pservice.PDiskData$ListName;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.service.pservice.PDiskData;
import java.util.Set;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class PreAppAgentDataHandler$3 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ PreAppAgentDataHandler this$0;
    final /* synthetic */ Set val$listsToFetch;
    final /* synthetic */ PDiskData val$newData;
    final /* synthetic */ PreAppAgentEventType val$updateType;
    
    PreAppAgentDataHandler$3(final PreAppAgentDataHandler this$0, final PDiskData val$newData, final Set val$listsToFetch, final PreAppAgentEventType val$updateType) {
        this.this$0 = this$0;
        this.val$newData = val$newData;
        this.val$listsToFetch = val$listsToFetch;
        this.val$updateType = val$updateType;
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        this.this$0.copyListIntoDiskData(this.val$newData, list, PDiskData$ListName.STANDARD_FIRST);
        this.val$listsToFetch.remove(PDiskData$ListName.STANDARD_FIRST);
        this.this$0.proceedAfterFetchOfLists(this.val$listsToFetch, this.val$newData, this.val$updateType);
    }
}
