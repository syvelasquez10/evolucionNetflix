// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.lang.reflect.InvocationTargetException;
import android.util.Log;
import android.media.MediaRouter$RouteInfo;
import android.media.MediaRouter;
import android.os.Build$VERSION;
import java.lang.reflect.Method;

public final class MediaRouterJellybean$SelectRouteWorkaround
{
    private Method mSelectRouteIntMethod;
    
    public MediaRouterJellybean$SelectRouteWorkaround() {
        if (Build$VERSION.SDK_INT < 16 || Build$VERSION.SDK_INT > 17) {
            throw new UnsupportedOperationException();
        }
        try {
            this.mSelectRouteIntMethod = MediaRouter.class.getMethod("selectRouteInt", Integer.TYPE, MediaRouter$RouteInfo.class);
        }
        catch (NoSuchMethodException ex) {}
    }
    
    public void selectRoute(Object o, final int n, Object o2) {
        o = o;
        o2 = o2;
        if ((((MediaRouter$RouteInfo)o2).getSupportedTypes() & 0x800000) == 0x0) {
            if (this.mSelectRouteIntMethod != null) {
                try {
                    this.mSelectRouteIntMethod.invoke(o, n, o2);
                    return;
                }
                catch (IllegalAccessException ex) {
                    Log.w("MediaRouterJellybean", "Cannot programmatically select non-user route.  Media routing may not work.", (Throwable)ex);
                }
                catch (InvocationTargetException ex2) {
                    Log.w("MediaRouterJellybean", "Cannot programmatically select non-user route.  Media routing may not work.", (Throwable)ex2);
                    goto Label_0064;
                }
            }
            Log.w("MediaRouterJellybean", "Cannot programmatically select non-user route because the platform is missing the selectRouteInt() method.  Media routing may not work.");
            goto Label_0064;
        }
        goto Label_0064;
    }
}
