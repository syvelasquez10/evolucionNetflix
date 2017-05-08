// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.presentation.PresentationWebClientFactory;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebCallback;
import com.netflix.mediaclient.service.logging.presentation.PresentationRequest;
import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebClient;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.PresentationTracking;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.presentation.PresentationEvent;
import java.util.List;
import com.netflix.mediaclient.servicemgr.UiLocation;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;

class PresentationTrackingManager$1 implements Runnable
{
    final /* synthetic */ PresentationTrackingManager this$0;
    final /* synthetic */ Trackable val$trackable;
    final /* synthetic */ UiLocation val$uiLocation;
    final /* synthetic */ List val$videoIds;
    final /* synthetic */ List val$videoImageTypeIdentifierIds;
    final /* synthetic */ int val$videoPos;
    
    PresentationTrackingManager$1(final PresentationTrackingManager this$0, final Trackable val$trackable, final List val$videoIds, final List val$videoImageTypeIdentifierIds, final int val$videoPos, final UiLocation val$uiLocation) {
        this.this$0 = this$0;
        this.val$trackable = val$trackable;
        this.val$videoIds = val$videoIds;
        this.val$videoImageTypeIdentifierIds = val$videoImageTypeIdentifierIds;
        this.val$videoPos = val$videoPos;
        this.val$uiLocation = val$uiLocation;
    }
    
    @Override
    public void run() {
        final PresentationEvent presentationEvent = new PresentationEvent(this.val$trackable, this.val$videoIds, this.val$videoImageTypeIdentifierIds, this.val$videoPos, this.val$uiLocation);
        if (Log.isLoggable()) {
            Log.d("nf_presentation", "PresentationEvent received " + presentationEvent);
        }
        this.this$0.mPresentationEventQueue.post(presentationEvent);
    }
}
