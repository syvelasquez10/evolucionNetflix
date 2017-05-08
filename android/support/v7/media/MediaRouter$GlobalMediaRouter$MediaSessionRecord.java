// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.session.MediaSessionCompat;

final class MediaRouter$GlobalMediaRouter$MediaSessionRecord
{
    private int mControlType;
    private int mMaxVolume;
    private final MediaSessionCompat mMsCompat;
    private VolumeProviderCompat mVpCompat;
    final /* synthetic */ MediaRouter$GlobalMediaRouter this$0;
    
    public void clearVolumeHandling() {
        this.mMsCompat.setPlaybackToLocal(this.this$0.mPlaybackInfo.playbackStream);
        this.mVpCompat = null;
    }
    
    public void configureVolume(final int n, final int n2, final int currentVolume) {
        if (this.mVpCompat != null && n == this.mControlType && n2 == this.mMaxVolume) {
            this.mVpCompat.setCurrentVolume(currentVolume);
            return;
        }
        this.mVpCompat = new MediaRouter$GlobalMediaRouter$MediaSessionRecord$1(this, n, n2, currentVolume);
        this.mMsCompat.setPlaybackToRemote(this.mVpCompat);
    }
}
