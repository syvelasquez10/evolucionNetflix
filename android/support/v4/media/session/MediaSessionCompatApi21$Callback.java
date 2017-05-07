// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.content.Intent;
import android.os.ResultReceiver;
import android.os.Bundle;

public interface MediaSessionCompatApi21$Callback
{
    void onCommand(final String p0, final Bundle p1, final ResultReceiver p2);
    
    void onFastForward();
    
    boolean onMediaButtonEvent(final Intent p0);
    
    void onPause();
    
    void onPlay();
    
    void onRewind();
    
    void onSeekTo(final long p0);
    
    void onSetRating(final Object p0);
    
    void onSkipToNext();
    
    void onSkipToPrevious();
    
    void onStop();
}
