// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.lang.reflect.InvocationTargetException;
import android.media.MediaRouter$RouteInfo;
import android.os.Build$VERSION;
import java.lang.reflect.Method;

public final class MediaRouterJellybeanMr1$IsConnectingWorkaround
{
    private Method mGetStatusCodeMethod;
    private int mStatusConnecting;
    
    public MediaRouterJellybeanMr1$IsConnectingWorkaround() {
        if (Build$VERSION.SDK_INT != 17) {
            throw new UnsupportedOperationException();
        }
        try {
            this.mStatusConnecting = MediaRouter$RouteInfo.class.getField("STATUS_CONNECTING").getInt(null);
            this.mGetStatusCodeMethod = MediaRouter$RouteInfo.class.getMethod("getStatusCode", (Class<?>[])new Class[0]);
        }
        catch (IllegalAccessException ex) {}
        catch (NoSuchMethodException ex2) {}
        catch (NoSuchFieldException ex3) {}
    }
    
    public boolean isConnecting(final Object o) {
        final MediaRouter$RouteInfo mediaRouter$RouteInfo = (MediaRouter$RouteInfo)o;
        if (this.mGetStatusCodeMethod == null) {
            goto Label_0046;
        }
        try {
            return (int)this.mGetStatusCodeMethod.invoke(mediaRouter$RouteInfo, new Object[0]) == this.mStatusConnecting;
        }
        catch (InvocationTargetException ex) {}
        catch (IllegalAccessException ex2) {
            goto Label_0046;
        }
    }
}
