// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import java.util.HashSet;
import com.netflix.mediaclient.util.data.DataRepository$DataSavedCallback;
import com.netflix.mediaclient.service.pservice.PDiskDataRepository$LoadCallback;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.pservice.PServiceWidgetAgent;
import com.netflix.mediaclient.service.pservice.PDiskData$ImageType;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.pservice.PService;
import android.content.Intent;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.service.pservice.PVideo;
import java.util.Iterator;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.service.pservice.PDiskData$ListType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import java.util.List;
import com.netflix.mediaclient.service.pservice.PDiskData;
import java.util.Set;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class PreAppAgentDataHandler$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ PreAppAgentDataHandler this$0;
    final /* synthetic */ Set val$fetchCallbacksList;
    final /* synthetic */ PDiskData val$newData;
    final /* synthetic */ PreAppAgentEventType val$updateType;
    
    PreAppAgentDataHandler$1(final PreAppAgentDataHandler this$0, final PDiskData val$newData, final Set val$fetchCallbacksList, final PreAppAgentEventType val$updateType) {
        this.this$0 = this$0;
        this.val$newData = val$newData;
        this.val$fetchCallbacksList = val$fetchCallbacksList;
        this.val$updateType = val$updateType;
    }
    
    @Override
    public void onBBVideosFetched(final List<Billboard> list, final Status status) {
        Log.d("nf_preappagentdatahandler", String.format("BB fetched videos:%s", list));
        this.this$0.copyBBListIntoDiskData(this.val$newData, list);
        this.val$fetchCallbacksList.remove(PDiskData$ListType.BILLBOARD);
        this.this$0.proceedAfterFetchOfLists(this.val$fetchCallbacksList, this.val$newData, this.val$updateType);
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final Status status) {
        Log.d("nf_preappagentdatahandler", String.format("IQ fetched videos:%s", list));
        this.this$0.copyCWListIntoDiskData(this.val$newData, list);
        this.val$fetchCallbacksList.remove(PDiskData$ListType.CW);
        this.this$0.proceedAfterFetchOfLists(this.val$fetchCallbacksList, this.val$newData, this.val$updateType);
    }
}
