// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.subtitles;

import com.netflix.mediaclient.service.player.subtitles.SubtitleParser;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.ui.offline.OfflineSubtitle;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.servicemgr.IPlayer;

public class SubtitleOfflineManager
{
    private static final String TAG = "nf_subtitles_offline";
    private long mBookmark;
    private float mDisplayAspectRatio;
    private OfflineSubtitleParser mParser;
    private IPlayer mPlayer;
    private ServiceAgent$UserAgentInterface mUser;
    
    public SubtitleOfflineManager(final ServiceAgent$UserAgentInterface mUser, final IPlayer mPlayer) {
        if (mUser == null) {
            throw new IllegalArgumentException("User is null!");
        }
        if (mPlayer == null) {
            throw new IllegalArgumentException("Player is null!");
        }
        this.mUser = mUser;
        this.mPlayer = mPlayer;
    }
    
    public void changeSubtitle(final Subtitle subtitle, final float mDisplayAspectRatio, final long mBookmark) {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_offline", "New subtitle selected: bookmark: " + mBookmark + ", " + subtitle);
            }
            this.mDisplayAspectRatio = mDisplayAspectRatio;
            this.mBookmark = mBookmark;
            final TextStyle userSubtitlePreferences = this.mUser.getUserSubtitlePreferences();
            final TextStyle subtitleDefaults = this.mUser.getSubtitleDefaults();
            if (subtitle instanceof OfflineSubtitle) {
                (this.mParser = SubtitleParserFactory.createParser(this.mPlayer, (OfflineSubtitle)subtitle, userSubtitlePreferences, subtitleDefaults, this.mDisplayAspectRatio, this.mBookmark)).load();
            }
            else {
                new IllegalStateException("SubtitleOfflineManager handles only OfflineSubtitle!");
            }
        }
    }
    
    public void close() {
        synchronized (this) {
            this.mParser = null;
            this.mDisplayAspectRatio = 0.0f;
            this.mBookmark = 0L;
        }
    }
    
    public Subtitle getCurrentSubtitle() {
        final OfflineSubtitleParser mParser = this.mParser;
        if (mParser != null) {
            return mParser.getCurrentSubtitle();
        }
        Log.e("nf_subtitles_offline", "getCurrentSubtitle:: parser is null!");
        return null;
    }
    
    public SubtitleParser getSubtitleParser() {
        synchronized (this) {
            return this.mParser;
        }
    }
}
