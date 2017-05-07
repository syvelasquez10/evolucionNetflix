// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import java.util.HashSet;
import com.netflix.mediaclient.util.data.DataRepository$DataSavedCallback;
import android.content.Intent;
import com.netflix.mediaclient.service.pservice.PService;
import com.netflix.mediaclient.service.pservice.PDiskDataRepository$LoadCallback;
import com.netflix.mediaclient.service.pservice.PServiceWidgetAgent;
import com.netflix.mediaclient.service.pservice.PDiskData$ImageType;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
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
import com.netflix.mediaclient.service.pservice.PDiskDataRepository;
import com.netflix.mediaclient.service.pservice.PDiskData;

class PreAppAgentDataHandler$14$1 implements Runnable
{
    final /* synthetic */ PreAppAgentDataHandler$14 this$1;
    final /* synthetic */ PDiskData val$onDiskData;
    
    PreAppAgentDataHandler$14$1(final PreAppAgentDataHandler$14 this$1, final PDiskData val$onDiskData) {
        this.this$1 = this$1;
        this.val$onDiskData = val$onDiskData;
    }
    
    @Override
    public void run() {
        if (this.val$onDiskData != null) {
            PDiskDataRepository.clearDiskData(PreAppAgentDataHandler.mContext);
            this.val$onDiskData.clearMemberlists();
            this.this$1.this$0.clearOldImages(this.val$onDiskData);
            Log.d("nf_preappagentdatahandler", "preapp member data cleared. storing rest of data?");
            this.this$1.this$0.proceedToStoreAndNotify(this.val$onDiskData, this.this$1.val$updateType);
            return;
        }
        this.this$1.this$0.notifyOthers(PreAppAgentDataHandler.mContext, this.this$1.val$updateType);
    }
}
