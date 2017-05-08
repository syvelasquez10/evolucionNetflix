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
import com.netflix.mediaclient.service.logging.presentation.PresentationEvent;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebClient;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.PresentationTracking;

class PresentationTrackingManager$PresentationWebCallbackImpl$1 implements Runnable
{
    final /* synthetic */ PresentationTrackingManager$PresentationWebCallbackImpl this$1;
    final /* synthetic */ String val$deliveryRequestId;
    
    PresentationTrackingManager$PresentationWebCallbackImpl$1(final PresentationTrackingManager$PresentationWebCallbackImpl this$1, final String val$deliveryRequestId) {
        this.this$1 = this$1;
        this.val$deliveryRequestId = val$deliveryRequestId;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.loadAndSendEvent(this.val$deliveryRequestId);
    }
}
