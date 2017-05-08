// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import junit.framework.Assert;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface$OfflineManifest;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.common.PlayContext;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.IPdsLogging;

public class PdsPlaySession implements PdsPlaySessionInterface
{
    private static final String TAG;
    private String mAppSessionId;
    private long mCurrentBookmarkMs;
    private DownloadContext mDc;
    private String mDxid;
    private String mOxid;
    private IPdsLogging mPdsLogging;
    private JSONObject mPdsStopPlayLink;
    private PlayContext mPlayContext;
    private long mPlayDurationInMs;
    private long mPlayStartClientTime;
    private String mPlayableId;
    private long mStartPositionMs;
    private boolean mStopEventSent;
    private String mUserSessionId;
    private String mXid;
    
    static {
        TAG = PdsPlaySession.class.getSimpleName();
    }
    
    public PdsPlaySession(final String mPlayableId, final String mXid, final long n, final String mAppSessionId, final String mUserSessionId, final IPdsLogging mPdsLogging, final PlayContext mPlayContext) {
        this.mXid = mXid;
        this.mPlayableId = mPlayableId;
        this.mCurrentBookmarkMs = n;
        this.mStartPositionMs = n;
        this.mAppSessionId = mAppSessionId;
        this.mUserSessionId = mUserSessionId;
        this.mPdsLogging = mPdsLogging;
        this.mPlayContext = mPlayContext;
    }
    
    private void sendPdsEventViaLogging(final String s) {
        if (StringUtils.isNotEmpty(s)) {
            this.mPdsLogging.sendPdsEventViaLogging(s);
        }
    }
    
    @Override
    public void notifyPlayProgress(final long mCurrentBookmarkMs) {
        if (mCurrentBookmarkMs >= this.mCurrentBookmarkMs) {
            this.mPlayDurationInMs += mCurrentBookmarkMs - this.mCurrentBookmarkMs;
            this.mCurrentBookmarkMs = mCurrentBookmarkMs;
        }
        if (this.mPlayStartClientTime == 0L) {
            this.mPlayStartClientTime = System.currentTimeMillis();
        }
    }
    
    @Override
    public void onManifest(final OfflinePlaybackInterface$OfflineManifest offlinePlaybackInterface$OfflineManifest) {
        final JSONObject links = offlinePlaybackInterface$OfflineManifest.getLinks();
        if (Log.isLoggable()) {
            Assert.assertNotNull((Object)links);
        }
        if (links != null) {
            this.mPdsStopPlayLink = links.optJSONObject("stopPlayback");
            Log.dumpVerbose(PdsPlaySession.TAG, "mPdsStopPlayLink: " + this.mPdsStopPlayLink);
        }
        this.mOxid = offlinePlaybackInterface$OfflineManifest.getOxId();
        this.mDxid = offlinePlaybackInterface$OfflineManifest.getDxId();
        this.mDc = offlinePlaybackInterface$OfflineManifest.getDownloadContext();
    }
    
    @Override
    public void pause() {
    }
    
    @Override
    public void play() {
    }
    
    @Override
    public void seekTo(final long mCurrentBookmarkMs) {
        this.mCurrentBookmarkMs = mCurrentBookmarkMs;
    }
    
    @Override
    public void stop(final JSONObject playTimes, final String s, final String s2) {
        if (this.mStopEventSent) {
            Log.d(PdsPlaySession.TAG, "ignore duplicate stop message, playableId: %s, errorCode: %s", playTimes, s);
            return;
        }
        this.sendPdsEventViaLogging(new PdsPlayEventParamBuilder(this.mXid, this.mPlayableId, this.mPdsStopPlayLink, this.mAppSessionId, this.mUserSessionId).setPlayContext(this.mPlayContext).setOfflineIds(this.mOxid, this.mDxid).setDownloadContext(this.mDc).setPlayDuration(this.mPlayDurationInMs).setPlayTimes(playTimes).setBookmarkPosition(this.mStartPositionMs, this.mCurrentBookmarkMs).setSessionTimes(this.mPlayStartClientTime, System.currentTimeMillis()).setError(s, s2).build());
        this.mPdsLogging.flushEventsInLogging();
        this.mStopEventSent = true;
    }
}
