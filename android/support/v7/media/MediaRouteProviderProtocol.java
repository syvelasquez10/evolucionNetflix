// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.IBinder;
import android.os.Messenger;

abstract class MediaRouteProviderProtocol
{
    public static boolean isValidRemoteMessenger(final Messenger messenger) {
        boolean b = false;
        if (messenger == null) {
            return b;
        }
        try {
            final IBinder binder = messenger.getBinder();
            b = b;
            if (binder != null) {
                b = true;
            }
            return b;
        }
        catch (NullPointerException ex) {
            return false;
        }
    }
}
