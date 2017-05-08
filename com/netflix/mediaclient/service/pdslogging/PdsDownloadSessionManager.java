// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import com.netflix.mediaclient.service.player.exoplayback.logblob.OfflineErrorLogblob;
import com.netflix.mediaclient.servicemgr.Logblob$Severity;
import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.IntentUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$ManifestCallback;
import junit.framework.Assert;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.content.Context;
import com.netflix.mediaclient.service.logging.IPdsLogging;
import java.util.Map;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;
import com.netflix.mediaclient.servicemgr.LogblobLogging;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;

public class PdsDownloadSessionManager implements OfflineAgentListener
{
    public static String CATEGORY_NF_PDSLOG_DOWNLOAD;
    private static final boolean ENABLE_PROGRESS_IN_CODE = true;
    public static String EXTRA_ERROR_CODE;
    public static String EXTRA_ERROR_MESSAGE;
    public static String EXTRA_PLAYABLE_ID;
    public static String STOP_DOWNLOAD_ERROR;
    public static String STOP_DOWNLOAD_LICENSE_ERROR;
    public static String STOP_DOWNLOAD_MANIFEST_EXPIRED;
    private static final String TAG;
    private String mAppSessionId;
    LogblobLogging mLogblobLogging;
    private OfflinePlaybackInterface mOfflinePlaybackInterface;
    private final BroadcastReceiver mPdsDownloadEventReceiver;
    private Map<String, PdsDownloadSession> mPdsDownloadSessions;
    IPdsLogging mPdsLogging;
    private Object mSessionsLock;
    private String mUserSessionId;
    
    static {
        TAG = PdsDownloadSessionManager.class.getSimpleName();
        PdsDownloadSessionManager.CATEGORY_NF_PDSLOG_DOWNLOAD = "com.netflix.mediaclient.intent.category.PDSLOG_DOWNLOAD";
        PdsDownloadSessionManager.STOP_DOWNLOAD_LICENSE_ERROR = "com.netflix.mediaclient.intent.action.LICENSE_ERROR";
        PdsDownloadSessionManager.STOP_DOWNLOAD_MANIFEST_EXPIRED = "com.netflix.mediaclient.intent.action.MANIFEST_EXPIRED";
        PdsDownloadSessionManager.STOP_DOWNLOAD_ERROR = "com.netflix.mediaclient.intent.action.DOWNLOAD_ERROR";
        PdsDownloadSessionManager.EXTRA_ERROR_CODE = "errorCode";
        PdsDownloadSessionManager.EXTRA_ERROR_MESSAGE = "errorMessage";
        PdsDownloadSessionManager.EXTRA_PLAYABLE_ID = "playableId";
    }
    
    public PdsDownloadSessionManager(final Context context, final OfflinePlaybackInterface mOfflinePlaybackInterface, final IClientLogging clientLogging) {
        this.mSessionsLock = new Object();
        this.mPdsDownloadEventReceiver = new PdsDownloadSessionManager$4(this);
        this.mPdsDownloadSessions = new HashMap<String, PdsDownloadSession>();
        this.mOfflinePlaybackInterface = mOfflinePlaybackInterface;
        this.mLogblobLogging = clientLogging.getLogblobLogging();
        this.mPdsLogging = clientLogging.getPdsLogging();
        this.mAppSessionId = clientLogging.getApplicationId();
        this.mUserSessionId = clientLogging.getUserSessionId();
        this.registerReceiver(context);
        Log.d(PdsDownloadSessionManager.TAG, "inited download session manager");
    }
    
    private void addDownloadSession(final String s, final PdsDownloadSession pdsDownloadSession) {
        if (Log.isLoggable()) {
            Assert.assertNull((Object)this.mPdsDownloadSessions.get(s));
        }
        synchronized (this.mSessionsLock) {
            this.mPdsDownloadSessions.put(s, pdsDownloadSession);
        }
    }
    
    private PdsDownloadSession createDownloadSession(final String s, final String s2, final String s3, final DownloadContext downloadContext, final JSONObject links) {
        final PdsDownloadSession setLinks = new PdsDownloadSession(s, s2, s3, this.mAppSessionId, this.mUserSessionId, this.mPdsLogging).setDownloadContext(downloadContext).setLinks(links);
        this.addDownloadSession(s, setLinks);
        return setLinks;
    }
    
    private void dumpSessions() {
        Log.d(PdsDownloadSessionManager.TAG, "is mPdsDownloadSessionsEmpty :%b", this.mPdsDownloadSessions.isEmpty());
        Log.d(PdsDownloadSessionManager.TAG, "keySet : %s", this.mPdsDownloadSessions.keySet());
    }
    
    private void fetchPersistedManifest(final PdsDownloadSession pdsDownloadSession, final PdsDownloadSessionManager$ManifestCallback pdsDownloadSessionManager$ManifestCallback) {
        pdsDownloadSession.setManifestFetchInProgress(true);
        this.mOfflinePlaybackInterface.requestOfflineManifest(Long.parseLong(pdsDownloadSession.getPlayableId()), new PdsDownloadSessionManager$3(this, pdsDownloadSessionManager$ManifestCallback));
    }
    
    private PdsDownloadSession getDownloadSession(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return this.mPdsDownloadSessions.get(s);
    }
    
    private PdsDownloadSession getDownloadSessionForEvent(final OfflinePlayableViewData offlinePlayableViewData) {
        final PdsDownloadSession downloadSession = this.getDownloadSession(offlinePlayableViewData.getPlayableId());
        if (downloadSession != null) {
            return downloadSession;
        }
        return this.createDownloadSession(offlinePlayableViewData.getPlayableId(), offlinePlayableViewData.getOxId(), offlinePlayableViewData.getDxId(), DownloadContext.createDownloadContext(offlinePlayableViewData), null);
    }
    
    private void handleDownloadComplete(final PdsDownloadSession pdsDownloadSession) {
        pdsDownloadSession.sendDownloadCompleteMessage();
        this.removeDownloadSession(pdsDownloadSession.getPlayableId());
    }
    
    private void handleProgressMessage(final PdsDownloadSession pdsDownloadSession, final int n) {
        if (pdsDownloadSession.isPaused()) {
            pdsDownloadSession.setPaused(false);
            pdsDownloadSession.sendDownloadResumeMessage();
        }
        pdsDownloadSession.sendDownloadProgressMessage(n);
    }
    
    private void registerReceiver(final Context context) {
        Log.d(PdsDownloadSessionManager.TAG, "Register receiver");
        IntentUtils.registerSafelyLocalBroadcastReceiver(context, this.mPdsDownloadEventReceiver, PdsDownloadSessionManager.CATEGORY_NF_PDSLOG_DOWNLOAD, PdsDownloadSessionManager.STOP_DOWNLOAD_ERROR, PdsDownloadSessionManager.STOP_DOWNLOAD_MANIFEST_EXPIRED, PdsDownloadSessionManager.STOP_DOWNLOAD_LICENSE_ERROR);
    }
    
    private void removeAllDownloadSessions() {
        synchronized (this.mSessionsLock) {
            this.mPdsDownloadSessions.clear();
        }
    }
    
    private void removeDownloadSession(final String s) {
        synchronized (this.mSessionsLock) {
            if (this.mPdsDownloadSessions.containsKey(s)) {
                this.mPdsDownloadSessions.remove(s);
            }
        }
    }
    
    private void unregisterReceiver(final Context context) {
        IntentUtils.unregisterSafelyLocalBroadcastReceiver(context, this.mPdsDownloadEventReceiver);
    }
    
    public void destroy(final Context context) {
        this.unregisterReceiver(context);
    }
    
    @Override
    public boolean isListenerDestroyed() {
        return false;
    }
    
    @Override
    public void onAllPlayablesDeleted(final Status status) {
        this.removeAllDownloadSessions();
    }
    
    @Override
    public void onCreateRequestResponse(final String s, final Status status) {
    }
    
    @Override
    public void onDownloadCompleted(final OfflinePlayableViewData offlinePlayableViewData) {
        final PdsDownloadSession downloadSessionForEvent = this.getDownloadSessionForEvent(offlinePlayableViewData);
        if (downloadSessionForEvent.needToFetchManifest()) {
            this.fetchPersistedManifest(downloadSessionForEvent, new PdsDownloadSessionManager$2(this));
            return;
        }
        this.handleDownloadComplete(downloadSessionForEvent);
    }
    
    @Override
    public void onDownloadResumedByUser(final OfflinePlayableViewData offlinePlayableViewData) {
    }
    
    @Override
    public void onDownloadStopped(final OfflinePlayableViewData offlinePlayableViewData, final StopReason stopReason) {
        int n = 0;
        final PdsDownloadSession downloadSession = this.getDownloadSession(offlinePlayableViewData.getPlayableId());
        if (downloadSession != null) {
            Logblob$Severity logblob$Severity = Logblob$Severity.error;
            switch (PdsDownloadSessionManager$5.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$offline$StopReason[stopReason.ordinal()]) {
                default: {
                    Log.d(PdsDownloadSessionManager.TAG, " onDownlodStopped stopReason: %s, no-op", stopReason);
                    break;
                }
                case 1:
                case 2:
                case 3:
                case 4: {
                    break;
                }
                case 5:
                case 6:
                case 7: {
                    downloadSession.setPaused(true);
                    downloadSession.sendDownloadPauseMessage();
                    break;
                }
                case 8: {
                    logblob$Severity = Logblob$Severity.error;
                    n = 1;
                    break;
                }
                case 9: {
                    logblob$Severity = Logblob$Severity.warn;
                    n = 1;
                    break;
                }
                case 10: {
                    logblob$Severity = Logblob$Severity.info;
                    n = 1;
                    break;
                }
                case 11: {
                    logblob$Severity = Logblob$Severity.error;
                    n = 1;
                    break;
                }
                case 12: {
                    logblob$Severity = Logblob$Severity.error;
                    n = 1;
                    break;
                }
                case 13: {
                    logblob$Severity = Logblob$Severity.error;
                    n = 1;
                    break;
                }
            }
            if (n != 0) {
                OfflineErrorLogblob.sendDownloadStopError(this.mLogblobLogging, logblob$Severity, downloadSession.getPlayableId(), downloadSession.getOxId(), downloadSession.getDxId(), stopReason);
            }
        }
    }
    
    @Override
    public void onError(final Status status) {
    }
    
    @Override
    public void onLicenseRefreshDone(final OfflinePlayableViewData offlinePlayableViewData, final Status status) {
    }
    
    @Override
    public void onOfflinePlayableDeleted(final String s, final Status status) {
        this.removeDownloadSession(s);
    }
    
    @Override
    public void onOfflinePlayableProgress(final OfflinePlayableViewData offlinePlayableViewData, final int n) {
        if (Log.isLoggable()) {
            Assert.assertTrue(n >= 0 && n <= 100);
        }
        final PdsDownloadSession downloadSessionForEvent = this.getDownloadSessionForEvent(offlinePlayableViewData);
        if (downloadSessionForEvent.needToFetchManifest()) {
            this.fetchPersistedManifest(downloadSessionForEvent, new PdsDownloadSessionManager$1(this, n));
            return;
        }
        this.handleProgressMessage(downloadSessionForEvent, n);
    }
    
    @Override
    public void onPlayWindowRenewDone(final OfflinePlayableViewData offlinePlayableViewData, final Status status) {
    }
    
    public void setOfflineManifest(final String s, final String s2, final String s3, final DownloadContext downloadContext, final JSONObject jsonObject) {
        this.removeDownloadSession(s);
        Log.d(PdsDownloadSessionManager.TAG, "onDownloadOfFirstTimeOfflineManifest playableId: %s, oxid: %s, dxid: %s", s, s2, s3);
        this.createDownloadSession(s, s2, s3, downloadContext, jsonObject).sendStartDownloadMessage();
    }
}
