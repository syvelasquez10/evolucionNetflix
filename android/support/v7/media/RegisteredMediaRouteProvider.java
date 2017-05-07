// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.lang.ref.WeakReference;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.Message;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.os.IBinder$DeathRecipient;
import android.os.Messenger;
import android.os.IBinder;
import java.util.List;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import android.content.ComponentName;
import android.content.ServiceConnection;

final class RegisteredMediaRouteProvider extends MediaRouteProvider implements ServiceConnection
{
    private static final boolean DEBUG;
    private static final String TAG = "MediaRouteProviderProxy";
    private Connection mActiveConnection;
    private boolean mBound;
    private final ComponentName mComponentName;
    private boolean mConnectionReady;
    private final ArrayList<Controller> mControllers;
    private final PrivateHandler mPrivateHandler;
    private boolean mStarted;
    
    static {
        DEBUG = Log.isLoggable("MediaRouteProviderProxy", 3);
    }
    
    public RegisteredMediaRouteProvider(final Context context, final ComponentName mComponentName) {
        super(context, new ProviderMetadata(mComponentName));
        this.mControllers = new ArrayList<Controller>();
        this.mComponentName = mComponentName;
        this.mPrivateHandler = new PrivateHandler();
    }
    
    private void attachControllersToConnection() {
        for (int size = this.mControllers.size(), i = 0; i < size; ++i) {
            this.mControllers.get(i).attachConnection(this.mActiveConnection);
        }
    }
    
    private void bind() {
        if (this.mBound) {
            return;
        }
        if (RegisteredMediaRouteProvider.DEBUG) {
            Log.d("MediaRouteProviderProxy", this + ": Binding");
        }
        final Intent intent = new Intent("android.media.MediaRouteProviderService");
        intent.setComponent(this.mComponentName);
        try {
            this.mBound = this.getContext().bindService(intent, (ServiceConnection)this, 1);
            if (!this.mBound && RegisteredMediaRouteProvider.DEBUG) {
                Log.d("MediaRouteProviderProxy", this + ": Bind failed");
            }
        }
        catch (SecurityException ex) {
            if (RegisteredMediaRouteProvider.DEBUG) {
                Log.d("MediaRouteProviderProxy", this + ": Bind failed", (Throwable)ex);
            }
        }
    }
    
    private void detachControllersFromConnection() {
        for (int size = this.mControllers.size(), i = 0; i < size; ++i) {
            this.mControllers.get(i).detachConnection();
        }
    }
    
    private void disconnect() {
        if (this.mActiveConnection != null) {
            this.setDescriptor(null);
            this.mConnectionReady = false;
            this.detachControllersFromConnection();
            this.mActiveConnection.dispose();
            this.mActiveConnection = null;
        }
    }
    
    private void onConnectionDescriptorChanged(final Connection connection, final MediaRouteProviderDescriptor descriptor) {
        if (this.mActiveConnection == connection) {
            if (RegisteredMediaRouteProvider.DEBUG) {
                Log.d("MediaRouteProviderProxy", this + ": Descriptor changed, descriptor=" + descriptor);
            }
            this.setDescriptor(descriptor);
        }
    }
    
    private void onConnectionDied(final Connection connection) {
        if (this.mActiveConnection == connection) {
            if (RegisteredMediaRouteProvider.DEBUG) {
                Log.d("MediaRouteProviderProxy", this + ": Service connection died");
            }
            this.disconnect();
        }
    }
    
    private void onConnectionError(final Connection connection, final String s) {
        if (this.mActiveConnection == connection) {
            if (RegisteredMediaRouteProvider.DEBUG) {
                Log.d("MediaRouteProviderProxy", this + ": Service connection error - " + s);
            }
            this.unbind();
        }
    }
    
    private void onConnectionReady(final Connection connection) {
        if (this.mActiveConnection == connection) {
            this.mConnectionReady = true;
            this.attachControllersToConnection();
            final MediaRouteDiscoveryRequest discoveryRequest = this.getDiscoveryRequest();
            if (discoveryRequest != null) {
                this.mActiveConnection.setDiscoveryRequest(discoveryRequest);
            }
        }
    }
    
    private void onControllerReleased(final Controller controller) {
        this.mControllers.remove(controller);
        controller.detachConnection();
        this.updateBinding();
    }
    
    private boolean shouldBind() {
        return this.mStarted && (this.getDiscoveryRequest() != null || !this.mControllers.isEmpty());
    }
    
    private void unbind() {
        if (this.mBound) {
            if (RegisteredMediaRouteProvider.DEBUG) {
                Log.d("MediaRouteProviderProxy", this + ": Unbinding");
            }
            this.mBound = false;
            this.disconnect();
            this.getContext().unbindService((ServiceConnection)this);
        }
    }
    
    private void updateBinding() {
        if (this.shouldBind()) {
            this.bind();
            return;
        }
        this.unbind();
    }
    
    public boolean hasComponentName(final String s, final String s2) {
        return this.mComponentName.getPackageName().equals(s) && this.mComponentName.getClassName().equals(s2);
    }
    
    @Override
    public RouteController onCreateRouteController(final String s) {
        final MediaRouteProviderDescriptor descriptor = this.getDescriptor();
        if (descriptor != null) {
            final List<MediaRouteDescriptor> routes = descriptor.getRoutes();
            for (int size = routes.size(), i = 0; i < size; ++i) {
                if (routes.get(i).getId().equals(s)) {
                    final Controller controller = new Controller(s);
                    this.mControllers.add(controller);
                    if (this.mConnectionReady) {
                        controller.attachConnection(this.mActiveConnection);
                    }
                    this.updateBinding();
                    return controller;
                }
            }
        }
        return null;
    }
    
    @Override
    public void onDiscoveryRequestChanged(final MediaRouteDiscoveryRequest discoveryRequest) {
        if (this.mConnectionReady) {
            this.mActiveConnection.setDiscoveryRequest(discoveryRequest);
        }
        this.updateBinding();
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        if (RegisteredMediaRouteProvider.DEBUG) {
            Log.d("MediaRouteProviderProxy", this + ": Connected");
        }
        if (this.mBound) {
            this.disconnect();
            Messenger messenger;
            if (binder != null) {
                messenger = new Messenger(binder);
            }
            else {
                messenger = null;
            }
            if (!MediaRouteProviderProtocol.isValidRemoteMessenger(messenger)) {
                Log.e("MediaRouteProviderProxy", this + ": Service returned invalid messenger binder");
                return;
            }
            final Connection mActiveConnection = new Connection(messenger);
            if (mActiveConnection.register()) {
                this.mActiveConnection = mActiveConnection;
            }
            else if (RegisteredMediaRouteProvider.DEBUG) {
                Log.d("MediaRouteProviderProxy", this + ": Registration failed");
            }
        }
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        if (RegisteredMediaRouteProvider.DEBUG) {
            Log.d("MediaRouteProviderProxy", this + ": Service disconnected");
        }
        this.disconnect();
    }
    
    public void rebindIfDisconnected() {
        if (this.mActiveConnection == null && this.shouldBind()) {
            this.unbind();
            this.bind();
        }
    }
    
    public void start() {
        if (!this.mStarted) {
            if (RegisteredMediaRouteProvider.DEBUG) {
                Log.d("MediaRouteProviderProxy", this + ": Starting");
            }
            this.mStarted = true;
            this.updateBinding();
        }
    }
    
    public void stop() {
        if (this.mStarted) {
            if (RegisteredMediaRouteProvider.DEBUG) {
                Log.d("MediaRouteProviderProxy", this + ": Stopping");
            }
            this.mStarted = false;
            this.updateBinding();
        }
    }
    
    public String toString() {
        return "Service connection " + this.mComponentName.flattenToShortString();
    }
    
    private final class Connection implements IBinder$DeathRecipient
    {
        private int mNextControllerId;
        private int mNextRequestId;
        private final SparseArray<MediaRouter.ControlRequestCallback> mPendingCallbacks;
        private int mPendingRegisterRequestId;
        private final ReceiveHandler mReceiveHandler;
        private final Messenger mReceiveMessenger;
        private final Messenger mServiceMessenger;
        private int mServiceVersion;
        
        public Connection(final Messenger mServiceMessenger) {
            this.mNextRequestId = 1;
            this.mNextControllerId = 1;
            this.mPendingCallbacks = (SparseArray<MediaRouter.ControlRequestCallback>)new SparseArray();
            this.mServiceMessenger = mServiceMessenger;
            this.mReceiveHandler = new ReceiveHandler(this);
            this.mReceiveMessenger = new Messenger((Handler)this.mReceiveHandler);
        }
        
        private void failPendingCallbacks() {
            for (int i = 0; i < this.mPendingCallbacks.size(); ++i) {
                ((MediaRouter.ControlRequestCallback)this.mPendingCallbacks.valueAt(i)).onError(null, null);
            }
            this.mPendingCallbacks.clear();
        }
        
        private boolean sendRequest(final int what, final int arg1, final int arg2, final Object obj, final Bundle data) {
            final Message obtain = Message.obtain();
            obtain.what = what;
            obtain.arg1 = arg1;
            obtain.arg2 = arg2;
            obtain.obj = obj;
            obtain.setData(data);
            obtain.replyTo = this.mReceiveMessenger;
            try {
                this.mServiceMessenger.send(obtain);
                return true;
            }
            catch (RemoteException ex) {
                if (what == 2) {
                    goto Label_0074;
                }
                Log.e("MediaRouteProviderProxy", "Could not send message to service.", (Throwable)ex);
            }
            catch (DeadObjectException ex2) {
                goto Label_0074;
            }
        }
        
        public void binderDied() {
            RegisteredMediaRouteProvider.this.mPrivateHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    RegisteredMediaRouteProvider.this.onConnectionDied(Connection.this);
                }
            });
        }
        
        public int createRouteController(final String s) {
            final int n = this.mNextControllerId++;
            final Bundle bundle = new Bundle();
            bundle.putString("routeId", s);
            this.sendRequest(3, this.mNextRequestId++, n, null, bundle);
            return n;
        }
        
        public void dispose() {
            this.sendRequest(2, 0, 0, null, null);
            this.mReceiveHandler.dispose();
            this.mServiceMessenger.getBinder().unlinkToDeath((IBinder$DeathRecipient)this, 0);
            RegisteredMediaRouteProvider.this.mPrivateHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    Connection.this.failPendingCallbacks();
                }
            });
        }
        
        public boolean onControlRequestFailed(final int n, final String s, final Bundle bundle) {
            final MediaRouter.ControlRequestCallback controlRequestCallback = (MediaRouter.ControlRequestCallback)this.mPendingCallbacks.get(n);
            if (controlRequestCallback != null) {
                this.mPendingCallbacks.remove(n);
                controlRequestCallback.onError(s, bundle);
                return true;
            }
            return false;
        }
        
        public boolean onControlRequestSucceeded(final int n, final Bundle bundle) {
            final MediaRouter.ControlRequestCallback controlRequestCallback = (MediaRouter.ControlRequestCallback)this.mPendingCallbacks.get(n);
            if (controlRequestCallback != null) {
                this.mPendingCallbacks.remove(n);
                controlRequestCallback.onResult(bundle);
                return true;
            }
            return false;
        }
        
        public boolean onDescriptorChanged(final Bundle bundle) {
            if (this.mServiceVersion != 0) {
                RegisteredMediaRouteProvider.this.onConnectionDescriptorChanged(this, MediaRouteProviderDescriptor.fromBundle(bundle));
                return true;
            }
            return false;
        }
        
        public boolean onGenericFailure(final int n) {
            if (n == this.mPendingRegisterRequestId) {
                this.mPendingRegisterRequestId = 0;
                RegisteredMediaRouteProvider.this.onConnectionError(this, "Registation failed");
            }
            final MediaRouter.ControlRequestCallback controlRequestCallback = (MediaRouter.ControlRequestCallback)this.mPendingCallbacks.get(n);
            if (controlRequestCallback != null) {
                this.mPendingCallbacks.remove(n);
                controlRequestCallback.onError(null, null);
            }
            return true;
        }
        
        public boolean onGenericSuccess(final int n) {
            return true;
        }
        
        public boolean onRegistered(final int n, final int mServiceVersion, final Bundle bundle) {
            if (this.mServiceVersion == 0 && n == this.mPendingRegisterRequestId && mServiceVersion >= 1) {
                this.mPendingRegisterRequestId = 0;
                this.mServiceVersion = mServiceVersion;
                RegisteredMediaRouteProvider.this.onConnectionDescriptorChanged(this, MediaRouteProviderDescriptor.fromBundle(bundle));
                RegisteredMediaRouteProvider.this.onConnectionReady(this);
                return true;
            }
            return false;
        }
        
        public boolean register() {
            this.mPendingRegisterRequestId = this.mNextRequestId++;
            if (!this.sendRequest(1, this.mPendingRegisterRequestId, 1, null, null)) {
                return false;
            }
            try {
                this.mServiceMessenger.getBinder().linkToDeath((IBinder$DeathRecipient)this, 0);
                return true;
            }
            catch (RemoteException ex) {
                this.binderDied();
                return false;
            }
        }
        
        public void releaseRouteController(final int n) {
            this.sendRequest(4, this.mNextRequestId++, n, null, null);
        }
        
        public void selectRoute(final int n) {
            this.sendRequest(5, this.mNextRequestId++, n, null, null);
        }
        
        public boolean sendControlRequest(final int n, final Intent intent, final MediaRouter.ControlRequestCallback controlRequestCallback) {
            final int n2 = this.mNextRequestId++;
            if (this.sendRequest(9, n2, n, intent, null)) {
                if (controlRequestCallback != null) {
                    this.mPendingCallbacks.put(n2, (Object)controlRequestCallback);
                }
                return true;
            }
            return false;
        }
        
        public void setDiscoveryRequest(final MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
            final int n = this.mNextRequestId++;
            Bundle bundle;
            if (mediaRouteDiscoveryRequest != null) {
                bundle = mediaRouteDiscoveryRequest.asBundle();
            }
            else {
                bundle = null;
            }
            this.sendRequest(10, n, 0, bundle, null);
        }
        
        public void setVolume(final int n, int n2) {
            final Bundle bundle = new Bundle();
            bundle.putInt("volume", n2);
            n2 = this.mNextRequestId++;
            this.sendRequest(7, n2, n, null, bundle);
        }
        
        public void unselectRoute(final int n) {
            this.sendRequest(6, this.mNextRequestId++, n, null, null);
        }
        
        public void updateVolume(final int n, int n2) {
            final Bundle bundle = new Bundle();
            bundle.putInt("volume", n2);
            n2 = this.mNextRequestId++;
            this.sendRequest(8, n2, n, null, bundle);
        }
    }
    
    private final class Controller extends RouteController
    {
        private Connection mConnection;
        private int mControllerId;
        private int mPendingSetVolume;
        private int mPendingUpdateVolumeDelta;
        private final String mRouteId;
        private boolean mSelected;
        
        public Controller(final String mRouteId) {
            this.mPendingSetVolume = -1;
            this.mRouteId = mRouteId;
        }
        
        public void attachConnection(final Connection mConnection) {
            this.mConnection = mConnection;
            this.mControllerId = mConnection.createRouteController(this.mRouteId);
            if (this.mSelected) {
                mConnection.selectRoute(this.mControllerId);
                if (this.mPendingSetVolume >= 0) {
                    mConnection.setVolume(this.mControllerId, this.mPendingSetVolume);
                    this.mPendingSetVolume = -1;
                }
                if (this.mPendingUpdateVolumeDelta != 0) {
                    mConnection.updateVolume(this.mControllerId, this.mPendingUpdateVolumeDelta);
                    this.mPendingUpdateVolumeDelta = 0;
                }
            }
        }
        
        public void detachConnection() {
            if (this.mConnection != null) {
                this.mConnection.releaseRouteController(this.mControllerId);
                this.mConnection = null;
                this.mControllerId = 0;
            }
        }
        
        @Override
        public boolean onControlRequest(final Intent intent, final MediaRouter.ControlRequestCallback controlRequestCallback) {
            return this.mConnection != null && this.mConnection.sendControlRequest(this.mControllerId, intent, controlRequestCallback);
        }
        
        @Override
        public void onRelease() {
            RegisteredMediaRouteProvider.this.onControllerReleased(this);
        }
        
        @Override
        public void onSelect() {
            this.mSelected = true;
            if (this.mConnection != null) {
                this.mConnection.selectRoute(this.mControllerId);
            }
        }
        
        @Override
        public void onSetVolume(final int mPendingSetVolume) {
            if (this.mConnection != null) {
                this.mConnection.setVolume(this.mControllerId, mPendingSetVolume);
                return;
            }
            this.mPendingSetVolume = mPendingSetVolume;
            this.mPendingUpdateVolumeDelta = 0;
        }
        
        @Override
        public void onUnselect() {
            this.mSelected = false;
            if (this.mConnection != null) {
                this.mConnection.unselectRoute(this.mControllerId);
            }
        }
        
        @Override
        public void onUpdateVolume(final int n) {
            if (this.mConnection != null) {
                this.mConnection.updateVolume(this.mControllerId, n);
                return;
            }
            this.mPendingUpdateVolumeDelta += n;
        }
    }
    
    private final class PrivateHandler extends Handler
    {
    }
    
    private static final class ReceiveHandler extends Handler
    {
        private final WeakReference<Connection> mConnectionRef;
        
        public ReceiveHandler(final Connection connection) {
            this.mConnectionRef = new WeakReference<Connection>(connection);
        }
        
        private boolean processMessage(final Connection connection, final int n, final int n2, final int n3, final Object o, final Bundle bundle) {
            switch (n) {
                case 0: {
                    connection.onGenericFailure(n2);
                    return true;
                }
                case 1: {
                    connection.onGenericSuccess(n2);
                    return true;
                }
                case 2: {
                    if (o == null || o instanceof Bundle) {
                        return connection.onRegistered(n2, n3, (Bundle)o);
                    }
                    break;
                }
                case 5: {
                    if (o == null || o instanceof Bundle) {
                        return connection.onDescriptorChanged((Bundle)o);
                    }
                    break;
                }
                case 3: {
                    if (o == null || o instanceof Bundle) {
                        return connection.onControlRequestSucceeded(n2, (Bundle)o);
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
                        return connection.onControlRequestFailed(n2, string, (Bundle)o);
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
            final Connection connection = this.mConnectionRef.get();
            if (connection != null && !this.processMessage(connection, message.what, message.arg1, message.arg2, message.obj, message.peekData()) && RegisteredMediaRouteProvider.DEBUG) {
                Log.d("MediaRouteProviderProxy", "Unhandled message from server: " + message);
            }
        }
    }
}
