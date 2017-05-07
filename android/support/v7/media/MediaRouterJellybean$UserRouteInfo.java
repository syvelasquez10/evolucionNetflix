// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.media.MediaRouter$VolumeCallback;
import android.media.MediaRouter$UserRouteInfo;

public final class MediaRouterJellybean$UserRouteInfo
{
    public static void setName(final Object o, final CharSequence name) {
        ((MediaRouter$UserRouteInfo)o).setName(name);
    }
    
    public static void setPlaybackStream(final Object o, final int playbackStream) {
        ((MediaRouter$UserRouteInfo)o).setPlaybackStream(playbackStream);
    }
    
    public static void setPlaybackType(final Object o, final int playbackType) {
        ((MediaRouter$UserRouteInfo)o).setPlaybackType(playbackType);
    }
    
    public static void setVolume(final Object o, final int volume) {
        ((MediaRouter$UserRouteInfo)o).setVolume(volume);
    }
    
    public static void setVolumeCallback(final Object o, final Object o2) {
        ((MediaRouter$UserRouteInfo)o).setVolumeCallback((MediaRouter$VolumeCallback)o2);
    }
    
    public static void setVolumeHandling(final Object o, final int volumeHandling) {
        ((MediaRouter$UserRouteInfo)o).setVolumeHandling(volumeHandling);
    }
    
    public static void setVolumeMax(final Object o, final int volumeMax) {
        ((MediaRouter$UserRouteInfo)o).setVolumeMax(volumeMax);
    }
}
