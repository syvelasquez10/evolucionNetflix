// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.lang.reflect.InvocationTargetException;
import android.media.MediaRouter;
import android.os.Build$VERSION;
import java.lang.reflect.Method;

public final class MediaRouterJellybean$GetDefaultRouteWorkaround
{
    private Method mGetSystemAudioRouteMethod;
    
    public MediaRouterJellybean$GetDefaultRouteWorkaround() {
        if (Build$VERSION.SDK_INT < 16 || Build$VERSION.SDK_INT > 17) {
            throw new UnsupportedOperationException();
        }
        try {
            this.mGetSystemAudioRouteMethod = MediaRouter.class.getMethod("getSystemAudioRoute", (Class<?>[])new Class[0]);
        }
        catch (NoSuchMethodException ex) {}
    }
    
    public Object getDefaultRoute(Object o) {
        o = o;
        if (this.mGetSystemAudioRouteMethod == null) {
            goto Label_0028;
        }
        try {
            return this.mGetSystemAudioRouteMethod.invoke(o, new Object[0]);
        }
        catch (InvocationTargetException ex) {}
        catch (IllegalAccessException ex2) {
            goto Label_0028;
        }
    }
}
