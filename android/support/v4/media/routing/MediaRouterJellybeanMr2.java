// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.routing;

import android.media.MediaRouter$Callback;
import android.media.MediaRouter;

class MediaRouterJellybeanMr2 extends MediaRouterJellybeanMr1
{
    public static void addCallback(final Object o, final int n, final Object o2, final int n2) {
        ((MediaRouter)o).addCallback(n, (MediaRouter$Callback)o2, n2);
    }
    
    public static Object getDefaultRoute(final Object o) {
        return ((MediaRouter)o).getDefaultRoute();
    }
}
