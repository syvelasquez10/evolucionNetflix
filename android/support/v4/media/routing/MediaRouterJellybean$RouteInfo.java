// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.routing;

import android.media.MediaRouter$RouteGroup;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaRouter$RouteInfo;

public final class MediaRouterJellybean$RouteInfo
{
    public static Object getCategory(final Object o) {
        return ((MediaRouter$RouteInfo)o).getCategory();
    }
    
    public static Object getGroup(final Object o) {
        return ((MediaRouter$RouteInfo)o).getGroup();
    }
    
    public static Drawable getIconDrawable(final Object o) {
        return ((MediaRouter$RouteInfo)o).getIconDrawable();
    }
    
    public static CharSequence getName(final Object o, final Context context) {
        return ((MediaRouter$RouteInfo)o).getName(context);
    }
    
    public static int getPlaybackStream(final Object o) {
        return ((MediaRouter$RouteInfo)o).getPlaybackStream();
    }
    
    public static int getPlaybackType(final Object o) {
        return ((MediaRouter$RouteInfo)o).getPlaybackType();
    }
    
    public static CharSequence getStatus(final Object o) {
        return ((MediaRouter$RouteInfo)o).getStatus();
    }
    
    public static int getSupportedTypes(final Object o) {
        return ((MediaRouter$RouteInfo)o).getSupportedTypes();
    }
    
    public static Object getTag(final Object o) {
        return ((MediaRouter$RouteInfo)o).getTag();
    }
    
    public static int getVolume(final Object o) {
        return ((MediaRouter$RouteInfo)o).getVolume();
    }
    
    public static int getVolumeHandling(final Object o) {
        return ((MediaRouter$RouteInfo)o).getVolumeHandling();
    }
    
    public static int getVolumeMax(final Object o) {
        return ((MediaRouter$RouteInfo)o).getVolumeMax();
    }
    
    public static boolean isGroup(final Object o) {
        return o instanceof MediaRouter$RouteGroup;
    }
    
    public static void requestSetVolume(final Object o, final int n) {
        ((MediaRouter$RouteInfo)o).requestSetVolume(n);
    }
    
    public static void requestUpdateVolume(final Object o, final int n) {
        ((MediaRouter$RouteInfo)o).requestUpdateVolume(n);
    }
    
    public static void setTag(final Object o, final Object tag) {
        ((MediaRouter$RouteInfo)o).setTag(tag);
    }
}
