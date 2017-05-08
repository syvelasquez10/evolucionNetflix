// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.perf;

import java.util.Map;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.BaseLoggingSession;

public class PerfSession extends BaseLoggingSession
{
    public static final String NAME = "PerformanceSession";
    private String TAG;
    SessionEndedEvent end;
    SessionStartedEvent start;
    
    public PerfSession() {
        this.TAG = "PerfSession";
    }
    
    public static PerfSession createSession(final Sessions sessions, final Map<String, String> map) {
        final PerfSession perfSession = new PerfSession();
        final PerfSession$1 startEvent = new PerfSession$1(sessions.name(), map);
        startEvent.setSessionId(perfSession.getId());
        startEvent.setCategory("PerformanceProfiler");
        perfSession.setStartEvent(startEvent);
        return perfSession;
    }
    
    private void setEndEvent(final SessionEndedEvent end) {
        this.end = end;
    }
    
    public void closeSession(final Map<String, String> map) {
        final PerfSession$2 endEvent = new PerfSession$2(this, this.start, 0L, map);
        endEvent.setCategory("PerformanceProfiler");
        endEvent.setDuration(endEvent.getTime() - this.start.getTime());
        this.setEndEvent(endEvent);
    }
    
    @Override
    public String getCategory() {
        return "PerformanceProfiler";
    }
    
    public SessionEndedEvent getEndEvent() {
        return this.end;
    }
    
    @Override
    public String getName() {
        return "PerformanceSession";
    }
    
    public SessionStartedEvent getStartEvent() {
        return this.start;
    }
    
    public String getStringId() {
        return String.valueOf(super.getId().getValue());
    }
    
    public boolean isComplete() {
        return this.start != null && this.end != null;
    }
    
    public void setStartEvent(final SessionStartedEvent start) {
        this.start = start;
    }
    
    @Override
    public String toString() {
        String string = "PerfSession: ";
        if (this.start != null) {
            string = "PerfSession: " + this.start.getName();
        }
        String string2 = string;
        if (this.end != null) {
            string2 = string + this.end.getName();
        }
        return string2;
    }
}
