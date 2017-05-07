// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.servicemgr.UiLocation;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebClientFactory;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebCallback;
import com.netflix.mediaclient.service.logging.presentation.PresentationRequest;
import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import com.netflix.mediaclient.service.logging.presentation.PresentationEvent;
import java.util.List;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebClient;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.PresentationTracking;
import com.netflix.mediaclient.util.data.DataRepository$LoadedCallback;
import com.netflix.mediaclient.Log;

class PresentationTrackingManager$2 implements Runnable
{
    final /* synthetic */ PresentationTrackingManager this$0;
    
    PresentationTrackingManager$2(final PresentationTrackingManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("nf_presentation", "Check if we have not delivered events from last time our app was runnung...");
        this.this$0.mDataRepository.loadAll(new PresentationTrackingManager$2$1(this));
    }
}
