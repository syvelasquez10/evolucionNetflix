// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.Parcel;
import android.os.ResultReceiver;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.os.IInterface;
import android.support.v4.media.RatingCompat;
import android.net.Uri;
import android.support.v4.media.VolumeProviderCompat;
import java.util.List;
import android.support.v4.media.MediaMetadataCompat;
import android.app.PendingIntent;
import android.os.Bundle;

class MediaSessionCompat$MediaSessionImplBase$MediaSessionStub extends IMediaSession$Stub
{
    final /* synthetic */ MediaSessionCompat$MediaSessionImplBase this$0;
    
    MediaSessionCompat$MediaSessionImplBase$MediaSessionStub(final MediaSessionCompat$MediaSessionImplBase this$0) {
        this.this$0 = this$0;
    }
    
    public void adjustVolume(final int n, final int n2, final String s) {
        this.this$0.adjustVolume(n, n2);
    }
    
    public void fastForward() {
        this.this$0.postToHandler(16);
    }
    
    public Bundle getExtras() {
        synchronized (this.this$0.mLock) {
            return this.this$0.mExtras;
        }
    }
    
    public long getFlags() {
        synchronized (this.this$0.mLock) {
            return this.this$0.mFlags;
        }
    }
    
    public PendingIntent getLaunchPendingIntent() {
        synchronized (this.this$0.mLock) {
            return this.this$0.mSessionActivity;
        }
    }
    
    public MediaMetadataCompat getMetadata() {
        return this.this$0.mMetadata;
    }
    
    public String getPackageName() {
        return this.this$0.mPackageName;
    }
    
    public PlaybackStateCompat getPlaybackState() {
        return this.this$0.getStateWithUpdatedPosition();
    }
    
    public List<MediaSessionCompat$QueueItem> getQueue() {
        synchronized (this.this$0.mLock) {
            return this.this$0.mQueue;
        }
    }
    
    public CharSequence getQueueTitle() {
        return this.this$0.mQueueTitle;
    }
    
    public int getRatingType() {
        return this.this$0.mRatingType;
    }
    
    public String getTag() {
        return this.this$0.mTag;
    }
    
    public ParcelableVolumeInfo getVolumeAttributes() {
        int volumeControl = 2;
        synchronized (this.this$0.mLock) {
            final int mVolumeType = this.this$0.mVolumeType;
            final int mLocalStream = this.this$0.mLocalStream;
            final VolumeProviderCompat mVolumeProvider = this.this$0.mVolumeProvider;
            int n;
            int n2;
            if (mVolumeType == 2) {
                volumeControl = mVolumeProvider.getVolumeControl();
                n = mVolumeProvider.getMaxVolume();
                n2 = mVolumeProvider.getCurrentVolume();
            }
            else {
                n = this.this$0.mAudioManager.getStreamMaxVolume(mLocalStream);
                n2 = this.this$0.mAudioManager.getStreamVolume(mLocalStream);
            }
            // monitorexit(this.this$0.mLock)
            return new ParcelableVolumeInfo(mVolumeType, mLocalStream, volumeControl, n, n2);
        }
    }
    
    public boolean isTransportControlEnabled() {
        return (this.this$0.mFlags & 0x2) != 0x0;
    }
    
    public void next() {
        this.this$0.postToHandler(14);
    }
    
    public void pause() {
        this.this$0.postToHandler(12);
    }
    
    public void play() {
        this.this$0.postToHandler(7);
    }
    
    public void playFromMediaId(final String s, final Bundle bundle) {
        this.this$0.postToHandler(8, s, bundle);
    }
    
    public void playFromSearch(final String s, final Bundle bundle) {
        this.this$0.postToHandler(9, s, bundle);
    }
    
    public void playFromUri(final Uri uri, final Bundle bundle) {
        this.this$0.postToHandler(10, uri, bundle);
    }
    
    public void prepare() {
        this.this$0.postToHandler(3);
    }
    
    public void prepareFromMediaId(final String s, final Bundle bundle) {
        this.this$0.postToHandler(4, s, bundle);
    }
    
    public void prepareFromSearch(final String s, final Bundle bundle) {
        this.this$0.postToHandler(5, s, bundle);
    }
    
    public void prepareFromUri(final Uri uri, final Bundle bundle) {
        this.this$0.postToHandler(6, uri, bundle);
    }
    
    public void previous() {
        this.this$0.postToHandler(15);
    }
    
    public void rate(final RatingCompat ratingCompat) {
        this.this$0.postToHandler(19, ratingCompat);
    }
    
    public void registerCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
        Label_0017: {
            if (!this.this$0.mDestroyed) {
                break Label_0017;
            }
            try {
                mediaControllerCallback.onSessionDestroyed();
                return;
                this.this$0.mControllerCallbacks.register((IInterface)mediaControllerCallback);
            }
            catch (Exception ex) {}
        }
    }
    
    public void rewind() {
        this.this$0.postToHandler(17);
    }
    
    public void seekTo(final long n) {
        this.this$0.postToHandler(18, n);
    }
    
    public void sendCommand(final String s, final Bundle bundle, final MediaSessionCompat$ResultReceiverWrapper mediaSessionCompat$ResultReceiverWrapper) {
        this.this$0.postToHandler(1, new MediaSessionCompat$MediaSessionImplBase$Command(s, bundle, mediaSessionCompat$ResultReceiverWrapper.mResultReceiver));
    }
    
    public void sendCustomAction(final String s, final Bundle bundle) {
        this.this$0.postToHandler(20, s, bundle);
    }
    
    public boolean sendMediaButton(final KeyEvent keyEvent) {
        final boolean b = (this.this$0.mFlags & 0x1) != 0x0;
        if (b) {
            this.this$0.postToHandler(21, keyEvent);
        }
        return b;
    }
    
    public void setVolumeTo(final int n, final int n2, final String s) {
        this.this$0.setVolumeTo(n, n2);
    }
    
    public void skipToQueueItem(final long n) {
        this.this$0.postToHandler(11, n);
    }
    
    public void stop() {
        this.this$0.postToHandler(13);
    }
    
    public void unregisterCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
        this.this$0.mControllerCallbacks.unregister((IInterface)mediaControllerCallback);
    }
}
