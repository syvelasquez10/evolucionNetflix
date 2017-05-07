// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v7.media.MediaRouter$RouteInfo;
import java.util.Comparator;

final class MediaRouteChooserDialog$RouteComparator implements Comparator<MediaRouter$RouteInfo>
{
    public static final MediaRouteChooserDialog$RouteComparator sInstance;
    
    static {
        sInstance = new MediaRouteChooserDialog$RouteComparator();
    }
    
    @Override
    public int compare(final MediaRouter$RouteInfo mediaRouter$RouteInfo, final MediaRouter$RouteInfo mediaRouter$RouteInfo2) {
        return mediaRouter$RouteInfo.getName().compareTo(mediaRouter$RouteInfo2.getName());
    }
}
