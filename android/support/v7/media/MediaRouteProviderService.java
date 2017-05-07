// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.lang.ref.WeakReference;
import android.util.SparseArray;
import android.os.IBinder$DeathRecipient;
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
    private final ArrayList<ClientRecord> mClients;
    private MediaRouteDiscoveryRequest mCompositeDiscoveryRequest;
    private final PrivateHandler mPrivateHandler;
    private MediaRouteProvider mProvider;
    private final ProviderCallback mProviderCallback;
    private final ReceiveHandler mReceiveHandler;
    private final Messenger mReceiveMessenger;
    
    static {
        DEBUG = Log.isLoggable("MediaRouteProviderSrv", 3);
    }
    
    public MediaRouteProviderService() {
        this.mClients = new ArrayList<ClientRecord>();
        this.mReceiveHandler = new ReceiveHandler(this);
        this.mReceiveMessenger = new Messenger((Handler)this.mReceiveHandler);
        this.mPrivateHandler = new PrivateHandler();
        this.mProviderCallback = new ProviderCallback();
    }
    
    private int findClient(final Messenger messenger) {
        for (int size = this.mClients.size(), i = 0; i < size; ++i) {
            if (this.mClients.get(i).hasMessenger(messenger)) {
                return i;
            }
        }
        return -1;
    }
    
    private ClientRecord getClient(final Messenger messenger) {
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
            final ClientRecord clientRecord = this.mClients.remove(client);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", clientRecord + ": Binder died");
            }
            clientRecord.dispose();
        }
    }
    
    private boolean onCreateRouteController(final Messenger messenger, final int n, final int n2, final String s) {
        final ClientRecord client = this.getClient(messenger);
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
            final ClientRecord clientRecord = new ClientRecord(messenger, n2);
            if (clientRecord.register()) {
                this.mClients.add(clientRecord);
                if (MediaRouteProviderService.DEBUG) {
                    Log.d("MediaRouteProviderSrv", clientRecord + ": Registered, version=" + n2);
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
        final ClientRecord client = this.getClient(messenger);
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
        final ClientRecord client = this.getClient(messenger);
        if (client != null) {
            final MediaRouteProvider.RouteController routeController = client.getRouteController(n2);
            if (routeController != null) {
                MediaRouter.ControlRequestCallback controlRequestCallback = null;
                if (n != 0) {
                    controlRequestCallback = new MediaRouter.ControlRequestCallback() {
                        @Override
                        public void onError(final String s, final Bundle bundle) {
                            if (MediaRouteProviderService.DEBUG) {
                                Log.d("MediaRouteProviderSrv", client + ": Route control request failed" + ", controllerId=" + n2 + ", intent=" + intent + ", error=" + s + ", data=" + bundle);
                            }
                            if (MediaRouteProviderService.this.findClient(messenger) >= 0) {
                                if (s == null) {
                                    sendReply(messenger, 4, n, 0, bundle, null);
                                    return;
                                }
                                final Bundle bundle2 = new Bundle();
                                bundle2.putString("error", s);
                                sendReply(messenger, 4, n, 0, bundle, bundle2);
                            }
                        }
                        
                        @Override
                        public void onResult(final Bundle bundle) {
                            if (MediaRouteProviderService.DEBUG) {
                                Log.d("MediaRouteProviderSrv", client + ": Route control request succeeded" + ", controllerId=" + n2 + ", intent=" + intent + ", data=" + bundle);
                            }
                            if (MediaRouteProviderService.this.findClient(messenger) >= 0) {
                                sendReply(messenger, 3, n, 0, bundle, null);
                            }
                        }
                    };
                }
                if (routeController.onControlRequest(intent, controlRequestCallback)) {
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
        final ClientRecord client = this.getClient(messenger);
        if (client != null) {
            final MediaRouteProvider.RouteController routeController = client.getRouteController(n2);
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
        final ClientRecord client = this.getClient(messenger);
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
        final ClientRecord client = this.getClient(messenger);
        if (client != null) {
            final MediaRouteProvider.RouteController routeController = client.getRouteController(n2);
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
            final ClientRecord clientRecord = this.mClients.remove(client);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", clientRecord + ": Unregistered");
            }
            clientRecord.dispose();
            sendGenericSuccess(messenger, n);
            return true;
        }
        return false;
    }
    
    private boolean onUnselectRoute(final Messenger messenger, final int n, final int n2) {
        final ClientRecord client = this.getClient(messenger);
        if (client != null) {
            final MediaRouteProvider.RouteController routeController = client.getRouteController(n2);
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
        final ClientRecord client = this.getClient(messenger);
        if (client != null) {
            final MediaRouteProvider.RouteController routeController = client.getRouteController(n2);
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
            final ClientRecord clientRecord = this.mClients.get(i);
            sendReply(clientRecord.mMessenger, 5, 0, 0, bundle, null);
            if (MediaRouteProviderService.DEBUG) {
                Log.d("MediaRouteProviderSrv", clientRecord + ": Sent descriptor change event, descriptor=" + mediaRouteProviderDescriptor);
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
        MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest = null;
        MediaRouteSelector.Builder builder = null;
        boolean b = false;
        boolean b2;
        MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest2;
        MediaRouteSelector.Builder builder2;
        for (int size = this.mClients.size(), i = 0; i < size; ++i, b = b2, mediaRouteDiscoveryRequest = mediaRouteDiscoveryRequest2, builder = builder2) {
            final MediaRouteDiscoveryRequest mDiscoveryRequest = this.mClients.get(i).mDiscoveryRequest;
            b2 = b;
            mediaRouteDiscoveryRequest2 = mediaRouteDiscoveryRequest;
            builder2 = builder;
            if (mDiscoveryRequest != null) {
                if (mDiscoveryRequest.getSelector().isEmpty()) {
                    b2 = b;
                    mediaRouteDiscoveryRequest2 = mediaRouteDiscoveryRequest;
                    builder2 = builder;
                    if (!mDiscoveryRequest.isActiveScan()) {
                        continue;
                    }
                }
                b2 = (b | mDiscoveryRequest.isActiveScan());
                if (mediaRouteDiscoveryRequest == null) {
                    mediaRouteDiscoveryRequest2 = mDiscoveryRequest;
                    builder2 = builder;
                }
                else {
                    if ((builder2 = builder) == null) {
                        builder2 = new MediaRouteSelector.Builder(mediaRouteDiscoveryRequest.getSelector());
                    }
                    builder2.addSelector(mDiscoveryRequest.getSelector());
                    mediaRouteDiscoveryRequest2 = mediaRouteDiscoveryRequest;
                }
            }
        }
        if (builder != null) {
            mediaRouteDiscoveryRequest = new MediaRouteDiscoveryRequest(builder.build(), b);
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
                    (this.mProvider = onCreateMediaRouteProvider).setCallback((MediaRouteProvider.Callback)this.mProviderCallback);
                }
            }
            if (this.mProvider != null) {
                return this.mReceiveMessenger.getBinder();
            }
        }
        return null;
    }
    
    public abstract MediaRouteProvider onCreateMediaRouteProvider();
    
    private final class ClientRecord implements IBinder$DeathRecipient
    {
        private final SparseArray<MediaRouteProvider.RouteController> mControllers;
        public MediaRouteDiscoveryRequest mDiscoveryRequest;
        public final Messenger mMessenger;
        public final int mVersion;
        
        public ClientRecord(final Messenger mMessenger, final int mVersion) {
            this.mControllers = (SparseArray<MediaRouteProvider.RouteController>)new SparseArray();
            this.mMessenger = mMessenger;
            this.mVersion = mVersion;
        }
        
        public void binderDied() {
            MediaRouteProviderService.this.mPrivateHandler.obtainMessage(1, (Object)this.mMessenger).sendToTarget();
        }
        
        public boolean createRouteController(final String s, final int n) {
            if (this.mControllers.indexOfKey(n) < 0) {
                final MediaRouteProvider.RouteController onCreateRouteController = MediaRouteProviderService.this.mProvider.onCreateRouteController(s);
                if (onCreateRouteController != null) {
                    this.mControllers.put(n, (Object)onCreateRouteController);
                    return true;
                }
            }
            return false;
        }
        
        public void dispose() {
            for (int size = this.mControllers.size(), i = 0; i < size; ++i) {
                ((MediaRouteProvider.RouteController)this.mControllers.valueAt(i)).onRelease();
            }
            this.mControllers.clear();
            this.mMessenger.getBinder().unlinkToDeath((IBinder$DeathRecipient)this, 0);
            this.setDiscoveryRequest(null);
        }
        
        public MediaRouteProvider.RouteController getRouteController(final int n) {
            return (MediaRouteProvider.RouteController)this.mControllers.get(n);
        }
        
        public boolean hasMessenger(final Messenger messenger) {
            return this.mMessenger.getBinder() == messenger.getBinder();
        }
        
        public boolean register() {
            try {
                this.mMessenger.getBinder().linkToDeath((IBinder$DeathRecipient)this, 0);
                return true;
            }
            catch (RemoteException ex) {
                this.binderDied();
                return false;
            }
        }
        
        public boolean releaseRouteController(final int n) {
            final MediaRouteProvider.RouteController routeController = (MediaRouteProvider.RouteController)this.mControllers.get(n);
            if (routeController != null) {
                this.mControllers.remove(n);
                routeController.onRelease();
                return true;
            }
            return false;
        }
        
        public boolean setDiscoveryRequest(final MediaRouteDiscoveryRequest mDiscoveryRequest) {
            if (this.mDiscoveryRequest != mDiscoveryRequest && (this.mDiscoveryRequest == null || !this.mDiscoveryRequest.equals(mDiscoveryRequest))) {
                this.mDiscoveryRequest = mDiscoveryRequest;
                return MediaRouteProviderService.this.updateCompositeDiscoveryRequest();
            }
            return false;
        }
        
        @Override
        public String toString() {
            return getClientId(this.mMessenger);
        }
    }
    
    private final class PrivateHandler extends Handler
    {
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {}
                case 1: {
                    MediaRouteProviderService.this.onBinderDied((Messenger)message.obj);
                }
            }
        }
    }
    
    private final class ProviderCallback extends Callback
    {
        @Override
        public void onDescriptorChanged(final MediaRouteProvider mediaRouteProvider, final MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            MediaRouteProviderService.this.sendDescriptorChanged(mediaRouteProviderDescriptor);
        }
    }
    
    private static final class ReceiveHandler extends Handler
    {
        private final WeakReference<MediaRouteProviderService> mServiceRef;
        
        public ReceiveHandler(final MediaRouteProviderService mediaRouteProviderService) {
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
}
