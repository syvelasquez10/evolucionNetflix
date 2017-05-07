// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.support.v4.media.RatingCompat;
import android.content.Intent;
import android.os.ResultReceiver;
import android.os.Bundle;

class MediaSessionCompat$Callback$StubApi21 implements MediaSessionCompatApi21$Callback
{
    final /* synthetic */ MediaSessionCompat$Callback this$0;
    
    private MediaSessionCompat$Callback$StubApi21(final MediaSessionCompat$Callback this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        this.this$0.onCommand(s, bundle, resultReceiver);
    }
    
    @Override
    public void onFastForward() {
        this.this$0.onFastForward();
    }
    
    @Override
    public boolean onMediaButtonEvent(final Intent intent) {
        return this.this$0.onMediaButtonEvent(intent);
    }
    
    @Override
    public void onPause() {
        this.this$0.onPause();
    }
    
    @Override
    public void onPlay() {
        this.this$0.onPlay();
    }
    
    @Override
    public void onRewind() {
        this.this$0.onRewind();
    }
    
    @Override
    public void onSeekTo(final long n) {
        this.this$0.onSeekTo(n);
    }
    
    @Override
    public void onSetRating(final Object o) {
        this.this$0.onSetRating(RatingCompat.fromRating(o));
    }
    
    @Override
    public void onSkipToNext() {
        this.this$0.onSkipToNext();
    }
    
    @Override
    public void onSkipToPrevious() {
        this.this$0.onSkipToPrevious();
    }
    
    @Override
    public void onStop() {
        this.this$0.onStop();
    }
}
