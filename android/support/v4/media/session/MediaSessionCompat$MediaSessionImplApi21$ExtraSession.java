// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import java.util.Iterator;
import java.util.ArrayList;
import android.support.v4.media.VolumeProviderCompat;
import java.lang.ref.WeakReference;
import android.os.Handler;
import android.os.RemoteException;
import android.os.Build$VERSION;
import android.content.Context;
import android.os.RemoteCallbackList;
import android.view.KeyEvent;
import android.os.IInterface;
import android.support.v4.media.RatingCompat;
import android.net.Uri;
import java.util.List;
import android.support.v4.media.MediaMetadataCompat;
import android.app.PendingIntent;
import android.os.Bundle;

class MediaSessionCompat$MediaSessionImplApi21$ExtraSession extends IMediaSession$Stub
{
    final /* synthetic */ MediaSessionCompat$MediaSessionImplApi21 this$0;
    
    MediaSessionCompat$MediaSessionImplApi21$ExtraSession(final MediaSessionCompat$MediaSessionImplApi21 this$0) {
        this.this$0 = this$0;
    }
    
    public void adjustVolume(final int n, final int n2, final String s) {
        throw new AssertionError();
    }
    
    public void fastForward() {
        throw new AssertionError();
    }
    
    public Bundle getExtras() {
        throw new AssertionError();
    }
    
    public long getFlags() {
        throw new AssertionError();
    }
    
    public PendingIntent getLaunchPendingIntent() {
        throw new AssertionError();
    }
    
    public MediaMetadataCompat getMetadata() {
        throw new AssertionError();
    }
    
    public String getPackageName() {
        throw new AssertionError();
    }
    
    public PlaybackStateCompat getPlaybackState() {
        return this.this$0.mPlaybackState;
    }
    
    public List<MediaSessionCompat$QueueItem> getQueue() {
        return null;
    }
    
    public CharSequence getQueueTitle() {
        throw new AssertionError();
    }
    
    public int getRatingType() {
        return this.this$0.mRatingType;
    }
    
    public String getTag() {
        throw new AssertionError();
    }
    
    public ParcelableVolumeInfo getVolumeAttributes() {
        throw new AssertionError();
    }
    
    public boolean isTransportControlEnabled() {
        throw new AssertionError();
    }
    
    public void next() {
        throw new AssertionError();
    }
    
    public void pause() {
        throw new AssertionError();
    }
    
    public void play() {
        throw new AssertionError();
    }
    
    public void playFromMediaId(final String s, final Bundle bundle) {
        throw new AssertionError();
    }
    
    public void playFromSearch(final String s, final Bundle bundle) {
        throw new AssertionError();
    }
    
    public void playFromUri(final Uri uri, final Bundle bundle) {
        throw new AssertionError();
    }
    
    public void prepare() {
        throw new AssertionError();
    }
    
    public void prepareFromMediaId(final String s, final Bundle bundle) {
        throw new AssertionError();
    }
    
    public void prepareFromSearch(final String s, final Bundle bundle) {
        throw new AssertionError();
    }
    
    public void prepareFromUri(final Uri uri, final Bundle bundle) {
        throw new AssertionError();
    }
    
    public void previous() {
        throw new AssertionError();
    }
    
    public void rate(final RatingCompat ratingCompat) {
        throw new AssertionError();
    }
    
    public void registerCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
        if (!this.this$0.mDestroyed) {
            this.this$0.mExtraControllerCallbacks.register((IInterface)mediaControllerCallback);
        }
    }
    
    public void rewind() {
        throw new AssertionError();
    }
    
    public void seekTo(final long n) {
        throw new AssertionError();
    }
    
    public void sendCommand(final String s, final Bundle bundle, final MediaSessionCompat$ResultReceiverWrapper mediaSessionCompat$ResultReceiverWrapper) {
        throw new AssertionError();
    }
    
    public void sendCustomAction(final String s, final Bundle bundle) {
        throw new AssertionError();
    }
    
    public boolean sendMediaButton(final KeyEvent keyEvent) {
        throw new AssertionError();
    }
    
    public void setVolumeTo(final int n, final int n2, final String s) {
        throw new AssertionError();
    }
    
    public void skipToQueueItem(final long n) {
        throw new AssertionError();
    }
    
    public void stop() {
        throw new AssertionError();
    }
    
    public void unregisterCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
        this.this$0.mExtraControllerCallbacks.unregister((IInterface)mediaControllerCallback);
    }
}
