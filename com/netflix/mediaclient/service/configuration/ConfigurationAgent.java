// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import org.json.JSONObject;
import com.netflix.mediaclient.net.IpConnectivityPolicy;
import com.netflix.mediaclient.javabridge.transport.NativeTransport;
import com.netflix.mediaclient.media.bitrate.VideoBitrateRange;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.webclient.model.leafs.ConsolidatedLoggingSessionSpecification;
import org.json.JSONArray;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;
import com.netflix.mediaclient.service.configuration.esn.EsnProviderRegistry;
import com.netflix.mediaclient.nccp.NccpKeyStore;
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import java.util.Iterator;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.api.Api19Util;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ConfigData;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.Log;
import android.os.Handler;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent;

public class ConfigurationAgent extends ServiceAgent implements ConfigurationAgentInterface
{
    private static final int APM_USER_SESSION_TIMEOUT_SEC = 1800;
    private static final long CONFIG_REFRESH_DELAY_MS = 28800000L;
    private static final int DATA_REQUEST_TIMEOUT_MS = 10000;
    private static final float DISK_CACHE_SIZE_AS_PERCENTAGE_OF_AVLBLMEM = 0.25f;
    private static final int HIGH_MEM_THREAD_COUNT = 4;
    private static final float IMAGE_CACHE_SIZE_AS_PERCENTAGE_OF_MAX_HEAP = 0.5f;
    private static final KidsOnPhoneConfiguration KIDS_CONFIG_OVERRIDE;
    private static final String LEVEL_HIGH = "high";
    private static final String LEVEL_LOW = "low";
    private static final long LOW_MEMORY_CONFIG_THRESHOLD_IN_BYTES = 33554432L;
    private static final int LOW_MEM_THREAD_COUNT = 2;
    private static final int MAX_DISK_CACHE_SIZE_IN_BYTES = 26214400;
    private static final int MAX_VIDEO_BUFFERSIZE = 33554432;
    private static final long MILLISECONDS_PER_DAY = 86400000L;
    private static final long MINIMUM_IMAGE_CACHE_TTL = 1209600000L;
    private static final int MIN_DISK_CACHE_SIZE_IN_BYTES = 5242880;
    private static final int MIN_VIDEO_BUFFERSIZE = 4194304;
    private static final int RESOURCE_REQUEST_TIMEOUT_MS = 1000;
    private static final String TAG = "nf_configurationagent";
    private static final String VIDEO_PLAYREADY_H264_BPL30_DASH = "playready-h264bpl30-dash";
    private static final String VIDEO_PLAYREADY_H264_MPL30_DASH = "playready-h264mpl30-dash";
    private static final String VIDEO_PLAYREADY_H264_MPL31_DASH = "playready-h264mpl31-dash";
    private static final String sMemLevel;
    private AccountConfiguration mAccountConfigOverride;
    private int mAppVersionCode;
    private final ArrayList<ConfigAgentListener> mConfigAgentListeners;
    private int mConfigRefreshStatus;
    private ConfigurationWebClient mConfigurationWebClient;
    private DeviceConfiguration mDeviceConfigOverride;
    private int mDiskCacheSizeBytes;
    private DrmManager mDrmManager;
    private EsnProvider mESN;
    private EndpointRegistryProvider mEndpointRegistry;
    private int mImageCacheSizeBytes;
    private boolean mIsConfigRefreshInBackground;
    private boolean mNeedEsMigration;
    private boolean mNeedLogout;
    private String mSoftwareVersion;
    private Handler refreshHandler;
    private final Runnable refreshRunnable;
    
    static {
        KIDS_CONFIG_OVERRIDE = new KidsOnPhoneConfiguration() {
            @Override
            public LolomoImageType getLolomoImageType() {
                return LolomoImageType.HORIZONTAL;
            }
            
            @Override
            public ScrollBehavior getScrollBehavior() {
                return ScrollBehavior.UP_DOWN;
            }
            
            @Override
            public boolean isInKidsOnPhoneTest() {
                return false;
            }
            
            @Override
            public boolean isKidsOnPhoneEnabled() {
                return false;
            }
            
            @Override
            public boolean shouldShowKidsEntryInActionBar() {
                return false;
            }
            
            @Override
            public boolean shouldShowKidsEntryInGenreLomo() {
                return false;
            }
            
            @Override
            public boolean shouldShowKidsEntryInMenu() {
                return false;
            }
        };
        sMemLevel = getMemLevel();
    }
    
    public ConfigurationAgent() {
        this.mConfigAgentListeners = new ArrayList<ConfigAgentListener>();
        this.mIsConfigRefreshInBackground = false;
        this.mConfigRefreshStatus = -1;
        this.mAppVersionCode = -1;
        this.mSoftwareVersion = null;
        this.mNeedLogout = false;
        this.mNeedEsMigration = false;
        this.refreshRunnable = new Runnable() {
            @Override
            public void run() {
                Log.i("nf_configurationagent", "Refreshing config via runnable");
                ConfigurationAgent.this.fetchAccountConfigData(null);
                Log.i("nf_configurationagent", "Check if we should report ad id via runnable");
                final IClientLogging clientLogging = ConfigurationAgent.this.getService().getClientLogging();
                if (clientLogging == null) {
                    Log.e("nf_configurationagent", "CL is not available!");
                    return;
                }
                final AdvertiserIdLogging advertiserIdLogging = clientLogging.getAdvertiserIdLogging();
                if (advertiserIdLogging == null) {
                    Log.e("nf_configurationagent", "AD logger is not available!");
                    return;
                }
                advertiserIdLogging.sendAdvertiserId();
            }
        };
    }
    
    private void doRefreshConfig(final ConfigurationAgentWebCallback configurationAgentWebCallback) {
        this.mIsConfigRefreshInBackground = true;
        this.fetchConfigData(configurationAgentWebCallback);
    }
    
    private void fetchConfigData(final ConfigurationAgentWebCallback configurationAgentWebCallback) {
        this.prepareConfigWebClient();
        Log.d("nf_configurationagent", "fetchConfigData");
        this.mIsConfigRefreshInBackground = true;
        this.launchTask((FetchTask)new FetchConfigDataTask(configurationAgentWebCallback));
    }
    
    public static String getMemLevel() {
        String s = "high";
        final long maxMemory = Runtime.getRuntime().maxMemory();
        if (maxMemory <= 33554432L) {
            s = "low";
        }
        String s2 = s;
        if (AndroidUtils.getAndroidVersion() >= 19) {
            s2 = s;
            if (Api19Util.isLowRamDevice()) {
                Log.v("nf_configurationagent", "isLowRamDevice() is true");
                s2 = "low";
            }
        }
        if (Log.isLoggable("nf_configurationagent", 4)) {
            Log.i("nf_configurationagent", String.format("maxMemoryAllocated: %d, memLevel: %s", maxMemory, s2));
        }
        return s2;
    }
    
    private String getUiResolutionType() {
        return ConfigurationAgent.sMemLevel;
    }
    
    private void launchTask(final FetchTask fetchTask) {
        if (Log.isLoggable("nf_configurationagent", 2)) {
            Log.v("nf_configurationagent", "Launching task: " + fetchTask.getClass().getSimpleName());
        }
        if (this.mConfigurationWebClient.isSynchronous()) {
            new BackgroundTask().execute(fetchTask);
            return;
        }
        fetchTask.run();
    }
    
    private void notifyConfigRefreshed() {
        this.getMainHandler().post((Runnable)new Runnable() {
            @Override
            public void run() {
                synchronized (ConfigurationAgent.this) {
                    Log.d("nf_configurationagent", "Invoking ConfigAgentListeners.");
                    ConfigurationAgent.this.mIsConfigRefreshInBackground = false;
                    final Iterator<ConfigAgentListener> iterator = ConfigurationAgent.this.mConfigAgentListeners.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onConfigRefreshed(ConfigurationAgent.this.mConfigRefreshStatus);
                    }
                }
                ConfigurationAgent.this.mConfigAgentListeners.clear();
            }
            // monitorexit(configurationAgent)
        });
    }
    
    private void persistConfigOverride(final ConfigData configData) {
        if (Log.isLoggable("nf_configurationagent", 3)) {
            Log.d("nf_configurationagent", String.format("persistConfigOverride configData %s", configData.toString()));
        }
        this.mDeviceConfigOverride.persistDeviceConfigOverride(configData.getDeviceConfig());
        this.mAccountConfigOverride.persistStreamingOverride(configData.getStreamingConfig());
        this.mAccountConfigOverride.persistAccountConfigOverride(configData.getAccountConfig());
    }
    
    private void prepareConfigWebClient() {
        this.mEndpointRegistry.setUserAgentInterface(this.getUserAgent());
        this.mEndpointRegistry.setConfigurationAgentInterface(this.getConfigurationAgent());
        if (this.mConfigurationWebClient == null) {
            this.mConfigurationWebClient = ConfigurationWebClientFactory.create(this.getService(), this.getResourceFetcher().getApiNextWebClient());
        }
    }
    
    public static boolean shouldUseLowMemConfig() {
        return "low".equals(ConfigurationAgent.sMemLevel);
    }
    
    public void addConfigAgentListener(final ConfigAgentListener configAgentListener) {
        // monitorenter(this)
        if (configAgentListener == null) {
            return;
        }
        try {
            this.mConfigAgentListeners.add(configAgentListener);
        }
        finally {
        }
        // monitorexit(this)
    }
    
    @Override
    public void clearAccountConfigData() {
        this.mAccountConfigOverride.clear();
    }
    
    @Override
    public void destroy() {
        super.destroy();
        if (this.refreshHandler != null) {
            this.refreshHandler.removeCallbacks(this.refreshRunnable);
        }
        if (this.mDeviceConfigOverride != null) {
            this.mDeviceConfigOverride.clear();
        }
        if (this.mConfigAgentListeners != null) {
            this.mConfigAgentListeners.clear();
        }
        if (this.mDrmManager != null) {
            this.mDrmManager.destroy();
        }
        if (this.mESN != null) {
            this.mESN.destroy();
        }
    }
    
    @Override
    protected void doInit() {
        this.mNeedLogout = false;
        this.mNeedEsMigration = false;
        this.mImageCacheSizeBytes = Math.round(Runtime.getRuntime().maxMemory() * 0.5f);
        Log.i("nf_configurationagent", "Use low mem config: " + shouldUseLowMemConfig());
        this.mDiskCacheSizeBytes = PreferenceUtils.getIntPref(this.getContext(), "disk_cache_size", 0);
        if (this.mDiskCacheSizeBytes == 0) {
            final long availableInternalMemory = AndroidUtils.getAvailableInternalMemory();
            this.mDiskCacheSizeBytes = (int)Math.min(availableInternalMemory * 0.25f, 2.62144E7f);
            this.mDiskCacheSizeBytes = Math.max(this.mDiskCacheSizeBytes, 5242880);
            PreferenceUtils.putIntPref(this.getContext(), "disk_cache_size", this.mDiskCacheSizeBytes);
            if (Log.isLoggable("nf_configurationagent", 3)) {
                Log.d("nf_configurationagent", "Available disk space in bytes = " + availableInternalMemory + " Saving disk Cache Size = " + this.mDiskCacheSizeBytes);
            }
        }
        this.mAppVersionCode = AndroidManifestUtils.getVersionCode(this.getContext());
        if (Log.isLoggable("nf_configurationagent", 4)) {
            Log.i("nf_configurationagent", "Current app version code = " + this.mAppVersionCode);
        }
        this.mSoftwareVersion = AndroidManifestUtils.getVersionName(this.getContext());
        if (Log.isLoggable("nf_configurationagent", 4)) {
            Log.i("nf_configurationagent", "Current softwareVersion = " + this.mSoftwareVersion);
        }
        final DeviceModel deviceModel = new DeviceModel(this.mAppVersionCode, this.getContext());
        this.mDeviceConfigOverride = new DeviceConfiguration(this.getContext());
        this.mAccountConfigOverride = new AccountConfiguration(this.getContext());
        this.mEndpointRegistry = new EndpointRegistryProvider(this.getContext(), deviceModel, this.isDeviceHd(), this.mDeviceConfigOverride.getImageRepository(), this.getUiResolutionType());
        final int loadDeviceConfigOverrides = this.mDeviceConfigOverride.loadDeviceConfigOverrides(this.mEndpointRegistry.getDeviceConfigUrl());
        if (loadDeviceConfigOverrides != 0) {
            this.initCompleted(loadDeviceConfigOverrides);
            return;
        }
        final DrmManager.DrmReadyCallback drmReadyCallback = new DrmManager.DrmReadyCallback() {
            @Override
            public void drmError(final int n) {
                if (Log.isLoggable("nf_configurationagent", 6)) {
                    Log.d("nf_configurationagent", "DRM failed to initialize, Error code: " + n);
                }
                ConfigurationAgent.this.initCompleted(n);
            }
            
            @Override
            public void drmReady() {
                Log.d("nf_configurationagent", "DRM manager is ready");
                if (DrmManagerRegistry.isDrmSystemChanged()) {
                    ConfigurationAgent.this.mNeedEsMigration = true;
                }
                ConfigurationAgent.this.initCompleted(0);
            }
        };
        NccpKeyStore.init(this.getContext());
        this.mDrmManager = DrmManagerRegistry.createDrmManager(this.getContext(), this, this.getUserAgent(), this.getService().getClientLogging().getErrorLogging(), drmReadyCallback);
        this.mESN = EsnProviderRegistry.createESN(this.getContext(), this.mDrmManager, this);
        Log.d("nf_configurationagent", "Inject ESN to PlayerTypeFactory");
        PlayerTypeFactory.initialize(this.mESN);
        this.mDrmManager.init();
    }
    
    @Override
    public void esnMigrationComplete() {
        this.mNeedEsMigration = false;
    }
    
    @Override
    public void fetchAccountConfigData(final ConfigurationAgentWebCallback configurationAgentWebCallback) {
        if (this.refreshHandler == null) {
            this.refreshHandler = new Handler();
        }
        this.refreshConfig(configurationAgentWebCallback, null);
    }
    
    @Override
    public ApiEndpointRegistry getApiEndpointRegistry() {
        return this.mEndpointRegistry;
    }
    
    @Override
    public int getApmUserSessionDurationInSeconds() {
        final int userSessionDurationInSeconds = this.mDeviceConfigOverride.getUserSessionDurationInSeconds();
        if (userSessionDurationInSeconds > 0) {
            return userSessionDurationInSeconds;
        }
        return 1800;
    }
    
    @Override
    public int getAppVersionCode() {
        return this.mAppVersionCode;
    }
    
    @Override
    public int getBitrateCap() {
        return BitrateRangeFactory.getBitrateCap(this.getContext());
    }
    
    @Override
    public JSONArray getCastWhiteList() {
        return this.mAccountConfigOverride.getCastWhitelist();
    }
    
    @Override
    public ConsolidatedLoggingSessionSpecification getConsolidatedLoggingSessionSpecification(final String s) {
        return this.mDeviceConfigOverride.getConsolidatedLoggingSessionSpecification(s);
    }
    
    @Override
    public PlayerType getCurrentPlayerType() {
        return PlayerTypeFactory.getCurrentType(this.getContext());
    }
    
    @Override
    public int getDataRequestTimeout() {
        return 10000;
    }
    
    @Override
    public DeviceCategory getDeviceCategory() {
        final DeviceCategory category = this.mDeviceConfigOverride.getCategory();
        if (category != null) {
            if (Log.isLoggable("nf_configurationagent", 3)) {
                Log.d("nf_configurationagent", "Device category is overriden by configuration server: " + category);
            }
            return category;
        }
        if (DeviceUtils.isTabletByContext(this.getContext())) {
            return DeviceCategory.TABLET;
        }
        return DeviceCategory.PHONE;
    }
    
    public VideoBitrateRange[] getDeviceVideoProfiles() {
        final String[] supportedVideoProfiles = NativeTransport.getSupportedVideoProfiles();
        final VideoBitrateRange[] array = new VideoBitrateRange[supportedVideoProfiles.length];
        final boolean b = !this.isTablet();
        final PlayerType currentPlayerType = this.getCurrentPlayerType();
        if (Log.isLoggable("nf_configurationagent", 3)) {
            Log.d("VideoBitrateRange", "Device is phone: " + b + ",PlayerType: " + currentPlayerType);
        }
        int n = 0;
        for (int length = supportedVideoProfiles.length, i = 0; i < length; ++i) {
            final String s = supportedVideoProfiles[i];
            int n2 = 4800;
            if (s.equalsIgnoreCase("playready-h264bpl30-dash")) {
                n2 = 1350;
                Log.d("VideoBitrateRange", "player support BP30");
            }
            else if (s.equalsIgnoreCase("playready-h264mpl30-dash")) {
                n2 = 1750;
                Log.d("VideoBitrateRange", "player support MP30");
            }
            else if (s.equalsIgnoreCase("playready-h264mpl31-dash")) {
                n2 = 3600;
                Log.d("VideoBitrateRange", "player support MP31");
            }
            int n3 = n2;
            if (!PlayerTypeFactory.isJPlayer2(currentPlayerType)) {
                n3 = n2;
                if (b) {
                    if (s.equalsIgnoreCase("playready-h264bpl30-dash")) {
                        n3 = 500;
                    }
                    else {
                        n3 = n2;
                        if (s.equalsIgnoreCase("playready-h264mpl30-dash")) {
                            n3 = 750;
                        }
                    }
                }
            }
            if (Log.isLoggable("nf_configurationagent", 3)) {
                Log.d("VideoBitrateRange", "Profile: " + s + ", min: " + 0 + ", max: " + n3);
            }
            array[n] = new VideoBitrateRange(0, n3, s);
            ++n;
        }
        return array;
    }
    
    @Override
    public int getDiskCacheSizeBytes() {
        return this.mDiskCacheSizeBytes;
    }
    
    @Override
    public DrmManager getDrmManager() {
        return this.mDrmManager;
    }
    
    @Override
    public EsnProvider getEsnProvider() {
        return this.mESN;
    }
    
    @Override
    public long getImageCacheMinimumTtl() {
        return 1209600000L;
    }
    
    @Override
    public int getImageCacheSizeBytes() {
        return this.mImageCacheSizeBytes;
    }
    
    @Override
    public IpConnectivityPolicy getIpConnectivityPolicy() {
        return this.mDeviceConfigOverride.getIpConnectivityPolicy();
    }
    
    @Override
    public JSONObject getJPlayerConfig() {
        return this.mAccountConfigOverride.getJPlayerConfig();
    }
    
    @Override
    public KidsOnPhoneConfiguration getKidsOnPhoneConfiguration() {
        return ConfigurationAgent.KIDS_CONFIG_OVERRIDE;
    }
    
    @Override
    public JSONArray getMdxBlackListTargets() {
        return this.mAccountConfigOverride.getMdxBlacklist();
    }
    
    @Override
    public int getPresentationTrackingAggregationSize() {
        return this.mDeviceConfigOverride.getPTAggregationSize();
    }
    
    @Override
    public int getResFetcherThreadPoolSize() {
        if (shouldUseLowMemConfig()) {
            return 2;
        }
        return 4;
    }
    
    @Override
    public int getResourceRequestTimeout() {
        return 1000;
    }
    
    public SignUpConfiguration getSignUpConfiguration() {
        return this.mDeviceConfigOverride.getSignUpConfiguration();
    }
    
    @Override
    public String getSoftwareVersion() {
        return this.mSoftwareVersion;
    }
    
    @Override
    public String getStreamingQoe() {
        return this.mAccountConfigOverride.getStreamingQoe();
    }
    
    @Override
    public SubtitleConfiguration getSubtitleConfiguration() {
        return this.mDeviceConfigOverride.getSubtitleConfiguration();
    }
    
    @Override
    public VideoBitrateRange[] getVideoBitrateRange() {
        final VideoBitrateRange[] deviceVideoProfiles = this.getDeviceVideoProfiles();
        final int bitrateCap = this.getBitrateCap();
        final VideoBitrateRange[] array = new VideoBitrateRange[deviceVideoProfiles.length];
        int n = 0;
        for (int length = deviceVideoProfiles.length, i = 0; i < length; ++i) {
            final VideoBitrateRange videoBitrateRange = deviceVideoProfiles[i];
            int maximal;
            if ((maximal = videoBitrateRange.getMaximal()) > Integer.MAX_VALUE) {
                maximal = Integer.MAX_VALUE;
                Log.d("nf_configurationagent", "use limit");
            }
            int n2 = maximal;
            if (bitrateCap > 0 && (n2 = maximal) > bitrateCap) {
                n2 = bitrateCap;
                Log.d("nf_configurationagent", "use bitratecap");
            }
            array[n] = new VideoBitrateRange(videoBitrateRange.getMinimal(), n2, videoBitrateRange.getProfile());
            if (Log.isLoggable("nf_configurationagent", 3)) {
                Log.d("nf_configurationagent", " VideoBitrateRange[" + n + "] " + array[n]);
            }
            ++n;
        }
        return array;
    }
    
    @Override
    public int getVideoBufferSize() {
        final int videoBufferSize = this.mAccountConfigOverride.getVideoBufferSize();
        int n;
        if (videoBufferSize < 4194304 || (n = videoBufferSize) > 33554432) {
            final int n2 = n = 0;
            if (Log.isLoggable("nf_configurationagent", 3)) {
                Log.d("nf_configurationagent", " Invalid VideoBufferSize " + 0);
                n = n2;
            }
        }
        return n;
    }
    
    public boolean isAppVersionObsolete() {
        final int appMinimalVersion = this.mDeviceConfigOverride.getAppMinimalVersion();
        if (Log.isLoggable("nf_configurationagent", 3)) {
            Log.i("nf_configurationagent", "minimalVersion = " + appMinimalVersion + " appVersionCode = " + this.mAppVersionCode + " so isAppVersionObsolete = " + (this.mAppVersionCode < appMinimalVersion));
        }
        return this.mAppVersionCode < appMinimalVersion;
    }
    
    public boolean isAppVersionRecommended() {
        final int appRecommendedVersion = this.mDeviceConfigOverride.getAppRecommendedVersion();
        if (Log.isLoggable("nf_configurationagent", 3)) {
            Log.i("nf_configurationagent", "recommendedVersion = " + appRecommendedVersion + " appVersionCode = " + this.mAppVersionCode + " so isAppVersionRecommended = " + (this.mAppVersionCode >= appRecommendedVersion));
        }
        return this.mAppVersionCode >= appRecommendedVersion;
    }
    
    @Override
    public boolean isCurrentDrmWidevine() {
        return DrmManagerRegistry.isCurrentDrmWidevine();
    }
    
    public boolean isDeviceHd() {
        return DrmManagerRegistry.drmSupportsHd();
    }
    
    @Override
    public boolean isDeviceLowMem() {
        return getMemLevel().equals("low");
    }
    
    @Override
    public boolean isDisableMdx() {
        return this.mDeviceConfigOverride.isDisableMdx();
    }
    
    @Override
    public boolean isDisableWebsocket() {
        return this.mDeviceConfigOverride.isDisableWebsocket();
    }
    
    @Override
    public boolean isDisableWidevine() {
        return this.mDeviceConfigOverride.isDisableWidevine();
    }
    
    @Override
    public boolean isEnableCast() {
        return this.mAccountConfigOverride.getCastEnabled();
    }
    
    @Override
    public boolean isEsnMigrationRequired() {
        return this.mNeedEsMigration;
    }
    
    @Override
    public boolean isLogoutRequired() {
        return this.mNeedLogout;
    }
    
    public boolean isTablet() {
        return DeviceCategory.TABLET.equals(this.getDeviceCategory());
    }
    
    public void refreshConfig(final ConfigurationAgentWebCallback configurationAgentWebCallback, final ConfigAgentListener configAgentListener) {
        // monitorenter(this)
        Label_0015: {
            if (configAgentListener == null) {
                break Label_0015;
            }
            try {
                this.mConfigAgentListeners.add(configAgentListener);
                if (!this.mIsConfigRefreshInBackground) {
                    Log.i("nf_configurationagent", "Starting a config refresh in the background.");
                    this.doRefreshConfig(configurationAgentWebCallback);
                }
                else {
                    Log.i("nf_configurationagent", "Ignoring request to refreshConfig because one is on-going.");
                }
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    public void removeConfigAgentListener(final ConfigAgentListener configAgentListener) {
        // monitorenter(this)
        if (configAgentListener == null) {
            return;
        }
        try {
            this.mConfigAgentListeners.remove(configAgentListener);
        }
        finally {
        }
        // monitorexit(this)
    }
    
    @Override
    public void userAgentLogoutComplete() {
        this.mNeedLogout = false;
    }
    
    public interface ConfigAgentListener
    {
        void onConfigRefreshed(final int p0);
    }
    
    private class FetchConfigDataTask extends FetchTask
    {
        private final ConfigurationAgentWebCallback webClientCallback;
        
        public FetchConfigDataTask(final ConfigurationAgentWebCallback configurationAgentWebCallback) {
            super(configurationAgentWebCallback);
            this.webClientCallback = new SimpleConfigurationAgentWebCallback() {
                @Override
                public void onConfigDataFetched(final ConfigData configData, final int n) {
                    Log.d("nf_configurationagent", String.format("onConfigDataFetched statusCode=%d", n));
                    ConfigurationAgent.this.mConfigRefreshStatus = -7;
                    if (n == 0 && configData != null) {
                        ConfigurationAgent.this.persistConfigOverride(configData);
                        ConfigurationAgent.this.mConfigRefreshStatus = 0;
                    }
                    ConfigurationAgent.this.mIsConfigRefreshInBackground = false;
                    ConfigurationAgent.this.refreshHandler.postDelayed(ConfigurationAgent.this.refreshRunnable, 28800000L);
                    ConfigurationAgent.this.notifyConfigRefreshed();
                    if (((FetchTask)FetchConfigDataTask.this).getCallback() != null) {
                        ((FetchTask)FetchConfigDataTask.this).getCallback().onConfigDataFetched(configData, ConfigurationAgent.this.mConfigRefreshStatus);
                    }
                }
            };
        }
        
        @Override
        public void run() {
            ConfigurationAgent.this.mConfigurationWebClient.fetchConfigData(this.webClientCallback);
        }
    }
    
    private abstract static class FetchTask implements Runnable
    {
        private final ConfigurationAgentWebCallback callback;
        
        public FetchTask(final ConfigurationAgentWebCallback callback) {
            this.callback = callback;
        }
        
        protected ConfigurationAgentWebCallback getCallback() {
            return this.callback;
        }
    }
}
