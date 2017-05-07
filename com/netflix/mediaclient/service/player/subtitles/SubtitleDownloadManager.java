// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;

public class SubtitleDownloadManager implements SubtitleParser$DownloadFailedCallback
{
    private static final String TAG = "subtitleManager";
    private long mBookmark;
    private SubtitleUrl mCurrentSubtitleUrl;
    private SubtitleData mData;
    private float mDisplayAspectRatio;
    private SubtitleParser mParser;
    private PlayerAgent mPlayer;
    private ServiceAgent$UserAgentInterface mUser;
    
    public SubtitleDownloadManager(final PlayerAgent mPlayer, final ServiceAgent$UserAgentInterface mUser) {
        this.mPlayer = mPlayer;
        this.mUser = mUser;
    }
    
    private boolean createParserAndStartDownload() {
        this.mCurrentSubtitleUrl = this.mData.pop();
        if (this.mCurrentSubtitleUrl == null) {
            Log.e("subtitleManager", "Received empty subtitle data without ANY url? It should NOT happen!");
            return false;
        }
        if (Log.isLoggable()) {
            Log.d("subtitleManager", "Subtitles download started from URL " + this.mCurrentSubtitleUrl.getUrl() + ", content type " + this.mCurrentSubtitleUrl.getProfile().getNccpCode());
        }
        (this.mParser = SubtitleParserFactory.createParser(this.mPlayer, this.mCurrentSubtitleUrl, this.mUser.getUserSubtitlePreferences(), this.mUser.getSubtitleDefaults(), this.mDisplayAspectRatio, this.mBookmark, this)).load();
        return true;
    }
    
    public void changeSubtitle(final SubtitleData mData, final float mDisplayAspectRatio, final long mBookmark) {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d("subtitleManager", "New subtitle selected: " + mData);
            }
            this.mDisplayAspectRatio = mDisplayAspectRatio;
            this.mBookmark = mBookmark;
            if (mData != null) {
                this.mData = mData;
                this.createParserAndStartDownload();
            }
        }
    }
    
    public void close() {
        synchronized (this) {
            this.mParser = null;
            this.mCurrentSubtitleUrl = null;
            this.mData = null;
            this.mDisplayAspectRatio = 0.0f;
            this.mBookmark = 0L;
        }
    }
    
    @Override
    public boolean downloadFailed(final SubtitleUrl subtitleUrl) {
        while (true) {
            final boolean b = false;
            synchronized (this) {
                if (this.mCurrentSubtitleUrl == subtitleUrl) {
                    Log.d("subtitleManager", "Failed to download current subtitle, go for next...");
                    boolean b2;
                    if (this.createParserAndStartDownload()) {
                        Log.d("subtitleManager", "Parser created...");
                        b2 = true;
                    }
                    else {
                        Log.e("subtitleManager", "We failed all available links, report final failure!");
                        b2 = b;
                    }
                    return b2;
                }
            }
            boolean b2 = b;
            if (Log.isLoggable()) {
                Log.d("subtitleManager", "Current subtitle " + this.mCurrentSubtitleUrl);
                final Throwable t;
                Log.d("subtitleManager", "Tried to download " + t);
                Log.d("subtitleManager", "It looks that we changed subtitle since we tried to download last, ignore!");
                b2 = b;
                return b2;
            }
            return b2;
        }
    }
    
    public SubtitleParser getSubtitleParser() {
        return this.mParser;
    }
}
