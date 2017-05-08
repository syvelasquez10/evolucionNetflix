// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.util.ConnectivityUtils;
import android.content.Intent;
import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONObject;
import org.json.JSONArray;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import com.netflix.mediaclient.service.msl.volley.MSLVolleyRequest;
import com.netflix.mediaclient.service.logging.logpds.volley.SendPdsEventsMSLRequest;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;

public class PdsLoggingImpl implements IPdsLogging
{
    private static final int MAX_TIME_THAN_EVENT_CAN_STAY_IN_QUEUE_MS = 120000;
    private static final int MIN_NUMBER_OF_EVENTS_TO_POST = 6;
    private static final String REPOSITORY_DIR = "pdsevents";
    private static final String TAG = "nf_pds_logs";
    private Context mContext;
    private DataRepository mDataRepository;
    private PdsLoggingImpl$PdsEventQueue mEventQueue;
    private ScheduledExecutorService mExecutor;
    private LoggingAgent mOwner;
    private final List<String> mPendingCachedLogPayloads;
    
    public PdsLoggingImpl(final LoggingAgent mOwner) {
        this.mEventQueue = new PdsLoggingImpl$PdsEventQueue(this);
        this.mPendingCachedLogPayloads = new ArrayList<String>();
        if (mOwner == null) {
            throw new IllegalStateException("Owner is null?");
        }
        this.mOwner = mOwner;
    }
    
    private void deliverSavedPayloads(final DataRepository$Entry[] array, final boolean b) {
        if (array == null || array.length < 1) {
            Log.d("nf_pds_logs", "No saved events found");
        }
        else {
            if (Log.isLoggable()) {
                Log.d("nf_pds_logs", "Found " + array.length + " payloads waiting");
            }
            for (int length = array.length, i = 0; i < length; ++i) {
                final String key = array[i].getKey();
                if (this.mPendingCachedLogPayloads.contains(key)) {
                    Log.w("nf_pds_logs", "We are already trying to deliver %s deliveryRequestId, skip", key);
                }
                else {
                    this.mPendingCachedLogPayloads.add(key);
                    if (b) {
                        this.mExecutor.schedule(new PdsLoggingImpl$2(this, key), this.mOwner.getNextTimeToDeliverAfterFailure(), TimeUnit.MILLISECONDS);
                    }
                    else {
                        this.mExecutor.execute(new PdsLoggingImpl$3(this, key));
                    }
                }
            }
        }
    }
    
    private void flushEventsInQueue(final List<String> list, final boolean b) {
        Log.d("nf_pds_logs", "flushing events in queue (%d)", list.size());
        final String[] array = list.toArray(new String[list.size()]);
        if (array == null || array.length < 1) {
            Log.d("nf_pds_logs", "dropping bad pds event");
            return;
        }
        PdsLoggingImpl$PdsEventsSentCallback pdsLoggingImpl$PdsEventsSentCallback = null;
        Label_0102: {
            if (!b) {
                break Label_0102;
            }
            try {
                final String saveEvents = this.saveEvents(this.toJsonString(list));
                this.mPendingCachedLogPayloads.add(saveEvents);
                pdsLoggingImpl$PdsEventsSentCallback = new PdsLoggingImpl$PdsEventsSentCallbackImpl(this, saveEvents);
                this.mOwner.getMSLClient().addRequest(new SendPdsEventsMSLRequest(array, pdsLoggingImpl$PdsEventsSentCallback));
            }
            catch (Exception ex) {
                Log.e("nf_pds_logs", "Failed to create JSON object for logging request", ex);
            }
        }
    }
    
    private void initDataRepository() {
        Log.d("nf_pds_logs", "::init data repository started ");
        final File file = new File(this.mContext.getFilesDir(), "pdsevents");
        file.mkdirs();
        this.mDataRepository = (DataRepository)new FileSystemDataRepositoryImpl(file);
        this.mExecutor.execute(new PdsLoggingImpl$1(this));
        Log.d("nf_pds_logs", "::init data repository done ");
    }
    
    private void loadAndSendSavedPdsEvents(final String s) {
        if (Log.isLoggable()) {
            Log.d("nf_pds_logs", "Load pdsEvent " + s);
        }
        this.mDataRepository.load(s, (DataRepository$DataLoadedCallback)new PdsLoggingImpl$4(this, s));
    }
    
    private void removeSavedPdsEvents(final String s) {
        try {
            this.mPendingCachedLogPayloads.remove(s);
            this.mDataRepository.remove(s);
        }
        catch (Throwable t) {
            Log.e("nf_pds_logs", "Failed to remove payload from repository", t);
        }
    }
    
    private String saveEvents(String save) {
        try {
            save = this.mDataRepository.save(String.valueOf(System.currentTimeMillis()), save.getBytes("utf-8"));
            return save;
        }
        catch (Throwable t) {
            Log.e("nf_pds_logs", "Failed to save payload to repository", t);
            return null;
        }
    }
    
    private void sendSavedPdsEventBundle(final String s, final PdsLoggingImpl$PdsEventsSentCallback pdsLoggingImpl$PdsEventsSentCallback) {
        Log.d("nf_pds_logs", "sendSavedPdsEventBundle start...");
        final String[] pdsEvents = this.toPdsEvents(s);
        if (pdsEvents == null || pdsEvents.length < 1) {
            Log.d("nf_pds_logs", "Nothing to sent, array is null. sendSavedPdsEventBundle done.");
            pdsLoggingImpl$PdsEventsSentCallback.onPdsEventsSent(CommonStatus.OK);
            return;
        }
        this.mOwner.getMSLClient().addRequest(new SendPdsEventsMSLRequest(pdsEvents, pdsLoggingImpl$PdsEventsSentCallback));
        Log.d("nf_pds_logs", "sendSavedPdsEventBundle done.");
    }
    
    private String toJsonString(final List<String> list) {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsonArray.put((Object)new JSONObject((String)iterator.next()));
        }
        return jsonArray.toString();
    }
    
    private String[] toPdsEvents(final String s) {
    Label_0082_Outer:
        while (true) {
            JSONArray jsonArray = null;
            String[] array;
            int n = 0;
            String[] array2 = null;
            try {
                jsonArray = new JSONArray(s);
                array = new String[jsonArray.length()];
                n = 0;
                while (true) {
                    array2 = array;
                    final int n2 = n;
                    final JSONArray jsonArray2 = jsonArray;
                    final int n3 = jsonArray2.length();
                    if (n2 >= n3) {
                        return array2;
                    }
                    final String[] array3 = array;
                    final int n4 = n;
                    final JSONArray jsonArray3 = jsonArray;
                    final int n5 = n;
                    final JSONObject jsonObject = jsonArray3.getJSONObject(n5);
                    final String s2 = jsonObject.toString();
                    array3[n4] = s2;
                    final String s3 = "nf_pds_logs";
                    final String s4 = "toPdsEvents: %d : %s";
                    final int n6 = 2;
                    final Object[] array4 = new Object[n6];
                    final int n7 = 0;
                    final int n8 = n;
                    final Integer n9 = n8;
                    array4[n7] = n9;
                    final int n10 = 1;
                    final String[] array5 = array;
                    final int n11 = n;
                    final String s5 = array5[n11];
                    array4[n10] = s5;
                    Log.d(s3, s4, array4);
                    final int n12 = n;
                    final int n13 = 1;
                    final int n14 = n = n12 + n13;
                }
            }
            catch (JSONException ex) {
                array = null;
            }
            while (true) {
                try {
                    final int n2 = n;
                    final JSONArray jsonArray2 = jsonArray;
                    final int n3 = jsonArray2.length();
                    if (n2 < n3) {
                        final String[] array3 = array;
                        final int n4 = n;
                        final JSONArray jsonArray3 = jsonArray;
                        final int n5 = n;
                        final JSONObject jsonObject = jsonArray3.getJSONObject(n5);
                        final String s2 = jsonObject.toString();
                        array3[n4] = s2;
                        final String s3 = "nf_pds_logs";
                        final String s4 = "toPdsEvents: %d : %s";
                        final int n6 = 2;
                        final Object[] array4 = new Object[n6];
                        final int n7 = 0;
                        final int n8 = n;
                        final Integer n9 = n8;
                        array4[n7] = n9;
                        final int n10 = 1;
                        final String[] array5 = array;
                        final int n11 = n;
                        final String s5 = array5[n11];
                        array4[n10] = s5;
                        Log.d(s3, s4, array4);
                        final int n12 = n;
                        final int n13 = 1;
                        n = n12 + n13;
                        continue Label_0082_Outer;
                    }
                    return array2;
                    final JSONException ex;
                    Log.e("nf_pds_logs", "Unable to create JSON array from payload " + s, (Throwable)ex);
                    array2 = array;
                    return array2;
                }
                catch (JSONException ex) {
                    continue;
                }
                break;
            }
            break;
        }
    }
    
    public void checkState() {
        if (this.mEventQueue.flushIfCriteriaIsFulfilled()) {
            Log.d("nf_pds_logs", "PdsLog events were sent recently. We reached timeout, force send");
        }
    }
    
    void destroy() {
    }
    
    @Override
    public void flushEventsInLogging() {
        this.mEventQueue.flushEvents(true);
    }
    
    public void handleConnectivityChange(final Intent intent) {
        if (ConnectivityUtils.isConnectedOrConnecting(this.mContext)) {
            Log.d("nf_pds_logs", "Device is connected, lets see if we need to deliver cached events...");
            final DataRepository$Entry[] entries = this.mDataRepository.getEntries();
            if (entries != null || entries.length > 0) {
                Log.d("nf_pds_logs", "We found %d cached log entries, network is connected, lets try to deliver them", entries.length);
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
    public void sendPdsEventViaLogging(final String s) {
        this.mEventQueue.post((Object)s);
    }
}
