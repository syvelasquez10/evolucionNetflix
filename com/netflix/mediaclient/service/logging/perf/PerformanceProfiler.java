// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.perf;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import android.os.SystemClock;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import android.widget.Toast;
import com.netflix.mediaclient.util.FileUtils;
import org.json.JSONException;
import android.support.v4.app.ActivityCompat;
import android.content.Context;
import com.netflix.mediaclient.util.PermissionUtils;
import android.app.Activity;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import java.util.Collections;
import java.util.Map;
import org.json.JSONObject;
import java.util.HashMap;
import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;
import java.util.ArrayList;

public class PerformanceProfiler
{
    public static final String CATEGORY = "PerformanceProfiler";
    public static final String FAILURE = "failed";
    private static final String GRAPH_COLOR_FAILED = "red";
    private static final String GRAPH_COLOR_SUCCESS = "green";
    private static final int GRAPH_DISCRETE_EVENT_WIDTH = 30;
    private static final String GRAPH_PARAM_COLOR = "color";
    private static final String GRAPH_PARAM_DURATION = "duration";
    private static final String GRAPH_PARAM_EPOCH = "epoch";
    private static final String OUTPUT_FILENAME = "perf_dump.txt";
    private static final String TAG = "PerformanceProfiler";
    private static PerformanceProfiler instance;
    ArrayList<DiscreteEvent> events;
    HashMap<String, PerfSession> sessions;
    
    private PerformanceProfiler() {
        this.events = new ArrayList<DiscreteEvent>();
        this.sessions = new HashMap<String, PerfSession>();
    }
    
    private void addTimes(final JSONObject jsonObject, final long n, final long n2, final boolean b, final StringBuilder sb) {
        jsonObject.put("epoch", this.getEpoch());
        jsonObject.put("duration", n2 - n);
        String s;
        if (b) {
            s = "red";
        }
        else {
            s = "green";
        }
        jsonObject.put("color", (Object)s);
        sb.append(jsonObject);
        sb.append(",");
    }
    
    public static DiscreteEvent createEvent(final Events events, final Map<String, String> map) {
        final PerformanceProfiler$1 performanceProfiler$1 = new PerformanceProfiler$1(map);
        performanceProfiler$1.setName(events.name());
        performanceProfiler$1.setCategory("PerformanceProfiler");
        return performanceProfiler$1;
    }
    
    public static Map<String, String> createFailedMap() {
        return Collections.singletonMap("failed", "true");
    }
    
    public static PerformanceProfiler getInstance() {
        if (PerformanceProfiler.instance == null) {
            PerformanceProfiler.instance = new PerformanceProfiler();
        }
        return PerformanceProfiler.instance;
    }
    
    private boolean hasFailed(JSONObject optJSONObject) {
        optJSONObject = optJSONObject.optJSONObject("custom");
        return optJSONObject != null && optJSONObject.optBoolean("failed");
    }
    
    private void prepDataForGraphing(final DiscreteEvent discreteEvent, final StringBuilder sb) {
        this.addTimes(discreteEvent.toJSONObject(), discreteEvent.getTime(), discreteEvent.getTime() + 30L, this.hasFailed(discreteEvent.toJSONObject()), sb);
    }
    
    private void prepDataForGraphing(final PerfSession perfSession, final StringBuilder sb) {
        final JSONObject jsonObject = perfSession.getStartEvent().toJSONObject();
        if (perfSession.getEndEvent() != null) {
            this.addTimes(jsonObject, perfSession.getStartEvent().getTime(), perfSession.getEndEvent().getTime(), this.hasFailed(perfSession.getEndEvent().toJSONObject()), sb);
            return;
        }
        Log.e("PerformanceProfiler", "Session not closed, so we can't graph it..." + perfSession);
    }
    
    private void warnOfOpenSessions() {
        for (final PerfSession perfSession : this.sessions.values()) {
            if (!perfSession.isComplete() && Log.isLoggable()) {
                Log.e("PerformanceProfiler", "Session not closed!! :" + perfSession.toString());
            }
        }
    }
    
    public void clear() {
        this.events.clear();
        this.sessions.clear();
    }
    
    public void dumpToDisk(final Activity activity) {
        this.warnOfOpenSessions();
        if (PermissionUtils.shouldRequestPermission((Context)activity, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            ActivityCompat.requestPermissions(activity, new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" }, 232);
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (final DiscreteEvent discreteEvent : this.events) {
            try {
                this.prepDataForGraphing(discreteEvent, sb);
            }
            catch (JSONException ex) {
                Log.i("PerformanceProfiler", "DiscreteEvent prep failed: " + ex.getMessage());
            }
        }
        for (final PerfSession perfSession : this.sessions.values()) {
            try {
                this.prepDataForGraphing(perfSession, sb);
            }
            catch (JSONException ex2) {
                Log.i("PerformanceProfiler", "Session prep failed: " + ex2.getMessage());
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        if (FileUtils.writeStringToFile("PerformanceProfiler", sb.toString(), "perf_dump.txt")) {
            Toast.makeText((Context)activity, (CharSequence)"File dumped! Please run perfScripts/perf.sh", 0).show();
            Log.i("PerformanceProfiler", "File dumped! Please run perfScripts/perf.sh");
        }
        else {
            Toast.makeText((Context)activity, (CharSequence)"File dump failed!", 0).show();
            Log.i("PerformanceProfiler", "File dump failed!");
        }
        this.clear();
    }
    
    public void endSession(final Sessions sessions) {
        for (final PerfSession perfSession : this.sessions.values()) {
            if (perfSession.getEndEvent() == null && perfSession.getStartEvent().getSessionName().equals(sessions.name())) {
                this.endSession(sessions, null, perfSession.getStringId());
            }
        }
    }
    
    public void endSession(final Sessions sessions, final Map<String, String> map) {
        for (final PerfSession perfSession : this.sessions.values()) {
            if (perfSession.getEndEvent() == null && perfSession.getStartEvent().getSessionName().equals(sessions.name())) {
                this.endSession(sessions, map, perfSession.getStringId());
            }
        }
    }
    
    public void endSession(final Sessions sessions, final Map<String, String> map, final String s) {
        if (s != null && this.sessions.containsKey(s)) {
            final PerfSession perfSession = this.sessions.get(s);
            if (perfSession != null) {
                if (Log.isLoggable()) {
                    Log.v("PerformanceProfiler", "endSession: " + sessions.toString());
                }
                perfSession.closeSession(map);
            }
            return;
        }
        Log.i("PerformanceProfiler", "Couldn't find the SessionStartedEvent");
    }
    
    public void flushApmEvents(final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging) {
        this.warnOfOpenSessions();
        final Iterator<DiscreteEvent> iterator = this.events.iterator();
        while (iterator.hasNext()) {
            ApmLogUtils.reportPerformanceEvent(iterator.next(), applicationPerformanceMetricsLogging);
        }
        for (final PerfSession perfSession : this.sessions.values()) {
            if (perfSession.isComplete() && !Sessions.IMAGE_FETCH.name().equals(perfSession.getName())) {
                ApmLogUtils.startPerformanceSession(perfSession, applicationPerformanceMetricsLogging);
                ApmLogUtils.endPerformanceSession(perfSession, applicationPerformanceMetricsLogging);
            }
        }
        this.clear();
    }
    
    public long getEpoch() {
        if (this.events.size() > 0) {
            return this.events.get(0).getTime();
        }
        return SystemClock.elapsedRealtime();
    }
    
    public void logEvent(final Events events, final Map<String, String> map) {
        this.events.add(createEvent(events, map));
        if (Log.isLoggable()) {
            Log.v("PerformanceProfiler", "logEvent: " + events.toString());
        }
    }
    
    public String startSession(final Sessions sessions) {
        final PerfSession session = PerfSession.createSession(sessions, null);
        this.sessions.put(String.valueOf(session.getId().getValue()), session);
        if (Log.isLoggable()) {
            Log.v("PerformanceProfiler", "startSession: " + session.toString());
        }
        return String.valueOf(session.getId().getValue());
    }
    
    public String startSession(final Sessions sessions, final Map<String, String> map) {
        final PerfSession session = PerfSession.createSession(sessions, map);
        this.sessions.put(String.valueOf(session.getId().getValue()), session);
        if (Log.isLoggable()) {
            Log.v("PerformanceProfiler", "startSession: " + session.toString());
        }
        return String.valueOf(session.getId().getValue());
    }
    
    public void startSession(final Sessions sessions, final Map<String, String> map, final String s) {
        final PerfSession session = PerfSession.createSession(sessions, map);
        this.sessions.put(s, session);
        if (Log.isLoggable()) {
            Log.v("PerformanceProfiler", "startSession: " + session.toString());
        }
    }
}
