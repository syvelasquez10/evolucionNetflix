// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

class MediaSessionCompatApi24$CallbackProxy<T extends MediaSessionCompatApi24$Callback> extends MediaSessionCompatApi23$CallbackProxy<T>
{
    public MediaSessionCompatApi24$CallbackProxy(final T t) {
        super(t);
    }
    
    public void onPrepare() {
        this.mCallback.onPrepare();
    }
    
    public void onPrepareFromMediaId(final String s, final Bundle bundle) {
        this.mCallback.onPrepareFromMediaId(s, bundle);
    }
    
    public void onPrepareFromSearch(final String s, final Bundle bundle) {
        this.mCallback.onPrepareFromSearch(s, bundle);
    }
    
    public void onPrepareFromUri(final Uri uri, final Bundle bundle) {
        this.mCallback.onPrepareFromUri(uri, bundle);
    }
}
