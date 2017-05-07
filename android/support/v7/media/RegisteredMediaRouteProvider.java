// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

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
    private RegisteredMediaRouteProvider$Connection mActiveConnection;
    private boolean mBound;
    private final ComponentName mComponentName;
    private boolean mConnectionReady;
    private final ArrayList<RegisteredMediaRouteProvider$Controller> mControllers;
    private final RegisteredMediaRouteProvider$PrivateHandler mPrivateHandler;
    private boolean mStarted;
    
    static {
        DEBUG = Log.isLoggable("MediaRouteProviderProxy", 3);
    }
    
    public RegisteredMediaRouteProvider(final Context context, final ComponentName mComponentName) {
        super(context, new MediaRouteProvider$ProviderMetadata(mComponentName));
        this.mControllers = new ArrayList<RegisteredMediaRouteProvider$Controller>();
        this.mComponentName = mComponentName;
        this.mPrivateHandler = new RegisteredMediaRouteProvider$PrivateHandler(this, null);
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
    
    private void onConnectionDescriptorChanged(final RegisteredMediaRouteProvider$Connection registeredMediaRouteProvider$Connection, final MediaRouteProviderDescriptor descriptor) {
        if (this.mActiveConnection == registeredMediaRouteProvider$Connection) {
            if (RegisteredMediaRouteProvider.DEBUG) {
                Log.d("MediaRouteProviderProxy", this + ": Descriptor changed, descriptor=" + descriptor);
            }
            this.setDescriptor(descriptor);
        }
    }
    
    private void onConnectionDied(final RegisteredMediaRouteProvider$Connection registeredMediaRouteProvider$Connection) {
        if (this.mActiveConnection == registeredMediaRouteProvider$Connection) {
            if (RegisteredMediaRouteProvider.DEBUG) {
                Log.d("MediaRouteProviderProxy", this + ": Service connection died");
            }
            this.disconnect();
        }
    }
    
    private void onConnectionError(final RegisteredMediaRouteProvider$Connection registeredMediaRouteProvider$Connection, final String s) {
        if (this.mActiveConnection == registeredMediaRouteProvider$Connection) {
            if (RegisteredMediaRouteProvider.DEBUG) {
                Log.d("MediaRouteProviderProxy", this + ": Service connection error - " + s);
            }
            this.unbind();
        }
    }
    
    private void onConnectionReady(final RegisteredMediaRouteProvider$Connection registeredMediaRouteProvider$Connection) {
        if (this.mActiveConnection == registeredMediaRouteProvider$Connection) {
            this.mConnectionReady = true;
            this.attachControllersToConnection();
            final MediaRouteDiscoveryRequest discoveryRequest = this.getDiscoveryRequest();
            if (discoveryRequest != null) {
                this.mActiveConnection.setDiscoveryRequest(discoveryRequest);
            }
        }
    }
    
    private void onControllerReleased(final RegisteredMediaRouteProvider$Controller registeredMediaRouteProvider$Controller) {
        this.mControllers.remove(registeredMediaRouteProvider$Controller);
        registeredMediaRouteProvider$Controller.detachConnection();
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
    public MediaRouteProvider$RouteController onCreateRouteController(final String s) {
        final MediaRouteProviderDescriptor descriptor = this.getDescriptor();
        if (descriptor != null) {
            final List<MediaRouteDescriptor> routes = descriptor.getRoutes();
            for (int size = routes.size(), i = 0; i < size; ++i) {
                if (routes.get(i).getId().equals(s)) {
                    final RegisteredMediaRouteProvider$Controller registeredMediaRouteProvider$Controller = new RegisteredMediaRouteProvider$Controller(this, s);
                    this.mControllers.add(registeredMediaRouteProvider$Controller);
                    if (this.mConnectionReady) {
                        registeredMediaRouteProvider$Controller.attachConnection(this.mActiveConnection);
                    }
                    this.updateBinding();
                    return registeredMediaRouteProvider$Controller;
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
            final RegisteredMediaRouteProvider$Connection mActiveConnection = new RegisteredMediaRouteProvider$Connection(this, messenger);
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
}
