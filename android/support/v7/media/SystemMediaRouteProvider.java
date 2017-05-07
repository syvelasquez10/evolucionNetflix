// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.content.Intent;
import android.content.res.Resources;
import android.content.BroadcastReceiver;
import android.media.AudioManager;
import android.view.Display;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import android.support.v7.mediarouter.R;
import android.content.IntentFilter;
import java.util.ArrayList;
import android.os.Build$VERSION;
import android.content.ComponentName;
import android.content.Context;

abstract class SystemMediaRouteProvider extends MediaRouteProvider
{
    public static final String DEFAULT_ROUTE_ID = "DEFAULT_ROUTE";
    public static final String PACKAGE_NAME = "android";
    private static final String TAG = "SystemMediaRouteProvider";
    
    protected SystemMediaRouteProvider(final Context context) {
        super(context, new ProviderMetadata(new ComponentName("android", SystemMediaRouteProvider.class.getName())));
    }
    
    public static SystemMediaRouteProvider obtain(final Context context, final SyncCallback syncCallback) {
        if (Build$VERSION.SDK_INT >= 18) {
            return new JellybeanMr2Impl(context, syncCallback);
        }
        if (Build$VERSION.SDK_INT >= 17) {
            return new JellybeanMr1Impl(context, syncCallback);
        }
        if (Build$VERSION.SDK_INT >= 16) {
            return new JellybeanImpl(context, syncCallback);
        }
        return new LegacyImpl(context);
    }
    
    public void onSyncRouteAdded(final MediaRouter.RouteInfo routeInfo) {
    }
    
    public void onSyncRouteChanged(final MediaRouter.RouteInfo routeInfo) {
    }
    
    public void onSyncRouteRemoved(final MediaRouter.RouteInfo routeInfo) {
    }
    
    public void onSyncRouteSelected(final MediaRouter.RouteInfo routeInfo) {
    }
    
    static class JellybeanImpl extends SystemMediaRouteProvider implements MediaRouterJellybean.Callback, VolumeCallback
    {
        private static final ArrayList<IntentFilter> LIVE_AUDIO_CONTROL_FILTERS;
        private static final ArrayList<IntentFilter> LIVE_VIDEO_CONTROL_FILTERS;
        protected boolean mActiveScan;
        protected final Object mCallbackObj;
        protected boolean mCallbackRegistered;
        private GetDefaultRouteWorkaround mGetDefaultRouteWorkaround;
        protected int mRouteTypes;
        protected final Object mRouterObj;
        private SelectRouteWorkaround mSelectRouteWorkaround;
        private final SyncCallback mSyncCallback;
        protected final ArrayList<SystemRouteRecord> mSystemRouteRecords;
        protected final Object mUserRouteCategoryObj;
        protected final ArrayList<UserRouteRecord> mUserRouteRecords;
        protected final Object mVolumeCallbackObj;
        
        static {
            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addCategory("android.media.intent.category.LIVE_AUDIO");
            (LIVE_AUDIO_CONTROL_FILTERS = new ArrayList<IntentFilter>()).add(intentFilter);
            final IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addCategory("android.media.intent.category.LIVE_VIDEO");
            (LIVE_VIDEO_CONTROL_FILTERS = new ArrayList<IntentFilter>()).add(intentFilter2);
        }
        
        public JellybeanImpl(final Context context, final SyncCallback mSyncCallback) {
            super(context);
            this.mSystemRouteRecords = new ArrayList<SystemRouteRecord>();
            this.mUserRouteRecords = new ArrayList<UserRouteRecord>();
            this.mSyncCallback = mSyncCallback;
            this.mRouterObj = MediaRouterJellybean.getMediaRouter(context);
            this.mCallbackObj = this.createCallbackObj();
            this.mVolumeCallbackObj = this.createVolumeCallbackObj();
            this.mUserRouteCategoryObj = MediaRouterJellybean.createRouteCategory(this.mRouterObj, context.getResources().getString(R.string.mr_user_route_category_name), false);
            this.updateSystemRoutes();
        }
        
        private boolean addSystemRouteNoPublish(final Object o) {
            if (this.getUserRouteRecord(o) == null && this.findSystemRouteRecord(o) < 0) {
                final SystemRouteRecord systemRouteRecord = new SystemRouteRecord(o, this.assignRouteId(o));
                this.updateSystemRouteDescriptor(systemRouteRecord);
                this.mSystemRouteRecords.add(systemRouteRecord);
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
            return MediaRouterJellybean.createCallback((MediaRouterJellybean.Callback)this);
        }
        
        protected Object createVolumeCallbackObj() {
            return MediaRouterJellybean.createVolumeCallback((MediaRouterJellybean.VolumeCallback)this);
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
        
        protected int findUserRouteRecord(final MediaRouter.RouteInfo routeInfo) {
            for (int size = this.mUserRouteRecords.size(), i = 0; i < size; ++i) {
                if (this.mUserRouteRecords.get(i).mRoute == routeInfo) {
                    return i;
                }
            }
            return -1;
        }
        
        protected Object getDefaultRoute() {
            if (this.mGetDefaultRouteWorkaround == null) {
                this.mGetDefaultRouteWorkaround = new MediaRouterJellybean.GetDefaultRouteWorkaround();
            }
            return this.mGetDefaultRouteWorkaround.getDefaultRoute(this.mRouterObj);
        }
        
        protected String getRouteName(final Object o) {
            final CharSequence name = MediaRouterJellybean.RouteInfo.getName(o, this.getContext());
            if (name != null) {
                return name.toString();
            }
            return "";
        }
        
        protected UserRouteRecord getUserRouteRecord(Object tag) {
            tag = MediaRouterJellybean.RouteInfo.getTag(tag);
            if (tag instanceof UserRouteRecord) {
                return (UserRouteRecord)tag;
            }
            return null;
        }
        
        protected void onBuildSystemRouteDescriptor(final SystemRouteRecord systemRouteRecord, final MediaRouteDescriptor.Builder builder) {
            final int supportedTypes = MediaRouterJellybean.RouteInfo.getSupportedTypes(systemRouteRecord.mRouteObj);
            if ((supportedTypes & 0x1) != 0x0) {
                builder.addControlFilters(JellybeanImpl.LIVE_AUDIO_CONTROL_FILTERS);
            }
            if ((supportedTypes & 0x2) != 0x0) {
                builder.addControlFilters(JellybeanImpl.LIVE_VIDEO_CONTROL_FILTERS);
            }
            builder.setPlaybackType(MediaRouterJellybean.RouteInfo.getPlaybackType(systemRouteRecord.mRouteObj));
            builder.setPlaybackStream(MediaRouterJellybean.RouteInfo.getPlaybackStream(systemRouteRecord.mRouteObj));
            builder.setVolume(MediaRouterJellybean.RouteInfo.getVolume(systemRouteRecord.mRouteObj));
            builder.setVolumeMax(MediaRouterJellybean.RouteInfo.getVolumeMax(systemRouteRecord.mRouteObj));
            builder.setVolumeHandling(MediaRouterJellybean.RouteInfo.getVolumeHandling(systemRouteRecord.mRouteObj));
        }
        
        @Override
        public RouteController onCreateRouteController(final String s) {
            final int systemRouteRecordByDescriptorId = this.findSystemRouteRecordByDescriptorId(s);
            if (systemRouteRecordByDescriptorId >= 0) {
                return new SystemRouteController(this.mSystemRouteRecords.get(systemRouteRecordByDescriptorId).mRouteObj);
            }
            return null;
        }
        
        @Override
        public void onDiscoveryRequestChanged(final MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
            int mRouteTypes = 0;
            final boolean b = false;
            boolean activeScan = false;
            if (mediaRouteDiscoveryRequest != null) {
                final List<String> controlCategories = mediaRouteDiscoveryRequest.getSelector().getControlCategories();
                final int size = controlCategories.size();
                final int n = 0;
                mRouteTypes = (b ? 1 : 0);
                for (int i = n; i < size; ++i) {
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
                }
                activeScan = mediaRouteDiscoveryRequest.isActiveScan();
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
                final UserRouteRecord userRouteRecord = this.getUserRouteRecord(o);
                if (userRouteRecord != null) {
                    userRouteRecord.mRoute.select();
                    return;
                }
                systemRouteRecord = this.findSystemRouteRecord(o);
                if (systemRouteRecord >= 0) {
                    final MediaRouter.RouteInfo systemRouteByDescriptorId = this.mSyncCallback.getSystemRouteByDescriptorId(this.mSystemRouteRecords.get(systemRouteRecord).mRouteDescriptorId);
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
                    final SystemRouteRecord systemRouteRecord2 = this.mSystemRouteRecords.get(systemRouteRecord);
                    final int volume = MediaRouterJellybean.RouteInfo.getVolume(o);
                    if (volume != systemRouteRecord2.mRouteDescriptor.getVolume()) {
                        systemRouteRecord2.mRouteDescriptor = new MediaRouteDescriptor.Builder(systemRouteRecord2.mRouteDescriptor).setVolume(volume).build();
                        this.publishRoutes();
                    }
                }
            }
        }
        
        @Override
        public void onSyncRouteAdded(final MediaRouter.RouteInfo routeInfo) {
            if (routeInfo.getProviderInstance() != this) {
                final Object userRoute = MediaRouterJellybean.createUserRoute(this.mRouterObj, this.mUserRouteCategoryObj);
                final UserRouteRecord userRouteRecord = new UserRouteRecord(routeInfo, userRoute);
                MediaRouterJellybean.RouteInfo.setTag(userRoute, userRouteRecord);
                MediaRouterJellybean.UserRouteInfo.setVolumeCallback(userRoute, this.mVolumeCallbackObj);
                this.updateUserRouteProperties(userRouteRecord);
                this.mUserRouteRecords.add(userRouteRecord);
                MediaRouterJellybean.addUserRoute(this.mRouterObj, userRoute);
            }
            else {
                final int systemRouteRecord = this.findSystemRouteRecord(MediaRouterJellybean.getSelectedRoute(this.mRouterObj, 8388611));
                if (systemRouteRecord >= 0 && this.mSystemRouteRecords.get(systemRouteRecord).mRouteDescriptorId.equals(routeInfo.getDescriptorId())) {
                    routeInfo.select();
                }
            }
        }
        
        @Override
        public void onSyncRouteChanged(final MediaRouter.RouteInfo routeInfo) {
            if (routeInfo.getProviderInstance() != this) {
                final int userRouteRecord = this.findUserRouteRecord(routeInfo);
                if (userRouteRecord >= 0) {
                    this.updateUserRouteProperties(this.mUserRouteRecords.get(userRouteRecord));
                }
            }
        }
        
        @Override
        public void onSyncRouteRemoved(final MediaRouter.RouteInfo routeInfo) {
            if (routeInfo.getProviderInstance() != this) {
                final int userRouteRecord = this.findUserRouteRecord(routeInfo);
                if (userRouteRecord >= 0) {
                    final UserRouteRecord userRouteRecord2 = this.mUserRouteRecords.remove(userRouteRecord);
                    MediaRouterJellybean.RouteInfo.setTag(userRouteRecord2.mRouteObj, null);
                    MediaRouterJellybean.UserRouteInfo.setVolumeCallback(userRouteRecord2.mRouteObj, null);
                    MediaRouterJellybean.removeUserRoute(this.mRouterObj, userRouteRecord2.mRouteObj);
                }
            }
        }
        
        @Override
        public void onSyncRouteSelected(final MediaRouter.RouteInfo routeInfo) {
            if (routeInfo.isSelected()) {
                if (routeInfo.getProviderInstance() != this) {
                    final int userRouteRecord = this.findUserRouteRecord(routeInfo);
                    if (userRouteRecord >= 0) {
                        this.selectRoute(this.mUserRouteRecords.get(userRouteRecord).mRouteObj);
                    }
                }
                else {
                    final int systemRouteRecordByDescriptorId = this.findSystemRouteRecordByDescriptorId(routeInfo.getDescriptorId());
                    if (systemRouteRecordByDescriptorId >= 0) {
                        this.selectRoute(this.mSystemRouteRecords.get(systemRouteRecordByDescriptorId).mRouteObj);
                    }
                }
            }
        }
        
        @Override
        public void onVolumeSetRequest(final Object o, final int n) {
            final UserRouteRecord userRouteRecord = this.getUserRouteRecord(o);
            if (userRouteRecord != null) {
                userRouteRecord.mRoute.requestSetVolume(n);
            }
        }
        
        @Override
        public void onVolumeUpdateRequest(final Object o, final int n) {
            final UserRouteRecord userRouteRecord = this.getUserRouteRecord(o);
            if (userRouteRecord != null) {
                userRouteRecord.mRoute.requestUpdateVolume(n);
            }
        }
        
        protected void publishRoutes() {
            final MediaRouteProviderDescriptor.Builder builder = new MediaRouteProviderDescriptor.Builder();
            for (int size = this.mSystemRouteRecords.size(), i = 0; i < size; ++i) {
                builder.addRoute(this.mSystemRouteRecords.get(i).mRouteDescriptor);
            }
            this.setDescriptor(builder.build());
        }
        
        protected void selectRoute(final Object o) {
            if (this.mSelectRouteWorkaround == null) {
                this.mSelectRouteWorkaround = new MediaRouterJellybean.SelectRouteWorkaround();
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
        
        protected void updateSystemRouteDescriptor(final SystemRouteRecord systemRouteRecord) {
            final MediaRouteDescriptor.Builder builder = new MediaRouteDescriptor.Builder(systemRouteRecord.mRouteDescriptorId, this.getRouteName(systemRouteRecord.mRouteObj));
            this.onBuildSystemRouteDescriptor(systemRouteRecord, builder);
            systemRouteRecord.mRouteDescriptor = builder.build();
        }
        
        protected void updateUserRouteProperties(final UserRouteRecord userRouteRecord) {
            MediaRouterJellybean.UserRouteInfo.setName(userRouteRecord.mRouteObj, userRouteRecord.mRoute.getName());
            MediaRouterJellybean.UserRouteInfo.setPlaybackType(userRouteRecord.mRouteObj, userRouteRecord.mRoute.getPlaybackType());
            MediaRouterJellybean.UserRouteInfo.setPlaybackStream(userRouteRecord.mRouteObj, userRouteRecord.mRoute.getPlaybackStream());
            MediaRouterJellybean.UserRouteInfo.setVolume(userRouteRecord.mRouteObj, userRouteRecord.mRoute.getVolume());
            MediaRouterJellybean.UserRouteInfo.setVolumeMax(userRouteRecord.mRouteObj, userRouteRecord.mRoute.getVolumeMax());
            MediaRouterJellybean.UserRouteInfo.setVolumeHandling(userRouteRecord.mRouteObj, userRouteRecord.mRoute.getVolumeHandling());
        }
        
        protected final class SystemRouteController extends RouteController
        {
            private final Object mRouteObj;
            
            public SystemRouteController(final Object mRouteObj) {
                this.mRouteObj = mRouteObj;
            }
            
            @Override
            public void onSetVolume(final int n) {
                MediaRouterJellybean.RouteInfo.requestSetVolume(this.mRouteObj, n);
            }
            
            @Override
            public void onUpdateVolume(final int n) {
                MediaRouterJellybean.RouteInfo.requestUpdateVolume(this.mRouteObj, n);
            }
        }
        
        protected static final class SystemRouteRecord
        {
            public MediaRouteDescriptor mRouteDescriptor;
            public final String mRouteDescriptorId;
            public final Object mRouteObj;
            
            public SystemRouteRecord(final Object mRouteObj, final String mRouteDescriptorId) {
                this.mRouteObj = mRouteObj;
                this.mRouteDescriptorId = mRouteDescriptorId;
            }
        }
        
        protected static final class UserRouteRecord
        {
            public final MediaRouter.RouteInfo mRoute;
            public final Object mRouteObj;
            
            public UserRouteRecord(final MediaRouter.RouteInfo mRoute, final Object mRouteObj) {
                this.mRoute = mRoute;
                this.mRouteObj = mRouteObj;
            }
        }
    }
    
    private static class JellybeanMr1Impl extends JellybeanImpl implements MediaRouterJellybeanMr1.Callback
    {
        private ActiveScanWorkaround mActiveScanWorkaround;
        private IsConnectingWorkaround mIsConnectingWorkaround;
        
        public JellybeanMr1Impl(final Context context, final SyncCallback syncCallback) {
            super(context, syncCallback);
        }
        
        @Override
        protected Object createCallbackObj() {
            return MediaRouterJellybeanMr1.createCallback((MediaRouterJellybeanMr1.Callback)this);
        }
        
        protected boolean isConnecting(final SystemRouteRecord systemRouteRecord) {
            if (this.mIsConnectingWorkaround == null) {
                this.mIsConnectingWorkaround = new MediaRouterJellybeanMr1.IsConnectingWorkaround();
            }
            return this.mIsConnectingWorkaround.isConnecting(systemRouteRecord.mRouteObj);
        }
        
        @Override
        protected void onBuildSystemRouteDescriptor(final SystemRouteRecord systemRouteRecord, final MediaRouteDescriptor.Builder builder) {
            super.onBuildSystemRouteDescriptor(systemRouteRecord, builder);
            if (!MediaRouterJellybeanMr1.RouteInfo.isEnabled(systemRouteRecord.mRouteObj)) {
                builder.setEnabled(false);
            }
            if (this.isConnecting(systemRouteRecord)) {
                builder.setConnecting(true);
            }
            final Display presentationDisplay = MediaRouterJellybeanMr1.RouteInfo.getPresentationDisplay(systemRouteRecord.mRouteObj);
            if (presentationDisplay != null) {
                builder.setPresentationDisplayId(presentationDisplay.getDisplayId());
            }
        }
        
        @Override
        public void onRoutePresentationDisplayChanged(final Object o) {
            final int systemRouteRecord = ((JellybeanImpl)this).findSystemRouteRecord(o);
            if (systemRouteRecord >= 0) {
                final SystemRouteRecord systemRouteRecord2 = this.mSystemRouteRecords.get(systemRouteRecord);
                final Display presentationDisplay = MediaRouterJellybeanMr1.RouteInfo.getPresentationDisplay(o);
                int displayId;
                if (presentationDisplay != null) {
                    displayId = presentationDisplay.getDisplayId();
                }
                else {
                    displayId = -1;
                }
                if (displayId != systemRouteRecord2.mRouteDescriptor.getPresentationDisplayId()) {
                    systemRouteRecord2.mRouteDescriptor = new MediaRouteDescriptor.Builder(systemRouteRecord2.mRouteDescriptor).setPresentationDisplayId(displayId).build();
                    ((JellybeanImpl)this).publishRoutes();
                }
            }
        }
        
        @Override
        protected void updateCallback() {
            super.updateCallback();
            if (this.mActiveScanWorkaround == null) {
                this.mActiveScanWorkaround = new MediaRouterJellybeanMr1.ActiveScanWorkaround(this.getContext(), this.getHandler());
            }
            final ActiveScanWorkaround mActiveScanWorkaround = this.mActiveScanWorkaround;
            int mRouteTypes;
            if (this.mActiveScan) {
                mRouteTypes = this.mRouteTypes;
            }
            else {
                mRouteTypes = 0;
            }
            mActiveScanWorkaround.setActiveScanRouteTypes(mRouteTypes);
        }
    }
    
    private static class JellybeanMr2Impl extends JellybeanMr1Impl
    {
        public JellybeanMr2Impl(final Context context, final SyncCallback syncCallback) {
            super(context, syncCallback);
        }
        
        @Override
        protected Object getDefaultRoute() {
            return MediaRouterJellybeanMr2.getDefaultRoute(this.mRouterObj);
        }
        
        @Override
        protected boolean isConnecting(final SystemRouteRecord systemRouteRecord) {
            return MediaRouterJellybeanMr2.RouteInfo.isConnecting(systemRouteRecord.mRouteObj);
        }
        
        @Override
        protected void onBuildSystemRouteDescriptor(final SystemRouteRecord systemRouteRecord, final MediaRouteDescriptor.Builder builder) {
            super.onBuildSystemRouteDescriptor(systemRouteRecord, builder);
            final CharSequence description = MediaRouterJellybeanMr2.RouteInfo.getDescription(systemRouteRecord.mRouteObj);
            if (description != null) {
                builder.setDescription(description.toString());
            }
        }
        
        @Override
        protected void selectRoute(final Object o) {
            MediaRouterJellybean.selectRoute(this.mRouterObj, 8388611, o);
        }
        
        @Override
        protected void updateCallback() {
            boolean b = true;
            if (this.mCallbackRegistered) {
                MediaRouterJellybean.removeCallback(this.mRouterObj, this.mCallbackObj);
            }
            this.mCallbackRegistered = true;
            final Object mRouterObj = this.mRouterObj;
            final int mRouteTypes = this.mRouteTypes;
            final Object mCallbackObj = this.mCallbackObj;
            if (!this.mActiveScan) {
                b = false;
            }
            MediaRouterJellybeanMr2.addCallback(mRouterObj, mRouteTypes, mCallbackObj, (b ? 1 : 0) | 0x2);
        }
        
        @Override
        protected void updateUserRouteProperties(final UserRouteRecord userRouteRecord) {
            super.updateUserRouteProperties(userRouteRecord);
            MediaRouterJellybeanMr2.UserRouteInfo.setDescription(userRouteRecord.mRouteObj, userRouteRecord.mRoute.getDescription());
        }
    }
    
    static class LegacyImpl extends SystemMediaRouteProvider
    {
        private static final ArrayList<IntentFilter> CONTROL_FILTERS;
        private static final int PLAYBACK_STREAM = 3;
        private final AudioManager mAudioManager;
        private int mLastReportedVolume;
        private final VolumeChangeReceiver mVolumeChangeReceiver;
        
        static {
            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addCategory("android.media.intent.category.LIVE_AUDIO");
            intentFilter.addCategory("android.media.intent.category.LIVE_VIDEO");
            (CONTROL_FILTERS = new ArrayList<IntentFilter>()).add(intentFilter);
        }
        
        public LegacyImpl(final Context context) {
            super(context);
            this.mLastReportedVolume = -1;
            this.mAudioManager = (AudioManager)context.getSystemService("audio");
            context.registerReceiver((BroadcastReceiver)(this.mVolumeChangeReceiver = new VolumeChangeReceiver()), new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
            this.publishRoutes();
        }
        
        private void publishRoutes() {
            final Resources resources = this.getContext().getResources();
            final int streamMaxVolume = this.mAudioManager.getStreamMaxVolume(3);
            this.mLastReportedVolume = this.mAudioManager.getStreamVolume(3);
            this.setDescriptor(new MediaRouteProviderDescriptor.Builder().addRoute(new MediaRouteDescriptor.Builder("DEFAULT_ROUTE", resources.getString(R.string.mr_system_route_name)).addControlFilters(LegacyImpl.CONTROL_FILTERS).setPlaybackStream(3).setPlaybackType(0).setVolumeHandling(1).setVolumeMax(streamMaxVolume).setVolume(this.mLastReportedVolume).build()).build());
        }
        
        @Override
        public RouteController onCreateRouteController(final String s) {
            if (s.equals("DEFAULT_ROUTE")) {
                return new DefaultRouteController();
            }
            return null;
        }
        
        final class DefaultRouteController extends RouteController
        {
            @Override
            public void onSetVolume(final int n) {
                LegacyImpl.this.mAudioManager.setStreamVolume(3, n, 0);
                LegacyImpl.this.publishRoutes();
            }
            
            @Override
            public void onUpdateVolume(final int n) {
                final int streamVolume = LegacyImpl.this.mAudioManager.getStreamVolume(3);
                if (Math.min(LegacyImpl.this.mAudioManager.getStreamMaxVolume(3), Math.max(0, streamVolume + n)) != streamVolume) {
                    LegacyImpl.this.mAudioManager.setStreamVolume(3, streamVolume, 0);
                }
                LegacyImpl.this.publishRoutes();
            }
        }
        
        final class VolumeChangeReceiver extends BroadcastReceiver
        {
            public static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
            public static final String EXTRA_VOLUME_STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";
            public static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
            
            public void onReceive(final Context context, final Intent intent) {
                if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION") && intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) == 3) {
                    final int intExtra = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", -1);
                    if (intExtra >= 0 && intExtra != LegacyImpl.this.mLastReportedVolume) {
                        LegacyImpl.this.publishRoutes();
                    }
                }
            }
        }
    }
    
    public interface SyncCallback
    {
        MediaRouter.RouteInfo getSystemRouteByDescriptorId(final String p0);
    }
}
