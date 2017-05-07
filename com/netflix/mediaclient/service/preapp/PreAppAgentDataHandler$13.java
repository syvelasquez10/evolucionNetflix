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
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.service.pservice.PVideo;
import java.util.Iterator;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.service.pservice.PDiskData$ListType;
import java.util.Set;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import java.util.List;
import com.netflix.mediaclient.service.pservice.PDiskData;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.Log;

class PreAppAgentDataHandler$13 implements Runnable
{
    final /* synthetic */ PreAppAgentDataHandler this$0;
    final /* synthetic */ String val$fileToDelete;
    final /* synthetic */ String val$localFilename;
    
    PreAppAgentDataHandler$13(final PreAppAgentDataHandler this$0, final String val$fileToDelete, final String val$localFilename) {
        this.this$0 = this$0;
        this.val$fileToDelete = val$fileToDelete;
        this.val$localFilename = val$localFilename;
    }
    
    @Override
    public void run() {
        if (!PreAppAgentDataHandler.mServiceAgent.getResourceFetcher().deleteLocalResource(this.val$fileToDelete)) {
            Log.w("nf_preappagentdatahandler", String.format("localFilename: %s, filename: %s delete failed", this.val$localFilename, this.val$fileToDelete));
            return;
        }
        Log.d("nf_preappagentdatahandler", String.format("deleted local file: %s", this.val$localFilename));
    }
}
