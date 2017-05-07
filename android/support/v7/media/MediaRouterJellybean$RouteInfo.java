// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.media.MediaRouter$RouteInfo;
import android.content.Context;

public final class MediaRouterJellybean$RouteInfo
{
    public static CharSequence getName(final Object o, final Context context) {
        return ((MediaRouter$RouteInfo)o).getName(context);
    }
    
    public static int getPlaybackStream(final Object o) {
        return ((MediaRouter$RouteInfo)o).getPlaybackStream();
    }
    
    public static int getPlaybackType(final Object o) {
        return ((MediaRouter$RouteInfo)o).getPlaybackType();
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
