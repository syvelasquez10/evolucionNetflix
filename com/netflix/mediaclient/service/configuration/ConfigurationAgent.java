// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import android.view.Display;
import android.util.DisplayMetrics;
import android.hardware.display.DisplayManager;
import com.netflix.mediaclient.media.VideoResolutionRange;
import org.json.JSONObject;
import com.netflix.mediaclient.net.IpConnectivityPolicy;
import com.netflix.mediaclient.service.webclient.model.leafs.ErrorLoggingSpecification;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.webclient.model.leafs.ConsolidatedLoggingSessionSpecification;
import com.netflix.mediaclient.service.webclient.model.leafs.BreadcrumbLoggingSpecification;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;
import com.netflix.mediaclient.service.configuration.esn.EsnProviderRegistry;
import com.netflix.mediaclient.service.configuration.drm.DrmManager$DrmReadyCallback;
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import com.netflix.mediaclient.nccp.NccpKeyStore;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Locale;
import java.io.IOException;
import com.netflix.mediaclient.service.configuration.volley.FetchConfigDataRequest;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.api.Api19Util;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.ConfigData;
import com.netflix.mediaclient.service.NetflixService;
import android.os.Handler;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;
import com.netflix.mediaclient.android.app.Status;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;

@SuppressLint({ "InlinedApi" })
public class ConfigurationAgent extends ServiceAgent implements ServiceAgent$ConfigurationAgentInterface
{
    private static final int APM_USER_SESSION_TIMEOUT_SEC = 1800;
    private static final long CONFIG_REFRESH_DELAY_MS = 28800000L;
    private static final int DATA_REQUEST_TIMEOUT_MS = 10000;
    public static final int DEFAULT_IMAGE_CACHE_SIZE_BYTES;
    private static final float DISK_CACHE_SIZE_AS_PERCENTAGE_OF_AVLBLMEM = 0.25f;
    private static final KidsOnPhoneConfiguration DUMMY_KIDS_CONFIG;
    private static final KubrickConfiguration DUMMY_KUBRICK_CONFIG;
    private static final int HIGH_MEM_THREAD_COUNT = 4;
    private static final float IMAGE_CACHE_SIZE_AS_PERCENTAGE_OF_MAX_HEAP = 0.5f;
    private static final String LEVEL_HIGH = "high";
    private static final String LEVEL_LOW = "low";
    private static final long LOW_MEMORY_CONFIG_THRESHOLD_IN_BYTES = 33554432L;
    private static final int LOW_MEM_THREAD_COUNT = 2;
    private static final int MAX_DISK_CACHE_SIZE_IN_BYTES = 26214400;
    private static final int MAX_VIDEO_BUFFERSIZE = 33554432;
    private static final long MILLISECONDS_PER_DAY = 86400000L;
    public static final long MINIMUM_IMAGE_CACHE_TTL = 1209600000L;
    private static final int MIN_DISK_CACHE_SIZE_IN_BYTES = 5242880;
    private static final int MIN_VIDEO_BUFFERSIZE = 4194304;
    private static final boolean OVERRIDE_SERVER_CONFIG_FOR_KIDS_ON_PHONE = false;
    private static final boolean OVERRIDE_SERVER_CONFIG_FOR_KUBRICK = false;
    public static final int RESOURCE_REQUEST_TIMEOUT_MS = 1000;
    private static final String TAG = "nf_configurationagent";
    private static final String sMemLevel;
    private AccountConfiguration mAccountConfigOverride;
    private int mAppVersionCode;
    private final ArrayList<ConfigurationAgent$ConfigAgentListener> mConfigAgentListeners;
    private Status mConfigRefreshStatus;
    private ConfigurationWebClient mConfigurationWebClient;
    private DeviceConfiguration mDeviceConfigOverride;
    private int mDiskCacheSizeBytes;
    private DrmManager mDrmManager;
    private EsnProvider mESN;
    private EndpointRegistryProvider mEndpointRegistry;
    private boolean mIsConfigRefreshInBackground;
    private final MdxConfiguration mMdxConfiguration;
    private boolean mNeedEsMigration;
    private boolean mNeedLogout;
    private final PlaybackConfiguration mPlaybackConfiguration;
    private String mSoftwareVersion;
    private StreamingConfiguration mStreamingConfigOverride;
    private Handler refreshHandler;
    private final Runnable refreshRunnable;
    
    static {
        DUMMY_KUBRICK_CONFIG = new ConfigurationAgent$1();
        DUMMY_KIDS_CONFIG = new ConfigurationAgent$2();
        DEFAULT_IMAGE_CACHE_SIZE_BYTES = (int)(Runtime.getRuntime().maxMemory() * 0.5f);
        sMemLevel = computeMemLevel();
    }
    
    public ConfigurationAgent() {
        this.mConfigAgentListeners = new ArrayList<ConfigurationAgent$ConfigAgentListener>();
        this.mIsConfigRefreshInBackground = false;
        this.mAppVersionCode = -1;
        this.mSoftwareVersion = null;
        this.mNeedLogout = false;
        this.mNeedEsMigration = false;
        this.refreshRunnable = new ConfigurationAgent$5(this);
        this.mMdxConfiguration = new ConfigurationAgent$6(this);
        this.mPlaybackConfiguration = new ConfigurationAgent$7(this);
    }
    
    private ImageResolutionClass computeImageResolutionClass(final Context context) {
        if (ConfigurationAgent.sMemLevel == "low") {
            Log.v("nf_configurationagent", "Device is low memory category - use low resolution images");
            return ImageResolutionClass.LOW;
        }
        if (DeviceUtils.getScreenResolutionDpi(context) <= 160 && DeviceUtils.getScreenSizeCategory(context) == 3) {
            Log.v("nf_configurationagent", "Device is a low-res, small tablet - use low resolution images");
            return ImageResolutionClass.LOW;
        }
        Log.v("nf_configurationagent", "Using high resolution images");
        return ImageResolutionClass.HIGH;
    }
    
    private static String computeMemLevel() {
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
    
    private void doRefreshConfig(final ConfigurationAgentWebCallback configurationAgentWebCallback) {
        this.mIsConfigRefreshInBackground = true;
        this.fetchConfigData(configurationAgentWebCallback);
    }
    
    private void fetchConfigData(final ConfigurationAgentWebCallback configurationAgentWebCallback) {
        this.prepareConfigWebClient();
        Log.d("nf_configurationagent", "fetchConfigData");
        this.mIsConfigRefreshInBackground = true;
        this.launchTask(new ConfigurationAgent$FetchConfigDataTask(this, configurationAgentWebCallback));
    }
    
    private Status fetchConfigSynchronouslyOnAppStart(String s) {
        Log.d("nf_configurationagent", "Need to fetchdeviceConfig synchronously ");
        ConfigData configString;
        try {
            if (Log.isLoggable("nf_configurationagent", 3)) {
                Log.d("nf_configurationagent", String.format("configurationUrl %s", s));
            }
            s = StringUtils.getRemoteDataAsString(s);
            if (Log.isLoggable("nf_configurationagent", 3)) {
                Log.d("nf_configurationagent", String.format("Device config data=%s", s));
            }
            configString = FetchConfigDataRequest.parseConfigString(s);
            if (configString.deviceConfig == null) {
                throw new IOException();
            }
        }
        catch (Exception ex) {
            s = ex.toString().toLowerCase(Locale.US);
            Log.e("nf_configurationagent", "Could not fetch configuration! " + s);
            if (s.contains("could not validate certificate") || s.contains("sslhandshakeexception")) {
                return CommonStatus.HTTP_SSL_DATE_TIME_ERROR;
            }
            return CommonStatus.CONFIG_DOWNLOAD_FAILED;
        }
        this.mDeviceConfigOverride.persistDeviceConfigOverride(configString.getDeviceConfig());
        this.mStreamingConfigOverride.persistStreamingOverride(configString.getStreamingConfig());
        return CommonStatus.OK;
    }
    
    private int getMaxResolutionConfigured() {
        int videoResolutionOverride;
        if ((videoResolutionOverride = this.mDeviceConfigOverride.getVideoResolutionOverride()) <= 0) {
            videoResolutionOverride = Integer.MAX_VALUE;
        }
        return videoResolutionOverride;
    }
    
    public static String getMemLevel() {
        return ConfigurationAgent.sMemLevel;
    }
    
    private void launchTask(final ConfigurationAgent$FetchTask configurationAgent$FetchTask) {
        if (Log.isLoggable("nf_configurationagent", 2)) {
            Log.v("nf_configurationagent", "Launching task: " + configurationAgent$FetchTask.getClass().getSimpleName());
        }
        if (this.mConfigurationWebClient.isSynchronous()) {
            new BackgroundTask().execute(configurationAgent$FetchTask);
            return;
        }
        configurationAgent$FetchTask.run();
    }
    
    private Status loadConfigOverridesOnAppStart(final String s) {
        final NetflixImmutableStatus ok = CommonStatus.OK;
        if (this.mDeviceConfigOverride.isDeviceConfigInCache() && this.mStreamingConfigOverride.isStreamingConfigInCache()) {
            Log.d("nf_configurationagent", "Device Config & Streaming Config in cache... proceed!");
            return ok;
        }
        return this.fetchConfigSynchronouslyOnAppStart(s);
    }
    
    private void notifyConfigRefreshed() {
        this.getMainHandler().post((Runnable)new ConfigurationAgent$4(this));
    }
    
    private void persistConfigOverride(final ConfigData configData) {
        if (Log.isLoggable("nf_configurationagent", 3)) {
            Log.d("nf_configurationagent", String.format("persistConfigOverride configData %s", configData.toString()));
        }
        this.mDeviceConfigOverride.persistDeviceConfigOverride(configData.getDeviceConfig());
        this.mStreamingConfigOverride.persistStreamingOverride(configData.getStreamingConfig());
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
    
    public void addConfigAgentListener(final ConfigurationAgent$ConfigAgentListener configurationAgent$ConfigAgentListener) {
        // monitorenter(this)
        if (configurationAgent$ConfigAgentListener == null) {
            return;
        }
        try {
            this.mConfigAgentListeners.add(configurationAgent$ConfigAgentListener);
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
        this.mSoftwareVersion = AndroidManifestUtils.getVersion(this.getContext());
        if (Log.isLoggable("nf_configurationagent", 4)) {
            Log.i("nf_configurationagent", "Current softwareVersion = " + this.mSoftwareVersion);
        }
        final DeviceModel deviceModel = new DeviceModel(this.mAppVersionCode, this.getContext());
        this.mDeviceConfigOverride = new DeviceConfiguration(this.getContext());
        this.mAccountConfigOverride = new AccountConfiguration(this.getContext());
        this.mStreamingConfigOverride = new StreamingConfiguration(this.getContext());
        this.mEndpointRegistry = new EndpointRegistryProvider(this.getContext(), deviceModel, this.isDeviceHd(), this.mDeviceConfigOverride.getImageRepository(), this.computeImageResolutionClass(this.getContext()));
        final Status loadConfigOverridesOnAppStart = this.loadConfigOverridesOnAppStart(this.mEndpointRegistry.getAppStartConfigUrl());
        if (loadConfigOverridesOnAppStart.isError()) {
            this.initCompleted(loadConfigOverridesOnAppStart);
            return;
        }
        final ConfigurationAgent$3 configurationAgent$3 = new ConfigurationAgent$3(this);
        NccpKeyStore.init(this.getContext());
        this.mDrmManager = DrmManagerRegistry.createDrmManager(this.getContext(), this, this.getUserAgent(), this.getService().getClientLogging().getErrorLogging(), configurationAgent$3);
        this.mESN = EsnProviderRegistry.createESN(this.getContext(), this.mDrmManager, this);
        Log.d("nf_configurationagent", "Inject ESN to PlayerTypeFactory");
        PlayerTypeFactory.initialize(this.mESN);
        this.mDrmManager.init();
    }
    
    @Override
    public boolean enableHTTPSAuth() {
        return this.mAccountConfigOverride.enableHTTPSAuth();
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
    public BreadcrumbLoggingSpecification getBreadcrumbLoggingSpecification() {
        return this.mDeviceConfigOverride.getBreadcrumbLoggingSpecification();
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
    
    @Override
    public int getDiskCacheSizeBytes() {
        return this.mDiskCacheSizeBytes;
    }
    
    @Override
    public DrmManager getDrmManager() {
        return this.mDrmManager;
    }
    
    @Override
    public ErrorLoggingSpecification getErrorLoggingSpecification() {
        return this.mDeviceConfigOverride.getErrorLoggingSpecification();
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
        return ConfigurationAgent.DEFAULT_IMAGE_CACHE_SIZE_BYTES;
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
        return this.mAccountConfigOverride.getKidsOnPhoneConfiguration();
    }
    
    @Override
    public KubrickConfiguration getKubrickConfiguration() {
        return this.mAccountConfigOverride.getKubrickConfig();
    }
    
    @Override
    public MdxConfiguration getMdxConfiguration() {
        return this.mMdxConfiguration;
    }
    
    @Override
    public PlaybackConfiguration getPlaybackConfiguration() {
        return this.mPlaybackConfiguration;
    }
    
    @Override
    public String getPreAppPartnerExperience() {
        return this.mAccountConfigOverride.getPreAppPartnerExperience();
    }
    
    @Override
    public String getPreAppWidgetExperience() {
        return this.mAccountConfigOverride.getPreAppWidgetExperience();
    }
    
    @Override
    public int getPresentationTrackingAggregationSize() {
        return this.mDeviceConfigOverride.getPTAggregationSize();
    }
    
    @Override
    public int getRateLimitForGcmBrowseEvents() {
        return this.mDeviceConfigOverride.getRateLimitForGcmBrowseEvents();
    }
    
    @Override
    public int getRateLimitForNListChangeEvents() {
        return this.mDeviceConfigOverride.getRateLimitForNListChangeEvents();
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
    
    @Override
    public int getSearchTest() {
        return this.mAccountConfigOverride.getSearchTest();
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
        return this.mStreamingConfigOverride.getStreamingQoe();
    }
    
    @Override
    public SubtitleConfiguration getSubtitleConfiguration() {
        return this.mDeviceConfigOverride.getSubtitleConfiguration();
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
    
    @SuppressLint({ "NewApi" })
    @Override
    public VideoResolutionRange getVideoResolutionRange() {
        int maxResolutionConfigured = this.getMaxResolutionConfigured();
        if (!this.isTablet()) {
            maxResolutionConfigured = maxResolutionConfigured;
            if (!PlayerTypeFactory.isJPlayer2(this.getCurrentPlayerType())) {
                final int n = maxResolutionConfigured = 384;
                if (Log.isLoggable("nf_configurationagent", 3)) {
                    Log.d("nf_configurationagent", "apply phone restriction, max height " + 384);
                    maxResolutionConfigured = n;
                }
            }
        }
        if (AndroidUtils.getAndroidVersion() >= 17) {
            final Display[] displays = ((DisplayManager)this.getContext().getSystemService("display")).getDisplays();
            for (int length = displays.length, i = 0; i < length; ++i) {
                final Display display = displays[i];
                if (Log.isLoggable("nf_configurationagent", 3)) {
                    Log.d("nf_configurationagent", "getMaxResolutionRestriction " + display.toString());
                }
                if (display.isValid() && display.getDisplayId() == 0) {
                    final DisplayMetrics displayMetrics = new DisplayMetrics();
                    display.getMetrics(displayMetrics);
                    final int heightPixels = displayMetrics.heightPixels;
                    return VideoResolutionRange.getVideoResolutionRangeFromMaxHieght(Math.min(maxResolutionConfigured, heightPixels));
                }
            }
        }
        Label_0207: {
            break Label_0207;
        }
        final int heightPixels = Integer.MAX_VALUE;
        return VideoResolutionRange.getVideoResolutionRangeFromMaxHieght(Math.min(maxResolutionConfigured, heightPixels));
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
        return shouldUseLowMemConfig();
    }
    
    @Override
    public boolean isDisableMcQueenV2() {
        return this.mAccountConfigOverride.toDisableMcQueenV2();
    }
    
    @Override
    public boolean isDisableWidevine() {
        return this.mDeviceConfigOverride.isDisableWidevine();
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
    
    public void refreshConfig(final ConfigurationAgentWebCallback configurationAgentWebCallback, final ConfigurationAgent$ConfigAgentListener configurationAgent$ConfigAgentListener) {
        // monitorenter(this)
        Label_0015: {
            if (configurationAgent$ConfigAgentListener == null) {
                break Label_0015;
            }
            try {
                this.mConfigAgentListeners.add(configurationAgent$ConfigAgentListener);
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
    
    public void removeConfigAgentListener(final ConfigurationAgent$ConfigAgentListener configurationAgent$ConfigAgentListener) {
        // monitorenter(this)
        if (configurationAgent$ConfigAgentListener == null) {
            return;
        }
        try {
            this.mConfigAgentListeners.remove(configurationAgent$ConfigAgentListener);
        }
        finally {
        }
        // monitorexit(this)
    }
    
    public boolean shouldUseLegacyBrowseVolleyClient() {
        return this.mAccountConfigOverride.shouldUseLegacyBrowseVolleyClient();
    }
    
    @Override
    public void userAgentLogoutComplete() {
        this.mNeedLogout = false;
    }
}
