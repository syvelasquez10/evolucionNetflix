// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.support.v4.media.RatingCompat;

public abstract class MediaControllerCompat$TransportControls
{
    public abstract void fastForward();
    
    public abstract void pause();
    
    public abstract void play();
    
    public abstract void rewind();
    
    public abstract void seekTo(final long p0);
    
    public abstract void setRating(final RatingCompat p0);
    
    public abstract void skipToNext();
    
    public abstract void skipToPrevious();
    
    public abstract void stop();
}
