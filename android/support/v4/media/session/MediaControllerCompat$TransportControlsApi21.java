// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.support.v4.media.RatingCompat;

class MediaControllerCompat$TransportControlsApi21 extends MediaControllerCompat$TransportControls
{
    private final Object mControlsObj;
    
    public MediaControllerCompat$TransportControlsApi21(final Object mControlsObj) {
        this.mControlsObj = mControlsObj;
    }
    
    @Override
    public void fastForward() {
        MediaControllerCompatApi21$TransportControls.fastForward(this.mControlsObj);
    }
    
    @Override
    public void pause() {
        MediaControllerCompatApi21$TransportControls.pause(this.mControlsObj);
    }
    
    @Override
    public void play() {
        MediaControllerCompatApi21$TransportControls.play(this.mControlsObj);
    }
    
    @Override
    public void rewind() {
        MediaControllerCompatApi21$TransportControls.rewind(this.mControlsObj);
    }
    
    @Override
    public void seekTo(final long n) {
        MediaControllerCompatApi21$TransportControls.seekTo(this.mControlsObj, n);
    }
    
    @Override
    public void setRating(final RatingCompat ratingCompat) {
        final Object mControlsObj = this.mControlsObj;
        Object rating;
        if (ratingCompat != null) {
            rating = ratingCompat.getRating();
        }
        else {
            rating = null;
        }
        MediaControllerCompatApi21$TransportControls.setRating(mControlsObj, rating);
    }
    
    @Override
    public void skipToNext() {
        MediaControllerCompatApi21$TransportControls.skipToNext(this.mControlsObj);
    }
    
    @Override
    public void skipToPrevious() {
        MediaControllerCompatApi21$TransportControls.skipToPrevious(this.mControlsObj);
    }
    
    @Override
    public void stop() {
        MediaControllerCompatApi21$TransportControls.stop(this.mControlsObj);
    }
}
