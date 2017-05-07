// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.IBinder;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.Message;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.os.Messenger;
import java.util.ArrayList;
import android.app.Service;

public abstract class MediaRouteProviderService extends Service
{
    private static final boolean DEBUG;
    private static final int PRIVATE_MSG_CLIENT_DIED = 1;
    public static final String SERVICE_INTERFACE = "android.media.MediaRouteProviderService";
    private static final String TAG = "MediaRouteProviderSrv";
    private final ArrayList<MediaRouteProviderService$ClientRecord> mClients;
    private MediaRouteDiscoveryRequest mCompositeDiscoveryRequest;
    private final MediaRouteProviderService$PrivateHandler mPrivateHandler;
    private MediaRouteProvider mProvider;
    private final MediaRouteProviderService$ProviderCallback mProviderCallback;
    private final MediaRouteProviderService$ReceiveHandler mReceiveHandler;
    private final Messenger mReceiveMessenger;
    
    static {
        DEBUG = Log.isLoggable("MediaRouteProviderSrv", 3);
    }
    
    public MediaRouteProviderService() {
        this.mClients = new ArrayList<MediaRouteProviderService$ClientRecord>();
        this.mReceiveHandler = new MediaRouteProviderService$ReceiveHandler(this);
        this.mReceiveMessenger = new Messenger((Handler)this.mReceiveHandler);
        this.mPrivateHandler = new MediaRouteProviderService$PrivateHandler(this, null);
        this.mProviderCallback = new MediaRouteProviderService$ProviderCallback(this, null);
    }
    
    private int findClient(final Messenger messenger) {
        for (int size = this.mClients.size(), i = 0; i < size; ++i) {
            if (this.mClients.get(i).hasMessenger(messenger)) {
                return i;
            }
        }
        return -1;
    }
    
    private MediaRouteProviderService$ClientRecord getClient(final Messenger messenger) {
        final int client = this.findClient(messenger);
        if (client >= 0) {
            return this.mClients.get(client);
        }
        return null;
    }
    
    private static String getClientId(final Messenger messenger) {
        return "Client connection " + messenger.getBinder().toString();
    }
    
    private void onBinderDied(final Messenger messenger) {
        final int client = this.findClient(messenger);
        if (client >= 0) {
            final MediaRouteProviderService$ClientRecord mediaRouteProviderService$ClientRecord = this.mClients.remove(client);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", mediaRouteProviderService$ClientRecord + ": Binder died");
            }
            mediaRouteProviderService$ClientRecord.dispose();
        }
    }
    
    private boolean onCreateRouteController(final Messenger messenger, final int n, final int n2, final String s) {
        final MediaRouteProviderService$ClientRecord client = this.getClient(messenger);
        if (client != null && client.createRouteController(s, n2)) {
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", client + ": Route controller created" + ", controllerId=" + n2 + ", routeId=" + s);
            }
            sendGenericSuccess(messenger, n);
            return true;
        }
        return false;
    }
    
    private boolean onRegisterClient(final Messenger messenger, final int n, final int n2) {
        if (n2 >= 1 && this.findClient(messenger) < 0) {
            final MediaRouteProviderService$ClientRecord mediaRouteProviderService$ClientRecord = new MediaRouteProviderService$ClientRecord(this, messenger, n2);
            if (mediaRouteProviderService$ClientRecord.register()) {
                this.mClients.add(mediaRouteProviderService$ClientRecord);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d("MediaRouteProviderSrv", mediaRouteProviderService$ClientRecord + ": Registered, version=" + n2);
                }
                if (n != 0) {
                    final MediaRouteProviderDescriptor descriptor = this.mProvider.getDescriptor();
                    Bundle bundle;
                    if (descriptor != null) {
                        bundle = descriptor.asBundle();
                    }
                    else {
                        bundle = null;
                    }
                    sendReply(messenger, 2, n, 1, bundle, null);
                }
                return true;
            }
        }
        return false;
    }
    
    private boolean onReleaseRouteController(final Messenger messenger, final int n, final int n2) {
        final MediaRouteProviderService$ClientRecord client = this.getClient(messenger);
        if (client != null && client.releaseRouteController(n2)) {
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", client + ": Route controller released" + ", controllerId=" + n2);
            }
            sendGenericSuccess(messenger, n);
            return true;
        }
        return false;
    }
    
    private boolean onRouteControlRequest(final Messenger messenger, final int n, final int n2, final Intent intent) {
        final MediaRouteProviderService$ClientRecord client = this.getClient(messenger);
        if (client != null) {
            final MediaRouteProvider$RouteController routeController = client.getRouteController(n2);
            if (routeController != null) {
                MediaRouter$ControlRequestCallback mediaRouter$ControlRequestCallback = null;
                if (n != 0) {
                    mediaRouter$ControlRequestCallback = new MediaRouteProviderService$1(this, client, n2, intent, messenger, n);
                }
                if (routeController.onControlRequest(intent, mediaRouter$ControlRequestCallback)) {
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d("MediaRouteProviderSrv", client + ": Route control request delivered" + ", controllerId=" + n2 + ", intent=" + intent);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean onSelectRoute(final Messenger messenger, final int n, final int n2) {
        final MediaRouteProviderService$ClientRecord client = this.getClient(messenger);
        if (client != null) {
            final MediaRouteProvider$RouteController routeController = client.getRouteController(n2);
            if (routeController != null) {
                routeController.onSelect();
                if (MediaRouteProviderService.DEBUG) {
                    Log.d("MediaRouteProviderSrv", client + ": Route selected" + ", controllerId=" + n2);
                }
                sendGenericSuccess(messenger, n);
                return true;
            }
        }
        return false;
    }
    
    private boolean onSetDiscoveryRequest(final Messenger messenger, final int n, final MediaRouteDiscoveryRequest discoveryRequest) {
        final MediaRouteProviderService$ClientRecord client = this.getClient(messenger);
        if (client != null) {
            final boolean setDiscoveryRequest = client.setDiscoveryRequest(discoveryRequest);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", client + ": Set discovery request, request=" + discoveryRequest + ", actuallyChanged=" + setDiscoveryRequest + ", compositeDiscoveryRequest=" + this.mCompositeDiscoveryRequest);
            }
            sendGenericSuccess(messenger, n);
            return true;
        }
        return false;
    }
    
    private boolean onSetRouteVolume(final Messenger messenger, final int n, final int n2, final int n3) {
        final MediaRouteProviderService$ClientRecord client = this.getClient(messenger);
        if (client != null) {
            final MediaRouteProvider$RouteController routeController = client.getRouteController(n2);
            if (routeController != null) {
                routeController.onSetVolume(n3);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d("MediaRouteProviderSrv", client + ": Route volume changed" + ", controllerId=" + n2 + ", volume=" + n3);
                }
                sendGenericSuccess(messenger, n);
                return true;
            }
        }
        return false;
    }
    
    private boolean onUnregisterClient(final Messenger messenger, final int n) {
        final int client = this.findClient(messenger);
        if (client >= 0) {
            final MediaRouteProviderService$ClientRecord mediaRouteProviderService$ClientRecord = this.mClients.remove(client);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", mediaRouteProviderService$ClientRecord + ": Unregistered");
            }
            mediaRouteProviderService$ClientRecord.dispose();
            sendGenericSuccess(messenger, n);
            return true;
        }
        return false;
    }
    
    private boolean onUnselectRoute(final Messenger messenger, final int n, final int n2) {
        final MediaRouteProviderService$ClientRecord client = this.getClient(messenger);
        if (client != null) {
            final MediaRouteProvider$RouteController routeController = client.getRouteController(n2);
            if (routeController != null) {
                routeController.onUnselect();
                if (MediaRouteProviderService.DEBUG) {
                    Log.d("MediaRouteProviderSrv", client + ": Route unselected" + ", controllerId=" + n2);
                }
                sendGenericSuccess(messenger, n);
                return true;
            }
        }
        return false;
    }
    
    private boolean onUpdateRouteVolume(final Messenger messenger, final int n, final int n2, final int n3) {
        final MediaRouteProviderService$ClientRecord client = this.getClient(messenger);
        if (client != null) {
            final MediaRouteProvider$RouteController routeController = client.getRouteController(n2);
            if (routeController != null) {
                routeController.onUpdateVolume(n3);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d("MediaRouteProviderSrv", client + ": Route volume updated" + ", controllerId=" + n2 + ", delta=" + n3);
                }
                sendGenericSuccess(messenger, n);
                return true;
            }
        }
        return false;
    }
    
    private void sendDescriptorChanged(final MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
        Bundle bundle;
        if (mediaRouteProviderDescriptor != null) {
            bundle = mediaRouteProviderDescriptor.asBundle();
        }
        else {
            bundle = null;
        }
        for (int size = this.mClients.size(), i = 0; i < size; ++i) {
            final MediaRouteProviderService$ClientRecord mediaRouteProviderService$ClientRecord = this.mClients.get(i);
            sendReply(mediaRouteProviderService$ClientRecord.mMessenger, 5, 0, 0, bundle, null);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", mediaRouteProviderService$ClientRecord + ": Sent descriptor change event, descriptor=" + mediaRouteProviderDescriptor);
            }
        }
    }
    
    private static void sendGenericFailure(final Messenger messenger, final int n) {
        if (n != 0) {
            sendReply(messenger, 0, n, 0, null, null);
        }
    }
    
    private static void sendGenericSuccess(final Messenger messenger, final int n) {
        if (n != 0) {
            sendReply(messenger, 1, n, 0, null, null);
        }
    }
    
    private static void sendReply(final Messenger messenger, final int what, final int arg1, final int arg2, final Object obj, final Bundle data) {
        final Message obtain = Message.obtain();
        obtain.what = what;
        obtain.arg1 = arg1;
        obtain.arg2 = arg2;
        obtain.obj = obj;
        obtain.setData(data);
        try {
            messenger.send(obtain);
        }
        catch (RemoteException ex) {
            Log.e("MediaRouteProviderSrv", "Could not send message to " + getClientId(messenger), (Throwable)ex);
        }
        catch (DeadObjectException ex2) {}
    }
    
    private boolean updateCompositeDiscoveryRequest() {
        MediaRouteSelector$Builder mediaRouteSelector$Builder = null;
        final int size = this.mClients.size();
        int i = 0;
        boolean b = false;
        MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest = null;
        while (i < size) {
            final MediaRouteDiscoveryRequest mDiscoveryRequest = this.mClients.get(i).mDiscoveryRequest;
            MediaRouteSelector$Builder mediaRouteSelector$Builder2;
            MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest2;
            if (mDiscoveryRequest != null && (!mDiscoveryRequest.getSelector().isEmpty() || mDiscoveryRequest.isActiveScan())) {
                b |= mDiscoveryRequest.isActiveScan();
                if (mediaRouteDiscoveryRequest == null) {
                    mediaRouteSelector$Builder2 = mediaRouteSelector$Builder;
                    mediaRouteDiscoveryRequest2 = mDiscoveryRequest;
                }
                else {
                    if (mediaRouteSelector$Builder == null) {
                        mediaRouteSelector$Builder = new MediaRouteSelector$Builder(mediaRouteDiscoveryRequest.getSelector());
                    }
                    mediaRouteSelector$Builder.addSelector(mDiscoveryRequest.getSelector());
                    final MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest3 = mediaRouteDiscoveryRequest;
                    mediaRouteSelector$Builder2 = mediaRouteSelector$Builder;
                    mediaRouteDiscoveryRequest2 = mediaRouteDiscoveryRequest3;
                }
            }
            else {
                final MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest4 = mediaRouteDiscoveryRequest;
                mediaRouteSelector$Builder2 = mediaRouteSelector$Builder;
                mediaRouteDiscoveryRequest2 = mediaRouteDiscoveryRequest4;
            }
            ++i;
            final MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest5 = mediaRouteDiscoveryRequest2;
            mediaRouteSelector$Builder = mediaRouteSelector$Builder2;
            mediaRouteDiscoveryRequest = mediaRouteDiscoveryRequest5;
        }
        if (mediaRouteSelector$Builder != null) {
            mediaRouteDiscoveryRequest = new MediaRouteDiscoveryRequest(mediaRouteSelector$Builder.build(), b);
        }
        if (this.mCompositeDiscoveryRequest != mediaRouteDiscoveryRequest && (this.mCompositeDiscoveryRequest == null || !this.mCompositeDiscoveryRequest.equals(mediaRouteDiscoveryRequest))) {
            this.mCompositeDiscoveryRequest = mediaRouteDiscoveryRequest;
            this.mProvider.setDiscoveryRequest(mediaRouteDiscoveryRequest);
            return true;
        }
        return false;
    }
    
    public MediaRouteProvider getMediaRouteProvider() {
        return this.mProvider;
    }
    
    public IBinder onBind(final Intent intent) {
        if (intent.getAction().equals("android.media.MediaRouteProviderService")) {
            if (this.mProvider == null) {
                final MediaRouteProvider onCreateMediaRouteProvider = this.onCreateMediaRouteProvider();
                if (onCreateMediaRouteProvider != null) {
                    final String packageName = onCreateMediaRouteProvider.getMetadata().getPackageName();
                    if (!packageName.equals(this.getPackageName())) {
                        throw new IllegalStateException("onCreateMediaRouteProvider() returned a provider whose package name does not match the package name of the service.  A media route provider service can only export its own media route providers.  Provider package name: " + packageName + ".  Service package name: " + this.getPackageName() + ".");
                    }
                    (this.mProvider = onCreateMediaRouteProvider).setCallback(this.mProviderCallback);
                }
            }
            if (this.mProvider != null) {
                return this.mReceiveMessenger.getBinder();
            }
        }
        return null;
    }
    
    public abstract MediaRouteProvider onCreateMediaRouteProvider();
}
