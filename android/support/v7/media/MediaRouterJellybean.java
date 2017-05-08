// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import android.media.MediaRouter$RouteInfo;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.media.MediaRouter$RouteCategory;
import android.media.MediaRouter$UserRouteInfo;
import android.media.MediaRouter$Callback;
import android.media.MediaRouter;
import android.annotation.TargetApi;

@TargetApi(16)
final class MediaRouterJellybean
{
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
    
    static boolean isBluetoothA2dpOn(Object value) {
        try {
            final Field declaredField = value.getClass().getDeclaredField("sStatic");
            declaredField.setAccessible(true);
            value = declaredField.get(null);
            final Method declaredMethod = value.getClass().getDeclaredMethod("isBluetoothA2dpOn", (Class<?>[])null);
            declaredMethod.setAccessible(true);
            return (boolean)declaredMethod.invoke(value, (Object[])null);
        }
        catch (NoSuchFieldException ex) {}
        catch (IllegalAccessException ex2) {
            goto Label_0053;
        }
        catch (IllegalArgumentException ex3) {
            goto Label_0053;
        }
        catch (NoSuchMethodException ex4) {
            goto Label_0053;
        }
        catch (InvocationTargetException ex5) {
            goto Label_0053;
        }
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
