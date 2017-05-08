// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.media.session.MediaSession$QueueItem;
import java.util.List;
import android.media.session.PlaybackState;
import android.media.MediaMetadata;
import android.os.Bundle;
import android.media.session.MediaController$PlaybackInfo;
import android.media.session.MediaController$Callback;

class MediaControllerCompatApi21$CallbackProxy<T extends MediaControllerCompatApi21$Callback> extends MediaController$Callback
{
    protected final T mCallback;
    
    public MediaControllerCompatApi21$CallbackProxy(final T mCallback) {
        this.mCallback = mCallback;
    }
    
    public void onAudioInfoChanged(final MediaController$PlaybackInfo mediaController$PlaybackInfo) {
        this.mCallback.onAudioInfoChanged(mediaController$PlaybackInfo.getPlaybackType(), MediaControllerCompatApi21$PlaybackInfo.getLegacyAudioStream(mediaController$PlaybackInfo), mediaController$PlaybackInfo.getVolumeControl(), mediaController$PlaybackInfo.getMaxVolume(), mediaController$PlaybackInfo.getCurrentVolume());
    }
    
    public void onExtrasChanged(final Bundle bundle) {
        this.mCallback.onExtrasChanged(bundle);
    }
    
    public void onMetadataChanged(final MediaMetadata mediaMetadata) {
        this.mCallback.onMetadataChanged(mediaMetadata);
    }
    
    public void onPlaybackStateChanged(final PlaybackState playbackState) {
        this.mCallback.onPlaybackStateChanged(playbackState);
    }
    
    public void onQueueChanged(final List<MediaSession$QueueItem> list) {
        this.mCallback.onQueueChanged(list);
    }
    
    public void onQueueTitleChanged(final CharSequence charSequence) {
        this.mCallback.onQueueTitleChanged(charSequence);
    }
    
    public void onSessionDestroyed() {
        this.mCallback.onSessionDestroyed();
    }
    
    public void onSessionEvent(final String s, final Bundle bundle) {
        this.mCallback.onSessionEvent(s, bundle);
    }
}
