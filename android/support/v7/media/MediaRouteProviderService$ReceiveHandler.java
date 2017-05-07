// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.IBinder;
import android.os.DeadObjectException;
import android.os.RemoteException;
import java.util.ArrayList;
import android.app.Service;
import android.util.Log;
import android.os.Message;
import android.content.Intent;
import android.os.Bundle;
import android.os.Messenger;
import java.lang.ref.WeakReference;
import android.os.Handler;

final class MediaRouteProviderService$ReceiveHandler extends Handler
{
    private final WeakReference<MediaRouteProviderService> mServiceRef;
    
    public MediaRouteProviderService$ReceiveHandler(final MediaRouteProviderService mediaRouteProviderService) {
        this.mServiceRef = new WeakReference<MediaRouteProviderService>(mediaRouteProviderService);
    }
    
    private boolean processMessage(int n, final Messenger messenger, final int n2, final int n3, final Object o, final Bundle bundle) {
        final MediaRouteProviderService mediaRouteProviderService = this.mServiceRef.get();
        if (mediaRouteProviderService != null) {
            switch (n) {
                case 1: {
                    return mediaRouteProviderService.onRegisterClient(messenger, n2, n3);
                }
                case 2: {
                    return mediaRouteProviderService.onUnregisterClient(messenger, n2);
                }
                case 3: {
                    final String string = bundle.getString("routeId");
                    if (string != null) {
                        return mediaRouteProviderService.onCreateRouteController(messenger, n2, n3, string);
                    }
                    break;
                }
                case 4: {
                    return mediaRouteProviderService.onReleaseRouteController(messenger, n2, n3);
                }
                case 5: {
                    return mediaRouteProviderService.onSelectRoute(messenger, n2, n3);
                }
                case 6: {
                    return mediaRouteProviderService.onUnselectRoute(messenger, n2, n3);
                }
                case 7: {
                    n = bundle.getInt("volume", -1);
                    if (n >= 0) {
                        return mediaRouteProviderService.onSetRouteVolume(messenger, n2, n3, n);
                    }
                    break;
                }
                case 8: {
                    n = bundle.getInt("volume", 0);
                    if (n != 0) {
                        return mediaRouteProviderService.onUpdateRouteVolume(messenger, n2, n3, n);
                    }
                    break;
                }
                case 9: {
                    if (o instanceof Intent) {
                        return mediaRouteProviderService.onRouteControlRequest(messenger, n2, n3, (Intent)o);
                    }
                    break;
                }
                case 10: {
                    if (o == null || o instanceof Bundle) {
                        MediaRouteDiscoveryRequest fromBundle = MediaRouteDiscoveryRequest.fromBundle((Bundle)o);
                        if (fromBundle == null || !fromBundle.isValid()) {
                            fromBundle = null;
                        }
                        return mediaRouteProviderService.onSetDiscoveryRequest(messenger, n2, fromBundle);
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    public void handleMessage(final Message message) {
        final Messenger replyTo = message.replyTo;
        if (MediaRouteProviderProtocol.isValidRemoteMessenger(replyTo)) {
            final int what = message.what;
            final int arg1 = message.arg1;
            final int arg2 = message.arg2;
            final Object obj = message.obj;
            final Bundle peekData = message.peekData();
            if (!this.processMessage(what, replyTo, arg1, arg2, obj, peekData)) {
                if (MediaRouteProviderService.DEBUG) {
                    Log.d("MediaRouteProviderSrv", getClientId(replyTo) + ": Message failed, what=" + what + ", requestId=" + arg1 + ", arg=" + arg2 + ", obj=" + obj + ", data=" + peekData);
                }
                sendGenericFailure(replyTo, arg1);
            }
        }
        else if (MediaRouteProviderService.DEBUG) {
            Log.d("MediaRouteProviderSrv", "Ignoring message without valid reply messenger.");
        }
    }
}
