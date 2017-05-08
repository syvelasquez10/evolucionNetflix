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
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.pservice.PDiskData$ListType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import java.util.List;
import com.netflix.mediaclient.service.pservice.PDiskData;
import java.util.Set;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class PreAppAgentDataHandler$6 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ PreAppAgentDataHandler this$0;
    final /* synthetic */ SimpleBrowseAgentCallback val$callbacks;
    final /* synthetic */ Set val$fetchCallbacksList;
    final /* synthetic */ SimpleBrowseAgentCallback val$iqCallback;
    final /* synthetic */ PDiskData val$newData;
    final /* synthetic */ SimpleBrowseAgentCallback val$nonMemberListCallback;
    final /* synthetic */ SimpleBrowseAgentCallback val$standardListsCallback;
    final /* synthetic */ SimpleBrowseAgentCallback val$standardListsCallback2;
    final /* synthetic */ PreAppAgentEventType val$updateType;
    
    PreAppAgentDataHandler$6(final PreAppAgentDataHandler this$0, final PDiskData val$newData, final Set val$fetchCallbacksList, final PreAppAgentEventType val$updateType, final SimpleBrowseAgentCallback val$callbacks, final SimpleBrowseAgentCallback val$iqCallback, final SimpleBrowseAgentCallback val$standardListsCallback, final SimpleBrowseAgentCallback val$standardListsCallback2, final SimpleBrowseAgentCallback val$nonMemberListCallback) {
        this.this$0 = this$0;
        this.val$newData = val$newData;
        this.val$fetchCallbacksList = val$fetchCallbacksList;
        this.val$updateType = val$updateType;
        this.val$callbacks = val$callbacks;
        this.val$iqCallback = val$iqCallback;
        this.val$standardListsCallback = val$standardListsCallback;
        this.val$standardListsCallback2 = val$standardListsCallback2;
        this.val$nonMemberListCallback = val$nonMemberListCallback;
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final Status status) {
        Log.d("nf_preappagentdatahandler", String.format("LoMos fetched ", new Object[0]));
        if (status.isSucces()) {
            this.this$0.copyListInfoIntoDiskData(this.val$newData, list);
            this.val$fetchCallbacksList.remove(PDiskData$ListType.LOMO_INFO);
            this.this$0.fetchLists(this.val$updateType, this.val$callbacks, this.val$iqCallback, this.val$standardListsCallback, this.val$standardListsCallback2, this.val$nonMemberListCallback);
            return;
        }
        Log.e("nf_preappagentdatahandler", String.format(" updateType: %s - skip fetching data for widget because lomo fetch failed - avoid triggering multiple lolomos", this.val$updateType));
    }
}
