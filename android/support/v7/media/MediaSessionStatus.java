// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.support.v4.util.TimeUtils;
import android.os.SystemClock;
import android.os.Bundle;

public final class MediaSessionStatus
{
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_QUEUE_PAUSED = "queuePaused";
    private static final String KEY_SESSION_STATE = "sessionState";
    private static final String KEY_TIMESTAMP = "timestamp";
    public static final int SESSION_STATE_ACTIVE = 0;
    public static final int SESSION_STATE_ENDED = 1;
    public static final int SESSION_STATE_INVALIDATED = 2;
    private final Bundle mBundle;
    
    private MediaSessionStatus(final Bundle mBundle) {
        this.mBundle = mBundle;
    }
    
    public static MediaSessionStatus fromBundle(final Bundle bundle) {
        if (bundle != null) {
            return new MediaSessionStatus(bundle);
        }
        return null;
    }
    
    private static String sessionStateToString(final int n) {
        switch (n) {
            default: {
                return Integer.toString(n);
            }
            case 0: {
                return "active";
            }
            case 1: {
                return "ended";
            }
            case 2: {
                return "invalidated";
            }
        }
    }
    
    public Bundle asBundle() {
        return this.mBundle;
    }
    
    public Bundle getExtras() {
        return this.mBundle.getBundle("extras");
    }
    
    public int getSessionState() {
        return this.mBundle.getInt("sessionState", 2);
    }
    
    public long getTimestamp() {
        return this.mBundle.getLong("timestamp");
    }
    
    public boolean isQueuePaused() {
        return this.mBundle.getBoolean("queuePaused");
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MediaSessionStatus{ ");
        sb.append("timestamp=");
        TimeUtils.formatDuration(SystemClock.elapsedRealtime() - this.getTimestamp(), sb);
        sb.append(" ms ago");
        sb.append(", sessionState=").append(sessionStateToString(this.getSessionState()));
        sb.append(", queuePaused=").append(this.isQueuePaused());
        sb.append(", extras=").append(this.getExtras());
        sb.append(" }");
        return sb.toString();
    }
}
