// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import com.facebook.internal.Utility$FetchedAppSettings;
import com.facebook.internal.Logger;
import java.util.Set;
import com.facebook.internal.Validate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import android.content.Context;
import java.util.Iterator;
import java.util.Collection;
import android.os.Bundle;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObject$Factory;
import org.json.JSONArray;
import java.io.UnsupportedEncodingException;
import com.facebook.internal.Utility;
import java.util.ArrayList;
import com.facebook.internal.AttributionIdentifiers;
import java.util.List;

class AppEventsLogger$SessionEventsState
{
    private final int MAX_ACCUMULATED_LOG_EVENTS;
    private List<AppEventsLogger$AppEvent> accumulatedEvents;
    private AttributionIdentifiers attributionIdentifiers;
    private String hashedDeviceAndAppId;
    private List<AppEventsLogger$AppEvent> inFlightEvents;
    private int numSkippedEventsDueToFullBuffer;
    private String packageName;
    
    public AppEventsLogger$SessionEventsState(final AttributionIdentifiers attributionIdentifiers, final String packageName, final String hashedDeviceAndAppId) {
        this.accumulatedEvents = new ArrayList<AppEventsLogger$AppEvent>();
        this.inFlightEvents = new ArrayList<AppEventsLogger$AppEvent>();
        this.MAX_ACCUMULATED_LOG_EVENTS = 1000;
        this.attributionIdentifiers = attributionIdentifiers;
        this.packageName = packageName;
        this.hashedDeviceAndAppId = hashedDeviceAndAppId;
    }
    
    private byte[] getStringAsByteArray(final String s) {
        try {
            return s.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            Utility.logd("Encoding exception: ", ex);
            return null;
        }
    }
    
    private void populateRequest(final Request request, final int n, JSONArray string, final boolean b, final boolean b2) {
        Object o = GraphObject$Factory.create();
        ((GraphObject)o).setProperty("event", "CUSTOM_APP_EVENTS");
        if (this.numSkippedEventsDueToFullBuffer > 0) {
            ((GraphObject)o).setProperty("num_skipped_events", n);
        }
        if (b) {
            Utility.setAppEventAttributionParameters((GraphObject)o, this.attributionIdentifiers, this.hashedDeviceAndAppId, b2);
        }
        while (true) {
            try {
                Utility.setAppEventExtendedDeviceInfoParameters((GraphObject)o, AppEventsLogger.applicationContext);
                ((GraphObject)o).setProperty("application_package_name", this.packageName);
                request.setGraphObject((GraphObject)o);
                if ((o = request.getParameters()) == null) {
                    o = new Bundle();
                }
                string = (JSONArray)string.toString();
                if (string != null) {
                    ((Bundle)o).putByteArray("custom_events_file", this.getStringAsByteArray((String)string));
                    request.setTag(string);
                }
                request.setParameters((Bundle)o);
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public void accumulatePersistedEvents(final List<AppEventsLogger$AppEvent> list) {
        synchronized (this) {
            this.accumulatedEvents.addAll(list);
        }
    }
    
    public void addEvent(final AppEventsLogger$AppEvent appEventsLogger$AppEvent) {
        synchronized (this) {
            if (this.accumulatedEvents.size() + this.inFlightEvents.size() >= 1000) {
                ++this.numSkippedEventsDueToFullBuffer;
            }
            else {
                this.accumulatedEvents.add(appEventsLogger$AppEvent);
            }
        }
    }
    
    public void clearInFlightAndStats(final boolean b) {
        // monitorenter(this)
        Label_0020: {
            if (!b) {
                break Label_0020;
            }
            try {
                this.accumulatedEvents.addAll(this.inFlightEvents);
                this.inFlightEvents.clear();
                this.numSkippedEventsDueToFullBuffer = 0;
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    public int getAccumulatedEventCount() {
        synchronized (this) {
            return this.accumulatedEvents.size();
        }
    }
    
    public List<AppEventsLogger$AppEvent> getEventsToPersist() {
        synchronized (this) {
            final List<AppEventsLogger$AppEvent> accumulatedEvents = this.accumulatedEvents;
            this.accumulatedEvents = new ArrayList<AppEventsLogger$AppEvent>();
            return accumulatedEvents;
        }
    }
    
    public int populateRequest(final Request request, final boolean b, final boolean b2, final boolean b3) {
        final int numSkippedEventsDueToFullBuffer;
        final JSONArray jsonArray;
        synchronized (this) {
            numSkippedEventsDueToFullBuffer = this.numSkippedEventsDueToFullBuffer;
            this.inFlightEvents.addAll(this.accumulatedEvents);
            this.accumulatedEvents.clear();
            jsonArray = new JSONArray();
            for (final AppEventsLogger$AppEvent appEventsLogger$AppEvent : this.inFlightEvents) {
                if (b || !appEventsLogger$AppEvent.getIsImplicit()) {
                    jsonArray.put((Object)appEventsLogger$AppEvent.getJSONObject());
                }
            }
        }
        if (jsonArray.length() == 0) {
            // monitorexit(this)
            return 0;
        }
        // monitorexit(this)
        final Request request2;
        this.populateRequest(request2, numSkippedEventsDueToFullBuffer, jsonArray, b2, b3);
        return jsonArray.length();
    }
}
