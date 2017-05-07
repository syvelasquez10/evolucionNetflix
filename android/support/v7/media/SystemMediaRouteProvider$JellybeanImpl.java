// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import android.support.v7.mediarouter.R$string;
import android.content.Context;
import android.content.IntentFilter;
import java.util.ArrayList;

class SystemMediaRouteProvider$JellybeanImpl extends SystemMediaRouteProvider implements MediaRouterJellybean$Callback, MediaRouterJellybean$VolumeCallback
{
    private static final ArrayList<IntentFilter> LIVE_AUDIO_CONTROL_FILTERS;
    private static final ArrayList<IntentFilter> LIVE_VIDEO_CONTROL_FILTERS;
    protected boolean mActiveScan;
    protected final Object mCallbackObj;
    protected boolean mCallbackRegistered;
    private MediaRouterJellybean$GetDefaultRouteWorkaround mGetDefaultRouteWorkaround;
    protected int mRouteTypes;
    protected final Object mRouterObj;
    private MediaRouterJellybean$SelectRouteWorkaround mSelectRouteWorkaround;
    private final SystemMediaRouteProvider$SyncCallback mSyncCallback;
    protected final ArrayList<SystemMediaRouteProvider$JellybeanImpl$SystemRouteRecord> mSystemRouteRecords;
    protected final Object mUserRouteCategoryObj;
    protected final ArrayList<SystemMediaRouteProvider$JellybeanImpl$UserRouteRecord> mUserRouteRecords;
    protected final Object mVolumeCallbackObj;
    
    static {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addCategory("android.media.intent.category.LIVE_AUDIO");
        (LIVE_AUDIO_CONTROL_FILTERS = new ArrayList<IntentFilter>()).add(intentFilter);
        final IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addCategory("android.media.intent.category.LIVE_VIDEO");
        (LIVE_VIDEO_CONTROL_FILTERS = new ArrayList<IntentFilter>()).add(intentFilter2);
    }
    
    public SystemMediaRouteProvider$JellybeanImpl(final Context context, final SystemMediaRouteProvider$SyncCallback mSyncCallback) {
        super(context);
        this.mSystemRouteRecords = new ArrayList<SystemMediaRouteProvider$JellybeanImpl$SystemRouteRecord>();
        this.mUserRouteRecords = new ArrayList<SystemMediaRouteProvider$JellybeanImpl$UserRouteRecord>();
        this.mSyncCallback = mSyncCallback;
        this.mRouterObj = MediaRouterJellybean.getMediaRouter(context);
        this.mCallbackObj = this.createCallbackObj();
        this.mVolumeCallbackObj = this.createVolumeCallbackObj();
        this.mUserRouteCategoryObj = MediaRouterJellybean.createRouteCategory(this.mRouterObj, context.getResources().getString(R$string.mr_user_route_category_name), false);
        this.updateSystemRoutes();
    }
    
    private boolean addSystemRouteNoPublish(final Object o) {
        if (this.getUserRouteRecord(o) == null && this.findSystemRouteRecord(o) < 0) {
            final SystemMediaRouteProvider$JellybeanImpl$SystemRouteRecord systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord = new SystemMediaRouteProvider$JellybeanImpl$SystemRouteRecord(o, this.assignRouteId(o));
            this.updateSystemRouteDescriptor(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord);
            this.mSystemRouteRecords.add(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord);
            return true;
        }
        return false;
    }
    
    private String assignRouteId(final Object o) {
        int n;
        if (this.getDefaultRoute() == o) {
            n = 1;
        }
        else {
            n = 0;
        }
        String format;
        if (n != 0) {
            format = "DEFAULT_ROUTE";
        }
        else {
            format = String.format(Locale.US, "ROUTE_%08x", this.getRouteName(o).hashCode());
        }
        if (this.findSystemRouteRecordByDescriptorId(format) < 0) {
            return format;
        }
        int n2 = 2;
        String format2;
        while (true) {
            format2 = String.format(Locale.US, "%s_%d", format, n2);
            if (this.findSystemRouteRecordByDescriptorId(format2) < 0) {
                break;
            }
            ++n2;
        }
        return format2;
    }
    
    private void updateSystemRoutes() {
        boolean b = false;
        final Iterator<Object> iterator = MediaRouterJellybean.getRoutes(this.mRouterObj).iterator();
        while (iterator.hasNext()) {
            b |= this.addSystemRouteNoPublish(iterator.next());
        }
        if (b) {
            this.publishRoutes();
        }
    }
    
    protected Object createCallbackObj() {
        return MediaRouterJellybean.createCallback(this);
    }
    
    protected Object createVolumeCallbackObj() {
        return MediaRouterJellybean.createVolumeCallback(this);
    }
    
    protected int findSystemRouteRecord(final Object o) {
        for (int size = this.mSystemRouteRecords.size(), i = 0; i < size; ++i) {
            if (this.mSystemRouteRecords.get(i).mRouteObj == o) {
                return i;
            }
        }
        return -1;
    }
    
    protected int findSystemRouteRecordByDescriptorId(final String s) {
        for (int size = this.mSystemRouteRecords.size(), i = 0; i < size; ++i) {
            if (this.mSystemRouteRecords.get(i).mRouteDescriptorId.equals(s)) {
                return i;
            }
        }
        return -1;
    }
    
    protected int findUserRouteRecord(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        for (int size = this.mUserRouteRecords.size(), i = 0; i < size; ++i) {
            if (this.mUserRouteRecords.get(i).mRoute == mediaRouter$RouteInfo) {
                return i;
            }
        }
        return -1;
    }
    
    protected Object getDefaultRoute() {
        if (this.mGetDefaultRouteWorkaround == null) {
            this.mGetDefaultRouteWorkaround = new MediaRouterJellybean$GetDefaultRouteWorkaround();
        }
        return this.mGetDefaultRouteWorkaround.getDefaultRoute(this.mRouterObj);
    }
    
    protected String getRouteName(final Object o) {
        final CharSequence name = MediaRouterJellybean$RouteInfo.getName(o, this.getContext());
        if (name != null) {
            return name.toString();
        }
        return "";
    }
    
    protected SystemMediaRouteProvider$JellybeanImpl$UserRouteRecord getUserRouteRecord(Object tag) {
        tag = MediaRouterJellybean$RouteInfo.getTag(tag);
        if (tag instanceof SystemMediaRouteProvider$JellybeanImpl$UserRouteRecord) {
            return (SystemMediaRouteProvider$JellybeanImpl$UserRouteRecord)tag;
        }
        return null;
    }
    
    protected void onBuildSystemRouteDescriptor(final SystemMediaRouteProvider$JellybeanImpl$SystemRouteRecord systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord, final MediaRouteDescriptor$Builder mediaRouteDescriptor$Builder) {
        final int supportedTypes = MediaRouterJellybean$RouteInfo.getSupportedTypes(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteObj);
        if ((supportedTypes & 0x1) != 0x0) {
            mediaRouteDescriptor$Builder.addControlFilters(SystemMediaRouteProvider$JellybeanImpl.LIVE_AUDIO_CONTROL_FILTERS);
        }
        if ((supportedTypes & 0x2) != 0x0) {
            mediaRouteDescriptor$Builder.addControlFilters(SystemMediaRouteProvider$JellybeanImpl.LIVE_VIDEO_CONTROL_FILTERS);
        }
        mediaRouteDescriptor$Builder.setPlaybackType(MediaRouterJellybean$RouteInfo.getPlaybackType(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteObj));
        mediaRouteDescriptor$Builder.setPlaybackStream(MediaRouterJellybean$RouteInfo.getPlaybackStream(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteObj));
        mediaRouteDescriptor$Builder.setVolume(MediaRouterJellybean$RouteInfo.getVolume(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteObj));
        mediaRouteDescriptor$Builder.setVolumeMax(MediaRouterJellybean$RouteInfo.getVolumeMax(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteObj));
        mediaRouteDescriptor$Builder.setVolumeHandling(MediaRouterJellybean$RouteInfo.getVolumeHandling(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteObj));
    }
    
    @Override
    public MediaRouteProvider$RouteController onCreateRouteController(final String s) {
        final int systemRouteRecordByDescriptorId = this.findSystemRouteRecordByDescriptorId(s);
        if (systemRouteRecordByDescriptorId >= 0) {
            return new SystemMediaRouteProvider$JellybeanImpl$SystemRouteController(this, this.mSystemRouteRecords.get(systemRouteRecordByDescriptorId).mRouteObj);
        }
        return null;
    }
    
    @Override
    public void onDiscoveryRequestChanged(final MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
        boolean activeScan = false;
        int mRouteTypes;
        if (mediaRouteDiscoveryRequest != null) {
            final List<String> controlCategories = mediaRouteDiscoveryRequest.getSelector().getControlCategories();
            final int size = controlCategories.size();
            int i = 0;
            mRouteTypes = 0;
            while (i < size) {
                final String s = controlCategories.get(i);
                if (s.equals("android.media.intent.category.LIVE_AUDIO")) {
                    mRouteTypes |= 0x1;
                }
                else if (s.equals("android.media.intent.category.LIVE_VIDEO")) {
                    mRouteTypes |= 0x2;
                }
                else {
                    mRouteTypes |= 0x800000;
                }
                ++i;
            }
            activeScan = mediaRouteDiscoveryRequest.isActiveScan();
        }
        else {
            mRouteTypes = 0;
        }
        if (this.mRouteTypes != mRouteTypes || this.mActiveScan != activeScan) {
            this.mRouteTypes = mRouteTypes;
            this.mActiveScan = activeScan;
            this.updateCallback();
            this.updateSystemRoutes();
        }
    }
    
    @Override
    public void onRouteAdded(final Object o) {
        if (this.addSystemRouteNoPublish(o)) {
            this.publishRoutes();
        }
    }
    
    @Override
    public void onRouteChanged(final Object o) {
        if (this.getUserRouteRecord(o) == null) {
            final int systemRouteRecord = this.findSystemRouteRecord(o);
            if (systemRouteRecord >= 0) {
                this.updateSystemRouteDescriptor(this.mSystemRouteRecords.get(systemRouteRecord));
                this.publishRoutes();
            }
        }
    }
    
    @Override
    public void onRouteGrouped(final Object o, final Object o2, final int n) {
    }
    
    @Override
    public void onRouteRemoved(final Object o) {
        if (this.getUserRouteRecord(o) == null) {
            final int systemRouteRecord = this.findSystemRouteRecord(o);
            if (systemRouteRecord >= 0) {
                this.mSystemRouteRecords.remove(systemRouteRecord);
                this.publishRoutes();
            }
        }
    }
    
    @Override
    public void onRouteSelected(int systemRouteRecord, final Object o) {
        if (o == MediaRouterJellybean.getSelectedRoute(this.mRouterObj, 8388611)) {
            final SystemMediaRouteProvider$JellybeanImpl$UserRouteRecord userRouteRecord = this.getUserRouteRecord(o);
            if (userRouteRecord != null) {
                userRouteRecord.mRoute.select();
                return;
            }
            systemRouteRecord = this.findSystemRouteRecord(o);
            if (systemRouteRecord >= 0) {
                final MediaRouter$RouteInfo systemRouteByDescriptorId = this.mSyncCallback.getSystemRouteByDescriptorId(this.mSystemRouteRecords.get(systemRouteRecord).mRouteDescriptorId);
                if (systemRouteByDescriptorId != null) {
                    systemRouteByDescriptorId.select();
                }
            }
        }
    }
    
    @Override
    public void onRouteUngrouped(final Object o, final Object o2) {
    }
    
    @Override
    public void onRouteUnselected(final int n, final Object o) {
    }
    
    @Override
    public void onRouteVolumeChanged(final Object o) {
        if (this.getUserRouteRecord(o) == null) {
            final int systemRouteRecord = this.findSystemRouteRecord(o);
            if (systemRouteRecord >= 0) {
                final SystemMediaRouteProvider$JellybeanImpl$SystemRouteRecord systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord = this.mSystemRouteRecords.get(systemRouteRecord);
                final int volume = MediaRouterJellybean$RouteInfo.getVolume(o);
                if (volume != systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteDescriptor.getVolume()) {
                    systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteDescriptor = new MediaRouteDescriptor$Builder(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteDescriptor).setVolume(volume).build();
                    this.publishRoutes();
                }
            }
        }
    }
    
    @Override
    public void onSyncRouteAdded(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        if (mediaRouter$RouteInfo.getProviderInstance() != this) {
            final Object userRoute = MediaRouterJellybean.createUserRoute(this.mRouterObj, this.mUserRouteCategoryObj);
            final SystemMediaRouteProvider$JellybeanImpl$UserRouteRecord systemMediaRouteProvider$JellybeanImpl$UserRouteRecord = new SystemMediaRouteProvider$JellybeanImpl$UserRouteRecord(mediaRouter$RouteInfo, userRoute);
            MediaRouterJellybean$RouteInfo.setTag(userRoute, systemMediaRouteProvider$JellybeanImpl$UserRouteRecord);
            MediaRouterJellybean$UserRouteInfo.setVolumeCallback(userRoute, this.mVolumeCallbackObj);
            this.updateUserRouteProperties(systemMediaRouteProvider$JellybeanImpl$UserRouteRecord);
            this.mUserRouteRecords.add(systemMediaRouteProvider$JellybeanImpl$UserRouteRecord);
            MediaRouterJellybean.addUserRoute(this.mRouterObj, userRoute);
        }
        else {
            final int systemRouteRecord = this.findSystemRouteRecord(MediaRouterJellybean.getSelectedRoute(this.mRouterObj, 8388611));
            if (systemRouteRecord >= 0 && this.mSystemRouteRecords.get(systemRouteRecord).mRouteDescriptorId.equals(mediaRouter$RouteInfo.getDescriptorId())) {
                mediaRouter$RouteInfo.select();
            }
        }
    }
    
    @Override
    public void onSyncRouteChanged(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        if (mediaRouter$RouteInfo.getProviderInstance() != this) {
            final int userRouteRecord = this.findUserRouteRecord(mediaRouter$RouteInfo);
            if (userRouteRecord >= 0) {
                this.updateUserRouteProperties(this.mUserRouteRecords.get(userRouteRecord));
            }
        }
    }
    
    @Override
    public void onSyncRouteRemoved(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        if (mediaRouter$RouteInfo.getProviderInstance() != this) {
            final int userRouteRecord = this.findUserRouteRecord(mediaRouter$RouteInfo);
            if (userRouteRecord >= 0) {
                final SystemMediaRouteProvider$JellybeanImpl$UserRouteRecord systemMediaRouteProvider$JellybeanImpl$UserRouteRecord = this.mUserRouteRecords.remove(userRouteRecord);
                MediaRouterJellybean$RouteInfo.setTag(systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRouteObj, null);
                MediaRouterJellybean$UserRouteInfo.setVolumeCallback(systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRouteObj, null);
                MediaRouterJellybean.removeUserRoute(this.mRouterObj, systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRouteObj);
            }
        }
    }
    
    @Override
    public void onSyncRouteSelected(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        if (mediaRouter$RouteInfo.isSelected()) {
            if (mediaRouter$RouteInfo.getProviderInstance() != this) {
                final int userRouteRecord = this.findUserRouteRecord(mediaRouter$RouteInfo);
                if (userRouteRecord >= 0) {
                    this.selectRoute(this.mUserRouteRecords.get(userRouteRecord).mRouteObj);
                }
            }
            else {
                final int systemRouteRecordByDescriptorId = this.findSystemRouteRecordByDescriptorId(mediaRouter$RouteInfo.getDescriptorId());
                if (systemRouteRecordByDescriptorId >= 0) {
                    this.selectRoute(this.mSystemRouteRecords.get(systemRouteRecordByDescriptorId).mRouteObj);
                }
            }
        }
    }
    
    @Override
    public void onVolumeSetRequest(final Object o, final int n) {
        final SystemMediaRouteProvider$JellybeanImpl$UserRouteRecord userRouteRecord = this.getUserRouteRecord(o);
        if (userRouteRecord != null) {
            userRouteRecord.mRoute.requestSetVolume(n);
        }
    }
    
    @Override
    public void onVolumeUpdateRequest(final Object o, final int n) {
        final SystemMediaRouteProvider$JellybeanImpl$UserRouteRecord userRouteRecord = this.getUserRouteRecord(o);
        if (userRouteRecord != null) {
            userRouteRecord.mRoute.requestUpdateVolume(n);
        }
    }
    
    protected void publishRoutes() {
        final MediaRouteProviderDescriptor$Builder mediaRouteProviderDescriptor$Builder = new MediaRouteProviderDescriptor$Builder();
        for (int size = this.mSystemRouteRecords.size(), i = 0; i < size; ++i) {
            mediaRouteProviderDescriptor$Builder.addRoute(this.mSystemRouteRecords.get(i).mRouteDescriptor);
        }
        this.setDescriptor(mediaRouteProviderDescriptor$Builder.build());
    }
    
    protected void selectRoute(final Object o) {
        if (this.mSelectRouteWorkaround == null) {
            this.mSelectRouteWorkaround = new MediaRouterJellybean$SelectRouteWorkaround();
        }
        this.mSelectRouteWorkaround.selectRoute(this.mRouterObj, 8388611, o);
    }
    
    protected void updateCallback() {
        if (this.mCallbackRegistered) {
            this.mCallbackRegistered = false;
            MediaRouterJellybean.removeCallback(this.mRouterObj, this.mCallbackObj);
        }
        if (this.mRouteTypes != 0) {
            this.mCallbackRegistered = true;
            MediaRouterJellybean.addCallback(this.mRouterObj, this.mRouteTypes, this.mCallbackObj);
        }
    }
    
    protected void updateSystemRouteDescriptor(final SystemMediaRouteProvider$JellybeanImpl$SystemRouteRecord systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord) {
        final MediaRouteDescriptor$Builder mediaRouteDescriptor$Builder = new MediaRouteDescriptor$Builder(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteDescriptorId, this.getRouteName(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteObj));
        this.onBuildSystemRouteDescriptor(systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord, mediaRouteDescriptor$Builder);
        systemMediaRouteProvider$JellybeanImpl$SystemRouteRecord.mRouteDescriptor = mediaRouteDescriptor$Builder.build();
    }
    
    protected void updateUserRouteProperties(final SystemMediaRouteProvider$JellybeanImpl$UserRouteRecord systemMediaRouteProvider$JellybeanImpl$UserRouteRecord) {
        MediaRouterJellybean$UserRouteInfo.setName(systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRouteObj, systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRoute.getName());
        MediaRouterJellybean$UserRouteInfo.setPlaybackType(systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRouteObj, systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRoute.getPlaybackType());
        MediaRouterJellybean$UserRouteInfo.setPlaybackStream(systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRouteObj, systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRoute.getPlaybackStream());
        MediaRouterJellybean$UserRouteInfo.setVolume(systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRouteObj, systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRoute.getVolume());
        MediaRouterJellybean$UserRouteInfo.setVolumeMax(systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRouteObj, systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRoute.getVolumeMax());
        MediaRouterJellybean$UserRouteInfo.setVolumeHandling(systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRouteObj, systemMediaRouteProvider$JellybeanImpl$UserRouteRecord.mRoute.getVolumeHandling());
    }
}
