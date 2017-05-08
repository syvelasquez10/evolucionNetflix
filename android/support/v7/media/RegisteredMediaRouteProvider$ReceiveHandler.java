// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.util.Log;
import android.os.Message;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import android.os.Handler;

final class RegisteredMediaRouteProvider$ReceiveHandler extends Handler
{
    private final WeakReference<RegisteredMediaRouteProvider$Connection> mConnectionRef;
    
    public RegisteredMediaRouteProvider$ReceiveHandler(final RegisteredMediaRouteProvider$Connection registeredMediaRouteProvider$Connection) {
        this.mConnectionRef = new WeakReference<RegisteredMediaRouteProvider$Connection>(registeredMediaRouteProvider$Connection);
    }
    
    private boolean processMessage(final RegisteredMediaRouteProvider$Connection registeredMediaRouteProvider$Connection, final int n, final int n2, final int n3, final Object o, final Bundle bundle) {
        switch (n) {
            case 0: {
                registeredMediaRouteProvider$Connection.onGenericFailure(n2);
                return true;
            }
            case 1: {
                registeredMediaRouteProvider$Connection.onGenericSuccess(n2);
                return true;
            }
            case 2: {
                if (o == null || o instanceof Bundle) {
                    return registeredMediaRouteProvider$Connection.onRegistered(n2, n3, (Bundle)o);
                }
                break;
            }
            case 5: {
                if (o == null || o instanceof Bundle) {
                    return registeredMediaRouteProvider$Connection.onDescriptorChanged((Bundle)o);
                }
                break;
            }
            case 3: {
                if (o == null || o instanceof Bundle) {
                    return registeredMediaRouteProvider$Connection.onControlRequestSucceeded(n2, (Bundle)o);
                }
                break;
            }
            case 4: {
                if (o == null || o instanceof Bundle) {
                    String string;
                    if (bundle == null) {
                        string = null;
                    }
                    else {
                        string = bundle.getString("error");
                    }
                    return registeredMediaRouteProvider$Connection.onControlRequestFailed(n2, string, (Bundle)o);
                }
                break;
            }
        }
        return false;
    }
    
    public void dispose() {
        this.mConnectionRef.clear();
    }
    
    public void handleMessage(final Message message) {
        final RegisteredMediaRouteProvider$Connection registeredMediaRouteProvider$Connection = this.mConnectionRef.get();
        if (registeredMediaRouteProvider$Connection != null && !this.processMessage(registeredMediaRouteProvider$Connection, message.what, message.arg1, message.arg2, message.obj, message.peekData()) && RegisteredMediaRouteProvider.DEBUG) {
            Log.d("MediaRouteProviderProxy", "Unhandled message from server: " + message);
        }
    }
}
