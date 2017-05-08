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

class PresentationTrackingManager implements PresentationTracking
{
    private static final int PT_MAX_TIME_EVENT_CAN_STAY_IN_QUEUE_MS = 300000;
    private static final int PT_MIN_NUMBER_OF_PAGES_TO_POST = 50;
    static final String REPOSITORY_DIR = "ptevents";
    public static final String TAG = "nf_presentation";
    private Context mContext;
    private DataRepository mDataRepository;
    private ScheduledExecutorService mExecutor;
    private LoggingAgent mOwner;
    private final List<String> mPendingCachedLogPayloads;
    private PresentationTrackingManager$PresentationTrackingEventQueue mPresentationEventQueue;
    private PresentationWebClient mPresentationWebClient;
    private ServiceAgent$UserAgentInterface mUser;
    
    PresentationTrackingManager(final Context mContext, final LoggingAgent mOwner, final ServiceAgent$UserAgentInterface mUser) {
        this.mPendingCachedLogPayloads = new ArrayList<String>();
        this.mOwner = mOwner;
        this.mContext = mContext;
        this.mUser = mUser;
    }
    
    private void deliverSavedPayloads(final DataRepository$Entry[] array) {
        if (array == null || array.length < 1) {
            Log.d("nf_presentation", "No saved events found");
        }
        else {
            if (Log.isLoggable()) {
                Log.d("nf_presentation", "Found " + array.length + " payloads waiting");
            }
            for (int length = array.length, i = 0; i < length; ++i) {
                final String key = array[i].getKey();
                if (this.mPendingCachedLogPayloads.contains(key)) {
                    Log.w("nf_presentation", "We are already trying to deliver %s deliveryRequestId, skip");
                }
                else {
                    this.mPendingCachedLogPayloads.add(key);
                    this.mExecutor.schedule(new PresentationTrackingManager$3(this, key), this.mOwner.getNextTimeToDeliverAfterFailure(), TimeUnit.MILLISECONDS);
                }
            }
        }
    }
    
    private void initDataRepository() {
        Log.d("nf_presentation", "PtManager::init data repository started ");
        final File file = new File(this.mContext.getFilesDir(), "ptevents");
        file.mkdirs();
        this.mDataRepository = new FileSystemDataRepositoryImpl(file);
        this.mExecutor.execute(new PresentationTrackingManager$2(this));
        Log.d("nf_presentation", "PtManager::init data repository done ");
    }
    
    private void initPresentationQueue() {
        int presentationTrackingAggregationSize = this.mOwner.getConfigurationAgent().getPresentationTrackingAggregationSize();
        if (presentationTrackingAggregationSize == -1) {
            presentationTrackingAggregationSize = 50;
        }
        this.mPresentationEventQueue = new PresentationTrackingManager$PresentationTrackingEventQueue(this, presentationTrackingAggregationSize);
    }
    
    private void loadAndSendEvent(final String s) {
        if (Log.isLoggable()) {
            Log.d("nf_presentation", "Load event " + s);
        }
        this.mDataRepository.load(s, new PresentationTrackingManager$4(this, s));
    }
    
    private void removeSavedEvents(final String s) {
        try {
            this.mPendingCachedLogPayloads.remove(s);
            this.mDataRepository.remove(s);
        }
        catch (Throwable t) {
            Log.e("nf_presentation", "Failed to remove payload from repository", t);
        }
    }
    
    private String saveEvents(String save) {
        try {
            save = this.mDataRepository.save(String.valueOf(System.currentTimeMillis()), save.getBytes("utf-8"));
            return save;
        }
        catch (Throwable t) {
            Log.e("nf_presentation", "Failed to save payload to repository", t);
            return null;
        }
    }
    
    private void sendPresentationEvents(final List<PresentationEvent> list, final boolean b) {
        if (Log.isLoggable()) {
            Log.d("nf_presentation", "Send events " + list.size());
        }
        if (list.size() == 0) {
            Log.d("nf_presentation", "Dropping presentation message - 0 events");
            return;
        }
        final PresentationRequest presentationRequest = new PresentationRequest(this.mContext, this.mOwner.getConfigurationAgent(), this.mUser);
        presentationRequest.addAllEvent(list);
        try {
            final String string = presentationRequest.toJSONObject().toString();
            if (Log.isLoggable()) {
                Log.d("nf_presentation", "Payload for presentation request " + string);
            }
            if (b) {
                final String saveEvents = this.saveEvents(string);
                this.mPendingCachedLogPayloads.add(saveEvents);
                this.mPresentationWebClient.sendPresentationEvents(saveEvents, presentationRequest, new PresentationTrackingManager$PresentationWebCallbackImpl(this, string));
                return;
            }
        }
        catch (Exception ex) {
            Log.e("nf_presentation", "Failed to create JSON object for presentation request", ex);
            return;
        }
        this.mPresentationWebClient.sendPresentationEvents(presentationRequest);
    }
    
    void checkState() {
        if (this.mPresentationEventQueue.flushIfCriteriaIsFulfilled()) {
            Log.d("nf_presentation", "Presentation tracking events were send recently ms. We reached timeout, force send");
        }
    }
    
    void flush(final boolean b) {
        this.mPresentationEventQueue.flushEvents(b);
    }
    
    public void handleConnectivityChange(final Intent intent) {
        if (ConnectivityUtils.isConnectedOrConnecting(this.mContext)) {
            Log.d("nf_presentation", "Device is connected, lets see if we need to deliver cached events...");
            final DataRepository$Entry[] entries = this.mDataRepository.getEntries();
            if (entries != null || entries.length > 0) {
                Log.d("nf_presentation", "We found %d cached log entries, network is connected, lets try to deliver them");
                this.deliverSavedPayloads(entries);
            }
        }
    }
    
    void init(final ScheduledExecutorService mExecutor) {
        this.mExecutor = mExecutor;
        this.initPresentationQueue();
        this.mPresentationWebClient = PresentationWebClientFactory.create(this.mOwner.getResourceFetcher().getApiNextWebClient());
        this.initDataRepository();
    }
    
    void pauseDelivery() {
        this.mPresentationEventQueue.pauseDelivery();
    }
    
    @Override
    public void reportPresentation(final Trackable trackable, final List<String> list, final List<String> list2, final int n, final UiLocation uiLocation) {
        this.mExecutor.execute(new PresentationTrackingManager$1(this, trackable, list, list2, n, uiLocation));
    }
    
    void resumeDelivery(final boolean b) {
        this.mPresentationEventQueue.resumeDelivery(b);
    }
}
