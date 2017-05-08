// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;
import com.netflix.mediaclient.javabridge.ui.LogArguments;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;
import com.netflix.mediaclient.javabridge.ui.Nrdp;

public class OfflineNrdpLoggerDev implements OfflineNrdpLogger
{
    private static final String OFFLINE_LOG_TYPE = "offline";
    private final Nrdp mNrdp;
    
    OfflineNrdpLoggerDev(final Nrdp mNrdp) {
        this.mNrdp = mNrdp;
    }
    
    private OfflineNrdpLoggerDev$Msg createMsg(final String oxid, final String videoId) {
        final OfflineNrdpLoggerDev$Msg offlineNrdpLoggerDev$Msg = new OfflineNrdpLoggerDev$Msg(this);
        offlineNrdpLoggerDev$Msg.oxid = oxid;
        offlineNrdpLoggerDev$Msg.videoId = videoId;
        offlineNrdpLoggerDev$Msg.statusCode = -1;
        offlineNrdpLoggerDev$Msg.stopReason = -1;
        offlineNrdpLoggerDev$Msg.jobId = -1;
        return offlineNrdpLoggerDev$Msg;
    }
    
    private void sendErrorLog(final OfflineNrdpLoggerDev$Msg offlineNrdpLoggerDev$Msg) {
        this.mNrdp.getLog().log(new LogArguments(LogArguments$LogLevel.ERROR, NetflixApplication.getGson().toJson(offlineNrdpLoggerDev$Msg), "offline", null));
    }
    
    private void sendInfoLog(final OfflineNrdpLoggerDev$Msg offlineNrdpLoggerDev$Msg) {
        this.mNrdp.getLog().log(new LogArguments(LogArguments$LogLevel.INFO, NetflixApplication.getGson().toJson(offlineNrdpLoggerDev$Msg), "offline", null));
    }
    
    @Override
    public void onCdnUrlExpiredOrMoved(final OfflinePlayablePersistentData offlinePlayablePersistentData, final Status status) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mPlayableId);
            msg.msg = "onCdnUrlExpiredOrMoved";
            msg.statusCode = status.getStatusCode().getValue();
            this.sendErrorLog(msg);
        }
    }
    
    @Override
    public void onCdnUrlGeoCheckError(final OfflinePlayablePersistentData offlinePlayablePersistentData, final Status status) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mPlayableId);
            msg.msg = "onCdnUrlGeoCheckError";
            msg.statusCode = status.getStatusCode().getValue();
            this.sendErrorLog(msg);
        }
    }
    
    @Override
    public void onCdnUrlNetworkError(final OfflinePlayablePersistentData offlinePlayablePersistentData, final Status status) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mPlayableId);
            msg.msg = "onCdnUrlNetworkError";
            msg.statusCode = status.getStatusCode().getValue();
            this.sendErrorLog(msg);
        }
    }
    
    @Override
    public void onDownloadFinished(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mPlayableId);
            msg.msg = "onDownloadFinished";
            this.sendInfoLog(msg);
        }
    }
    
    @Override
    public void onFirstTimeLicenseError(final OfflinePlayablePersistentData offlinePlayablePersistentData, final Status status) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mPlayableId);
            msg.msg = "onFirstTimeLicenseError";
            msg.statusCode = status.getStatusCode().getValue();
            this.sendErrorLog(msg);
        }
    }
    
    @Override
    public void onFirstTimeLicenseReceived(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mPlayableId);
            msg.msg = "onFirstTimeLicenseReceived";
            this.sendInfoLog(msg);
        }
    }
    
    @Override
    public void onFirstTimeManifestFailed(final OfflinePlayablePersistentData offlinePlayablePersistentData, final Status status) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mPlayableId);
            msg.msg = "onFirstTimeManifestFailed";
            msg.statusCode = status.getStatusCode().getValue();
            this.sendErrorLog(msg);
        }
    }
    
    @Override
    public void onFirstTimeManifestReceived(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mPlayableId);
            msg.msg = "onFirstTimeManifestReceived";
            this.sendInfoLog(msg);
        }
    }
    
    @Override
    public void onNetflixStartJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(null, null);
            msg.msg = "onNetflixStartJob";
            msg.jobId = netflixJob$NetflixJobId.getIntValue();
            this.sendErrorLog(msg);
        }
    }
    
    @Override
    public void onNetflixStopJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(null, null);
            msg.msg = "onNetflixStopJob";
            msg.jobId = netflixJob$NetflixJobId.getIntValue();
            this.sendErrorLog(msg);
        }
    }
    
    @Override
    public void onOfflinePlayableDownloadDelete(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mPlayableId);
            msg.msg = "onOfflinePlayableDownloadDelete";
            this.sendInfoLog(msg);
        }
    }
    
    @Override
    public void onOfflinePlayableDownloadStart(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mPlayableId);
            msg.msg = "onOfflinePlayableDownloadStart";
            this.sendInfoLog(msg);
        }
    }
    
    @Override
    public void onOfflinePlayableDownloadStop(final OfflinePlayablePersistentData offlinePlayablePersistentData, final StopReason stopReason) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mPlayableId);
            msg.msg = "onOfflinePlayableDownloadStop";
            msg.stopReason = stopReason.getIntValue();
            this.sendInfoLog(msg);
        }
    }
    
    @Override
    public void onOfflinePlayableInitialize(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mPlayableId);
            msg.msg = "onOfflinePlayableInitialize";
            this.sendInfoLog(msg);
        }
    }
    
    @Override
    public void onRequestingNewManifestFromServer(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mPlayableId);
            msg.msg = "onRequestingNewManifestFromServer";
            this.sendErrorLog(msg);
        }
    }
    
    @Override
    public void onUrlDownloadDiskIOError(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        if (this.mNrdp != null) {
            final OfflineNrdpLoggerDev$Msg msg = this.createMsg(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mPlayableId);
            msg.msg = "onUrlDownloadDiskIOError";
            this.sendErrorLog(msg);
        }
    }
}
