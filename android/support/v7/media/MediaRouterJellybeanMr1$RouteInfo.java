// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.util.Log;
import android.media.MediaRouter$RouteInfo;
import android.view.Display;

public final class MediaRouterJellybeanMr1$RouteInfo
{
    public static Display getPresentationDisplay(final Object o) {
        try {
            return ((MediaRouter$RouteInfo)o).getPresentationDisplay();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            Log.w("MediaRouterJellybeanMr1", "Cannot get presentation display for the route.", (Throwable)noSuchMethodError);
            return null;
        }
    }
    
    public static boolean isEnabled(final Object o) {
        return ((MediaRouter$RouteInfo)o).isEnabled();
    }
}
