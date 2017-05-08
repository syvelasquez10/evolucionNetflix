// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.Bundle;
import android.net.Uri;

class MediaSessionCompatApi23$CallbackProxy<T extends MediaSessionCompatApi23$Callback> extends MediaSessionCompatApi21$CallbackProxy<T>
{
    public MediaSessionCompatApi23$CallbackProxy(final T t) {
        super(t);
    }
    
    public void onPlayFromUri(final Uri uri, final Bundle bundle) {
        this.mCallback.onPlayFromUri(uri, bundle);
    }
}
