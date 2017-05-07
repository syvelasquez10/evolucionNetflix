// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.Bundle;

public interface MediaControllerCompatApi21$Callback
{
    void onMetadataChanged(final Object p0);
    
    void onPlaybackStateChanged(final Object p0);
    
    void onSessionDestroyed();
    
    void onSessionEvent(final String p0, final Bundle p1);
}
