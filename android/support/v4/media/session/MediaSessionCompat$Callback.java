// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.support.v4.media.RatingCompat;
import android.content.Intent;
import android.os.ResultReceiver;
import android.os.Bundle;
import android.os.Build$VERSION;

public abstract class MediaSessionCompat$Callback
{
    final Object mCallbackObj;
    
    public MediaSessionCompat$Callback() {
        if (Build$VERSION.SDK_INT >= 21) {
            this.mCallbackObj = MediaSessionCompatApi21.createCallback(new MediaSessionCompat$Callback$StubApi21(this, null));
            return;
        }
        this.mCallbackObj = null;
    }
    
    public void onCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
    }
    
    public void onFastForward() {
    }
    
    public boolean onMediaButtonEvent(final Intent intent) {
        return false;
    }
    
    public void onPause() {
    }
    
    public void onPlay() {
    }
    
    public void onRewind() {
    }
    
    public void onSeekTo(final long n) {
    }
    
    public void onSetRating(final RatingCompat ratingCompat) {
    }
    
    public void onSkipToNext() {
    }
    
    public void onSkipToPrevious() {
    }
    
    public void onStop() {
    }
}
