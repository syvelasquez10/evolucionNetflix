// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.servicemgr.UiLocation;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebClientFactory;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.content.Intent;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import java.util.concurrent.TimeUnit;
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
import com.netflix.mediaclient.service.logging.presentation.PresentationWebCallback;
import com.netflix.mediaclient.service.logging.presentation.PresentationRequest;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;

class PresentationTrackingManager$4 implements DataRepository$DataLoadedCallback
{
    final /* synthetic */ PresentationTrackingManager this$0;
    final /* synthetic */ String val$deliveryRequestId;
    
    PresentationTrackingManager$4(final PresentationTrackingManager this$0, final String val$deliveryRequestId) {
        this.this$0 = this$0;
        this.val$deliveryRequestId = val$deliveryRequestId;
    }
    
    @Override
    public void onDataLoaded(final String s, final byte[] array, final long n) {
        if (array == null || array.length < 1) {
            Log.e("nf_presentation", "We failed to retrieve payload. Trying to delete it");
            this.this$0.removeSavedEvents(this.val$deliveryRequestId);
            return;
        }
        final PresentationRequest presentationRequest = new PresentationRequest();
        try {
            final String s2 = new String(array, "utf-8");
            presentationRequest.initFromString(s2);
            this.this$0.mPresentationWebClient.sendPresentationEvents(this.val$deliveryRequestId, presentationRequest, new PresentationTrackingManager$PresentationWebCallbackImpl(this.this$0, s2));
        }
        catch (Throwable t) {
            Log.e("nf_presentation", "Failed to send events. Try to delete it.", t);
            this.this$0.removeSavedEvents(this.val$deliveryRequestId);
        }
    }
}
