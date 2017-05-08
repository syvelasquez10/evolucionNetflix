// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.Collection;
import android.content.IntentSender;
import android.view.Display;
import android.net.Uri;
import android.os.Bundle;
import android.content.IntentFilter;
import android.content.ComponentName;
import java.util.HashSet;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.Locale;
import android.util.Log;
import android.support.v4.app.ActivityManagerCompat;
import android.app.ActivityManager;
import java.util.HashMap;
import android.support.v4.util.Pair;
import android.support.v4.media.session.MediaSessionCompat$OnActiveChangeListener;
import java.lang.ref.WeakReference;
import java.util.Map;
import android.support.v4.media.session.MediaSessionCompat;
import java.util.ArrayList;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.content.Context;

final class MediaRouter$GlobalMediaRouter implements RegisteredMediaRouteProviderWatcher$Callback, SystemMediaRouteProvider$SyncCallback
{
    final Context mApplicationContext;
    private MediaRouter$RouteInfo mBluetoothRoute;
    final MediaRouter$GlobalMediaRouter$CallbackHandler mCallbackHandler;
    private MediaRouter$RouteInfo mDefaultRoute;
    private MediaRouteDiscoveryRequest mDiscoveryRequest;
    private final DisplayManagerCompat mDisplayManager;
    private final boolean mLowRam;
    private MediaRouter$GlobalMediaRouter$MediaSessionRecord mMediaSession;
    final RemoteControlClientCompat$PlaybackInfo mPlaybackInfo;
    private final MediaRouter$GlobalMediaRouter$ProviderCallback mProviderCallback;
    private final ArrayList<MediaRouter$ProviderInfo> mProviders;
    MediaSessionCompat mRccMediaSession;
    private RegisteredMediaRouteProviderWatcher mRegisteredProviderWatcher;
    private final ArrayList<MediaRouter$GlobalMediaRouter$RemoteControlClientRecord> mRemoteControlClients;
    private final Map<String, MediaRouteProvider$RouteController> mRouteControllerMap;
    final ArrayList<WeakReference<MediaRouter>> mRouters;
    private final ArrayList<MediaRouter$RouteInfo> mRoutes;
    MediaRouter$RouteInfo mSelectedRoute;
    private MediaRouteProvider$RouteController mSelectedRouteController;
    private MediaSessionCompat$OnActiveChangeListener mSessionActiveListener;
    final SystemMediaRouteProvider mSystemProvider;
    private final Map<Pair<String, String>, String> mUniqueIdMap;
    
    MediaRouter$GlobalMediaRouter(final Context mApplicationContext) {
        this.mRouters = new ArrayList<WeakReference<MediaRouter>>();
        this.mRoutes = new ArrayList<MediaRouter$RouteInfo>();
        this.mUniqueIdMap = new HashMap<Pair<String, String>, String>();
        this.mProviders = new ArrayList<MediaRouter$ProviderInfo>();
        this.mRemoteControlClients = new ArrayList<MediaRouter$GlobalMediaRouter$RemoteControlClientRecord>();
        this.mPlaybackInfo = new RemoteControlClientCompat$PlaybackInfo();
        this.mProviderCallback = new MediaRouter$GlobalMediaRouter$ProviderCallback(this);
        this.mCallbackHandler = new MediaRouter$GlobalMediaRouter$CallbackHandler(this);
        this.mRouteControllerMap = new HashMap<String, MediaRouteProvider$RouteController>();
        this.mSessionActiveListener = new MediaRouter$GlobalMediaRouter$1(this);
        this.mApplicationContext = mApplicationContext;
        this.mDisplayManager = DisplayManagerCompat.getInstance(mApplicationContext);
        this.mLowRam = ActivityManagerCompat.isLowRamDevice((ActivityManager)mApplicationContext.getSystemService("activity"));
        this.addProvider(this.mSystemProvider = SystemMediaRouteProvider.obtain(mApplicationContext, this));
    }
    
    private String assignRouteUniqueId(final MediaRouter$ProviderInfo mediaRouter$ProviderInfo, final String s) {
        final String flattenToShortString = mediaRouter$ProviderInfo.getComponentName().flattenToShortString();
        final String string = flattenToShortString + ":" + s;
        if (this.findRouteByUniqueId(string) < 0) {
            this.mUniqueIdMap.put(new Pair<String, String>(flattenToShortString, s), string);
            return string;
        }
        Log.w("MediaRouter", "Either " + s + " isn't unique in " + flattenToShortString + " or we're trying to assign a unique ID for an already added route");
        int n = 2;
        String format;
        while (true) {
            format = String.format(Locale.US, "%s_%d", string, n);
            if (this.findRouteByUniqueId(format) < 0) {
                break;
            }
            ++n;
        }
        this.mUniqueIdMap.put(new Pair<String, String>(flattenToShortString, s), format);
        return format;
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
    
    private String getUniqueId(final MediaRouter$ProviderInfo mediaRouter$ProviderInfo, final String s) {
        return this.mUniqueIdMap.get(new Pair(mediaRouter$ProviderInfo.getComponentName().flattenToShortString(), s));
    }
    
    private boolean isRouteSelectable(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        return mediaRouter$RouteInfo.mDescriptor != null && mediaRouter$RouteInfo.mEnabled;
    }
    
    private boolean isSystemDefaultRoute(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        return mediaRouter$RouteInfo.getProviderInstance() == this.mSystemProvider && mediaRouter$RouteInfo.mDescriptorId.equals("DEFAULT_ROUTE");
    }
    
    private boolean isSystemLiveAudioOnlyRoute(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        return mediaRouter$RouteInfo.getProviderInstance() == this.mSystemProvider && mediaRouter$RouteInfo.supportsControlCategory("android.media.intent.category.LIVE_AUDIO") && !mediaRouter$RouteInfo.supportsControlCategory("android.media.intent.category.LIVE_VIDEO");
    }
    
    private void setSelectedRouteInternal(final MediaRouter$RouteInfo mSelectedRoute, final int n) {
        if (this.mSelectedRoute != mSelectedRoute) {
            if (this.mSelectedRoute != null) {
                if (MediaRouter.DEBUG) {
                    Log.d("MediaRouter", "Route unselected: " + this.mSelectedRoute + " reason: " + n);
                }
                this.mCallbackHandler.post(263, this.mSelectedRoute, n);
                if (this.mSelectedRouteController != null) {
                    this.mSelectedRouteController.onUnselect(n);
                    this.mSelectedRouteController.onRelease();
                    this.mSelectedRouteController = null;
                }
                if (!this.mRouteControllerMap.isEmpty()) {
                    for (final MediaRouteProvider$RouteController mediaRouteProvider$RouteController : this.mRouteControllerMap.values()) {
                        mediaRouteProvider$RouteController.onUnselect(n);
                        mediaRouteProvider$RouteController.onRelease();
                    }
                    this.mRouteControllerMap.clear();
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
                if (this.mSelectedRoute instanceof MediaRouter$RouteGroup) {
                    final List<MediaRouter$RouteInfo> routes = ((MediaRouter$RouteGroup)this.mSelectedRoute).getRoutes();
                    this.mRouteControllerMap.clear();
                    for (final MediaRouter$RouteInfo mediaRouter$RouteInfo : routes) {
                        final MediaRouteProvider$RouteController onCreateRouteController = mediaRouter$RouteInfo.getProviderInstance().onCreateRouteController(mediaRouter$RouteInfo.mDescriptorId, this.mSelectedRoute.mDescriptorId);
                        onCreateRouteController.onSelect();
                        this.mRouteControllerMap.put(mediaRouter$RouteInfo.mDescriptorId, onCreateRouteController);
                    }
                }
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
            if (this.mMediaSession != null) {
                if (this.mSelectedRoute != this.getDefaultRoute()) {
                    int n;
                    if (this.mPlaybackInfo.volumeHandling == 1) {
                        n = 2;
                    }
                    else {
                        n = 0;
                    }
                    this.mMediaSession.configureVolume(n, this.mPlaybackInfo.volumeMax, this.mPlaybackInfo.volume);
                    return;
                }
                this.mMediaSession.clearVolumeHandling();
            }
        }
        else if (this.mMediaSession != null) {
            this.mMediaSession.clearVolumeHandling();
        }
    }
    
    private void updateProviderContents(final MediaRouter$ProviderInfo mediaRouter$ProviderInfo, final MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
        if (mediaRouter$ProviderInfo.updateDescriptor(mediaRouteProviderDescriptor)) {
            final int n = 0;
            int n2 = 0;
            final boolean b = false;
            boolean b2 = false;
            boolean b3 = b;
            int n3 = n;
            if (mediaRouteProviderDescriptor != null) {
                if (mediaRouteProviderDescriptor.isValid()) {
                    final List<MediaRouteDescriptor> routes = mediaRouteProviderDescriptor.getRoutes();
                    final int size = routes.size();
                    final ArrayList<Pair<MediaRouter$RouteInfo, MediaRouteDescriptor>> list = new ArrayList<Pair<MediaRouter$RouteInfo, MediaRouteDescriptor>>();
                    final ArrayList<Pair> list2 = new ArrayList<Pair>();
                    for (int i = 0; i < size; ++i) {
                        final MediaRouteDescriptor mediaRouteDescriptor = routes.get(i);
                        final String id = mediaRouteDescriptor.getId();
                        final int routeByDescriptorId = mediaRouter$ProviderInfo.findRouteByDescriptorId(id);
                        if (routeByDescriptorId < 0) {
                            final String assignRouteUniqueId = this.assignRouteUniqueId(mediaRouter$ProviderInfo, id);
                            boolean b4;
                            if (mediaRouteDescriptor.getGroupMemberIds() != null) {
                                b4 = true;
                            }
                            else {
                                b4 = false;
                            }
                            MediaRouter$RouteInfo mediaRouter$RouteInfo;
                            if (b4) {
                                mediaRouter$RouteInfo = new MediaRouter$RouteGroup(mediaRouter$ProviderInfo, id, assignRouteUniqueId);
                            }
                            else {
                                mediaRouter$RouteInfo = new MediaRouter$RouteInfo(mediaRouter$ProviderInfo, id, assignRouteUniqueId);
                            }
                            mediaRouter$ProviderInfo.mRoutes.add(n2, mediaRouter$RouteInfo);
                            this.mRoutes.add(mediaRouter$RouteInfo);
                            if (b4) {
                                list.add(new Pair<MediaRouter$RouteInfo, MediaRouteDescriptor>(mediaRouter$RouteInfo, mediaRouteDescriptor));
                            }
                            else {
                                mediaRouter$RouteInfo.maybeUpdateDescriptor(mediaRouteDescriptor);
                                if (MediaRouter.DEBUG) {
                                    Log.d("MediaRouter", "Route added: " + mediaRouter$RouteInfo);
                                }
                                this.mCallbackHandler.post(257, mediaRouter$RouteInfo);
                            }
                            ++n2;
                        }
                        else if (routeByDescriptorId < n2) {
                            Log.w("MediaRouter", "Ignoring route descriptor with duplicate id: " + mediaRouteDescriptor);
                        }
                        else {
                            final MediaRouter$RouteInfo mediaRouter$RouteInfo2 = mediaRouter$ProviderInfo.mRoutes.get(routeByDescriptorId);
                            final List access$500 = mediaRouter$ProviderInfo.mRoutes;
                            final int n4 = n2 + 1;
                            Collections.swap(access$500, routeByDescriptorId, n2);
                            if (mediaRouter$RouteInfo2 instanceof MediaRouter$RouteGroup) {
                                list2.add(new Pair<MediaRouter$RouteInfo, MediaRouteDescriptor>(mediaRouter$RouteInfo2, mediaRouteDescriptor));
                                n2 = n4;
                            }
                            else if (this.updateRouteDescriptorAndNotify(mediaRouter$RouteInfo2, mediaRouteDescriptor) != 0 && mediaRouter$RouteInfo2 == this.mSelectedRoute) {
                                b2 = true;
                                n2 = n4;
                            }
                            else {
                                n2 = n4;
                            }
                        }
                    }
                    for (final Pair<MediaRouter$RouteInfo, MediaRouteDescriptor> pair : list) {
                        final MediaRouter$RouteInfo mediaRouter$RouteInfo3 = pair.first;
                        mediaRouter$RouteInfo3.maybeUpdateDescriptor(pair.second);
                        if (MediaRouter.DEBUG) {
                            Log.d("MediaRouter", "Route added: " + mediaRouter$RouteInfo3);
                        }
                        this.mCallbackHandler.post(257, mediaRouter$RouteInfo3);
                    }
                    final Iterator<Object> iterator2 = list2.iterator();
                    while (true) {
                        b3 = b2;
                        n3 = n2;
                        if (!iterator2.hasNext()) {
                            break;
                        }
                        final Pair pair2 = iterator2.next();
                        final MediaRouter$RouteInfo mediaRouter$RouteInfo4 = (MediaRouter$RouteInfo)pair2.first;
                        if (this.updateRouteDescriptorAndNotify(mediaRouter$RouteInfo4, (MediaRouteDescriptor)pair2.second) == 0 || mediaRouter$RouteInfo4 != this.mSelectedRoute) {
                            continue;
                        }
                        b2 = true;
                    }
                }
                else {
                    Log.w("MediaRouter", "Ignoring invalid provider descriptor: " + mediaRouteProviderDescriptor);
                    n3 = n;
                    b3 = b;
                }
            }
            for (int j = mediaRouter$ProviderInfo.mRoutes.size() - 1; j >= n3; --j) {
                final MediaRouter$RouteInfo mediaRouter$RouteInfo5 = mediaRouter$ProviderInfo.mRoutes.get(j);
                mediaRouter$RouteInfo5.maybeUpdateDescriptor(null);
                this.mRoutes.remove(mediaRouter$RouteInfo5);
            }
            this.updateSelectedRouteIfNeeded(b3);
            for (int k = mediaRouter$ProviderInfo.mRoutes.size() - 1; k >= n3; --k) {
                final MediaRouter$RouteInfo mediaRouter$RouteInfo6 = mediaRouter$ProviderInfo.mRoutes.remove(k);
                if (MediaRouter.DEBUG) {
                    Log.d("MediaRouter", "Route removed: " + mediaRouter$RouteInfo6);
                }
                this.mCallbackHandler.post(258, mediaRouter$RouteInfo6);
            }
            if (MediaRouter.DEBUG) {
                Log.d("MediaRouter", "Provider changed: " + mediaRouter$ProviderInfo);
            }
            this.mCallbackHandler.post(515, mediaRouter$ProviderInfo);
        }
    }
    
    private int updateRouteDescriptorAndNotify(final MediaRouter$RouteInfo mediaRouter$RouteInfo, final MediaRouteDescriptor mediaRouteDescriptor) {
        final int maybeUpdateDescriptor = mediaRouter$RouteInfo.maybeUpdateDescriptor(mediaRouteDescriptor);
        if (maybeUpdateDescriptor != 0) {
            if ((maybeUpdateDescriptor & 0x1) != 0x0) {
                if (MediaRouter.DEBUG) {
                    Log.d("MediaRouter", "Route changed: " + mediaRouter$RouteInfo);
                }
                this.mCallbackHandler.post(259, mediaRouter$RouteInfo);
            }
            if ((maybeUpdateDescriptor & 0x2) != 0x0) {
                if (MediaRouter.DEBUG) {
                    Log.d("MediaRouter", "Route volume changed: " + mediaRouter$RouteInfo);
                }
                this.mCallbackHandler.post(260, mediaRouter$RouteInfo);
            }
            if ((maybeUpdateDescriptor & 0x4) != 0x0) {
                if (MediaRouter.DEBUG) {
                    Log.d("MediaRouter", "Route presentation display changed: " + mediaRouter$RouteInfo);
                }
                this.mCallbackHandler.post(261, mediaRouter$RouteInfo);
            }
        }
        return maybeUpdateDescriptor;
    }
    
    private void updateSelectedRouteIfNeeded(final boolean b) {
        if (this.mDefaultRoute != null && !this.isRouteSelectable(this.mDefaultRoute)) {
            Log.i("MediaRouter", "Clearing the default route because it is no longer selectable: " + this.mDefaultRoute);
            this.mDefaultRoute = null;
        }
        if (this.mDefaultRoute == null && !this.mRoutes.isEmpty()) {
            for (final MediaRouter$RouteInfo mDefaultRoute : this.mRoutes) {
                if (this.isSystemDefaultRoute(mDefaultRoute) && this.isRouteSelectable(mDefaultRoute)) {
                    this.mDefaultRoute = mDefaultRoute;
                    Log.i("MediaRouter", "Found default route: " + this.mDefaultRoute);
                    break;
                }
            }
        }
        if (this.mBluetoothRoute != null && !this.isRouteSelectable(this.mBluetoothRoute)) {
            Log.i("MediaRouter", "Clearing the bluetooth route because it is no longer selectable: " + this.mBluetoothRoute);
            this.mBluetoothRoute = null;
        }
        if (this.mBluetoothRoute == null && !this.mRoutes.isEmpty()) {
            for (final MediaRouter$RouteInfo mBluetoothRoute : this.mRoutes) {
                if (this.isSystemLiveAudioOnlyRoute(mBluetoothRoute) && this.isRouteSelectable(mBluetoothRoute)) {
                    this.mBluetoothRoute = mBluetoothRoute;
                    Log.i("MediaRouter", "Found bluetooth route: " + this.mBluetoothRoute);
                    break;
                }
            }
        }
        if (this.mSelectedRoute != null && !this.isRouteSelectable(this.mSelectedRoute)) {
            Log.i("MediaRouter", "Unselecting the current route because it is no longer selectable: " + this.mSelectedRoute);
            this.setSelectedRouteInternal(null, 0);
        }
        if (this.mSelectedRoute == null) {
            this.setSelectedRouteInternal(this.chooseFallbackRoute(), 0);
        }
        else if (b) {
            if (this.mSelectedRoute instanceof MediaRouter$RouteGroup) {
                final List<MediaRouter$RouteInfo> routes = ((MediaRouter$RouteGroup)this.mSelectedRoute).getRoutes();
                final HashSet<String> set = new HashSet<String>();
                final Iterator<MediaRouter$RouteInfo> iterator3 = routes.iterator();
                while (iterator3.hasNext()) {
                    set.add(iterator3.next().mDescriptorId);
                }
                final Iterator<Map.Entry<String, MediaRouteProvider$RouteController>> iterator4 = this.mRouteControllerMap.entrySet().iterator();
                while (iterator4.hasNext()) {
                    final Map.Entry<String, MediaRouteProvider$RouteController> entry = iterator4.next();
                    if (!set.contains(entry.getKey())) {
                        final MediaRouteProvider$RouteController mediaRouteProvider$RouteController = entry.getValue();
                        mediaRouteProvider$RouteController.onUnselect();
                        mediaRouteProvider$RouteController.onRelease();
                        iterator4.remove();
                    }
                }
                for (final MediaRouter$RouteInfo mediaRouter$RouteInfo : routes) {
                    if (!this.mRouteControllerMap.containsKey(mediaRouter$RouteInfo.mDescriptorId)) {
                        final MediaRouteProvider$RouteController onCreateRouteController = mediaRouter$RouteInfo.getProviderInstance().onCreateRouteController(mediaRouter$RouteInfo.mDescriptorId, this.mSelectedRoute.mDescriptorId);
                        onCreateRouteController.onSelect();
                        this.mRouteControllerMap.put(mediaRouter$RouteInfo.mDescriptorId, onCreateRouteController);
                    }
                }
            }
            this.updatePlaybackInfoFromSelectedRoute();
        }
    }
    
    @Override
    public void addProvider(final MediaRouteProvider mediaRouteProvider) {
        if (this.findProviderInfo(mediaRouteProvider) < 0) {
            final MediaRouter$ProviderInfo mediaRouter$ProviderInfo = new MediaRouter$ProviderInfo(mediaRouteProvider);
            this.mProviders.add(mediaRouter$ProviderInfo);
            if (MediaRouter.DEBUG) {
                Log.d("MediaRouter", "Provider added: " + mediaRouter$ProviderInfo);
            }
            this.mCallbackHandler.post(513, mediaRouter$ProviderInfo);
            this.updateProviderContents(mediaRouter$ProviderInfo, mediaRouteProvider.getDescriptor());
            mediaRouteProvider.setCallback(this.mProviderCallback);
            mediaRouteProvider.setDiscoveryRequest(this.mDiscoveryRequest);
        }
    }
    
    public void addRemoteControlClient(final Object o) {
        if (this.findRemoteControlClientRecord(o) < 0) {
            this.mRemoteControlClients.add(new MediaRouter$GlobalMediaRouter$RemoteControlClientRecord(this, o));
        }
    }
    
    MediaRouter$RouteInfo chooseFallbackRoute() {
        for (final MediaRouter$RouteInfo mediaRouter$RouteInfo : this.mRoutes) {
            if (mediaRouter$RouteInfo != this.mDefaultRoute && this.isSystemLiveAudioOnlyRoute(mediaRouter$RouteInfo) && this.isRouteSelectable(mediaRouter$RouteInfo)) {
                return mediaRouter$RouteInfo;
            }
        }
        return this.mDefaultRoute;
    }
    
    public MediaRouter$RouteInfo getDefaultRoute() {
        if (this.mDefaultRoute == null) {
            throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
        }
        return this.mDefaultRoute;
    }
    
    public MediaRouter$RouteInfo getRoute(final String s) {
        for (final MediaRouter$RouteInfo mediaRouter$RouteInfo : this.mRoutes) {
            if (mediaRouter$RouteInfo.mUniqueId.equals(s)) {
                return mediaRouter$RouteInfo;
            }
        }
        return null;
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
    
    public MediaRouter$RouteInfo getSelectedRoute() {
        if (this.mSelectedRoute == null) {
            throw new IllegalStateException("There is no currently selected route.  The media router has not yet been fully initialized.");
        }
        return this.mSelectedRoute;
    }
    
    @Override
    public MediaRouter$RouteInfo getSystemRouteByDescriptorId(final String s) {
        final int providerInfo = this.findProviderInfo(this.mSystemProvider);
        if (providerInfo >= 0) {
            final MediaRouter$ProviderInfo mediaRouter$ProviderInfo = this.mProviders.get(providerInfo);
            final int routeByDescriptorId = mediaRouter$ProviderInfo.findRouteByDescriptorId(s);
            if (routeByDescriptorId >= 0) {
                return (MediaRouter$RouteInfo)mediaRouter$ProviderInfo.mRoutes.get(routeByDescriptorId);
            }
        }
        return null;
    }
    
    @Override
    public void removeProvider(final MediaRouteProvider mediaRouteProvider) {
        final int providerInfo = this.findProviderInfo(mediaRouteProvider);
        if (providerInfo >= 0) {
            mediaRouteProvider.setCallback(null);
            mediaRouteProvider.setDiscoveryRequest(null);
            final MediaRouter$ProviderInfo mediaRouter$ProviderInfo = this.mProviders.get(providerInfo);
            this.updateProviderContents(mediaRouter$ProviderInfo, null);
            if (MediaRouter.DEBUG) {
                Log.d("MediaRouter", "Provider removed: " + mediaRouter$ProviderInfo);
            }
            this.mCallbackHandler.post(514, mediaRouter$ProviderInfo);
            this.mProviders.remove(providerInfo);
        }
    }
    
    public void removeRemoteControlClient(final Object o) {
        final int remoteControlClientRecord = this.findRemoteControlClientRecord(o);
        if (remoteControlClientRecord >= 0) {
            this.mRemoteControlClients.remove(remoteControlClientRecord).disconnect();
        }
    }
    
    public void requestSetVolume(final MediaRouter$RouteInfo mediaRouter$RouteInfo, final int n) {
        if (mediaRouter$RouteInfo == this.mSelectedRoute && this.mSelectedRouteController != null) {
            this.mSelectedRouteController.onSetVolume(n);
        }
        else if (!this.mRouteControllerMap.isEmpty()) {
            final MediaRouteProvider$RouteController mediaRouteProvider$RouteController = this.mRouteControllerMap.get(mediaRouter$RouteInfo.mDescriptorId);
            if (mediaRouteProvider$RouteController != null) {
                mediaRouteProvider$RouteController.onSetVolume(n);
            }
        }
    }
    
    public void requestUpdateVolume(final MediaRouter$RouteInfo mediaRouter$RouteInfo, final int n) {
        if (mediaRouter$RouteInfo == this.mSelectedRoute && this.mSelectedRouteController != null) {
            this.mSelectedRouteController.onUpdateVolume(n);
        }
    }
    
    public void selectRoute(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.selectRoute(mediaRouter$RouteInfo, 3);
    }
    
    public void selectRoute(final MediaRouter$RouteInfo mediaRouter$RouteInfo, final int n) {
        if (!this.mRoutes.contains(mediaRouter$RouteInfo)) {
            Log.w("MediaRouter", "Ignoring attempt to select removed route: " + mediaRouter$RouteInfo);
            return;
        }
        if (!mediaRouter$RouteInfo.mEnabled) {
            Log.w("MediaRouter", "Ignoring attempt to select disabled route: " + mediaRouter$RouteInfo);
            return;
        }
        this.setSelectedRouteInternal(mediaRouter$RouteInfo, n);
    }
    
    public void start() {
        (this.mRegisteredProviderWatcher = new RegisteredMediaRouteProviderWatcher(this.mApplicationContext, this)).start();
    }
    
    public void updateDiscoveryRequest() {
        final MediaRouteSelector$Builder mediaRouteSelector$Builder = new MediaRouteSelector$Builder();
        int size = this.mRouters.size();
        boolean b = false;
        int n = 0;
        while (true) {
            final int n2 = size - 1;
            if (n2 < 0) {
                break;
            }
            final MediaRouter mediaRouter = this.mRouters.get(n2).get();
            int n3;
            boolean b2;
            if (mediaRouter == null) {
                this.mRouters.remove(n2);
                n3 = n;
                b2 = b;
            }
            else {
                final int size2 = mediaRouter.mCallbackRecords.size();
                int n4 = 0;
                while (true) {
                    b2 = b;
                    n3 = n;
                    if (n4 >= size2) {
                        break;
                    }
                    final MediaRouter$CallbackRecord mediaRouter$CallbackRecord = mediaRouter.mCallbackRecords.get(n4);
                    mediaRouteSelector$Builder.addSelector(mediaRouter$CallbackRecord.mSelector);
                    if ((mediaRouter$CallbackRecord.mFlags & 0x1) != 0x0) {
                        b = true;
                        n = 1;
                    }
                    int n5 = n;
                    if ((mediaRouter$CallbackRecord.mFlags & 0x4) != 0x0) {
                        n5 = n;
                        if (!this.mLowRam) {
                            n5 = 1;
                        }
                    }
                    n = n5;
                    if ((mediaRouter$CallbackRecord.mFlags & 0x8) != 0x0) {
                        n = 1;
                    }
                    ++n4;
                }
            }
            size = n2;
            b = b2;
            n = n3;
        }
        MediaRouteSelector mediaRouteSelector;
        if (n != 0) {
            mediaRouteSelector = mediaRouteSelector$Builder.build();
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
            if (n != 0 && !b && this.mLowRam) {
                Log.i("MediaRouter", "Forcing passive route discovery on a low-RAM device, system performance may be affected.  Please consider using CALLBACK_FLAG_REQUEST_DISCOVERY instead of CALLBACK_FLAG_FORCE_DISCOVERY.");
            }
            for (int size3 = this.mProviders.size(), i = 0; i < size3; ++i) {
                this.mProviders.get(i).mProviderInstance.setDiscoveryRequest(this.mDiscoveryRequest);
            }
        }
    }
    
    void updateProviderDescriptor(final MediaRouteProvider mediaRouteProvider, final MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
        final int providerInfo = this.findProviderInfo(mediaRouteProvider);
        if (providerInfo >= 0) {
            this.updateProviderContents(this.mProviders.get(providerInfo), mediaRouteProviderDescriptor);
        }
    }
}
