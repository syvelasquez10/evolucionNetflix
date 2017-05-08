// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.offline.model.DownloadSessionStartedEvent;
import com.netflix.mediaclient.service.logging.offline.model.CachedPlaySessionStartedEvent;
import com.netflix.mediaclient.service.logging.offline.model.RemoveCachedVideoSessionEndedEvent;
import com.netflix.mediaclient.service.logging.offline.model.DownloadSessionEndedEvent;
import com.netflix.mediaclient.service.logging.offline.model.CachedPlaySessionEndedEvent;
import com.netflix.mediaclient.service.logging.offline.model.AddCachedVideoSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import java.util.HashMap;
import com.netflix.mediaclient.service.logging.offline.RemoveCachedVideoSession;
import com.netflix.mediaclient.service.logging.offline.DownloadSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.offline.CachedPlaySession;
import com.netflix.mediaclient.service.logging.offline.AddCachedVideoSession;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.OfflineLogging;

final class OfflineLoggingImpl implements OfflineLogging
{
    private static final String TAG = "nf_log_offline";
    private Map<String, AddCachedVideoSession> mAddCachedVideoSessions;
    private CachedPlaySession mCachedPlaySession;
    private DataContext mDataContext;
    private Map<String, DownloadSession> mDownloadSessions;
    private EventHandler mEventHandler;
    private Map<String, RemoveCachedVideoSession> mRemoveCachedVideoSessions;
    
    OfflineLoggingImpl(final EventHandler mEventHandler) {
        this.mAddCachedVideoSessions = new HashMap<String, AddCachedVideoSession>();
        this.mRemoveCachedVideoSessions = new HashMap<String, RemoveCachedVideoSession>();
        this.mDownloadSessions = new HashMap<String, DownloadSession>();
        this.mEventHandler = mEventHandler;
    }
    
    private void handleAddCachedVideoEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        Log.d("nf_log_offline", "ADD_CACHED_VIDEO_SESSION_END");
        final String stringExtra = intent.getStringExtra("oxid");
        final String stringExtra2 = intent.getStringExtra("view");
        while (true) {
            Label_0105: {
                if (!StringUtils.isNotEmpty(stringExtra2)) {
                    break Label_0105;
                }
                final IClientLogging$ModalView value2 = IClientLogging$ModalView.valueOf(stringExtra2);
                final String stringExtra3 = intent.getStringExtra("reason");
                final String stringExtra4 = intent.getStringExtra("error");
                intent.getStringExtra("surveyType");
                while (true) {
                    try {
                        final UIError instance = UIError.createInstance(stringExtra4);
                        if (StringUtils.isNotEmpty(stringExtra3)) {
                            value = IClientLogging$CompletionReason.valueOf(stringExtra3);
                        }
                        this.endAddCachedVideoSession(stringExtra, value2, value, instance);
                        return;
                    }
                    catch (JSONException ex) {
                        Log.e("nf_log_offline", "Failed JSON", (Throwable)ex);
                        final UIError instance = null;
                        continue;
                    }
                    break;
                }
            }
            final IClientLogging$ModalView value2 = null;
            continue;
        }
    }
    
    private void handleAddCachedVideoStart(final Intent intent) {
        Log.d("nf_log_offline", "ADD_CACHED_VIDEO_SESSION_START");
        final String stringExtra = intent.getStringExtra("oxid");
        final String stringExtra2 = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value = null;
        if (!StringUtils.isEmpty(stringExtra2)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra2);
        }
        this.startAddCachedVideoSession(stringExtra, value);
    }
    
    private void handleCachedPlaybackEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        Log.d("nf_log_offline", "CACHED_PLAYBACK_END");
        final String stringExtra = intent.getStringExtra("view");
        while (true) {
            Label_0095: {
                if (!StringUtils.isNotEmpty(stringExtra)) {
                    break Label_0095;
                }
                final IClientLogging$ModalView value2 = IClientLogging$ModalView.valueOf(stringExtra);
                final String stringExtra2 = intent.getStringExtra("reason");
                final String stringExtra3 = intent.getStringExtra("error");
                intent.getStringExtra("surveyType");
                while (true) {
                    try {
                        final UIError instance = UIError.createInstance(stringExtra3);
                        if (StringUtils.isNotEmpty(stringExtra2)) {
                            value = IClientLogging$CompletionReason.valueOf(stringExtra2);
                        }
                        this.endCachedPlaySession(value2, value, instance);
                        return;
                    }
                    catch (JSONException ex) {
                        Log.e("nf_log_offline", "Failed JSON", (Throwable)ex);
                        final UIError instance = null;
                        continue;
                    }
                    break;
                }
            }
            final IClientLogging$ModalView value2 = null;
            continue;
        }
    }
    
    private void handleCachedPlaybackStart(final Intent intent) {
        Log.d("nf_log_offline", "CACHED_PLAYBACK_START");
        final String stringExtra = intent.getStringExtra("oxid");
        final String stringExtra2 = intent.getStringExtra("videoid");
        final int intExtra = intent.getIntExtra("runtime", 0);
        final int intExtra2 = intent.getIntExtra("logicalStart", 0);
        final int intExtra3 = intent.getIntExtra("logicalEnd", 0);
        final String stringExtra3 = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value = null;
        if (!StringUtils.isEmpty(stringExtra3)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra3);
        }
        this.startCachedPlaySession(value, stringExtra, stringExtra2, intExtra, intExtra2, intExtra3);
    }
    
    private void handleDownloadEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        Log.d("nf_log_offline", "DOWNLOAD_END");
        final String stringExtra = intent.getStringExtra("dxid");
        final String stringExtra2 = intent.getStringExtra("view");
        while (true) {
            Label_0105: {
                if (!StringUtils.isNotEmpty(stringExtra2)) {
                    break Label_0105;
                }
                final IClientLogging$ModalView value2 = IClientLogging$ModalView.valueOf(stringExtra2);
                final String stringExtra3 = intent.getStringExtra("reason");
                final String stringExtra4 = intent.getStringExtra("error");
                intent.getStringExtra("surveyType");
                while (true) {
                    try {
                        final UIError instance = UIError.createInstance(stringExtra4);
                        if (StringUtils.isNotEmpty(stringExtra3)) {
                            value = IClientLogging$CompletionReason.valueOf(stringExtra3);
                        }
                        this.endDownloadSession(stringExtra, value2, value, instance);
                        return;
                    }
                    catch (JSONException ex) {
                        Log.e("nf_log_offline", "Failed JSON", (Throwable)ex);
                        final UIError instance = null;
                        continue;
                    }
                    break;
                }
            }
            final IClientLogging$ModalView value2 = null;
            continue;
        }
    }
    
    private void handleDownloadStart(final Intent intent) {
        Log.d("nf_log_offline", "DOWNLOAD_START");
        final String stringExtra = intent.getStringExtra("dxid");
        final String stringExtra2 = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value = null;
        if (!StringUtils.isEmpty(stringExtra2)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra2);
        }
        this.startDownloadSession(stringExtra, value);
    }
    
    private void handleRemoveCachedVideoEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        Log.d("nf_log_offline", "REMOVE_CACHED_VIDEO_SESSION_END");
        final String stringExtra = intent.getStringExtra("oxid");
        final String stringExtra2 = intent.getStringExtra("view");
        while (true) {
            Label_0105: {
                if (!StringUtils.isNotEmpty(stringExtra2)) {
                    break Label_0105;
                }
                final IClientLogging$ModalView value2 = IClientLogging$ModalView.valueOf(stringExtra2);
                final String stringExtra3 = intent.getStringExtra("reason");
                final String stringExtra4 = intent.getStringExtra("error");
                intent.getStringExtra("surveyType");
                while (true) {
                    try {
                        final UIError instance = UIError.createInstance(stringExtra4);
                        if (StringUtils.isNotEmpty(stringExtra3)) {
                            value = IClientLogging$CompletionReason.valueOf(stringExtra3);
                        }
                        this.endRemoveCachedVideoSession(stringExtra, value2, value, instance);
                        return;
                    }
                    catch (JSONException ex) {
                        Log.e("nf_log_offline", "Failed JSON", (Throwable)ex);
                        final UIError instance = null;
                        continue;
                    }
                    break;
                }
            }
            final IClientLogging$ModalView value2 = null;
            continue;
        }
    }
    
    private void handleRemoveCachedVideoStart(final Intent intent) {
        Log.d("nf_log_offline", "REMOVE_CACHED_VIDEO_SESSION_START");
        final String stringExtra = intent.getStringExtra("oxid");
        final String stringExtra2 = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value = null;
        if (!StringUtils.isEmpty(stringExtra2)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra2);
        }
        this.startRemoveCachedVideoSession(stringExtra, value);
    }
    
    private void populateEvent(final Event event, final IClientLogging$ModalView modalView) {
        if (event == null) {
            return;
        }
        event.setDataContext(this.mDataContext);
        event.setModalView(modalView);
    }
    
    @Override
    public void endAddCachedVideoSession(final String s, final IClientLogging$ModalView clientLogging$ModalView, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        // monitorenter(this)
        Label_0006: {
            if (s != null) {
                try {
                    Log.d("nf_log_offline", "Add cached video session ends with oxid %s", s);
                    if (this.mAddCachedVideoSessions.remove(s) == null) {
                        Log.w("nf_log_offline", "endAddCachedVideoSession:: AddCachedVideoSession session does NOT exist! This should NOT happen!");
                        break Label_0006;
                    }
                }
                finally {
                }
                // monitorexit(this)
                Log.d("nf_log_offline", "AddCachedVideoSession session ended");
                final AddCachedVideoSession addCachedVideoSession;
                final AddCachedVideoSessionEndedEvent endedEvent = addCachedVideoSession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView);
                this.populateEvent(endedEvent, clientLogging$ModalView);
                this.mEventHandler.post(endedEvent);
                this.mEventHandler.removeSession(addCachedVideoSession);
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public void endAllActiveSessions() {
        synchronized (this) {
            this.endCachedPlaySession(null, IClientLogging$CompletionReason.canceled, null);
        }
    }
    
    @Override
    public void endCachedPlaySession(final IClientLogging$ModalView clientLogging$ModalView, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        synchronized (this) {
            Log.d("nf_log_offline", "End cached play session");
            if (this.mCachedPlaySession == null) {
                Log.w("nf_log_offline", "Cached playback session Does NOT exist!");
            }
            else {
                final CachedPlaySessionEndedEvent endedEvent = this.mCachedPlaySession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView);
                this.populateEvent(endedEvent, clientLogging$ModalView);
                this.mEventHandler.post(endedEvent);
                this.mEventHandler.removeSession(this.mCachedPlaySession);
                this.mCachedPlaySession = null;
            }
        }
    }
    
    @Override
    public void endDownloadSession(final String s, final IClientLogging$ModalView clientLogging$ModalView, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        // monitorenter(this)
        if (s != null) {
            try {
                Log.d("nf_log_offline", "Download session ends with dxid %s", s);
                DownloadSession downloadSession;
                if ((downloadSession = this.mDownloadSessions.remove(s)) == null) {
                    Log.d("nf_log_offline", "endDownloadSession:: DownloadSession session does NOT exist! Recreate it, but do NOT post start event!");
                    downloadSession = new DownloadSession(null, null);
                    this.mEventHandler.addSession(downloadSession);
                }
                Log.d("nf_log_offline", "Download session ended");
                final DownloadSessionEndedEvent endedEvent = downloadSession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView);
                this.populateEvent(endedEvent, clientLogging$ModalView);
                this.mEventHandler.post(endedEvent);
                this.mEventHandler.removeSession(downloadSession);
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    // monitorexit(this)
    
    @Override
    public void endRemoveCachedVideoSession(final String s, final IClientLogging$ModalView clientLogging$ModalView, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        // monitorenter(this)
        Label_0006: {
            if (s != null) {
                try {
                    Log.d("nf_log_offline", "Remove cached video session ends with oxid %s", s);
                    if (this.mRemoveCachedVideoSessions.remove(s) == null) {
                        Log.w("nf_log_offline", "endRemoveCachedVideoSession:: RemoveCachedVideoSession session does NOT exist! This should NOT happen!");
                        break Label_0006;
                    }
                }
                finally {
                }
                // monitorexit(this)
                Log.d("nf_log_offline", "AddCachedVideoSession session ended");
                final RemoveCachedVideoSession removeCachedVideoSession;
                final RemoveCachedVideoSessionEndedEvent endedEvent = removeCachedVideoSession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView);
                this.populateEvent(endedEvent, clientLogging$ModalView);
                this.mEventHandler.post(endedEvent);
                this.mEventHandler.removeSession(removeCachedVideoSession);
            }
        }
    }
    // monitorexit(this)
    
    public boolean handleIntent(final Intent intent) {
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.LOG_OFFLINE_ADD_CACHED_VIDEO_SESSION_STARTED".equals(action)) {
            this.handleAddCachedVideoStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_OFFLINE_ADD_CACHED_VIDEO_SESSION_ENDED".equals(action)) {
            this.handleAddCachedVideoEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_OFFLINE_REMOVE_CACHED_VIDEO_SESSION_STARTED".equals(action)) {
            this.handleRemoveCachedVideoStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_OFFLINE_REMOVE_CACHED_VIDEO_SESSION_ENDED".equals(action)) {
            this.handleRemoveCachedVideoEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_OFFLINE_DOWNLOAD_SESSION_STARTED".equals(action)) {
            this.handleDownloadStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_OFFLINE_DOWNLOAD_SESSION_ENDED".equals(action)) {
            this.handleDownloadEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_OFFLINE_CACHED_PLAY_SESSION_STARTED".equals(action)) {
            this.handleCachedPlaybackStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_OFFLINE_CACHED_PLAY_SESSION_ENDED".equals(action)) {
            this.handleCachedPlaybackEnded(intent);
            return true;
        }
        if (Log.isLoggable()) {
            Log.d("nf_log_offline", "We do not support action " + action);
        }
        return false;
    }
    
    @Override
    public void setDataContext(final DataContext mDataContext) {
        this.mDataContext = mDataContext;
    }
    
    @Override
    public void startAddCachedVideoSession(final String s, final UserActionLogging$CommandName userActionLogging$CommandName) {
        // monitorenter(this)
        Label_0006: {
            if (s != null) {
                try {
                    Log.d("nf_log_offline", "Add cached video session started with oxid %s", s);
                    if (this.mAddCachedVideoSessions.get(s) != null) {
                        Log.w("nf_log_offline", "AddCachedVideoSession session already exist! You can not start it!");
                        break Label_0006;
                    }
                }
                finally {
                }
                // monitorexit(this)
                Log.d("nf_log_offline", "AddCachedVideoSession session created");
                final String s2;
                final AddCachedVideoSession addCachedVideoSession = new AddCachedVideoSession(s2, userActionLogging$CommandName);
                this.mAddCachedVideoSessions.put(s2, addCachedVideoSession);
                this.mEventHandler.addSession(addCachedVideoSession);
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public void startCachedPlaySession(final UserActionLogging$CommandName userActionLogging$CommandName, final String s, final String s2, final int n, final int n2, final int n3) {
        // monitorenter(this)
        if (s != null) {
            try {
                Log.d("nf_log_offline", "Start cached play session with oxid %s", s);
                if (this.mCachedPlaySession != null) {
                    Log.w("nf_log_offline", "Cached playback session exist, cancel it!");
                    this.endCachedPlaySession(null, IClientLogging$CompletionReason.canceled, null);
                }
                final CachedPlaySession mCachedPlaySession = new CachedPlaySession(userActionLogging$CommandName, null);
                this.mCachedPlaySession = mCachedPlaySession;
                this.mEventHandler.addSession(mCachedPlaySession);
                final CachedPlaySessionStartedEvent startedEvent = mCachedPlaySession.createStartedEvent(s, s2, n, n2, n3);
                this.populateEvent(startedEvent, null);
                this.mEventHandler.post(startedEvent);
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    // monitorexit(this)
    
    @Override
    public void startDownloadSession(final String s, final UserActionLogging$CommandName userActionLogging$CommandName) {
        // monitorenter(this)
        Label_0006: {
            if (s != null) {
                try {
                    Log.d("nf_log_offline", "Start download session with dxid %s", s);
                    if (this.mDownloadSessions.get(s) != null) {
                        Log.w("nf_log_offline", "startDownloadSession:: DownloadSession session already exist! You can not start it!");
                        break Label_0006;
                    }
                }
                finally {
                }
                // monitorexit(this)
                Log.d("nf_log_offline", "DownloadSession session created");
                final DownloadSession downloadSession = new DownloadSession(userActionLogging$CommandName, null);
                final String s2;
                this.mDownloadSessions.put(s2, downloadSession);
                this.mEventHandler.addSession(downloadSession);
                final DownloadSessionStartedEvent startedEvent = downloadSession.createStartedEvent(s2);
                this.populateEvent(startedEvent, null);
                this.mEventHandler.post(startedEvent);
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public void startRemoveCachedVideoSession(final String s, final UserActionLogging$CommandName userActionLogging$CommandName) {
        // monitorenter(this)
        if (s == null) {
            try {
                throw new IllegalArgumentException("OXID can not be null!");
            }
            finally {
            }
            // monitorexit(this)
        }
        Log.d("nf_log_offline", "Remove cached video session started with oxid %s", s);
        if (this.mRemoveCachedVideoSessions.get(s) != null) {
            Log.w("nf_log_offline", "RemoveCachedVideoSession session already exist! You can not start it!");
        }
        else {
            Log.d("nf_log_offline", "RemoveCachedVideoSession session created");
            final RemoveCachedVideoSession removeCachedVideoSession = new RemoveCachedVideoSession(s, userActionLogging$CommandName);
            this.mRemoveCachedVideoSessions.put(s, removeCachedVideoSession);
            this.mEventHandler.addSession(removeCachedVideoSession);
        }
    }
    // monitorexit(this)
}
