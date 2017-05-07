// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.servicemgr.UiLocation;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebClientFactory;
import com.netflix.mediaclient.service.logging.presentation.PresentationRequest;
import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import com.netflix.mediaclient.service.logging.presentation.PresentationEvent;
import java.util.List;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebClient;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.PresentationTracking;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebCallback;

class PresentationTrackingManager$PresentationWebCallbackImpl implements PresentationWebCallback
{
    final /* synthetic */ PresentationTrackingManager this$0;
    
    public PresentationTrackingManager$PresentationWebCallbackImpl(final PresentationTrackingManager this$0, final String s) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onEventsDelivered(final String s) {
        if (Log.isLoggable()) {
            Log.d("nf_presentation", "Events delivered for  " + s);
        }
        this.this$0.mOwner.clearFailureCounter();
        this.this$0.removeSavedEvents(s);
    }
    
    @Override
    public void onEventsDeliveryFailed(final String s) {
        if (Log.isLoggable()) {
            Log.e("nf_presentation", "Events delivery failed for  " + s);
        }
        if (StringUtils.isEmpty(s)) {
            return;
        }
        this.this$0.mExecutor.schedule(new PresentationTrackingManager$PresentationWebCallbackImpl$1(this, s), this.this$0.mOwner.getNextTimeToDeliverAfterFailure(), TimeUnit.MILLISECONDS);
    }
}
