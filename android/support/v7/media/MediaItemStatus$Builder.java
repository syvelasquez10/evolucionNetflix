// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.support.v4.util.TimeUtils;
import android.os.SystemClock;
import android.os.Bundle;

public final class MediaItemStatus$Builder
{
    private final Bundle mBundle;
    
    public MediaItemStatus$Builder(final int playbackState) {
        this.mBundle = new Bundle();
        this.setTimestamp(SystemClock.elapsedRealtime());
        this.setPlaybackState(playbackState);
    }
    
    public MediaItemStatus$Builder(final MediaItemStatus mediaItemStatus) {
        if (mediaItemStatus == null) {
            throw new IllegalArgumentException("status must not be null");
        }
        this.mBundle = new Bundle(mediaItemStatus.mBundle);
    }
    
    public MediaItemStatus build() {
        return new MediaItemStatus(this.mBundle, null);
    }
    
    public MediaItemStatus$Builder setContentDuration(final long n) {
        this.mBundle.putLong("contentDuration", n);
        return this;
    }
    
    public MediaItemStatus$Builder setContentPosition(final long n) {
        this.mBundle.putLong("contentPosition", n);
        return this;
    }
    
    public MediaItemStatus$Builder setExtras(final Bundle bundle) {
        this.mBundle.putBundle("extras", bundle);
        return this;
    }
    
    public MediaItemStatus$Builder setPlaybackState(final int n) {
        this.mBundle.putInt("playbackState", n);
        return this;
    }
    
    public MediaItemStatus$Builder setTimestamp(final long n) {
        this.mBundle.putLong("timestamp", n);
        return this;
    }
}
