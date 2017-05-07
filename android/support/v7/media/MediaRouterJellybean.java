// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.media.MediaRouter$RouteInfo;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import android.media.MediaRouter$RouteCategory;
import android.media.MediaRouter$UserRouteInfo;
import android.media.MediaRouter$Callback;
import android.media.MediaRouter;

final class MediaRouterJellybean
{
    public static final int ALL_ROUTE_TYPES = 8388611;
    public static final int ROUTE_TYPE_LIVE_AUDIO = 1;
    public static final int ROUTE_TYPE_LIVE_VIDEO = 2;
    public static final int ROUTE_TYPE_USER = 8388608;
    private static final String TAG = "MediaRouterJellybean";
    
    public static void addCallback(final Object o, final int n, final Object o2) {
        ((MediaRouter)o).addCallback(n, (MediaRouter$Callback)o2);
    }
    
    public static void addUserRoute(final Object o, final Object o2) {
        ((MediaRouter)o).addUserRoute((MediaRouter$UserRouteInfo)o2);
    }
    
    public static Object createCallback(final MediaRouterJellybean$Callback mediaRouterJellybean$Callback) {
        return new MediaRouterJellybean$CallbackProxy(mediaRouterJellybean$Callback);
    }
    
    public static Object createRouteCategory(final Object o, final String s, final boolean b) {
        return ((MediaRouter)o).createRouteCategory((CharSequence)s, b);
    }
    
    public static Object createUserRoute(final Object o, final Object o2) {
        return ((MediaRouter)o).createUserRoute((MediaRouter$RouteCategory)o2);
    }
    
    public static Object createVolumeCallback(final MediaRouterJellybean$VolumeCallback mediaRouterJellybean$VolumeCallback) {
        return new MediaRouterJellybean$VolumeCallbackProxy(mediaRouterJellybean$VolumeCallback);
    }
    
    public static List getCategories(final Object o) {
        final MediaRouter mediaRouter = (MediaRouter)o;
        final int categoryCount = mediaRouter.getCategoryCount();
        final ArrayList list = new ArrayList<MediaRouter$RouteCategory>(categoryCount);
        for (int i = 0; i < categoryCount; ++i) {
            list.add(mediaRouter.getCategoryAt(i));
        }
        return list;
    }
    
    public static Object getMediaRouter(final Context context) {
        return context.getSystemService("media_router");
    }
    
    public static List getRoutes(final Object o) {
        final MediaRouter mediaRouter = (MediaRouter)o;
        final int routeCount = mediaRouter.getRouteCount();
        final ArrayList list = new ArrayList<MediaRouter$RouteInfo>(routeCount);
        for (int i = 0; i < routeCount; ++i) {
            list.add(mediaRouter.getRouteAt(i));
        }
        return list;
    }
    
    public static Object getSelectedRoute(final Object o, final int n) {
        return ((MediaRouter)o).getSelectedRoute(n);
    }
    
    public static void removeCallback(final Object o, final Object o2) {
        ((MediaRouter)o).removeCallback((MediaRouter$Callback)o2);
    }
    
    public static void removeUserRoute(final Object o, final Object o2) {
        ((MediaRouter)o).removeUserRoute((MediaRouter$UserRouteInfo)o2);
    }
    
    public static void selectRoute(final Object o, final int n, final Object o2) {
        ((MediaRouter)o).selectRoute(n, (MediaRouter$RouteInfo)o2);
    }
}
