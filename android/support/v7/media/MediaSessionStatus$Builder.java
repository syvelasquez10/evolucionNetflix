// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.support.v4.util.TimeUtils;
import android.os.SystemClock;
import android.os.Bundle;

public final class MediaSessionStatus$Builder
{
    private final Bundle mBundle;
    
    public MediaSessionStatus$Builder(final int sessionState) {
        this.mBundle = new Bundle();
        this.setTimestamp(SystemClock.elapsedRealtime());
        this.setSessionState(sessionState);
    }
    
    public MediaSessionStatus$Builder(final MediaSessionStatus mediaSessionStatus) {
        if (mediaSessionStatus == null) {
            throw new IllegalArgumentException("status must not be null");
        }
        this.mBundle = new Bundle(mediaSessionStatus.mBundle);
    }
    
    public MediaSessionStatus build() {
        return new MediaSessionStatus(this.mBundle, null);
    }
    
    public MediaSessionStatus$Builder setExtras(final Bundle bundle) {
        this.mBundle.putBundle("extras", bundle);
        return this;
    }
    
    public MediaSessionStatus$Builder setQueuePaused(final boolean b) {
        this.mBundle.putBoolean("queuePaused", b);
        return this;
    }
    
    public MediaSessionStatus$Builder setSessionState(final int n) {
        this.mBundle.putInt("sessionState", n);
        return this;
    }
    
    public MediaSessionStatus$Builder setTimestamp(final long n) {
        this.mBundle.putLong("timestamp", n);
        return this;
    }
}
