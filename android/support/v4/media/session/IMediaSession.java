// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.view.KeyEvent;
import android.support.v4.media.RatingCompat;
import android.net.Uri;
import java.util.List;
import android.support.v4.media.MediaMetadataCompat;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IInterface;

public interface IMediaSession extends IInterface
{
    void adjustVolume(final int p0, final int p1, final String p2);
    
    void fastForward();
    
    Bundle getExtras();
    
    long getFlags();
    
    PendingIntent getLaunchPendingIntent();
    
    MediaMetadataCompat getMetadata();
    
    String getPackageName();
    
    PlaybackStateCompat getPlaybackState();
    
    List<MediaSessionCompat$QueueItem> getQueue();
    
    CharSequence getQueueTitle();
    
    int getRatingType();
    
    String getTag();
    
    ParcelableVolumeInfo getVolumeAttributes();
    
    boolean isTransportControlEnabled();
    
    void next();
    
    void pause();
    
    void play();
    
    void playFromMediaId(final String p0, final Bundle p1);
    
    void playFromSearch(final String p0, final Bundle p1);
    
    void playFromUri(final Uri p0, final Bundle p1);
    
    void prepare();
    
    void prepareFromMediaId(final String p0, final Bundle p1);
    
    void prepareFromSearch(final String p0, final Bundle p1);
    
    void prepareFromUri(final Uri p0, final Bundle p1);
    
    void previous();
    
    void rate(final RatingCompat p0);
    
    void registerCallbackListener(final IMediaControllerCallback p0);
    
    void rewind();
    
    void seekTo(final long p0);
    
    void sendCommand(final String p0, final Bundle p1, final MediaSessionCompat$ResultReceiverWrapper p2);
    
    void sendCustomAction(final String p0, final Bundle p1);
    
    boolean sendMediaButton(final KeyEvent p0);
    
    void setVolumeTo(final int p0, final int p1, final String p2);
    
    void skipToQueueItem(final long p0);
    
    void stop();
    
    void unregisterCallbackListener(final IMediaControllerCallback p0);
}
