// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.Looper;
import java.util.Collection;
import android.content.IntentSender;
import android.view.Display;
import android.os.Bundle;
import android.content.IntentFilter;
import android.content.ComponentName;
import java.util.List;
import java.util.Collections;
import android.util.Log;
import java.util.Iterator;
import java.util.Locale;
import android.support.v4.app.ActivityManagerCompat;
import android.app.ActivityManager;
import android.support.v4.media.session.MediaSessionCompat$OnActiveChangeListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.content.Context;

final class MediaRouter$GlobalMediaRouter implements RegisteredMediaRouteProviderWatcher$Callback, SystemMediaRouteProvider$SyncCallback
{
    private final Context mApplicationContext;
    private final MediaRouter$GlobalMediaRouter$CallbackHandler mCallbackHandler;
    private MediaRouter$RouteInfo mDefaultRoute;
    private MediaRouteDiscoveryRequest mDiscoveryRequest;
    private final DisplayManagerCompat mDisplayManager;
    private final boolean mLowRam;
    private MediaRouter$GlobalMediaRouter$MediaSessionRecord mMediaSession;
    private final RemoteControlClientCompat$PlaybackInfo mPlaybackInfo;
    private final MediaRouter$GlobalMediaRouter$ProviderCallback mProviderCallback;
    private final ArrayList<MediaRouter$ProviderInfo> mProviders;
    private RegisteredMediaRouteProviderWatcher mRegisteredProviderWatcher;
    private final ArrayList<MediaRouter$GlobalMediaRouter$RemoteControlClientRecord> mRemoteControlClients;
    private final ArrayList<WeakReference<MediaRouter>> mRouters;
    private final ArrayList<MediaRouter$RouteInfo> mRoutes;
    private MediaRouter$RouteInfo mSelectedRoute;
    private MediaRouteProvider$RouteController mSelectedRouteController;
    private MediaSessionCompat$OnActiveChangeListener mSessionActiveListener;
    private final SystemMediaRouteProvider mSystemProvider;
    
    MediaRouter$GlobalMediaRouter(final Context mApplicationContext) {
        this.mRouters = new ArrayList<WeakReference<MediaRouter>>();
        this.mRoutes = new ArrayList<MediaRouter$RouteInfo>();
        this.mProviders = new ArrayList<MediaRouter$ProviderInfo>();
        this.mRemoteControlClients = new ArrayList<MediaRouter$GlobalMediaRouter$RemoteControlClientRecord>();
        this.mPlaybackInfo = new RemoteControlClientCompat$PlaybackInfo();
        this.mProviderCallback = new MediaRouter$GlobalMediaRouter$ProviderCallback(this, null);
        this.mCallbackHandler = new MediaRouter$GlobalMediaRouter$CallbackHandler(this, null);
        this.mSessionActiveListener = new MediaRouter$GlobalMediaRouter$1(this);
        this.mApplicationContext = mApplicationContext;
        this.mDisplayManager = DisplayManagerCompat.getInstance(mApplicationContext);
        this.mLowRam = ActivityManagerCompat.isLowRamDevice((ActivityManager)mApplicationContext.getSystemService("activity"));
        this.addProvider(this.mSystemProvider = SystemMediaRouteProvider.obtain(mApplicationContext, this));
    }
    
    private String assignRouteUniqueId(final MediaRouter$ProviderInfo mediaRouter$ProviderInfo, String format) {
        final String string = mediaRouter$ProviderInfo.getComponentName().flattenToShortString() + ":" + format;
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
    
    private MediaRouter$RouteInfo chooseFallbackRoute() {
        for (final MediaRouter$RouteInfo mediaRouter$RouteInfo : this.mRoutes) {
            if (mediaRouter$RouteInfo != this.mDefaultRoute && this.isSystemLiveAudioOnlyRoute(mediaRouter$RouteInfo) && this.isRouteSelectable(mediaRouter$RouteInfo)) {
                return mediaRouter$RouteInfo;
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
    
    private int findRouteByUniqueId(final String s) {
        for (int size = this.mRoutes.size(), i = 0; i < size; ++i) {
            if (this.mRoutes.get(i).mUniqueId.equals(s)) {
                return i;
            }
        }
        return -1;
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
                this.mCallbackHandler.post(263, this.mSelectedRoute);
                if (this.mSelectedRouteController != null) {
                    this.mSelectedRouteController.onUnselect(n);
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
        boolean b = false;
        boolean b2 = false;
        if (mediaRouter$ProviderInfo.updateDescriptor(mediaRouteProviderDescriptor)) {
            int n3 = 0;
            Label_0490: {
                if (mediaRouteProviderDescriptor != null) {
                    if (mediaRouteProviderDescriptor.isValid()) {
                        final List<MediaRouteDescriptor> routes = mediaRouteProviderDescriptor.getRoutes();
                        final int size = routes.size();
                        int n = 0;
                        int n2 = 0;
                        while (true) {
                            b = b2;
                            n3 = n2;
                            if (n >= size) {
                                break Label_0490;
                            }
                            final MediaRouteDescriptor mediaRouteDescriptor = routes.get(n);
                            final String id = mediaRouteDescriptor.getId();
                            final int routeByDescriptorId = mediaRouter$ProviderInfo.findRouteByDescriptorId(id);
                            Label_0195: {
                                if (routeByDescriptorId < 0) {
                                    final MediaRouter$RouteInfo mediaRouter$RouteInfo = new MediaRouter$RouteInfo(mediaRouter$ProviderInfo, id, this.assignRouteUniqueId(mediaRouter$ProviderInfo, id));
                                    final ArrayList access$700 = mediaRouter$ProviderInfo.mRoutes;
                                    final int n4 = n2 + 1;
                                    access$700.add(n2, mediaRouter$RouteInfo);
                                    this.mRoutes.add(mediaRouter$RouteInfo);
                                    mediaRouter$RouteInfo.updateDescriptor(mediaRouteDescriptor);
                                    if (MediaRouter.DEBUG) {
                                        Log.d("MediaRouter", "Route added: " + mediaRouter$RouteInfo);
                                    }
                                    this.mCallbackHandler.post(257, mediaRouter$RouteInfo);
                                    n2 = n4;
                                }
                                else if (routeByDescriptorId < n2) {
                                    Log.w("MediaRouter", "Ignoring route descriptor with duplicate id: " + mediaRouteDescriptor);
                                }
                                else {
                                    final MediaRouter$RouteInfo mediaRouter$RouteInfo2 = mediaRouter$ProviderInfo.mRoutes.get(routeByDescriptorId);
                                    final ArrayList access$701 = mediaRouter$ProviderInfo.mRoutes;
                                    final int n5 = n2 + 1;
                                    Collections.swap(access$701, routeByDescriptorId, n2);
                                    final int updateDescriptor = mediaRouter$RouteInfo2.updateDescriptor(mediaRouteDescriptor);
                                    if (updateDescriptor != 0) {
                                        if ((updateDescriptor & 0x1) != 0x0) {
                                            if (MediaRouter.DEBUG) {
                                                Log.d("MediaRouter", "Route changed: " + mediaRouter$RouteInfo2);
                                            }
                                            this.mCallbackHandler.post(259, mediaRouter$RouteInfo2);
                                        }
                                        if ((updateDescriptor & 0x2) != 0x0) {
                                            if (MediaRouter.DEBUG) {
                                                Log.d("MediaRouter", "Route volume changed: " + mediaRouter$RouteInfo2);
                                            }
                                            this.mCallbackHandler.post(260, mediaRouter$RouteInfo2);
                                        }
                                        if ((updateDescriptor & 0x4) != 0x0) {
                                            if (MediaRouter.DEBUG) {
                                                Log.d("MediaRouter", "Route presentation display changed: " + mediaRouter$RouteInfo2);
                                            }
                                            this.mCallbackHandler.post(261, mediaRouter$RouteInfo2);
                                        }
                                        if (mediaRouter$RouteInfo2 == this.mSelectedRoute) {
                                            b2 = true;
                                            n2 = n5;
                                            break Label_0195;
                                        }
                                    }
                                    n2 = n5;
                                }
                            }
                            ++n;
                        }
                    }
                    else {
                        Log.w("MediaRouter", "Ignoring invalid provider descriptor: " + mediaRouteProviderDescriptor);
                    }
                }
                n3 = 0;
            }
            for (int i = mediaRouter$ProviderInfo.mRoutes.size() - 1; i >= n3; --i) {
                final MediaRouter$RouteInfo mediaRouter$RouteInfo3 = mediaRouter$ProviderInfo.mRoutes.get(i);
                mediaRouter$RouteInfo3.updateDescriptor(null);
                this.mRoutes.remove(mediaRouter$RouteInfo3);
            }
            this.updateSelectedRouteIfNeeded(b);
            for (int j = mediaRouter$ProviderInfo.mRoutes.size() - 1; j >= n3; --j) {
                final MediaRouter$RouteInfo mediaRouter$RouteInfo4 = mediaRouter$ProviderInfo.mRoutes.remove(j);
                if (MediaRouter.DEBUG) {
                    Log.d("MediaRouter", "Route removed: " + mediaRouter$RouteInfo4);
                }
                this.mCallbackHandler.post(258, mediaRouter$RouteInfo4);
            }
            if (MediaRouter.DEBUG) {
                Log.d("MediaRouter", "Provider changed: " + mediaRouter$ProviderInfo);
            }
            this.mCallbackHandler.post(515, mediaRouter$ProviderInfo);
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
            for (final MediaRouter$RouteInfo mDefaultRoute : this.mRoutes) {
                if (this.isSystemDefaultRoute(mDefaultRoute) && this.isRouteSelectable(mDefaultRoute)) {
                    this.mDefaultRoute = mDefaultRoute;
                    Log.i("MediaRouter", "Found default route: " + this.mDefaultRoute);
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
    
    public MediaRouter$RouteInfo getDefaultRoute() {
        if (this.mDefaultRoute == null) {
            throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
        }
        return this.mDefaultRoute;
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
    
    public void requestSetVolume(final MediaRouter$RouteInfo mediaRouter$RouteInfo, final int n) {
        if (mediaRouter$RouteInfo == this.mSelectedRoute && this.mSelectedRouteController != null) {
            this.mSelectedRouteController.onSetVolume(n);
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
}
