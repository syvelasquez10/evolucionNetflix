// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.routing;

import android.media.MediaRouter$RouteInfo;
import java.util.ArrayList;
import android.media.MediaRouter$RouteGroup;
import java.util.List;

public final class MediaRouterJellybean$RouteGroup
{
    public static List getGroupedRoutes(final Object o) {
        final MediaRouter$RouteGroup mediaRouter$RouteGroup = (MediaRouter$RouteGroup)o;
        final int routeCount = mediaRouter$RouteGroup.getRouteCount();
        final ArrayList list = new ArrayList<MediaRouter$RouteInfo>(routeCount);
        for (int i = 0; i < routeCount; ++i) {
            list.add(mediaRouter$RouteGroup.getRouteAt(i));
        }
        return list;
    }
}
