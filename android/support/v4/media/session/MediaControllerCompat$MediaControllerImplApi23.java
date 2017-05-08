// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.content.Context;

class MediaControllerCompat$MediaControllerImplApi23 extends MediaControllerCompat$MediaControllerImplApi21
{
    public MediaControllerCompat$MediaControllerImplApi23(final Context context, final MediaSessionCompat$Token mediaSessionCompat$Token) {
        super(context, mediaSessionCompat$Token);
    }
    
    public MediaControllerCompat$MediaControllerImplApi23(final Context context, final MediaSessionCompat mediaSessionCompat) {
        super(context, mediaSessionCompat);
    }
    
    @Override
    public MediaControllerCompat$TransportControls getTransportControls() {
        final Object transportControls = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
        if (transportControls != null) {
            return new MediaControllerCompat$TransportControlsApi23(transportControls);
        }
        return null;
    }
}
