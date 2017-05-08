// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import java.lang.reflect.InvocationTargetException;
import android.media.session.MediaSession;

class MediaSessionCompatApi24
{
    private static final String TAG = "MediaSessionCompatApi24";
    
    public static Object createCallback(final MediaSessionCompatApi24$Callback mediaSessionCompatApi24$Callback) {
        return new MediaSessionCompatApi24$CallbackProxy(mediaSessionCompatApi24$Callback);
    }
    
    public static String getCallingPackage(Object ex) {
        ex = ex;
        try {
            ex = (NoSuchMethodException)ex.getClass().getMethod("getCallingPackage", (Class<?>[])new Class[0]).invoke(ex, new Object[0]);
            return (String)ex;
        }
        catch (IllegalAccessException ex2) {}
        catch (NoSuchMethodException ex) {
            goto Label_0033;
        }
        catch (InvocationTargetException ex) {
            goto Label_0033;
        }
    }
}
