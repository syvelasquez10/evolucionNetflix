// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.content.IntentFilter;
import android.content.ComponentName;
import android.content.res.Resources;
import java.util.Collection;
import android.os.Message;
import android.os.Handler;
import android.content.Intent;
import android.content.pm.PackageManager$NameNotFoundException;
import android.view.Display;
import android.content.ContentResolver;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.lang.ref.WeakReference;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.os.Bundle;
import java.util.List;
import android.os.Looper;
import android.util.Log;
import android.content.Context;
import java.util.ArrayList;

public final class MediaRouter
{
    public static final int AVAILABILITY_FLAG_IGNORE_DEFAULT_ROUTE = 1;
    public static final int CALLBACK_FLAG_PERFORM_ACTIVE_SCAN = 1;
    public static final int CALLBACK_FLAG_REQUEST_DISCOVERY = 4;
    public static final int CALLBACK_FLAG_UNFILTERED_EVENTS = 2;
    private static final boolean DEBUG;
    private static final String TAG = "MediaRouter";
    static GlobalMediaRouter sGlobal;
    final ArrayList<CallbackRecord> mCallbackRecords;
    final Context mContext;
    
    static {
        DEBUG = Log.isLoggable("MediaRouter", 3);
    }
    
    MediaRouter(final Context mContext) {
        this.mCallbackRecords = new ArrayList<CallbackRecord>();
        this.mContext = mContext;
    }
    
    static void checkCallingThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("The media router service must only be accessed on the application's main thread.");
        }
    }
    
    static <T> boolean equal(final T t, final T t2) {
        return t == t2 || (t != null && t2 != null && t.equals(t2));
    }
    
    private int findCallbackRecord(final Callback callback) {
        for (int size = this.mCallbackRecords.size(), i = 0; i < size; ++i) {
            if (this.mCallbackRecords.get(i).mCallback == callback) {
                return i;
            }
        }
        return -1;
    }
    
    public static MediaRouter getInstance(final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        checkCallingThread();
        if (MediaRouter.sGlobal == null) {
            (MediaRouter.sGlobal = new GlobalMediaRouter(context.getApplicationContext())).start();
        }
        return MediaRouter.sGlobal.getRouter(context);
    }
    
    public void addCallback(final MediaRouteSelector mediaRouteSelector, final Callback callback) {
        this.addCallback(mediaRouteSelector, callback, 0);
    }
    
    public void addCallback(final MediaRouteSelector mediaRouteSelector, final Callback callback, final int n) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "addCallback: selector=" + mediaRouteSelector + ", callback=" + callback + ", flags=" + Integer.toHexString(n));
        }
        final int callbackRecord = this.findCallbackRecord(callback);
        CallbackRecord callbackRecord2;
        if (callbackRecord < 0) {
            callbackRecord2 = new CallbackRecord(this, callback);
            this.mCallbackRecords.add(callbackRecord2);
        }
        else {
            callbackRecord2 = this.mCallbackRecords.get(callbackRecord);
        }
        boolean b = false;
        if ((~callbackRecord2.mFlags & n) != 0x0) {
            callbackRecord2.mFlags |= n;
            b = true;
        }
        if (!callbackRecord2.mSelector.contains(mediaRouteSelector)) {
            callbackRecord2.mSelector = new MediaRouteSelector.Builder(callbackRecord2.mSelector).addSelector(mediaRouteSelector).build();
            b = true;
        }
        if (b) {
            MediaRouter.sGlobal.updateDiscoveryRequest();
        }
    }
    
    public void addProvider(final MediaRouteProvider mediaRouteProvider) {
        if (mediaRouteProvider == null) {
            throw new IllegalArgumentException("providerInstance must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "addProvider: " + mediaRouteProvider);
        }
        MediaRouter.sGlobal.addProvider(mediaRouteProvider);
    }
    
    public void addRemoteControlClient(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("remoteControlClient must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "addRemoteControlClient: " + o);
        }
        MediaRouter.sGlobal.addRemoteControlClient(o);
    }
    
    public RouteInfo getDefaultRoute() {
        checkCallingThread();
        return MediaRouter.sGlobal.getDefaultRoute();
    }
    
    public List<ProviderInfo> getProviders() {
        checkCallingThread();
        return MediaRouter.sGlobal.getProviders();
    }
    
    public List<RouteInfo> getRoutes() {
        checkCallingThread();
        return MediaRouter.sGlobal.getRoutes();
    }
    
    public RouteInfo getSelectedRoute() {
        checkCallingThread();
        return MediaRouter.sGlobal.getSelectedRoute();
    }
    
    public boolean isRouteAvailable(final MediaRouteSelector mediaRouteSelector, final int n) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        checkCallingThread();
        return MediaRouter.sGlobal.isRouteAvailable(mediaRouteSelector, n);
    }
    
    public void removeCallback(final Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "removeCallback: callback=" + callback);
        }
        final int callbackRecord = this.findCallbackRecord(callback);
        if (callbackRecord >= 0) {
            this.mCallbackRecords.remove(callbackRecord);
            MediaRouter.sGlobal.updateDiscoveryRequest();
        }
    }
    
    public void removeProvider(final MediaRouteProvider mediaRouteProvider) {
        if (mediaRouteProvider == null) {
            throw new IllegalArgumentException("providerInstance must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "removeProvider: " + mediaRouteProvider);
        }
        MediaRouter.sGlobal.removeProvider(mediaRouteProvider);
    }
    
    public void removeRemoteControlClient(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("remoteControlClient must not be null");
        }
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "removeRemoteControlClient: " + o);
        }
        MediaRouter.sGlobal.removeRemoteControlClient(o);
    }
    
    public void selectRoute(final RouteInfo routeInfo) {
        if (routeInfo == null) {
            throw new IllegalArgumentException("route must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "selectRoute: " + routeInfo);
        }
        MediaRouter.sGlobal.selectRoute(routeInfo);
    }
    
    public RouteInfo updateSelectedRoute(final MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        checkCallingThread();
        if (MediaRouter.DEBUG) {
            Log.d("MediaRouter", "updateSelectedRoute: " + mediaRouteSelector);
        }
        Object o;
        final RouteInfo routeInfo = (RouteInfo)(o = MediaRouter.sGlobal.getSelectedRoute());
        if (!routeInfo.isDefault()) {
            o = routeInfo;
            if (!routeInfo.matchesSelector(mediaRouteSelector)) {
                o = MediaRouter.sGlobal.getDefaultRoute();
                MediaRouter.sGlobal.selectRoute((RouteInfo)o);
            }
        }
        return (RouteInfo)o;
    }
    
    public abstract static class Callback
    {
        public void onProviderAdded(final MediaRouter mediaRouter, final ProviderInfo providerInfo) {
        }
        
        public void onProviderChanged(final MediaRouter mediaRouter, final ProviderInfo providerInfo) {
        }
        
        public void onProviderRemoved(final MediaRouter mediaRouter, final ProviderInfo providerInfo) {
        }
        
        public void onRouteAdded(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
        }
        
        public void onRouteChanged(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
        }
        
        public void onRoutePresentationDisplayChanged(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
        }
        
        public void onRouteRemoved(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
        }
        
        public void onRouteSelected(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
        }
        
        public void onRouteUnselected(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
        }
        
        public void onRouteVolumeChanged(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
        }
    }
    
    private static final class CallbackRecord
    {
        public final Callback mCallback;
        public int mFlags;
        public final MediaRouter mRouter;
        public MediaRouteSelector mSelector;
        
        public CallbackRecord(final MediaRouter mRouter, final Callback mCallback) {
            this.mRouter = mRouter;
            this.mCallback = mCallback;
            this.mSelector = MediaRouteSelector.EMPTY;
        }
        
        public boolean filterRouteEvent(final RouteInfo routeInfo) {
            return (this.mFlags & 0x2) != 0x0 || routeInfo.matchesSelector(this.mSelector);
        }
    }
    
    public abstract static class ControlRequestCallback
    {
        public void onError(final String s, final Bundle bundle) {
        }
        
        public void onResult(final Bundle bundle) {
        }
    }
    
    private static final class GlobalMediaRouter implements SyncCallback, RegisteredMediaRouteProviderWatcher.Callback
    {
        private final Context mApplicationContext;
        private final CallbackHandler mCallbackHandler;
        private RouteInfo mDefaultRoute;
        private MediaRouteDiscoveryRequest mDiscoveryRequest;
        private final DisplayManagerCompat mDisplayManager;
        private final RemoteControlClientCompat.PlaybackInfo mPlaybackInfo;
        private final ProviderCallback mProviderCallback;
        private final ArrayList<ProviderInfo> mProviders;
        private RegisteredMediaRouteProviderWatcher mRegisteredProviderWatcher;
        private final ArrayList<RemoteControlClientRecord> mRemoteControlClients;
        private final ArrayList<WeakReference<MediaRouter>> mRouters;
        private final ArrayList<RouteInfo> mRoutes;
        private RouteInfo mSelectedRoute;
        private RouteController mSelectedRouteController;
        private final SystemMediaRouteProvider mSystemProvider;
        
        GlobalMediaRouter(final Context mApplicationContext) {
            this.mRouters = new ArrayList<WeakReference<MediaRouter>>();
            this.mRoutes = new ArrayList<RouteInfo>();
            this.mProviders = new ArrayList<ProviderInfo>();
            this.mRemoteControlClients = new ArrayList<RemoteControlClientRecord>();
            this.mPlaybackInfo = new RemoteControlClientCompat.PlaybackInfo();
            this.mProviderCallback = new ProviderCallback();
            this.mCallbackHandler = new CallbackHandler();
            this.mApplicationContext = mApplicationContext;
            this.mDisplayManager = DisplayManagerCompat.getInstance(mApplicationContext);
            this.addProvider(this.mSystemProvider = SystemMediaRouteProvider.obtain(mApplicationContext, (SystemMediaRouteProvider.SyncCallback)this));
        }
        
        private String assignRouteUniqueId(final ProviderInfo providerInfo, String format) {
            final String string = providerInfo.getComponentName().flattenToShortString() + ":" + format;
            if (this.findRouteByUniqueId(string) < 0) {
                return string;
            }
            int n = 2;
            while (true) {
                format = String.format(Locale.US, "%s_%d", string, n);
                if (this.findRouteByUniqueId(format) < 0) {
                    break;
                }
                ++n;
            }
            return format;
        }
        
        private RouteInfo chooseFallbackRoute() {
            for (final RouteInfo routeInfo : this.mRoutes) {
                if (routeInfo != this.mDefaultRoute && this.isSystemLiveAudioOnlyRoute(routeInfo) && this.isRouteSelectable(routeInfo)) {
                    return routeInfo;
                }
            }
            return this.mDefaultRoute;
        }
        
        private int findProviderInfo(final MediaRouteProvider mediaRouteProvider) {
            for (int size = this.mProviders.size(), i = 0; i < size; ++i) {
                if (this.mProviders.get(i).mProviderInstance == mediaRouteProvider) {
                    return i;
                }
            }
            return -1;
        }
        
        private int findRemoteControlClientRecord(final Object o) {
            for (int size = this.mRemoteControlClients.size(), i = 0; i < size; ++i) {
                if (this.mRemoteControlClients.get(i).getRemoteControlClient() == o) {
                    return i;
                }
            }
            return -1;
        }
        
        private int findRouteByUniqueId(final String s) {
            for (int size = this.mRoutes.size(), i = 0; i < size; ++i) {
                if (this.mRoutes.get(i).mUniqueId.equals(s)) {
                    return i;
                }
            }
            return -1;
        }
        
        private boolean isRouteSelectable(final RouteInfo routeInfo) {
            return routeInfo.mDescriptor != null && routeInfo.mEnabled;
        }
        
        private boolean isSystemDefaultRoute(final RouteInfo routeInfo) {
            return routeInfo.getProviderInstance() == this.mSystemProvider && routeInfo.mDescriptorId.equals("DEFAULT_ROUTE");
        }
        
        private boolean isSystemLiveAudioOnlyRoute(final RouteInfo routeInfo) {
            return routeInfo.getProviderInstance() == this.mSystemProvider && routeInfo.supportsControlCategory("android.media.intent.category.LIVE_AUDIO") && !routeInfo.supportsControlCategory("android.media.intent.category.LIVE_VIDEO");
        }
        
        private void setSelectedRouteInternal(final RouteInfo mSelectedRoute) {
            if (this.mSelectedRoute != mSelectedRoute) {
                if (this.mSelectedRoute != null) {
                    if (MediaRouter.DEBUG) {
                        Log.d("MediaRouter", "Route unselected: " + this.mSelectedRoute);
                    }
                    this.mCallbackHandler.post(263, this.mSelectedRoute);
                    if (this.mSelectedRouteController != null) {
                        this.mSelectedRouteController.onUnselect();
                        this.mSelectedRouteController.onRelease();
                        this.mSelectedRouteController = null;
                    }
                }
                this.mSelectedRoute = mSelectedRoute;
                if (this.mSelectedRoute != null) {
                    this.mSelectedRouteController = mSelectedRoute.getProviderInstance().onCreateRouteController(mSelectedRoute.mDescriptorId);
                    if (this.mSelectedRouteController != null) {
                        this.mSelectedRouteController.onSelect();
                    }
                    if (MediaRouter.DEBUG) {
                        Log.d("MediaRouter", "Route selected: " + this.mSelectedRoute);
                    }
                    this.mCallbackHandler.post(262, this.mSelectedRoute);
                }
                this.updatePlaybackInfoFromSelectedRoute();
            }
        }
        
        private void updatePlaybackInfoFromSelectedRoute() {
            if (this.mSelectedRoute != null) {
                this.mPlaybackInfo.volume = this.mSelectedRoute.getVolume();
                this.mPlaybackInfo.volumeMax = this.mSelectedRoute.getVolumeMax();
                this.mPlaybackInfo.volumeHandling = this.mSelectedRoute.getVolumeHandling();
                this.mPlaybackInfo.playbackStream = this.mSelectedRoute.getPlaybackStream();
                this.mPlaybackInfo.playbackType = this.mSelectedRoute.getPlaybackType();
                for (int size = this.mRemoteControlClients.size(), i = 0; i < size; ++i) {
                    this.mRemoteControlClients.get(i).updatePlaybackInfo();
                }
            }
        }
        
        private void updateProviderContents(final ProviderInfo providerInfo, final MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            if (providerInfo.updateDescriptor(mediaRouteProviderDescriptor)) {
                final int n = 0;
                final boolean b = false;
                final boolean b2 = false;
                boolean b3 = b;
                int n2 = n;
                if (mediaRouteProviderDescriptor != null) {
                    if (mediaRouteProviderDescriptor.isValid()) {
                        final List<MediaRouteDescriptor> routes = mediaRouteProviderDescriptor.getRoutes();
                        final int size = routes.size();
                        int i = 0;
                        n2 = 0;
                        b3 = b2;
                        while (i < size) {
                            final MediaRouteDescriptor mediaRouteDescriptor = routes.get(i);
                            final String id = mediaRouteDescriptor.getId();
                            final int routeByDescriptorId = providerInfo.findRouteByDescriptorId(id);
                            boolean b4;
                            if (routeByDescriptorId < 0) {
                                final RouteInfo routeInfo = new RouteInfo(providerInfo, id, this.assignRouteUniqueId(providerInfo, id));
                                final ArrayList access$600 = providerInfo.mRoutes;
                                final int n3 = n2 + 1;
                                access$600.add(n2, routeInfo);
                                this.mRoutes.add(routeInfo);
                                routeInfo.updateDescriptor(mediaRouteDescriptor);
                                if (MediaRouter.DEBUG) {
                                    Log.d("MediaRouter", "Route added: " + routeInfo);
                                }
                                this.mCallbackHandler.post(257, routeInfo);
                                n2 = n3;
                                b4 = b3;
                            }
                            else if (routeByDescriptorId < n2) {
                                Log.w("MediaRouter", "Ignoring route descriptor with duplicate id: " + mediaRouteDescriptor);
                                b4 = b3;
                            }
                            else {
                                final RouteInfo routeInfo2 = providerInfo.mRoutes.get(routeByDescriptorId);
                                final ArrayList access$601 = providerInfo.mRoutes;
                                final int n4 = n2 + 1;
                                Collections.swap(access$601, routeByDescriptorId, n2);
                                final int updateDescriptor = routeInfo2.updateDescriptor(mediaRouteDescriptor);
                                b4 = b3;
                                n2 = n4;
                                if (updateDescriptor != 0) {
                                    if ((updateDescriptor & 0x1) != 0x0) {
                                        if (MediaRouter.DEBUG) {
                                            Log.d("MediaRouter", "Route changed: " + routeInfo2);
                                        }
                                        this.mCallbackHandler.post(259, routeInfo2);
                                    }
                                    if ((updateDescriptor & 0x2) != 0x0) {
                                        if (MediaRouter.DEBUG) {
                                            Log.d("MediaRouter", "Route volume changed: " + routeInfo2);
                                        }
                                        this.mCallbackHandler.post(260, routeInfo2);
                                    }
                                    if ((updateDescriptor & 0x4) != 0x0) {
                                        if (MediaRouter.DEBUG) {
                                            Log.d("MediaRouter", "Route presentation display changed: " + routeInfo2);
                                        }
                                        this.mCallbackHandler.post(261, routeInfo2);
                                    }
                                    b4 = b3;
                                    n2 = n4;
                                    if (routeInfo2 == this.mSelectedRoute) {
                                        b4 = true;
                                        n2 = n4;
                                    }
                                }
                            }
                            ++i;
                            b3 = b4;
                        }
                    }
                    else {
                        Log.w("MediaRouter", "Ignoring invalid provider descriptor: " + mediaRouteProviderDescriptor);
                        b3 = b;
                        n2 = n;
                    }
                }
                for (int j = providerInfo.mRoutes.size() - 1; j >= n2; --j) {
                    final RouteInfo routeInfo3 = providerInfo.mRoutes.get(j);
                    routeInfo3.updateDescriptor(null);
                    this.mRoutes.remove(routeInfo3);
                }
                this.updateSelectedRouteIfNeeded(b3);
                for (int k = providerInfo.mRoutes.size() - 1; k >= n2; --k) {
                    final RouteInfo routeInfo4 = providerInfo.mRoutes.remove(k);
                    if (MediaRouter.DEBUG) {
                        Log.d("MediaRouter", "Route removed: " + routeInfo4);
                    }
                    this.mCallbackHandler.post(258, routeInfo4);
                }
                if (MediaRouter.DEBUG) {
                    Log.d("MediaRouter", "Provider changed: " + providerInfo);
                }
                this.mCallbackHandler.post(515, providerInfo);
            }
        }
        
        private void updateProviderDescriptor(final MediaRouteProvider mediaRouteProvider, final MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            final int providerInfo = this.findProviderInfo(mediaRouteProvider);
            if (providerInfo >= 0) {
                this.updateProviderContents(this.mProviders.get(providerInfo), mediaRouteProviderDescriptor);
            }
        }
        
        private void updateSelectedRouteIfNeeded(final boolean b) {
            if (this.mDefaultRoute != null && !this.isRouteSelectable(this.mDefaultRoute)) {
                Log.i("MediaRouter", "Clearing the default route because it is no longer selectable: " + this.mDefaultRoute);
                this.mDefaultRoute = null;
            }
            if (this.mDefaultRoute == null && !this.mRoutes.isEmpty()) {
                for (final RouteInfo mDefaultRoute : this.mRoutes) {
                    if (this.isSystemDefaultRoute(mDefaultRoute) && this.isRouteSelectable(mDefaultRoute)) {
                        this.mDefaultRoute = mDefaultRoute;
                        Log.i("MediaRouter", "Found default route: " + this.mDefaultRoute);
                        break;
                    }
                }
            }
            if (this.mSelectedRoute != null && !this.isRouteSelectable(this.mSelectedRoute)) {
                Log.i("MediaRouter", "Unselecting the current route because it is no longer selectable: " + this.mSelectedRoute);
                this.setSelectedRouteInternal(null);
            }
            if (this.mSelectedRoute == null) {
                this.setSelectedRouteInternal(this.chooseFallbackRoute());
            }
            else if (b) {
                this.updatePlaybackInfoFromSelectedRoute();
            }
        }
        
        @Override
        public void addProvider(final MediaRouteProvider mediaRouteProvider) {
            if (this.findProviderInfo(mediaRouteProvider) < 0) {
                final ProviderInfo providerInfo = new ProviderInfo(mediaRouteProvider);
                this.mProviders.add(providerInfo);
                if (MediaRouter.DEBUG) {
                    Log.d("MediaRouter", "Provider added: " + providerInfo);
                }
                this.mCallbackHandler.post(513, providerInfo);
                this.updateProviderContents(providerInfo, mediaRouteProvider.getDescriptor());
                mediaRouteProvider.setCallback((MediaRouteProvider.Callback)this.mProviderCallback);
                mediaRouteProvider.setDiscoveryRequest(this.mDiscoveryRequest);
            }
        }
        
        public void addRemoteControlClient(final Object o) {
            if (this.findRemoteControlClientRecord(o) < 0) {
                this.mRemoteControlClients.add(new RemoteControlClientRecord(o));
            }
        }
        
        public ContentResolver getContentResolver() {
            return this.mApplicationContext.getContentResolver();
        }
        
        public RouteInfo getDefaultRoute() {
            if (this.mDefaultRoute == null) {
                throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
            }
            return this.mDefaultRoute;
        }
        
        public Display getDisplay(final int n) {
            return this.mDisplayManager.getDisplay(n);
        }
        
        public Context getProviderContext(final String s) {
            if (s.equals("android")) {
                return this.mApplicationContext;
            }
            try {
                return this.mApplicationContext.createPackageContext(s, 4);
            }
            catch (PackageManager$NameNotFoundException ex) {
                return null;
            }
        }
        
        public List<ProviderInfo> getProviders() {
            return this.mProviders;
        }
        
        public MediaRouter getRouter(final Context context) {
            int size = this.mRouters.size();
            while (true) {
                --size;
                if (size < 0) {
                    final MediaRouter mediaRouter = new MediaRouter(context);
                    this.mRouters.add(new WeakReference<MediaRouter>(mediaRouter));
                    return mediaRouter;
                }
                final MediaRouter mediaRouter2 = this.mRouters.get(size).get();
                if (mediaRouter2 == null) {
                    this.mRouters.remove(size);
                }
                else {
                    if (mediaRouter2.mContext == context) {
                        return mediaRouter2;
                    }
                    continue;
                }
            }
        }
        
        public List<RouteInfo> getRoutes() {
            return this.mRoutes;
        }
        
        public RouteInfo getSelectedRoute() {
            if (this.mSelectedRoute == null) {
                throw new IllegalStateException("There is no currently selected route.  The media router has not yet been fully initialized.");
            }
            return this.mSelectedRoute;
        }
        
        @Override
        public RouteInfo getSystemRouteByDescriptorId(final String s) {
            final int providerInfo = this.findProviderInfo(this.mSystemProvider);
            if (providerInfo >= 0) {
                final ProviderInfo providerInfo2 = this.mProviders.get(providerInfo);
                final int routeByDescriptorId = providerInfo2.findRouteByDescriptorId(s);
                if (routeByDescriptorId >= 0) {
                    return (RouteInfo)providerInfo2.mRoutes.get(routeByDescriptorId);
                }
            }
            return null;
        }
        
        public boolean isRouteAvailable(final MediaRouteSelector mediaRouteSelector, final int n) {
            for (int size = this.mRoutes.size(), i = 0; i < size; ++i) {
                final RouteInfo routeInfo = this.mRoutes.get(i);
                if (((n & 0x1) == 0x0 || !routeInfo.isDefault()) && routeInfo.matchesSelector(mediaRouteSelector)) {
                    return true;
                }
            }
            return false;
        }
        
        @Override
        public void removeProvider(final MediaRouteProvider mediaRouteProvider) {
            final int providerInfo = this.findProviderInfo(mediaRouteProvider);
            if (providerInfo >= 0) {
                mediaRouteProvider.setCallback(null);
                mediaRouteProvider.setDiscoveryRequest(null);
                final ProviderInfo providerInfo2 = this.mProviders.get(providerInfo);
                this.updateProviderContents(providerInfo2, null);
                if (MediaRouter.DEBUG) {
                    Log.d("MediaRouter", "Provider removed: " + providerInfo2);
                }
                this.mCallbackHandler.post(514, providerInfo2);
                this.mProviders.remove(providerInfo);
            }
        }
        
        public void removeRemoteControlClient(final Object o) {
            final int remoteControlClientRecord = this.findRemoteControlClientRecord(o);
            if (remoteControlClientRecord >= 0) {
                this.mRemoteControlClients.remove(remoteControlClientRecord).disconnect();
            }
        }
        
        public void requestSetVolume(final RouteInfo routeInfo, final int n) {
            if (routeInfo == this.mSelectedRoute && this.mSelectedRouteController != null) {
                this.mSelectedRouteController.onSetVolume(n);
            }
        }
        
        public void requestUpdateVolume(final RouteInfo routeInfo, final int n) {
            if (routeInfo == this.mSelectedRoute && this.mSelectedRouteController != null) {
                this.mSelectedRouteController.onUpdateVolume(n);
            }
        }
        
        public void selectRoute(final RouteInfo selectedRouteInternal) {
            if (!this.mRoutes.contains(selectedRouteInternal)) {
                Log.w("MediaRouter", "Ignoring attempt to select removed route: " + selectedRouteInternal);
                return;
            }
            if (!selectedRouteInternal.mEnabled) {
                Log.w("MediaRouter", "Ignoring attempt to select disabled route: " + selectedRouteInternal);
                return;
            }
            this.setSelectedRouteInternal(selectedRouteInternal);
        }
        
        public void sendControlRequest(final RouteInfo routeInfo, final Intent intent, final ControlRequestCallback controlRequestCallback) {
            if ((routeInfo != this.mSelectedRoute || this.mSelectedRouteController == null || !this.mSelectedRouteController.onControlRequest(intent, controlRequestCallback)) && controlRequestCallback != null) {
                controlRequestCallback.onError(null, null);
            }
        }
        
        public void start() {
            (this.mRegisteredProviderWatcher = new RegisteredMediaRouteProviderWatcher(this.mApplicationContext, (RegisteredMediaRouteProviderWatcher.Callback)this)).start();
        }
        
        public void updateDiscoveryRequest() {
            int n = 0;
            boolean b = false;
            final MediaRouteSelector.Builder builder = new MediaRouteSelector.Builder();
            int size = this.mRouters.size();
            while (true) {
                final int n2 = size - 1;
                if (n2 < 0) {
                    break;
                }
                final MediaRouter mediaRouter = this.mRouters.get(n2).get();
                if (mediaRouter == null) {
                    this.mRouters.remove(n2);
                    size = n2;
                }
                else {
                    final int size2 = mediaRouter.mCallbackRecords.size();
                    int n3 = 0;
                    int n4 = n;
                    boolean b2 = b;
                    while (true) {
                        b = b2;
                        n = n4;
                        size = n2;
                        if (n3 >= size2) {
                            break;
                        }
                        final CallbackRecord callbackRecord = mediaRouter.mCallbackRecords.get(n3);
                        builder.addSelector(callbackRecord.mSelector);
                        if ((callbackRecord.mFlags & 0x1) != 0x0) {
                            b2 = true;
                            n4 = 1;
                        }
                        if ((callbackRecord.mFlags & 0x4) != 0x0) {
                            n4 = 1;
                        }
                        ++n3;
                    }
                }
            }
            MediaRouteSelector mediaRouteSelector;
            if (n != 0) {
                mediaRouteSelector = builder.build();
            }
            else {
                mediaRouteSelector = MediaRouteSelector.EMPTY;
            }
            if (this.mDiscoveryRequest == null || !this.mDiscoveryRequest.getSelector().equals(mediaRouteSelector) || this.mDiscoveryRequest.isActiveScan() != b) {
                if (mediaRouteSelector.isEmpty() && !b) {
                    if (this.mDiscoveryRequest == null) {
                        return;
                    }
                    this.mDiscoveryRequest = null;
                }
                else {
                    this.mDiscoveryRequest = new MediaRouteDiscoveryRequest(mediaRouteSelector, b);
                }
                if (MediaRouter.DEBUG) {
                    Log.d("MediaRouter", "Updated discovery request: " + this.mDiscoveryRequest);
                }
                for (int size3 = this.mProviders.size(), i = 0; i < size3; ++i) {
                    this.mProviders.get(i).mProviderInstance.setDiscoveryRequest(this.mDiscoveryRequest);
                }
            }
        }
        
        private final class CallbackHandler extends Handler
        {
            public static final int MSG_PROVIDER_ADDED = 513;
            public static final int MSG_PROVIDER_CHANGED = 515;
            public static final int MSG_PROVIDER_REMOVED = 514;
            public static final int MSG_ROUTE_ADDED = 257;
            public static final int MSG_ROUTE_CHANGED = 259;
            public static final int MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED = 261;
            public static final int MSG_ROUTE_REMOVED = 258;
            public static final int MSG_ROUTE_SELECTED = 262;
            public static final int MSG_ROUTE_UNSELECTED = 263;
            public static final int MSG_ROUTE_VOLUME_CHANGED = 260;
            private static final int MSG_TYPE_MASK = 65280;
            private static final int MSG_TYPE_PROVIDER = 512;
            private static final int MSG_TYPE_ROUTE = 256;
            private final ArrayList<CallbackRecord> mTempCallbackRecords;
            
            private CallbackHandler() {
                this.mTempCallbackRecords = new ArrayList<CallbackRecord>();
            }
            
            private void invokeCallback(final CallbackRecord callbackRecord, final int n, final Object o) {
                final MediaRouter mRouter = callbackRecord.mRouter;
                final MediaRouter.Callback mCallback = callbackRecord.mCallback;
                switch (0xFF00 & n) {
                    case 256: {
                        final RouteInfo routeInfo = (RouteInfo)o;
                        if (!callbackRecord.filterRouteEvent(routeInfo)) {
                            break;
                        }
                        switch (n) {
                            default: {
                                return;
                            }
                            case 257: {
                                mCallback.onRouteAdded(mRouter, routeInfo);
                                return;
                            }
                            case 258: {
                                mCallback.onRouteRemoved(mRouter, routeInfo);
                                return;
                            }
                            case 259: {
                                mCallback.onRouteChanged(mRouter, routeInfo);
                                return;
                            }
                            case 260: {
                                mCallback.onRouteVolumeChanged(mRouter, routeInfo);
                                return;
                            }
                            case 261: {
                                mCallback.onRoutePresentationDisplayChanged(mRouter, routeInfo);
                                return;
                            }
                            case 262: {
                                mCallback.onRouteSelected(mRouter, routeInfo);
                                return;
                            }
                            case 263: {
                                mCallback.onRouteUnselected(mRouter, routeInfo);
                                return;
                            }
                        }
                        break;
                    }
                    case 512: {
                        final ProviderInfo providerInfo = (ProviderInfo)o;
                        switch (n) {
                            default: {
                                return;
                            }
                            case 513: {
                                mCallback.onProviderAdded(mRouter, providerInfo);
                                return;
                            }
                            case 514: {
                                mCallback.onProviderRemoved(mRouter, providerInfo);
                                return;
                            }
                            case 515: {
                                mCallback.onProviderChanged(mRouter, providerInfo);
                                return;
                            }
                        }
                        break;
                    }
                }
            }
            
            private void syncWithSystemProvider(final int n, final Object o) {
                switch (n) {
                    default: {}
                    case 257: {
                        GlobalMediaRouter.this.mSystemProvider.onSyncRouteAdded((RouteInfo)o);
                    }
                    case 258: {
                        GlobalMediaRouter.this.mSystemProvider.onSyncRouteRemoved((RouteInfo)o);
                    }
                    case 259: {
                        GlobalMediaRouter.this.mSystemProvider.onSyncRouteChanged((RouteInfo)o);
                    }
                    case 262: {
                        GlobalMediaRouter.this.mSystemProvider.onSyncRouteSelected((RouteInfo)o);
                    }
                }
            }
            
            public void handleMessage(final Message message) {
                int what = 0;
            Label_0103:
                while (true) {
                    what = message.what;
                    this.syncWithSystemProvider(what, message.obj);
                    while (true) {
                        MediaRouter mediaRouter;
                        try {
                            int size = GlobalMediaRouter.this.mRouters.size();
                            while (true) {
                                --size;
                                if (size < 0) {
                                    break Label_0103;
                                }
                                mediaRouter = GlobalMediaRouter.this.mRouters.get(size).get();
                                if (mediaRouter != null) {
                                    break;
                                }
                                GlobalMediaRouter.this.mRouters.remove(size);
                            }
                        }
                        finally {
                            this.mTempCallbackRecords.clear();
                        }
                        this.mTempCallbackRecords.addAll((Collection<? extends CallbackRecord>)mediaRouter.mCallbackRecords);
                        continue;
                    }
                }
                for (int size2 = this.mTempCallbackRecords.size(), i = 0; i < size2; ++i) {
                    final Throwable t;
                    this.invokeCallback(this.mTempCallbackRecords.get(i), what, t);
                }
                this.mTempCallbackRecords.clear();
            }
            
            public void post(final int n, final Object o) {
                this.obtainMessage(n, o).sendToTarget();
            }
        }
        
        private final class ProviderCallback extends MediaRouteProvider.Callback
        {
            @Override
            public void onDescriptorChanged(final MediaRouteProvider mediaRouteProvider, final MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
                GlobalMediaRouter.this.updateProviderDescriptor(mediaRouteProvider, mediaRouteProviderDescriptor);
            }
        }
        
        private final class RemoteControlClientRecord implements VolumeCallback
        {
            private boolean mDisconnected;
            private final RemoteControlClientCompat mRccCompat;
            
            public RemoteControlClientRecord(final Object o) {
                (this.mRccCompat = RemoteControlClientCompat.obtain(GlobalMediaRouter.this.mApplicationContext, o)).setVolumeCallback((RemoteControlClientCompat.VolumeCallback)this);
                this.updatePlaybackInfo();
            }
            
            public void disconnect() {
                this.mDisconnected = true;
                this.mRccCompat.setVolumeCallback(null);
            }
            
            public Object getRemoteControlClient() {
                return this.mRccCompat.getRemoteControlClient();
            }
            
            @Override
            public void onVolumeSetRequest(final int n) {
                if (!this.mDisconnected && GlobalMediaRouter.this.mSelectedRoute != null) {
                    GlobalMediaRouter.this.mSelectedRoute.requestSetVolume(n);
                }
            }
            
            @Override
            public void onVolumeUpdateRequest(final int n) {
                if (!this.mDisconnected && GlobalMediaRouter.this.mSelectedRoute != null) {
                    GlobalMediaRouter.this.mSelectedRoute.requestUpdateVolume(n);
                }
            }
            
            public void updatePlaybackInfo() {
                this.mRccCompat.setPlaybackInfo(GlobalMediaRouter.this.mPlaybackInfo);
            }
        }
    }
    
    public static final class ProviderInfo
    {
        private MediaRouteProviderDescriptor mDescriptor;
        private final MediaRouteProvider.ProviderMetadata mMetadata;
        private final MediaRouteProvider mProviderInstance;
        private Resources mResources;
        private boolean mResourcesNotAvailable;
        private final ArrayList<RouteInfo> mRoutes;
        
        ProviderInfo(final MediaRouteProvider mProviderInstance) {
            this.mRoutes = new ArrayList<RouteInfo>();
            this.mProviderInstance = mProviderInstance;
            this.mMetadata = mProviderInstance.getMetadata();
        }
        
        int findRouteByDescriptorId(final String s) {
            for (int size = this.mRoutes.size(), i = 0; i < size; ++i) {
                if (this.mRoutes.get(i).mDescriptorId.equals(s)) {
                    return i;
                }
            }
            return -1;
        }
        
        public ComponentName getComponentName() {
            return this.mMetadata.getComponentName();
        }
        
        public String getPackageName() {
            return this.mMetadata.getPackageName();
        }
        
        public MediaRouteProvider getProviderInstance() {
            MediaRouter.checkCallingThread();
            return this.mProviderInstance;
        }
        
        Resources getResources() {
            if (this.mResources == null && !this.mResourcesNotAvailable) {
                final String packageName = this.getPackageName();
                final Context providerContext = MediaRouter.sGlobal.getProviderContext(packageName);
                if (providerContext != null) {
                    this.mResources = providerContext.getResources();
                }
                else {
                    Log.w("MediaRouter", "Unable to obtain resources for route provider package: " + packageName);
                    this.mResourcesNotAvailable = true;
                }
            }
            return this.mResources;
        }
        
        public List<RouteInfo> getRoutes() {
            MediaRouter.checkCallingThread();
            return this.mRoutes;
        }
        
        @Override
        public String toString() {
            return "MediaRouter.RouteProviderInfo{ packageName=" + this.getPackageName() + " }";
        }
        
        boolean updateDescriptor(final MediaRouteProviderDescriptor mDescriptor) {
            if (this.mDescriptor != mDescriptor) {
                this.mDescriptor = mDescriptor;
                return true;
            }
            return false;
        }
    }
    
    public static final class RouteInfo
    {
        static final int CHANGE_GENERAL = 1;
        static final int CHANGE_PRESENTATION_DISPLAY = 4;
        static final int CHANGE_VOLUME = 2;
        public static final int PLAYBACK_TYPE_LOCAL = 0;
        public static final int PLAYBACK_TYPE_REMOTE = 1;
        public static final int PLAYBACK_VOLUME_FIXED = 0;
        public static final int PLAYBACK_VOLUME_VARIABLE = 1;
        private boolean mConnecting;
        private final ArrayList<IntentFilter> mControlFilters;
        private String mDescription;
        private MediaRouteDescriptor mDescriptor;
        private final String mDescriptorId;
        private boolean mEnabled;
        private Bundle mExtras;
        private String mName;
        private int mPlaybackStream;
        private int mPlaybackType;
        private Display mPresentationDisplay;
        private int mPresentationDisplayId;
        private final ProviderInfo mProvider;
        private final String mUniqueId;
        private int mVolume;
        private int mVolumeHandling;
        private int mVolumeMax;
        
        RouteInfo(final ProviderInfo mProvider, final String mDescriptorId, final String mUniqueId) {
            this.mControlFilters = new ArrayList<IntentFilter>();
            this.mPresentationDisplayId = -1;
            this.mProvider = mProvider;
            this.mDescriptorId = mDescriptorId;
            this.mUniqueId = mUniqueId;
        }
        
        public List<IntentFilter> getControlFilters() {
            return this.mControlFilters;
        }
        
        public String getDescription() {
            return this.mDescription;
        }
        
        String getDescriptorId() {
            return this.mDescriptorId;
        }
        
        public Bundle getExtras() {
            return this.mExtras;
        }
        
        public String getId() {
            return this.mUniqueId;
        }
        
        public String getName() {
            return this.mName;
        }
        
        public int getPlaybackStream() {
            return this.mPlaybackStream;
        }
        
        public int getPlaybackType() {
            return this.mPlaybackType;
        }
        
        public Display getPresentationDisplay() {
            MediaRouter.checkCallingThread();
            if (this.mPresentationDisplayId >= 0 && this.mPresentationDisplay == null) {
                this.mPresentationDisplay = MediaRouter.sGlobal.getDisplay(this.mPresentationDisplayId);
            }
            return this.mPresentationDisplay;
        }
        
        public ProviderInfo getProvider() {
            return this.mProvider;
        }
        
        MediaRouteProvider getProviderInstance() {
            return this.mProvider.getProviderInstance();
        }
        
        public int getVolume() {
            return this.mVolume;
        }
        
        public int getVolumeHandling() {
            return this.mVolumeHandling;
        }
        
        public int getVolumeMax() {
            return this.mVolumeMax;
        }
        
        public boolean isConnecting() {
            return this.mConnecting;
        }
        
        public boolean isDefault() {
            MediaRouter.checkCallingThread();
            return MediaRouter.sGlobal.getDefaultRoute() == this;
        }
        
        public boolean isEnabled() {
            return this.mEnabled;
        }
        
        public boolean isSelected() {
            MediaRouter.checkCallingThread();
            return MediaRouter.sGlobal.getSelectedRoute() == this;
        }
        
        public boolean matchesSelector(final MediaRouteSelector mediaRouteSelector) {
            if (mediaRouteSelector == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            MediaRouter.checkCallingThread();
            return mediaRouteSelector.matchesControlFilters(this.mControlFilters);
        }
        
        public void requestSetVolume(final int n) {
            MediaRouter.checkCallingThread();
            MediaRouter.sGlobal.requestSetVolume(this, Math.min(this.mVolumeMax, Math.max(0, n)));
        }
        
        public void requestUpdateVolume(final int n) {
            MediaRouter.checkCallingThread();
            if (n != 0) {
                MediaRouter.sGlobal.requestUpdateVolume(this, n);
            }
        }
        
        public void select() {
            MediaRouter.checkCallingThread();
            MediaRouter.sGlobal.selectRoute(this);
        }
        
        public void sendControlRequest(final Intent intent, final ControlRequestCallback controlRequestCallback) {
            if (intent == null) {
                throw new IllegalArgumentException("intent must not be null");
            }
            MediaRouter.checkCallingThread();
            MediaRouter.sGlobal.sendControlRequest(this, intent, controlRequestCallback);
        }
        
        public boolean supportsControlAction(final String s, final String s2) {
            if (s == null) {
                throw new IllegalArgumentException("category must not be null");
            }
            if (s2 == null) {
                throw new IllegalArgumentException("action must not be null");
            }
            MediaRouter.checkCallingThread();
            for (int size = this.mControlFilters.size(), i = 0; i < size; ++i) {
                final IntentFilter intentFilter = this.mControlFilters.get(i);
                if (intentFilter.hasCategory(s) && intentFilter.hasAction(s2)) {
                    return true;
                }
            }
            return false;
        }
        
        public boolean supportsControlCategory(final String s) {
            if (s == null) {
                throw new IllegalArgumentException("category must not be null");
            }
            MediaRouter.checkCallingThread();
            for (int size = this.mControlFilters.size(), i = 0; i < size; ++i) {
                if (this.mControlFilters.get(i).hasCategory(s)) {
                    return true;
                }
            }
            return false;
        }
        
        public boolean supportsControlRequest(final Intent intent) {
            if (intent == null) {
                throw new IllegalArgumentException("intent must not be null");
            }
            MediaRouter.checkCallingThread();
            final ContentResolver contentResolver = MediaRouter.sGlobal.getContentResolver();
            for (int size = this.mControlFilters.size(), i = 0; i < size; ++i) {
                if (this.mControlFilters.get(i).match(contentResolver, intent, true, "MediaRouter") >= 0) {
                    return true;
                }
            }
            return false;
        }
        
        @Override
        public String toString() {
            return "MediaRouter.RouteInfo{ uniqueId=" + this.mUniqueId + ", name=" + this.mName + ", description=" + this.mDescription + ", enabled=" + this.mEnabled + ", connecting=" + this.mConnecting + ", playbackType=" + this.mPlaybackType + ", playbackStream=" + this.mPlaybackStream + ", volumeHandling=" + this.mVolumeHandling + ", volume=" + this.mVolume + ", volumeMax=" + this.mVolumeMax + ", presentationDisplayId=" + this.mPresentationDisplayId + ", extras=" + this.mExtras + ", providerPackageName=" + this.mProvider.getPackageName() + " }";
        }
        
        int updateDescriptor(final MediaRouteDescriptor mDescriptor) {
            final boolean b = false;
            boolean b2 = false;
            int n = b ? 1 : 0;
            if (this.mDescriptor != mDescriptor) {
                this.mDescriptor = mDescriptor;
                n = (b ? 1 : 0);
                if (mDescriptor != null) {
                    if (!MediaRouter.equal(this.mName, mDescriptor.getName())) {
                        this.mName = mDescriptor.getName();
                        b2 = (false | true);
                    }
                    boolean b3 = b2;
                    if (!MediaRouter.equal(this.mDescription, mDescriptor.getDescription())) {
                        this.mDescription = mDescriptor.getDescription();
                        b3 = (b2 | true);
                    }
                    boolean b4 = b3;
                    if (this.mEnabled != mDescriptor.isEnabled()) {
                        this.mEnabled = mDescriptor.isEnabled();
                        b4 = (b3 | true);
                    }
                    boolean b5 = b4;
                    if (this.mConnecting != mDescriptor.isConnecting()) {
                        this.mConnecting = mDescriptor.isConnecting();
                        b5 = (b4 | true);
                    }
                    boolean b6 = b5;
                    if (!this.mControlFilters.equals(mDescriptor.getControlFilters())) {
                        this.mControlFilters.clear();
                        this.mControlFilters.addAll(mDescriptor.getControlFilters());
                        b6 = (b5 | true);
                    }
                    boolean b7 = b6;
                    if (this.mPlaybackType != mDescriptor.getPlaybackType()) {
                        this.mPlaybackType = mDescriptor.getPlaybackType();
                        b7 = (b6 | true);
                    }
                    boolean b8 = b7;
                    if (this.mPlaybackStream != mDescriptor.getPlaybackStream()) {
                        this.mPlaybackStream = mDescriptor.getPlaybackStream();
                        b8 = (b7 | true);
                    }
                    int n2 = b8 ? 1 : 0;
                    if (this.mVolumeHandling != mDescriptor.getVolumeHandling()) {
                        this.mVolumeHandling = mDescriptor.getVolumeHandling();
                        n2 = ((b8 ? 1 : 0) | 0x3);
                    }
                    int n3 = n2;
                    if (this.mVolume != mDescriptor.getVolume()) {
                        this.mVolume = mDescriptor.getVolume();
                        n3 = (n2 | 0x3);
                    }
                    int n4 = n3;
                    if (this.mVolumeMax != mDescriptor.getVolumeMax()) {
                        this.mVolumeMax = mDescriptor.getVolumeMax();
                        n4 = (n3 | 0x3);
                    }
                    int n5 = n4;
                    if (this.mPresentationDisplayId != mDescriptor.getPresentationDisplayId()) {
                        this.mPresentationDisplayId = mDescriptor.getPresentationDisplayId();
                        this.mPresentationDisplay = null;
                        n5 = (n4 | 0x5);
                    }
                    n = n5;
                    if (!MediaRouter.equal(this.mExtras, mDescriptor.getExtras())) {
                        this.mExtras = mDescriptor.getExtras();
                        n = (n5 | 0x1);
                    }
                }
            }
            return n;
        }
    }
}
