// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import android.content.Intent;

public interface ISearchLogging
{
    public static final String[] ACTIONS = { "com.netflix.mediaclient.intent.action.LOG_SUS_SEARCH_SESSION_START", "com.netflix.mediaclient.intent.action.LOG_SUS_SEARCH_SESSION_END", "com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_SESSION_START", "com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_SESSION_END", "com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_EDIT", "com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_IMPRESSION" };
    public static final String EXTRA_CHILD_IDS = "childIds";
    public static final String EXTRA_FROM = "from";
    public static final String EXTRA_MODAL_VIEW = "modal_view";
    public static final String EXTRA_PARENT_ID = "parentId";
    public static final String EXTRA_PLAYABLE_ID = "playableId";
    public static final String EXTRA_QUERY = "query";
    public static final String EXTRA_SESSION_ID = "session_id";
    public static final String EXTRA_TO = "to";
    public static final String EXTRA_TRACK_ID = "track_id";
    public static final String EXTRA_VIEW = "view";
    public static final String SEARCH_EDIT = "com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_EDIT";
    public static final String SEARCH_FOCUS_SESSION_END = "com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_SESSION_END";
    public static final String SEARCH_FOCUS_SESSION_START = "com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_SESSION_START";
    public static final String SEARCH_IMPRESSION = "com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_IMPRESSION";
    public static final String SEARCH_SESSION_END = "com.netflix.mediaclient.intent.action.LOG_SUS_SEARCH_SESSION_END";
    public static final String SEARCH_SESSION_START = "com.netflix.mediaclient.intent.action.LOG_SUS_SEARCH_SESSION_START";
    
    void fireEditEvent(final Intent p0);
    
    void fireImpressionEvent(final Intent p0);
    
    void startFocusSession(final Intent p0);
    
    void startSession(final Intent p0);
    
    void stopFocusSession(final Intent p0);
    
    void stopSession(final Intent p0);
}
