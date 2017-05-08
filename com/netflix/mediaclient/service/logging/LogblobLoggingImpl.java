// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.logblob.BaseLogblob;
import com.netflix.mediaclient.javabridge.ui.LogArguments;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.content.Intent;
import org.json.JSONException;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.msl.volley.MSLVolleyRequest;
import com.netflix.mediaclient.service.logging.logblob.volley.SendLogblobsMSLRequest;
import com.netflix.mediaclient.service.logging.logblob.LogblobUtils;
import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.Logblob$CommonParams;
import com.netflix.mediaclient.servicemgr.Logblob;
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.LogblobLogging;

public class LogblobLoggingImpl implements LogblobLogging
{
    private static final int MAX_TIME_THAN_EVENT_CAN_STAY_IN_QUEUE_MS = 60000;
    private static final int MIN_NUMBER_OF_EVENTS_TO_POST = 30;
    private static final String REPOSITORY_DIR = "logblobs";
    private static final String TAG = "nf_logblob";
    private Context mContext;
    private DataRepository mDataRepository;
    private LogblobLoggingImpl$LogblobQueue mEventQueue;
    private ScheduledExecutorService mExecutor;
    private LoggingAgent mOwner;
    private final List<String> mPendingCachedLogPayloads;
    
    public LogblobLoggingImpl(final LoggingAgent mOwner) {
        this.mEventQueue = new LogblobLoggingImpl$LogblobQueue(this);
        this.mPendingCachedLogPayloads = new ArrayList<String>();
        if (mOwner == null) {
            throw new IllegalStateException("Owner is null?");
        }
        this.mOwner = mOwner;
    }
    
    private Logblob$CommonParams createCommonParams() {
        return new Logblob$CommonParams(this.mOwner.getConfigurationAgent().getEsnProvider().getEsn(), this.mOwner.getApplicationId(), this.mOwner.getUserSessionId(), this.mOwner.getConfigurationAgent().getEsnProvider().getDeviceModel());
    }
    
    private void deliverSavedPayloads(final DataRepository$Entry[] array, final boolean b) {
        if (array == null || array.length < 1) {
            Log.d("nf_logblob", "No saved events found");
        }
        else {
            if (Log.isLoggable()) {
                Log.d("nf_logblob", "Found " + array.length + " payloads waiting");
            }
            for (int length = array.length, i = 0; i < length; ++i) {
                final String key = array[i].getKey();
                if (this.mPendingCachedLogPayloads.contains(key)) {
                    Log.w("nf_logblob", "We are already trying to deliver %s deliveryRequestId, skip");
                }
                else {
                    if (b) {
                        this.mExecutor.schedule(new LogblobLoggingImpl$2(this, key), this.mOwner.getNextTimeToDeliverAfterFailure(), TimeUnit.MILLISECONDS);
                    }
                    else {
                        this.mExecutor.execute(new LogblobLoggingImpl$3(this, key));
                    }
                    this.mPendingCachedLogPayloads.add(key);
                }
            }
        }
    }
    
    private void initDataRepository() {
        Log.d("nf_logblob", "::init data repository started ");
        final File file = new File(this.mContext.getFilesDir(), "logblobs");
        file.mkdirs();
        this.mDataRepository = new FileSystemDataRepositoryImpl(file);
        this.mExecutor.execute(new LogblobLoggingImpl$1(this));
        Log.d("nf_logblob", "::init data repository done ");
    }
    
    private void loadAndSendLogblobs(final String s) {
        if (Log.isLoggable()) {
            Log.d("nf_logblob", "Load logblobs " + s);
        }
        this.mDataRepository.load(s, new LogblobLoggingImpl$4(this, s));
    }
    
    private void removeSavedLogblobs(final String s) {
        try {
            this.mPendingCachedLogPayloads.remove(s);
            this.mDataRepository.remove(s);
        }
        catch (Throwable t) {
            Log.e("nf_logblob", "Failed to remove payload from repository", t);
        }
    }
    
    private String saveEvents(String save) {
        try {
            save = this.mDataRepository.save(String.valueOf(System.currentTimeMillis()), save.getBytes("utf-8"));
            return save;
        }
        catch (Throwable t) {
            Log.e("nf_logblob", "Failed to save payload to repository", t);
            return null;
        }
    }
    
    private void sendEvents(final List<Logblob> list, final boolean b) {
        if (Log.isLoggable()) {
            Log.d("nf_logblob", "Send events " + list.size());
        }
        if (list == null || list.size() < 1) {
            Log.d("nf_logblob", "dropping bad logblob");
            return;
        }
        try {
            final String jsonString = LogblobUtils.toJsonString(list);
            if (Log.isLoggable()) {
                Log.v("nf_logblob", "Payload for logblob request: ");
                Log.dumpVerbose("nf_logblob", jsonString);
            }
            LogblobLoggingImpl$LogblobsSentCallback logblobLoggingImpl$LogblobsSentCallback = null;
            if (b) {
                final String saveEvents = this.saveEvents(jsonString);
                this.mPendingCachedLogPayloads.add(saveEvents);
                logblobLoggingImpl$LogblobsSentCallback = new LogblobLoggingImpl$LogblobsSentCallbackImpl(this, saveEvents);
            }
            this.mOwner.getMSLClient().addRequest(new SendLogblobsMSLRequest(this.mOwner.getContext(), list, this.createCommonParams(), logblobLoggingImpl$LogblobsSentCallback));
        }
        catch (Exception ex) {
            Log.e("nf_logblob", "Failed to create JSON object for logging request", ex);
        }
    }
    
    private void sendLogblobs(final String s, final LogblobLoggingImpl$LogblobsSentCallback logblobLoggingImpl$LogblobsSentCallback) {
        Log.d("nf_logblob", "sendLogblobs starts...");
        final List<Logblob> logBlobs = LogblobUtils.toLogBlobs(s);
        if (logBlobs == null || logBlobs.size() < 1) {
            Log.d("nf_logblob", "Nothing to sent, array is null. SendLogblobs done.");
            logblobLoggingImpl$LogblobsSentCallback.onLogblobsSent(CommonStatus.OK);
            return;
        }
        try {
            this.mOwner.getMSLClient().addRequest(new SendLogblobsMSLRequest(this.mOwner.getContext(), logBlobs, this.createCommonParams(), logblobLoggingImpl$LogblobsSentCallback));
            Log.d("nf_logblob", "sendLogblobs done.");
        }
        catch (JSONException ex) {
            Log.e("nf_logblob", (Throwable)ex, "Failed to add common parameters to JSON logbob?!", new Object[0]);
        }
    }
    
    void destroy() {
    }
    
    public void handleConnectivityChange(final Intent intent) {
        if (ConnectivityUtils.isConnectedOrConnecting(this.mContext)) {
            Log.d("nf_logblob", "Device is connected, lets see if we need to deliver cached events...");
            final DataRepository$Entry[] entries = this.mDataRepository.getEntries();
            if (entries != null || entries.length > 0) {
                Log.d("nf_logblob", "We found %d cached log entries, network is connected, lets try to deliver them");
                this.deliverSavedPayloads(entries, false);
            }
        }
    }
    
    void init(final ScheduledExecutorService mExecutor) {
        this.mExecutor = mExecutor;
        this.mContext = this.mOwner.getContext();
        this.initDataRepository();
    }
    
    @Override
    public void sendLogblob(final LogArguments logArguments) {
        try {
            this.sendLogblob(new com.netflix.mediaclient.service.logging.logblob.Log(logArguments));
        }
        catch (JSONException ex) {
            Log.e("nf_logblob", (Throwable)ex, "Failed to populate JSON", new Object[0]);
        }
    }
    
    @Override
    public void sendLogblob(final Logblob logblob) {
        final String applicationId = this.mOwner.getApplicationId();
        final String userSessionId = this.mOwner.getUserSessionId();
        while (true) {
            Label_0086: {
                if (!(logblob instanceof BaseLogblob)) {
                    break Label_0086;
                }
                try {
                    ((BaseLogblob)logblob).init(this.mOwner.getContext(), applicationId, userSessionId);
                    if (!this.mEventQueue.post(logblob) && logblob.shouldSendNow()) {
                        this.mEventQueue.flushEvents(true);
                    }
                    return;
                }
                catch (JSONException ex) {
                    Log.e("nf_logblob", (Throwable)ex, "Failed to populate JSON", new Object[0]);
                    continue;
                }
            }
            if (logblob != null) {
                Log.w("nf_logblob", "Unable to set common data by framework, logblob does not implement BaseLogblob %s ", logblob.toString());
                continue;
            }
            Log.e("nf_logblob", "Message is null!");
            continue;
        }
    }
}
