// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.servicemgr.UiLocation;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebClientFactory;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebCallback;
import com.netflix.mediaclient.service.logging.presentation.PresentationRequest;
import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebClient;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.PresentationTracking;
import java.util.List;
import com.netflix.mediaclient.service.logging.presentation.PresentationEvent;
import com.netflix.mediaclient.util.EventQueue;

class PresentationTrackingManager$PresentationTrackingEventQueue extends EventQueue<PresentationEvent>
{
    final /* synthetic */ PresentationTrackingManager this$0;
    
    public PresentationTrackingManager$PresentationTrackingEventQueue(final PresentationTrackingManager this$0, final int n) {
        this.this$0 = this$0;
        super("nf_pt_queue", n, 300000L, true, true);
    }
    
    @Override
    protected void doFlush(final List<PresentationEvent> list, final boolean b) {
        this.this$0.sendPresentationEvents(list, b);
    }
}
