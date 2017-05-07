// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.search.SearchSession;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.search.SearchFocusSession;
import com.netflix.mediaclient.service.logging.search.model.SearchImpressionEvent;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.service.logging.search.model.SearchEditEvent;
import android.content.Intent;
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
    
    @Override
    public void fireEditEvent(final Intent intent) {
        if (this.mEventHandler == null) {
            return;
        }
        this.mEventHandler.post(new SearchEditEvent(intent.getStringExtra("query")));
        Log.d("nf_log_search", "Search Edit Event fired");
    }
    
    @Override
    public void fireImpressionEvent(final Intent intent) {
        if (this.mEventHandler == null) {
            return;
        }
        final int intExtra = intent.getIntExtra("from", 0);
        final int intExtra2 = intent.getIntExtra("to", 0);
        final int intExtra3 = intent.getIntExtra("track_id", -1);
        final String stringExtra = intent.getStringExtra("view");
        final String stringExtra2 = intent.getStringExtra("modal_view");
        final String[] stringArrayExtra = intent.getStringArrayExtra("childIds");
        Enum<IClientLogging.ModalView> value = null;
        if (StringUtils.isNotEmpty(stringExtra)) {
            value = IClientLogging.ModalView.valueOf(stringExtra);
        }
        Enum<IClientLogging.ModalView> value2 = null;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging.ModalView.valueOf(stringExtra2);
        }
        this.mEventHandler.post(new SearchImpressionEvent(intExtra3, intExtra, intExtra2, stringArrayExtra, (IClientLogging.ModalView)value2, (IClientLogging.ModalView)value));
        Log.d("nf_log_search", "Search Impression Event fired");
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
        if (this.mEventHandler != null) {
            final long longExtra = intent.getLongExtra("session_id", 0L);
            if (longExtra != 0L) {
                final SearchFocusSession searchFocusSession = new SearchFocusSession(longExtra);
                this.focusFocusSessions.put(longExtra, searchFocusSession);
                this.mEventHandler.addSession(searchFocusSession);
                this.mEventHandler.post(searchFocusSession.createStartEvent());
                Log.d("nf_log_search", "startFocusSession done.");
            }
        }
    }
    
    @Override
    public void startSession(final Intent intent) {
        if (this.mEventHandler != null) {
            final long longExtra = intent.getLongExtra("session_id", 0L);
            if (longExtra != 0L) {
                final SearchSession searchSession = new SearchSession(longExtra);
                this.searchSessions.put(longExtra, searchSession);
                this.mEventHandler.addSession(searchSession);
                this.mEventHandler.post(searchSession.createStartEvent());
                Log.d("nf_log_search", "Search session start done.");
            }
        }
    }
    
    @Override
    public void stopFocusSession(final Intent intent) {
        if (this.mEventHandler != null) {
            final long longExtra = intent.getLongExtra("session_id", 0L);
            if (longExtra != 0L) {
                final SearchFocusSession searchFocusSession = this.focusFocusSessions.get(longExtra);
                if (searchFocusSession != null) {
                    this.mEventHandler.removeSession(searchFocusSession);
                    this.mEventHandler.post(searchFocusSession.createEndedEvent());
                    this.focusFocusSessions.remove(longExtra);
                    Log.d("nf_log_search", "SearchFocusSession stop done.");
                }
            }
        }
    }
    
    @Override
    public void stopSession(final Intent intent) {
        if (this.mEventHandler != null) {
            final long longExtra = intent.getLongExtra("session_id", 0L);
            if (longExtra != 0L) {
                final SearchSession searchSession = this.searchSessions.get(longExtra);
                if (searchSession != null) {
                    this.mEventHandler.removeSession(searchSession);
                    this.mEventHandler.post(searchSession.createEndedEvent());
                    this.searchSessions.remove(longExtra);
                }
                Log.d("nf_log_search", "Search session stop done.");
            }
        }
    }
}
