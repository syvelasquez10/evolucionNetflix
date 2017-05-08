// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import java.util.HashSet;
import com.netflix.mediaclient.util.data.DataRepository$DataSavedCallback;
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
import com.netflix.mediaclient.Log;
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
import com.netflix.mediaclient.service.pservice.PDiskData;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.pservice.PDiskDataRepository;
import com.netflix.mediaclient.service.pservice.PDiskDataRepository$LoadCallback;

class PreAppAgentDataHandler$12 implements Runnable
{
    final /* synthetic */ PreAppAgentDataHandler this$0;
    final /* synthetic */ PDiskDataRepository$LoadCallback val$loadCallback;
    
    PreAppAgentDataHandler$12(final PreAppAgentDataHandler this$0, final PDiskDataRepository$LoadCallback val$loadCallback) {
        this.this$0 = this$0;
        this.val$loadCallback = val$loadCallback;
    }
    
    @Override
    public void run() {
        PDiskDataRepository.startLoadFromDisk(PreAppAgentDataHandler.mContext, this.val$loadCallback);
    }
}
