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
import com.netflix.mediaclient.service.logging.presentation.PresentationEvent;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebClient;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.PresentationTracking;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import com.netflix.mediaclient.util.data.DataRepository$LoadedCallback;

class PresentationTrackingManager$2$1 implements DataRepository$LoadedCallback
{
    final /* synthetic */ PresentationTrackingManager$2 this$1;
    
    PresentationTrackingManager$2$1(final PresentationTrackingManager$2 this$1) {
        this.this$1 = this$1;
    }
    
    public void onLoaded(final DataRepository$Entry[] array) {
        if (array != null && array.length > 0) {
            this.this$1.this$0.deliverSavedPayloads(array);
            return;
        }
        Log.d("nf_presentation", "No saved payloads found.");
    }
}
