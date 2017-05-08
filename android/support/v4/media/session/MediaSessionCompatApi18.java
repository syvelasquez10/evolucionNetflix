// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.SystemClock;
import android.media.RemoteControlClient$OnPlaybackPositionUpdateListener;
import android.media.RemoteControlClient;
import android.util.Log;
import android.media.AudioManager;
import android.content.ComponentName;
import android.app.PendingIntent;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(18)
class MediaSessionCompatApi18
{
    private static final long ACTION_SEEK_TO = 256L;
    private static final String TAG = "MediaSessionCompatApi18";
    private static boolean sIsMbrPendingIntentSupported;
    
    static {
        MediaSessionCompatApi18.sIsMbrPendingIntentSupported = true;
    }
    
    public static Object createPlaybackPositionUpdateListener(final MediaSessionCompatApi18$Callback mediaSessionCompatApi18$Callback) {
        return new MediaSessionCompatApi18$OnPlaybackPositionUpdateListener(mediaSessionCompatApi18$Callback);
    }
    
    static int getRccTransportControlFlagsFromActions(final long n) {
        int rccTransportControlFlagsFromActions = MediaSessionCompatApi14.getRccTransportControlFlagsFromActions(n);
        if ((0x100L & n) != 0x0L) {
            rccTransportControlFlagsFromActions |= 0x100;
        }
        return rccTransportControlFlagsFromActions;
    }
    
    public static void registerMediaButtonEventReceiver(Context context, final PendingIntent pendingIntent, final ComponentName componentName) {
        context = (Context)context.getSystemService("audio");
        while (true) {
            if (!MediaSessionCompatApi18.sIsMbrPendingIntentSupported) {
                break Label_0021;
            }
            try {
                ((AudioManager)context).registerMediaButtonEventReceiver(pendingIntent);
                if (!MediaSessionCompatApi18.sIsMbrPendingIntentSupported) {
                    ((AudioManager)context).registerMediaButtonEventReceiver(componentName);
                }
            }
            catch (NullPointerException ex) {
                Log.w("MediaSessionCompatApi18", "Unable to register media button event receiver with PendingIntent, falling back to ComponentName.");
                MediaSessionCompatApi18.sIsMbrPendingIntentSupported = false;
                continue;
            }
            break;
        }
    }
    
    public static void setOnPlaybackPositionUpdateListener(final Object o, final Object o2) {
        ((RemoteControlClient)o).setPlaybackPositionUpdateListener((RemoteControlClient$OnPlaybackPositionUpdateListener)o2);
    }
    
    public static void setState(final Object o, int rccStateFromState, final long n, final float n2, long n3) {
        final long n4 = 0L;
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        long n5 = n;
        if (rccStateFromState == 3) {
            n5 = n;
            if (n > 0L) {
                long n6 = n4;
                if (n3 > 0L) {
                    n3 = (n6 = elapsedRealtime - n3);
                    if (n2 > 0.0f) {
                        n6 = n3;
                        if (n2 != 1.0f) {
                            n6 = (long)(n3 * n2);
                        }
                    }
                }
                n5 = n + n6;
            }
        }
        rccStateFromState = MediaSessionCompatApi14.getRccStateFromState(rccStateFromState);
        ((RemoteControlClient)o).setPlaybackState(rccStateFromState, n5, n2);
    }
    
    public static void setTransportControlFlags(final Object o, final long n) {
        ((RemoteControlClient)o).setTransportControlFlags(getRccTransportControlFlagsFromActions(n));
    }
    
    public static void unregisterMediaButtonEventReceiver(final Context context, final PendingIntent pendingIntent, final ComponentName componentName) {
        final AudioManager audioManager = (AudioManager)context.getSystemService("audio");
        if (MediaSessionCompatApi18.sIsMbrPendingIntentSupported) {
            audioManager.unregisterMediaButtonEventReceiver(pendingIntent);
            return;
        }
        audioManager.unregisterMediaButtonEventReceiver(componentName);
    }
}
