// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import com.netflix.mediaclient.Log;

public final class MediaPlayerHelperFactory
{
    private static final String TAG = "nf_player";
    private static AndroidMediaPlayerHelper ampHelper;
    private static DefaultMediaPlayerHelper defaultHelper;
    private static JPlayerHelper jpHelper;
    
    static {
        MediaPlayerHelperFactory.jpHelper = null;
        MediaPlayerHelperFactory.ampHelper = null;
        MediaPlayerHelperFactory.defaultHelper = null;
    }
    
    public static MediaPlayerHelper getInstance(final PlayerType playerType) {
        if (playerType == null) {
            throw new IllegalArgumentException("Type is null!");
        }
        if (playerType == PlayerType.device9) {
            Log.d("nf_player", "Use Android MediaPlayer helper");
            if (MediaPlayerHelperFactory.ampHelper == null) {
                MediaPlayerHelperFactory.ampHelper = new AndroidMediaPlayerHelper();
            }
            return MediaPlayerHelperFactory.ampHelper;
        }
        if (playerType == PlayerType.device10 || playerType == PlayerType.device11) {
            Log.d("nf_player", "Use JPlayer helper");
            if (MediaPlayerHelperFactory.jpHelper == null) {
                MediaPlayerHelperFactory.jpHelper = new JPlayerHelper();
            }
            return MediaPlayerHelperFactory.jpHelper;
        }
        if (playerType == PlayerType.device12) {
            Log.d("nf_player", "Use JPlayer2 helper");
            return new JPlayer2Helper();
        }
        Log.d("nf_player", "Use default player helper");
        if (MediaPlayerHelperFactory.defaultHelper == null) {
            MediaPlayerHelperFactory.defaultHelper = new DefaultMediaPlayerHelper();
        }
        return MediaPlayerHelperFactory.defaultHelper;
    }
}
