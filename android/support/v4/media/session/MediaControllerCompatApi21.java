// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.ResultReceiver;
import android.os.Bundle;
import android.media.session.MediaController$Callback;
import android.os.Handler;
import android.media.session.MediaSession$Token;
import android.content.Context;
import android.media.session.MediaController;
import android.view.KeyEvent;

class MediaControllerCompatApi21
{
    public static Object createCallback(final MediaControllerCompatApi21$Callback mediaControllerCompatApi21$Callback) {
        return new MediaControllerCompatApi21$CallbackProxy(mediaControllerCompatApi21$Callback);
    }
    
    public static boolean dispatchMediaButtonEvent(final Object o, final KeyEvent keyEvent) {
        return ((MediaController)o).dispatchMediaButtonEvent(keyEvent);
    }
    
    public static Object fromToken(final Context context, final Object o) {
        return new MediaController(context, (MediaSession$Token)o);
    }
    
    public static Object getMetadata(final Object o) {
        return ((MediaController)o).getMetadata();
    }
    
    public static Object getPlaybackInfo(final Object o) {
        return ((MediaController)o).getPlaybackInfo();
    }
    
    public static Object getPlaybackState(final Object o) {
        return ((MediaController)o).getPlaybackState();
    }
    
    public static int getRatingType(final Object o) {
        return ((MediaController)o).getRatingType();
    }
    
    public static Object getTransportControls(final Object o) {
        return ((MediaController)o).getTransportControls();
    }
    
    public static void registerCallback(final Object o, final Object o2, final Handler handler) {
        ((MediaController)o).registerCallback((MediaController$Callback)o2, handler);
    }
    
    public static void sendCommand(final Object o, final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        ((MediaController)o).sendCommand(s, bundle, resultReceiver);
    }
    
    public static void unregisterCallback(final Object o, final Object o2) {
        ((MediaController)o).unregisterCallback((MediaController$Callback)o2);
    }
}
