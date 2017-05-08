// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.annotation.TargetApi;

@TargetApi(23)
class MediaSessionCompatApi23
{
    public static Object createCallback(final MediaSessionCompatApi23$Callback mediaSessionCompatApi23$Callback) {
        return new MediaSessionCompatApi23$CallbackProxy(mediaSessionCompatApi23$Callback);
    }
}
