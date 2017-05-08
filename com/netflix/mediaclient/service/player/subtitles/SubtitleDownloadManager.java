// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleFailure;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.model.leafs.SubtitleDownloadRetryPolicy;
import com.netflix.mediaclient.util.net.ExponentialBackOff;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.util.net.BackOff;

public class SubtitleDownloadManager implements SubtitleParser$DownloadFailedCallback
{
    private static final String TAG = "nf_subtitles";
    private BackOff mBackOffPolicy;
    private long mBookmark;
    private SubtitleUrl mCurrentSubtitleUrl;
    private SubtitleData mData;
    private float mDisplayAspectRatio;
    private SubtitleParser mParser;
    private PlayerAgent mPlayer;
    private long mStartPositionForSubtitleQoeInMs;
    private ServiceAgent$UserAgentInterface mUser;
    
    public SubtitleDownloadManager(final PlayerAgent mPlayer, final ServiceAgent$UserAgentInterface mUser) {
        if (mPlayer == null) {
            throw new IllegalArgumentException("Player is null!");
        }
        if (mUser == null) {
            throw new IllegalArgumentException("User is null!");
        }
        this.mBackOffPolicy = createExponentialBackOffPolicy(mPlayer.getConfigurationAgent());
        this.mPlayer = mPlayer;
        this.mUser = mUser;
    }
    
    public static ExponentialBackOff createExponentialBackOffPolicy(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        final SubtitleDownloadRetryPolicy subtitleDownloadRetryPolicy = serviceAgent$ConfigurationAgentInterface.getSubtitleDownloadRetryPolicy();
        return new ExponentialBackOff(subtitleDownloadRetryPolicy.getInitialIntervalInMs(), subtitleDownloadRetryPolicy.getRandomizationFactor(), subtitleDownloadRetryPolicy.getMultiplier(), subtitleDownloadRetryPolicy.getMaxIntervalInMs(), subtitleDownloadRetryPolicy.getMaxElapsedTimeInMs());
    }
    
    private SubtitleDownloadManager$DownloadPolicy createParserAndStartDownload() {
        final SubtitleData mData = this.mData;
        if (mData == null) {
            return SubtitleDownloadManager$DownloadPolicy.canNotDownload;
        }
        this.mCurrentSubtitleUrl = mData.pop();
        if (this.mCurrentSubtitleUrl != null) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Subtitles download started from URL " + this.mCurrentSubtitleUrl.getDownloadUrl() + ", content type " + this.mCurrentSubtitleUrl.getProfile().getNccpCode());
            }
            (this.mParser = SubtitleParserFactory.createParser(this.mPlayer, this.mCurrentSubtitleUrl, this.mUser.getUserSubtitlePreferences(), this.mUser.getSubtitleDefaults(), this.mDisplayAspectRatio, this.mBookmark, this, this.mStartPositionForSubtitleQoeInMs)).load();
            return SubtitleDownloadManager$DownloadPolicy.downloading;
        }
        Log.d("nf_subtitles", "We tried all URLs, see if we should retry from start...");
        if (!this.mBackOffPolicy.canRetry()) {
            Log.d("nf_subtitles", "We can not retry again...");
            return SubtitleDownloadManager$DownloadPolicy.canNotDownload;
        }
        final long nextBackOffInMs = this.mBackOffPolicy.nextBackOffInMs();
        this.mData.reset();
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "We can retry again in " + nextBackOffInMs + " [ms]. Posting to handler...");
        }
        this.mPlayer.getMainHandler().postDelayed((Runnable)new SubtitleDownloadManager$1(this), nextBackOffInMs);
        return SubtitleDownloadManager$DownloadPolicy.retry;
    }
    
    public void changeSubtitle(final SubtitleData mData, final float mDisplayAspectRatio, final long mBookmark, final long mStartPositionForSubtitleQoeInMs) {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "New subtitle selected: " + mData + ", bookmark: " + mBookmark + ", startPositionForSubtitleQoeInMs: " + mStartPositionForSubtitleQoeInMs);
            }
            this.mDisplayAspectRatio = mDisplayAspectRatio;
            this.mBookmark = mBookmark;
            this.mStartPositionForSubtitleQoeInMs = mStartPositionForSubtitleQoeInMs;
            if (mData != null) {
                this.mData = mData;
                this.mBackOffPolicy = createExponentialBackOffPolicy(this.mPlayer.getConfigurationAgent());
                this.createParserAndStartDownload();
            }
        }
    }
    
    public void close() {
        while (true) {
            while (true) {
                Label_0163: {
                    synchronized (this) {
                        if (this.mParser != null) {
                            Log.d("nf_subtitles", "Dumping last Qoe data if available!");
                            final int numberOfSubtitlesExpectedToBeDisplayed = this.mParser.getNumberOfSubtitlesExpectedToBeDisplayed();
                            final int numberOfDisplayedSubtitles = this.mParser.getNumberOfDisplayedSubtitles();
                            final SubtitleUrl subtitleUrl = this.mParser.getSubtitleUrl();
                            if (subtitleUrl == null) {
                                break Label_0163;
                            }
                            final String downloadableId = subtitleUrl.getDownloadableId();
                            if (!StringUtils.isNotEmpty(downloadableId)) {
                                break Label_0163;
                            }
                            if (Log.isLoggable()) {
                                Log.d("nf_subtitles", "For subtitle " + downloadableId + " we where expected to show " + numberOfSubtitlesExpectedToBeDisplayed + " and we showed " + numberOfDisplayedSubtitles + " subtitles.");
                            }
                            this.mPlayer.reportSubtitleQoe(downloadableId, numberOfSubtitlesExpectedToBeDisplayed, numberOfDisplayedSubtitles);
                        }
                        this.mParser = null;
                        this.mCurrentSubtitleUrl = null;
                        this.mData = null;
                        this.mDisplayAspectRatio = 0.0f;
                        this.mBookmark = 0L;
                        return;
                    }
                }
                final String downloadableId = "";
                continue;
            }
        }
    }
    
    @Override
    public boolean downloadFailed(final SubtitleUrl subtitleUrl, final ISubtitleDef$SubtitleFailure subtitleDef$SubtitleFailure, final String s) {
        while (true) {
            boolean b = true;
            Label_0087: {
                Label_0072: {
                    synchronized (this) {
                        if (this.mCurrentSubtitleUrl == subtitleUrl) {
                            Log.d("nf_subtitles", "Failed to download current subtitle, go for next...");
                            final SubtitleDownloadManager$DownloadPolicy parserAndStartDownload = this.createParserAndStartDownload();
                            if (parserAndStartDownload == SubtitleDownloadManager$DownloadPolicy.downloading) {
                                Log.d("nf_subtitles", "Parser created...");
                            }
                            else {
                                if (parserAndStartDownload != SubtitleDownloadManager$DownloadPolicy.retry) {
                                    break Label_0072;
                                }
                                Log.d("nf_subtitles", "Will retry download...");
                            }
                            return b;
                        }
                        break Label_0087;
                    }
                }
                Log.e("nf_subtitles", "We failed all available links, report final failure!");
                b = false;
                return b;
            }
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Current subtitle " + this.mCurrentSubtitleUrl);
                final Throwable t;
                Log.d("nf_subtitles", "Tried to download " + t);
                Log.d("nf_subtitles", "It looks that we changed subtitle since we tried to download last, ignore!");
            }
            b = false;
            return b;
        }
    }
    
    public SubtitleParser getSubtitleParser() {
        synchronized (this) {
            return this.mParser;
        }
    }
}
