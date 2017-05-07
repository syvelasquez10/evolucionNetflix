// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.support.v4.util.TimeUtils;
import android.os.SystemClock;
import android.os.Bundle;

public final class MediaItemStatus
{
    public static final String EXTRA_HTTP_RESPONSE_HEADERS = "android.media.status.extra.HTTP_RESPONSE_HEADERS";
    public static final String EXTRA_HTTP_STATUS_CODE = "android.media.status.extra.HTTP_STATUS_CODE";
    private static final String KEY_CONTENT_DURATION = "contentDuration";
    private static final String KEY_CONTENT_POSITION = "contentPosition";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_PLAYBACK_STATE = "playbackState";
    private static final String KEY_TIMESTAMP = "timestamp";
    public static final int PLAYBACK_STATE_BUFFERING = 3;
    public static final int PLAYBACK_STATE_CANCELED = 5;
    public static final int PLAYBACK_STATE_ERROR = 7;
    public static final int PLAYBACK_STATE_FINISHED = 4;
    public static final int PLAYBACK_STATE_INVALIDATED = 6;
    public static final int PLAYBACK_STATE_PAUSED = 2;
    public static final int PLAYBACK_STATE_PENDING = 0;
    public static final int PLAYBACK_STATE_PLAYING = 1;
    private final Bundle mBundle;
    
    private MediaItemStatus(final Bundle mBundle) {
        this.mBundle = mBundle;
    }
    
    public static MediaItemStatus fromBundle(final Bundle bundle) {
        if (bundle != null) {
            return new MediaItemStatus(bundle);
        }
        return null;
    }
    
    private static String playbackStateToString(final int n) {
        switch (n) {
            default: {
                return Integer.toString(n);
            }
            case 0: {
                return "pending";
            }
            case 3: {
                return "buffering";
            }
            case 1: {
                return "playing";
            }
            case 2: {
                return "paused";
            }
            case 4: {
                return "finished";
            }
            case 5: {
                return "canceled";
            }
            case 6: {
                return "invalidated";
            }
            case 7: {
                return "error";
            }
        }
    }
    
    public Bundle asBundle() {
        return this.mBundle;
    }
    
    public long getContentDuration() {
        return this.mBundle.getLong("contentDuration", -1L);
    }
    
    public long getContentPosition() {
        return this.mBundle.getLong("contentPosition", -1L);
    }
    
    public Bundle getExtras() {
        return this.mBundle.getBundle("extras");
    }
    
    public int getPlaybackState() {
        return this.mBundle.getInt("playbackState", 7);
    }
    
    public long getTimestamp() {
        return this.mBundle.getLong("timestamp");
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MediaItemStatus{ ");
        sb.append("timestamp=");
        TimeUtils.formatDuration(SystemClock.elapsedRealtime() - this.getTimestamp(), sb);
        sb.append(" ms ago");
        sb.append(", playbackState=").append(playbackStateToString(this.getPlaybackState()));
        sb.append(", contentPosition=").append(this.getContentPosition());
        sb.append(", contentDuration=").append(this.getContentDuration());
        sb.append(", extras=").append(this.getExtras());
        sb.append(" }");
        return sb.toString();
    }
}
