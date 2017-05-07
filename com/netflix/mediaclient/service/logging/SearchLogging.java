// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.search.model.SearchSessionStartedEvent;
import com.netflix.mediaclient.service.logging.search.model.SearchFocusSessionStartedEvent;
import com.netflix.mediaclient.service.logging.search.model.SearchImpressionEvent;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.search.model.SearchEditEvent;
import android.content.Intent;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import com.netflix.mediaclient.service.logging.search.model.SearchSessionEndedEvent;
import com.netflix.mediaclient.service.logging.search.SearchSession;
import com.netflix.mediaclient.service.logging.search.model.SearchFocusSessionEndedEvent;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.search.SearchFocusSession;
import java.util.Hashtable;
import com.netflix.mediaclient.service.logging.apm.BaseApmSession;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.ISearchLogging;

public class SearchLogging implements ISearchLogging
{
    private static final String TAG = "nf_log_search";
    private Map<Long, BaseApmSession> focusFocusSessions;
    private EventHandler mEventHandler;
    private Map<Long, BaseApmSession> searchSessions;
    
    SearchLogging(final EventHandler mEventHandler) {
        this.focusFocusSessions = new Hashtable<Long, BaseApmSession>();
        this.searchSessions = new Hashtable<Long, BaseApmSession>();
        this.mEventHandler = mEventHandler;
    }
    
    private void stopFocusSession(final long n) {
        synchronized (this) {
            if (this.mEventHandler != null && n != 0L) {
                final SearchFocusSession searchFocusSession = this.focusFocusSessions.get(n);
                if (searchFocusSession != null) {
                    this.mEventHandler.removeSession(searchFocusSession);
                    final SearchFocusSessionEndedEvent endedEvent = searchFocusSession.createEndedEvent();
                    this.mEventHandler.post(endedEvent);
                    this.focusFocusSessions.remove(n);
                    if (Log.isLoggable("nf_log_search", 3)) {
                        try {
                            Log.d("nf_log_search", "SearchFocusSession stop done." + endedEvent.toJSONObject().toString(5));
                        }
                        catch (JSONException ex) {}
                    }
                }
            }
        }
    }
    
    private void stopSession(final long n) {
        if (this.mEventHandler != null && n != 0L) {
            final SearchSession searchSession = this.searchSessions.get(n);
            if (searchSession != null) {
                this.mEventHandler.removeSession(searchSession);
                final SearchSessionEndedEvent endedEvent = searchSession.createEndedEvent();
                this.mEventHandler.post(endedEvent);
                this.searchSessions.remove(n);
                if (Log.isLoggable("nf_log_search", 3)) {
                    try {
                        Log.d("nf_log_search", "Search session stop done." + endedEvent.toJSONObject().toString(5));
                    }
                    catch (JSONException ex) {}
                }
            }
        }
    }
    
    @Override
    public void endAllActiveSessions() {
        synchronized (this) {
            final HashSet<Long> set = new HashSet<Long>(this.focusFocusSessions.size());
            set.addAll((Collection<?>)this.focusFocusSessions.keySet());
            final Iterator<Object> iterator = set.iterator();
            while (iterator.hasNext()) {
                this.stopFocusSession(iterator.next());
            }
        }
        final HashSet<Long> set2 = new HashSet<Long>(this.searchSessions.size());
        set2.addAll((Collection<?>)this.searchSessions.keySet());
        final Iterator<Object> iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            this.stopSession(iterator2.next());
        }
        this.focusFocusSessions.clear();
        this.searchSessions.clear();
    }
    // monitorexit(this)
    
    @Override
    public void fireEditEvent(final Intent intent) {
        if (this.mEventHandler != null) {
            final SearchEditEvent searchEditEvent = new SearchEditEvent(intent.getStringExtra("query"));
            this.mEventHandler.post(searchEditEvent);
            if (Log.isLoggable("nf_log_search", 3)) {
                try {
                    Log.d("nf_log_search", "Search Edit Event fired" + searchEditEvent.toJSONObject().toString(5));
                }
                catch (JSONException ex) {}
            }
        }
    }
    
    @Override
    public void fireImpressionEvent(final Intent intent) {
        if (this.mEventHandler != null) {
            final int intExtra = intent.getIntExtra("from", 0);
            final int intExtra2 = intent.getIntExtra("to", 0);
            final String stringExtra = intent.getStringExtra("reference");
            final String stringExtra2 = intent.getStringExtra("view");
            final String stringExtra3 = intent.getStringExtra("modal_view");
            final String[] stringArrayExtra = intent.getStringArrayExtra("childIds");
            IClientLogging$ModalView value;
            if (StringUtils.isNotEmpty(stringExtra2)) {
                value = IClientLogging$ModalView.valueOf(stringExtra2);
            }
            else {
                value = null;
            }
            while (true) {
                Label_0156: {
                    if (!StringUtils.isNotEmpty(stringExtra3)) {
                        break Label_0156;
                    }
                    final IClientLogging$ModalView value2 = IClientLogging$ModalView.valueOf(stringExtra3);
                    final SearchImpressionEvent searchImpressionEvent = new SearchImpressionEvent(stringExtra, intExtra, intExtra2, stringArrayExtra, value2, value);
                    this.mEventHandler.post(searchImpressionEvent);
                    if (!Log.isLoggable("nf_log_search", 3)) {
                        return;
                    }
                    try {
                        Log.d("nf_log_search", "Search Impression Event fired" + searchImpressionEvent.toJSONObject().toString(5));
                        return;
                    }
                    catch (JSONException ex) {
                        return;
                    }
                }
                final IClientLogging$ModalView value2 = null;
                continue;
            }
        }
    }
    
    public boolean handleIntent(final Intent intent) {
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.LOG_SUS_SEARCH_SESSION_START".equals(action)) {
            this.startSession(intent);
            Log.d("nf_log_search", "SEARCH_SESSION_START");
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SUS_SEARCH_SESSION_END".equals(action)) {
            this.stopSession(intent);
            Log.d("nf_log_search", "SEARCH_SESSION_END");
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_SESSION_START".equals(action)) {
            this.startFocusSession(intent);
            Log.d("nf_log_search", "SEARCH_FOCUS_SESSION_START");
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_SESSION_END".equals(action)) {
            this.stopFocusSession(intent);
            Log.d("nf_log_search", "SEARCH_FOCUS_SESSION_END");
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_EDIT".equals(action)) {
            this.fireEditEvent(intent);
            Log.d("nf_log_search", "SEARCH_EDIT");
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_IMPRESSION".equals(action)) {
            this.fireImpressionEvent(intent);
            Log.d("nf_log_search", "SEARCH_IMPRESSION");
            return true;
        }
        if (Log.isLoggable("nf_log_search", 3)) {
            Log.d("nf_log_search", "We do not support action " + action);
        }
        return false;
    }
    
    @Override
    public void startFocusSession(final Intent intent) {
        synchronized (this) {
            if (this.mEventHandler != null) {
                final long longExtra = intent.getLongExtra("session_id", 0L);
                if (longExtra != 0L) {
                    final SearchFocusSession searchFocusSession = new SearchFocusSession(longExtra);
                    this.focusFocusSessions.put(longExtra, searchFocusSession);
                    this.mEventHandler.addSession(searchFocusSession);
                    final SearchFocusSessionStartedEvent startEvent = searchFocusSession.createStartEvent(intent.getStringExtra("term"));
                    this.mEventHandler.post(startEvent);
                    if (Log.isLoggable("nf_log_search", 3)) {
                        try {
                            Log.d("nf_log_search", "startFocusSession done." + startEvent.toJSONObject().toString(5));
                        }
                        catch (JSONException ex) {}
                    }
                }
            }
        }
    }
    
    @Override
    public void startSession(final Intent intent) {
        synchronized (this) {
            if (this.mEventHandler != null) {
                final long longExtra = intent.getLongExtra("session_id", 0L);
                if (longExtra != 0L) {
                    final SearchSession searchSession = new SearchSession(longExtra);
                    this.searchSessions.put(longExtra, searchSession);
                    this.mEventHandler.addSession(searchSession);
                    final SearchSessionStartedEvent startEvent = searchSession.createStartEvent();
                    this.mEventHandler.post(startEvent);
                    if (Log.isLoggable("nf_log_search", 3)) {
                        try {
                            Log.d("nf_log_search", "Search session start done." + startEvent.toJSONObject().toString(5));
                        }
                        catch (JSONException ex) {}
                    }
                }
            }
        }
    }
    
    @Override
    public void stopFocusSession(final Intent intent) {
        synchronized (this) {
            this.stopFocusSession(intent.getLongExtra("session_id", 0L));
        }
    }
    
    @Override
    public void stopSession(final Intent intent) {
        synchronized (this) {
            this.stopSession(intent.getLongExtra("session_id", 0L));
        }
    }
}
