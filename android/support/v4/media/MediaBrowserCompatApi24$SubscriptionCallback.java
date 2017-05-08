// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.Bundle;
import java.util.List;

interface MediaBrowserCompatApi24$SubscriptionCallback extends MediaBrowserCompatApi21$SubscriptionCallback
{
    void onChildrenLoaded(final String p0, final List<?> p1, final Bundle p2);
    
    void onError(final String p0, final Bundle p1);
}
