// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.util.EventQueue;
import com.netflix.mediaclient.servicemgr.UiLocation;
import com.netflix.mediaclient.servicemgr.Trackable;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebClientFactory;
import com.netflix.mediaclient.service.logging.presentation.PresentationRequest;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import com.netflix.mediaclient.service.logging.presentation.PresentationEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebClient;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebCallback;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.PresentationTracking;

class PresentationTrackingManager implements PresentationTracking
{
    private static final int PT_MAX_TIME_EVENT_CAN_STAY_IN_QUEUE_MS = 300000;
    private static final int PT_MIN_NUMBER_OF_PAGES_TO_POST = 50;
    static final String REPOSITORY_DIR = "ptevents";
    private static final String TAG = "nf_presentation";
    private Context mContext;
    private DataRepository mDataRepository;
    private ScheduledExecutorService mExecutor;
    private LoggingAgent mOwner;
    private PresentationWebCallback mPresentationCallback;
    private PresentationTrackingEventQueue mPresentationEventQueue;
    private PresentationWebClient mPresentationWebClient;
    private ServiceAgent.UserAgentInterface mUser;
    
    PresentationTrackingManager(final Context mContext, final LoggingAgent mOwner, final ServiceAgent.UserAgentInterface mUser) {
        this.mPresentationCallback = new PresentationWebCallback() {
            @Override
            public void onEventsDelivered(final String s) {
                if (Log.isLoggable("nf_presentation", 3)) {
                    Log.d("nf_presentation", "Events delivered for  " + s);
                }
                PresentationTrackingManager.this.mOwner.clearFailureCounter();
                PresentationTrackingManager.this.removeSavedEvents(s);
            }
            
            @Override
            public void onEventsDeliveryFailed(final String s) {
                if (Log.isLoggable("nf_presentation", 6)) {
                    Log.e("nf_presentation", "Events delivery failed for  " + s);
                }
                if (StringUtils.isEmpty(s)) {
                    return;
                }
                PresentationTrackingManager.this.mExecutor.schedule(new Runnable() {
                    @Override
                    public void run() {
                        PresentationTrackingManager.this.loadAndSendEvent(s);
                    }
                }, PresentationTrackingManager.this.mOwner.getNextTimeToDeliverAfterFailure(), TimeUnit.MILLISECONDS);
            }
        };
        this.mOwner = mOwner;
        this.mContext = mContext;
        this.mUser = mUser;
    }
    
    private void deliverSavedPayloads(final DataRepository.Entry[] array) {
        if (array == null || array.length < 1) {
            Log.d("nf_presentation", "No saved events found");
        }
        if (Log.isLoggable("nf_presentation", 3)) {
            Log.d("nf_presentation", "Found " + array.length + " payloads waiting");
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            this.mExecutor.schedule(new Runnable() {
                final /* synthetic */ String val$deliveryRequestId = array[i].getKey();
                
                @Override
                public void run() {
                    PresentationTrackingManager.this.loadAndSendEvent(this.val$deliveryRequestId);
                }
            }, this.mOwner.getNextTimeToDeliverAfterFailure(), TimeUnit.MILLISECONDS);
        }
    }
    
    private void initDataRepository() {
        Log.d("nf_presentation", "PtManager::init data repository started ");
        this.mDataRepository = new FileSystemDataRepositoryImpl(new File(this.mContext.getFilesDir(), "ptevents"));
        this.mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("nf_presentation", "Check if we have not delivered events from last time our app was runnung...");
                PresentationTrackingManager.this.mDataRepository.loadAll((DataRepository.LoadedCallback)new DataRepository.LoadedCallback() {
                    @Override
                    public void onLoaded(final Entry[] array) {
                        if (array != null && array.length > 0) {
                            PresentationTrackingManager.this.deliverSavedPayloads(array);
                            return;
                        }
                        Log.d("nf_presentation", "No saved payloads found.");
                    }
                });
            }
        });
        Log.d("nf_presentation", "PtManager::init data repository done ");
    }
    
    private void initPresentationQueue() {
        int n = 50;
        final int presentationTrackingAggregationSize = this.mOwner.getConfigurationAgent().getPresentationTrackingAggregationSize();
        if (presentationTrackingAggregationSize != -1) {
            n = presentationTrackingAggregationSize;
        }
        this.mPresentationEventQueue = new PresentationTrackingEventQueue(n);
    }
    
    private void loadAndSendEvent(final String s) {
        if (Log.isLoggable("nf_presentation", 3)) {
            Log.d("nf_presentation", "Load event " + s);
        }
        this.mDataRepository.load(s, (DataRepository.DataLoadedCallback)new DataRepository.DataLoadedCallback() {
            @Override
            public void onDataLoaded(final String s, final byte[] array, final long n) {
                if (array == null || array.length < 1) {
                    Log.e("nf_presentation", "We failed to retrieve payload. Trying to delete it");
                    PresentationTrackingManager.this.removeSavedEvents(s);
                    return;
                }
                final PresentationRequest presentationRequest = new PresentationRequest();
                try {
                    presentationRequest.initFromString(new String(array, "utf-8"));
                    PresentationTrackingManager.this.mPresentationWebClient.sendPresentationEvents(s, presentationRequest, PresentationTrackingManager.this.mPresentationCallback);
                }
                catch (Throwable t) {
                    Log.e("nf_presentation", "Failed to send events. Try to delete it.", t);
                    PresentationTrackingManager.this.removeSavedEvents(s);
                }
            }
        });
    }
    
    private void removeSavedEvents(final String s) {
        try {
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
    
    private void sendPresentationEvents(final List<PresentationEvent> list) {
        if (Log.isLoggable("nf_presentation", 3)) {
            Log.d("nf_presentation", "Send events " + list.size());
        }
        final PresentationRequest presentationRequest = new PresentationRequest(this.mContext, this.mOwner.getConfigurationAgent(), this.mUser);
        presentationRequest.addAllEvent(list);
        try {
            final String string = presentationRequest.toJSONObject().toString();
            if (Log.isLoggable("nf_presentation", 2)) {
                Log.d("nf_presentation", "Payload for presentation request " + string);
            }
            this.mPresentationWebClient.sendPresentationEvents(this.saveEvents(string), presentationRequest, this.mPresentationCallback);
        }
        catch (Exception ex) {
            Log.e("nf_presentation", "Failed to create JSON object for presentation request", ex);
        }
    }
    
    void checkState() {
        if (this.mPresentationEventQueue.flushIfCriteriaIsFulfilled()) {
            Log.d("nf_presentation", "Presentation tracking events were send recently ms. We reached timeout, force send");
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
    public void reportPresentation(final Trackable trackable, final List<String> list, final int n, final UiLocation uiLocation) {
        this.mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                final PresentationEvent presentationEvent = new PresentationEvent(trackable, list, n, uiLocation);
                if (Log.isLoggable("nf_presentation", 3)) {
                    Log.d("nf_presentation", "PresentationEvent received " + presentationEvent);
                }
                PresentationTrackingManager.this.mPresentationEventQueue.post(presentationEvent);
            }
        });
    }
    
    void resumeDelivery(final boolean b) {
        this.mPresentationEventQueue.resumeDelivery(b);
    }
    
    private class PresentationTrackingEventQueue extends EventQueue<PresentationEvent>
    {
        public PresentationTrackingEventQueue(final int n) {
            super("nf_pt_queue", n, 300000L, true, true);
        }
        
        @Override
        protected void doFlush(final List<PresentationEvent> list) {
            PresentationTrackingManager.this.sendPresentationEvents(list);
        }
    }
}
