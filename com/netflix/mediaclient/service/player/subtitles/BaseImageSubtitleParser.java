// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.servicemgr.IPlayer;

abstract class BaseImageSubtitleParser extends BaseSubtitleParser
{
    protected String mKey;
    protected long mLastKnownPosition;
    protected boolean mUseCache;
    
    public BaseImageSubtitleParser(final IPlayer player, final SubtitleUrl subtitleUrl, final long mLastKnownPosition, final SubtitleParser$DownloadFailedCallback subtitleParser$DownloadFailedCallback, final long n) {
        super(player, subtitleUrl, subtitleParser$DownloadFailedCallback, n);
        this.mUseCache = true;
        this.mLastKnownPosition = mLastKnownPosition;
    }
    
    protected void downloadNextRange(final int n) {
        this.downloadSegment(n);
        this.downloadSegment(n + 1);
    }
    
    protected abstract void downloadSegment(final int p0);
    
    protected abstract String initCache();
    
    protected void saveFileSafelyToCache(final String s, final byte[] array) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Saving " + s + "...");
        }
        try {
            this.mPlayer.getPlayerFileCache().saveFile(this.mKey, s, array);
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Saved " + s + ".");
            }
        }
        catch (Throwable t) {
            Log.e("nf_subtitles", "Failed to save " + s, t);
        }
    }
}
