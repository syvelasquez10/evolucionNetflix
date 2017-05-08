// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.service.webclient.model.leafs.VoipConfiguration;
import android.view.Display;
import android.util.DisplayMetrics;
import android.hardware.display.DisplayManager;
import com.netflix.mediaclient.media.VideoResolutionRange;
import com.netflix.mediaclient.service.webclient.model.leafs.SubtitleDownloadRetryPolicy;
import com.netflix.mediaclient.service.webclient.model.leafs.SignInConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.NrmConfigData;
import org.json.JSONObject;
import com.netflix.mediaclient.service.net.IpConnectivityPolicy;
import com.netflix.mediaclient.service.webclient.model.leafs.ErrorLoggingSpecification;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.webclient.model.leafs.ConsolidatedLoggingSessionSpecification;
import java.util.Arrays;
import com.netflix.mediaclient.util.Base64;
import android.util.Pair;
import com.netflix.mediaclient.service.webclient.model.leafs.BreadcrumbLoggingSpecification;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig;
import com.netflix.mediaclient.service.configuration.esn.EsnProviderRegistry;
import com.netflix.mediaclient.service.configuration.drm.DrmManager$DrmReadyCallback;
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.PreviewContentConfigData;
import com.netflix.mediaclient.ui.experience.PersistentExperience;
import com.netflix.mediaclient.javabridge.transport.NativeTransport;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.media.JPlayer.DolbyDigitalHelper;
import android.content.pm.PackageManager;
import com.netflix.mediaclient.service.voip.VoipAuthorizationTokensUpdater;
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
import java.util.ArrayList;
import android.os.Handler;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
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
    private static final boolean OVERRIDE_SERVER_CONFIG_FOR_KUBRICK = false;
    public static final int RESOURCE_REQUEST_TIMEOUT_MS = 1000;
    private static final String TAG = "nf_configurationagent";
    private static final String sMemLevel;
    private ABTestConfiguration mABTestConfigOverride;
    private AccountConfiguration mAccountConfigOverride;
    private int mAppVersionCode;
    private CastKeyConfiguration mCastKeyConfigOverride;
    private final List<ConfigurationAgent$ConfigAgentListener> mConfigAgentListeners;
    private Status mConfigRefreshStatus;
    private ConfigurationWebClient mConfigurationWebClient;
    private DeviceConfiguration mDeviceConfigOverride;
    private int mDiskCacheSizeBytes;
    private DrmManager mDrmManager;
    private EsnProvider mESN;
    private EndpointRegistryProvider mEndpointRegistry;
    private boolean mIsConfigRefreshInBackground;
    private final MdxConfiguration mMdxConfiguration;
    private boolean mMicrophoneExist;
    private boolean mNeedEsMigration;
    private boolean mNeedLogout;
    private NrmConfiguration mNrmConfigOverride;
    private final PlaybackConfiguration mPlaybackConfiguration;
    private SignInConfiguration mSignInConfigOverride;
    private String mSoftwareVersion;
    private StreamingConfiguration mStreamingConfigOverride;
    private Handler refreshHandler;
    private final Runnable refreshRunnable;
    
    static {
        DEFAULT_IMAGE_CACHE_SIZE_BYTES = (int)(Runtime.getRuntime().maxMemory() * 0.5f);
        sMemLevel = computeMemLevel();
        DUMMY_KUBRICK_CONFIG = new ConfigurationAgent$6();
    }
    
    public ConfigurationAgent() {
        this.mConfigAgentListeners = new ArrayList<ConfigurationAgent$ConfigAgentListener>();
        this.mIsConfigRefreshInBackground = false;
        this.mAppVersionCode = -1;
        this.mSoftwareVersion = null;
        this.mNeedLogout = false;
        this.mNeedEsMigration = false;
        this.refreshRunnable = new ConfigurationAgent$3(this);
        this.mMdxConfiguration = new ConfigurationAgent$4(this);
        this.mPlaybackConfiguration = new ConfigurationAgent$5(this);
    }
    
    private ImageResolutionClass computeImageResolutionClass(final Context context) {
        if (ConfigurationAgent.sMemLevel == "low") {
            Log.v("nf_configurationagent", "Device is low memory category - use low resolution images");
            return ImageResolutionClass.LOW;
        }
        if (DeviceUtils.getScreenResolutionDpi(context) <= 160 && DeviceUtils.getScreenSizeCategory(context) == 3) {
            Log.v("nf_configurationagent", "Device is a low-res, small tablet - use medium resolution images");
            return ImageResolutionClass.MEDIUM;
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
        if (Log.isLoggable()) {
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
        ConfigData configString;
        try {
            if (Log.isLoggable()) {
                Log.d("nf_configurationagent", String.format("configurationUrl %s", s));
            }
            s = StringUtils.getRemoteDataAsString(s);
            if (Log.isLoggable()) {
                Log.d("nf_configurationagent", String.format("Device config data=%s", s));
            }
            configString = FetchConfigDataRequest.parseConfigString(s);
            if (configString.deviceConfig == null) {
                throw new IOException();
            }
        }
        catch (Throwable t) {
            s = t.toString().toLowerCase(Locale.US);
            Log.e("nf_configurationagent", "Could not fetch configuration! " + s);
            if (s.contains("could not validate certificate") || s.contains("sslhandshakeexception")) {
                return CommonStatus.HTTP_SSL_DATE_TIME_ERROR;
            }
            return CommonStatus.FATAL_CONFIG_DOWNLOAD_FAILED;
        }
        this.mDeviceConfigOverride.persistDeviceConfigOverride(configString.getDeviceConfig());
        this.mStreamingConfigOverride.persistStreamingOverride(configString.getStreamingConfig());
        this.mNrmConfigOverride.persistNrmConfigOverride(configString.getNrmConfigData());
        this.mCastKeyConfigOverride.persistCastConfigOverride(configString.getCastKeyData());
        this.mSignInConfigOverride.persistSignInConfigOverride(configString.getSignInConfigData());
        return CommonStatus.OK;
    }
    
    private Status fetchVoipAuthorizationSynchronouslyOnAppStart(String s) {
        try {
            if (Log.isLoggable()) {
                Log.d("nf_configurationagent", String.format("configurationUrl %s", s));
            }
            s = StringUtils.getRemoteDataAsString(s);
            if (Log.isLoggable()) {
                Log.d("nf_configurationagent", String.format("Device config data=%s", s));
            }
            final ConfigData configString = FetchConfigDataRequest.parseConfigString(s);
            if (configString.getCustomerSupportVoipAuthorizations() != null) {
                Log.d("nf_configurationagent", "Update VOIP authorizations");
                ((VoipAuthorizationTokensUpdater)this.getService().getVoip()).updateAuthorizationData(configString.getCustomerSupportVoipAuthorizations());
            }
            return CommonStatus.OK;
        }
        catch (Exception ex) {
            s = ex.toString().toLowerCase(Locale.US);
            Log.e("nf_configurationagent", "Could not fetch configuration! " + s);
            if (s.contains("could not validate certificate") || s.contains("sslhandshakeexception")) {
                return CommonStatus.HTTP_SSL_DATE_TIME_ERROR;
            }
            return CommonStatus.VOIP_CONFIG_DOWNLOAD_FAILED;
        }
    }
    
    private int getMaxResolutionConfigured() {
        return this.mDeviceConfigOverride.getVideoResolutionOverride();
    }
    
    public static String getMemLevel() {
        return ConfigurationAgent.sMemLevel;
    }
    
    private boolean hasMicrophone() {
        final PackageManager packageManager = this.getContext().getPackageManager();
        if (packageManager == null) {
            Log.e("nf_configurationagent", "Unable to get PackageManager! This should NOT happen!");
            return false;
        }
        return packageManager.hasSystemFeature("android.hardware.microphone");
    }
    
    private void initVoipSettings() {
        if (this.shouldDisableVoip()) {
            Log.d("nf_configurationagent", "VOIP is disabled, no need to retrieve its settings...");
            return;
        }
        Log.d("nf_configurationagent", "VOIP is enabled, retrieving its settings...");
        if (this.loadVoipAuthorizationsOnAppStart(this.mEndpointRegistry.getCustomerSupportAuthTokensUrl(this.mESN.getEsn())).isError()) {
            Log.w("nf_configurationagent", "Problem getting non-member VOIP token");
            return;
        }
        Log.d("nf_configurationagent", "VOIP was enabled, its settings retrieved.");
    }
    
    private boolean isDolbyDigitalPlus20Supported() {
        return (this.mDeviceConfigOverride.getmAudioFormat() & 0x4) != 0x0 && DolbyDigitalHelper.isEAC3supported();
    }
    
    private void launchTask(final ConfigurationAgent$FetchTask configurationAgent$FetchTask) {
        if (Log.isLoggable()) {
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
        Log.d("nf_configurationagent", "Need to fetchdeviceConfig synchronously ");
        return this.fetchConfigSynchronouslyOnAppStart(s);
    }
    
    private Status loadVoipAuthorizationsOnAppStart(final String s) {
        final NetflixImmutableStatus ok = CommonStatus.OK;
        if (!((VoipAuthorizationTokensUpdater)this.getService().getVoip()).refreshAuthorizationTokens()) {
            Log.d("nf_configurationagent", "Not first app start... proceed!");
            return ok;
        }
        Log.d("nf_configurationagent", "Need to fetch VOIP authorization tokens synchronously ");
        return this.fetchVoipAuthorizationSynchronouslyOnAppStart(s);
    }
    
    private void notifyConfigRefreshedAndClearListeners() {
        this.getMainHandler().post((Runnable)new ConfigurationAgent$2(this));
    }
    
    private void persistConfigOverride(final ConfigData configData) {
        if (Log.isLoggable()) {
            Log.d("nf_configurationagent", String.format("persistConfigOverride configData %s", configData.toString()));
        }
        PreviewContentConfigData previewContentConfigData;
        if (this.mAccountConfigOverride != null) {
            previewContentConfigData = this.mAccountConfigOverride.getPreviewContentConfigData();
        }
        else {
            previewContentConfigData = null;
        }
        this.mDeviceConfigOverride.persistDeviceConfigOverride(configData.getDeviceConfig());
        this.mStreamingConfigOverride.persistStreamingOverride(configData.getStreamingConfig());
        this.mAccountConfigOverride.persistAccountConfigOverride(configData.getAccountConfig());
        this.mABTestConfigOverride.persistABTestConfigOverride(configData.getABTestConfigData());
        this.mNrmConfigOverride.persistNrmConfigOverride(configData.getNrmConfigData());
        this.mCastKeyConfigOverride.persistCastConfigOverride(configData.getCastKeyData());
        this.mSignInConfigOverride.persistSignInConfigOverride(configData.getSignInConfigData());
        ((VoipAuthorizationTokensUpdater)this.getService().getVoip()).updateAuthorizationData(configData.getCustomerSupportVoipAuthorizations());
        if (this.isDolbyDigitalPlus51Supported() && BandwidthUtility.canSupportDDPlus51(this.getContext())) {
            NativeTransport.enableDolbyDigitalPlus51();
        }
        if (this.isDolbyDigitalPlus20Supported()) {
            NativeTransport.enableDolbyDigitalPlus20();
        }
        NativeTransport.setSupportMaxVideoHeight(this.getVideoResolutionRange().getMaxHeight());
        if (this.isDeviceHd()) {
            NativeTransport.enableHDPlayback();
        }
        PersistentExperience.update(this.getContext(), this);
        PersistentConfig.update(this.getContext(), this);
        this.setPreviewContentConfig(previewContentConfigData);
    }
    
    private void prepareConfigWebClient() {
        this.mEndpointRegistry.setUserAgentInterface(this.getUserAgent());
        if (this.mConfigurationWebClient == null) {
            this.mConfigurationWebClient = ConfigurationWebClientFactory.create(this.getService(), this.getResourceFetcher().getApiNextWebClient());
        }
    }
    
    private void setPreviewContentConfig(final PreviewContentConfigData previewContentConfigData) {
        if (previewContentConfigData == null || !previewContentConfigData.equals(this.mAccountConfigOverride.getPreviewContentConfigData())) {
            Log.d("nf_configurationagent", "Preview content config changed, update NrdLib");
            this.getNrdController().getNrdp().getMedia().setPreviewContentConfig(this.getPreviewContentConfiguration());
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
        this.mABTestConfigOverride.clear();
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
            if (Log.isLoggable()) {
                Log.d("nf_configurationagent", "Available disk space in bytes = " + availableInternalMemory + " Saving disk Cache Size = " + this.mDiskCacheSizeBytes);
            }
        }
        this.mAppVersionCode = AndroidManifestUtils.getVersionCode(this.getContext());
        if (Log.isLoggable()) {
            Log.i("nf_configurationagent", "Current app version code = " + this.mAppVersionCode);
        }
        this.mSoftwareVersion = AndroidManifestUtils.getVersion(this.getContext());
        if (Log.isLoggable()) {
            Log.i("nf_configurationagent", "Current softwareVersion = " + this.mSoftwareVersion);
        }
        final DeviceModel deviceModel = new DeviceModel(this.mAppVersionCode, this.getContext());
        this.mDeviceConfigOverride = new DeviceConfiguration(this.getContext());
        this.mAccountConfigOverride = new AccountConfiguration(this.getContext());
        this.mStreamingConfigOverride = new StreamingConfiguration(this.getContext());
        this.mABTestConfigOverride = new ABTestConfiguration(this.getContext());
        this.mSignInConfigOverride = new SignInConfiguration(this.getContext());
        this.mNrmConfigOverride = new NrmConfiguration(this.getContext());
        this.mCastKeyConfigOverride = new CastKeyConfiguration(this.getContext());
        this.mEndpointRegistry = new EndpointRegistryProvider(this.getContext(), deviceModel, this.isDeviceHd(), this.mDeviceConfigOverride.getImageRepository(), this.computeImageResolutionClass(this.getContext()));
        this.mMicrophoneExist = this.hasMicrophone();
        final Status loadConfigOverridesOnAppStart = this.loadConfigOverridesOnAppStart(this.mEndpointRegistry.getAppStartConfigUrl());
        if (loadConfigOverridesOnAppStart.isError()) {
            this.initCompleted(loadConfigOverridesOnAppStart);
            return;
        }
        this.mDrmManager = DrmManagerRegistry.createDrmManager(this.getContext(), this, this.getUserAgent(), this.getService().getClientLogging().getErrorLogging(), this.getErrorHandler(), new ConfigurationAgent$1(this));
        this.mESN = EsnProviderRegistry.createESN(this.getContext(), this.mDrmManager, this);
        this.initVoipSettings();
        Log.d("nf_configurationagent", "Inject ESN to PlayerTypeFactory");
        PlayerTypeFactory.initialize(this.mESN);
        this.mDrmManager.init();
    }
    
    @Override
    public boolean enableHTTPSAuth() {
        return this.mAccountConfigOverride.enableHTTPSAuth();
    }
    
    @Override
    public boolean enableLowBitrateStreams() {
        return this.mAccountConfigOverride.enableLowBitrateStreams();
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
    public ABTestConfig getABTestConfiguration_6634() {
        return this.mAccountConfigOverride.getABTestConfiguration_6634();
    }
    
    @Override
    public ABTestConfig getABTestConfiguration_6725() {
        return this.mAccountConfigOverride.getABTestConfiguration_6725();
    }
    
    @Override
    public String getAlertMsgForMissingLocale() {
        return this.mDeviceConfigOverride.getAlertMsgForMissingLocale();
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
    public DataSaveConfigData getBWSaveConfigData() {
        return this.mAccountConfigOverride.getBWSaveConfigData();
    }
    
    @Override
    public BreadcrumbLoggingSpecification getBreadcrumbLoggingSpecification() {
        return this.mDeviceConfigOverride.getBreadcrumbLoggingSpecification();
    }
    
    @Override
    public Pair<String, byte[]> getCastPrefetchSharedSecret() {
        final String castKeyId = this.mCastKeyConfigOverride.getCastKeyId();
        final String castKey = this.mCastKeyConfigOverride.getCastKey();
        if (StringUtils.isEmpty(castKey) || StringUtils.isEmpty(castKeyId)) {
            Log.d("nf_configurationagent", "cast sharedSecret are null");
        }
        else {
            try {
                final byte[] decode = Base64.decode(castKey);
                if (Log.isLoggable()) {
                    Log.d("nf_configurationagent", String.format("cast keyId: %s, key:%s, decodedKey: %s", castKeyId, castKey, Arrays.toString(decode)));
                }
                return (Pair<String, byte[]>)Pair.create((Object)castKeyId, (Object)decode);
            }
            catch (IOException ex) {
                if (Log.isLoggable()) {
                    Log.e("nf_configurationagent", "error decoding castkey" + castKey, ex);
                    return null;
                }
            }
        }
        return null;
    }
    
    @Override
    public ConsolidatedLoggingSessionSpecification getConsolidatedLoggingSessionSpecification(final String s) {
        return this.mDeviceConfigOverride.getConsolidatedLoggingSessionSpecification(s);
    }
    
    @Override
    public ABTestConfig$Cell getCoppola1Experience() {
        return this.mABTestConfigOverride.getCoppola1TestCell();
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
            if (Log.isLoggable()) {
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
    public int getJPlayerStreamErrorRestartCount() {
        return this.mDeviceConfigOverride.getJPlayerStreamErrorRestartCount();
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
    public ABTestConfig$Cell getMotionBBTestConfig() {
        return this.mABTestConfigOverride.getMotionBBTestConfig();
    }
    
    public String getNrdDeviceModel() {
        return this.getEsnProvider().getDeviceModel();
    }
    
    @Override
    public NrmConfigData getNrmConfigData() {
        return this.mNrmConfigOverride.mNrmConfigData;
    }
    
    @Override
    public ABTestConfig$Cell getPhoneOrientationConfig() {
        return this.mABTestConfigOverride.getPhoneOrientationConfig();
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
    public PreviewContentConfigData getPreviewContentConfiguration() {
        if (this.getCurrentPlayerType() != PlayerType.device12) {
            Log.d("nf_configurationagent", "Not JPLAYER2, preview content is not enabled");
            return PreviewContentConfigData.getDisabledConfig();
        }
        if (this.mAccountConfigOverride == null) {
            return null;
        }
        return this.mAccountConfigOverride.getPreviewContentConfigData();
    }
    
    @Override
    public ABTestConfig$Cell getPushNotifOptInConfig() {
        return this.mABTestConfigOverride.getPushNotificationOptInConfig();
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
    public SignInConfigData getSignInConfigData() {
        return this.mSignInConfigOverride.mSignInConfigData;
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
    public SubtitleDownloadRetryPolicy getSubtitleDownloadRetryPolicy() {
        return this.mDeviceConfigOverride.getSubtitleDownloadRetryPolicy();
    }
    
    @Override
    public int getVideoBufferSize() {
        final int videoBufferSize = this.mAccountConfigOverride.getVideoBufferSize();
        int n;
        if (videoBufferSize < 4194304 || (n = videoBufferSize) > 33554432) {
            if (Log.isLoggable()) {
                Log.d("nf_configurationagent", "Invalid VideoBufferSize " + videoBufferSize);
            }
            n = 0;
        }
        return n;
    }
    
    @SuppressLint({ "NewApi" })
    @Override
    public VideoResolutionRange getVideoResolutionRange() {
        int maxResolutionConfigured;
        final int n = maxResolutionConfigured = this.getMaxResolutionConfigured();
        if (!this.isTablet()) {
            maxResolutionConfigured = n;
            if (!PlayerTypeFactory.isJPlayer2(this.getCurrentPlayerType())) {
                final int n2 = maxResolutionConfigured = 384;
                if (Log.isLoggable()) {
                    Log.d("nf_configurationagent", "apply phone restriction, max height " + 384);
                    maxResolutionConfigured = n2;
                }
            }
        }
        if (maxResolutionConfigured > 0) {
            return VideoResolutionRange.getVideoResolutionRangeFromMaxHieght(maxResolutionConfigured);
        }
        if (AndroidUtils.getAndroidVersion() >= 17) {
            final Display[] displays = ((DisplayManager)this.getContext().getSystemService("display")).getDisplays();
            for (int length = displays.length, i = 0; i < length; ++i) {
                final Display display = displays[i];
                if (Log.isLoggable()) {
                    Log.d("nf_configurationagent", "getMaxResolutionRestriction " + display.toString());
                }
                if (display.isValid() && display.getDisplayId() == 0) {
                    final DisplayMetrics displayMetrics = new DisplayMetrics();
                    display.getMetrics(displayMetrics);
                    final int heightPixels = displayMetrics.heightPixels;
                    return VideoResolutionRange.getVideoResolutionRangeFromMaxHieght(heightPixels);
                }
            }
        }
        Label_0201: {
            break Label_0201;
        }
        final int heightPixels = Integer.MAX_VALUE;
        return VideoResolutionRange.getVideoResolutionRangeFromMaxHieght(heightPixels);
    }
    
    @Override
    public ABTestConfig$Cell getVoiceSearchABTestConfig() {
        return this.mABTestConfigOverride.getVoiceSearchABTestConfig();
    }
    
    @Override
    public VoipConfiguration getVoipConfiguration() {
        return this.mDeviceConfigOverride.getVoipConfiguration();
    }
    
    @Override
    public boolean ignorePreloadForPlayBilling() {
        return this.mDeviceConfigOverride.ignorePreloadForPlayBilling();
    }
    
    public boolean isAppVersionObsolete() {
        final int appMinimalVersion = this.mDeviceConfigOverride.getAppMinimalVersion();
        if (Log.isLoggable()) {
            Log.i("nf_configurationagent", "minimalVersion = " + appMinimalVersion + " appVersionCode = " + this.mAppVersionCode + " so isAppVersionObsolete = " + (this.mAppVersionCode < appMinimalVersion));
        }
        return this.mAppVersionCode < appMinimalVersion;
    }
    
    public boolean isAppVersionRecommended() {
        final int appRecommendedVersion = this.mDeviceConfigOverride.getAppRecommendedVersion();
        if (Log.isLoggable()) {
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
    public boolean isDisableCastFaststart() {
        return this.mDeviceConfigOverride != null && this.mDeviceConfigOverride.isDisableCastFaststart();
    }
    
    @Override
    public boolean isDisableMcQueenV2() {
        return this.mAccountConfigOverride.toDisableMcQueenV2();
    }
    
    @Override
    public boolean isDolbyDigitalPlus51Supported() {
        return (this.mDeviceConfigOverride.getmAudioFormat() & 0x2) != 0x0 && DolbyDigitalHelper.isEAC3supported();
    }
    
    @Override
    public boolean isDynecomSignInEnabled() {
        return this.mDeviceConfigOverride.isDynecomSignInEnabled();
    }
    
    @Override
    public boolean isEsnMigrationRequired() {
        return this.mNeedEsMigration;
    }
    
    @Override
    public boolean isLogoutRequired() {
        return this.mNeedLogout;
    }
    
    @Override
    public boolean isPlayBillingDisabled() {
        return this.mDeviceConfigOverride.isPlayBillingDisabled();
    }
    
    @Override
    public boolean isPreviewContentEnabled() {
        if (this.getCurrentPlayerType() == PlayerType.device12) {
            boolean previewContentEnabled;
            final boolean b = previewContentEnabled = true;
            if (this.mAccountConfigOverride != null) {
                previewContentEnabled = b;
                if (this.mAccountConfigOverride.getPreviewContentConfigData() != null) {
                    previewContentEnabled = this.mAccountConfigOverride.getPreviewContentConfigData().isPreviewContentEnabled();
                }
            }
            Log.d("nf_configurationagent", "JPLAYER2, preview content is enabled " + previewContentEnabled);
            return previewContentEnabled;
        }
        Log.d("nf_configurationagent", "Not JPLAYER2, preview content is not enabled");
        return false;
    }
    
    public boolean isTablet() {
        return DeviceCategory.TABLET.equals(this.getDeviceCategory());
    }
    
    @Override
    public boolean isWidevineL1Enabled() {
        return this.mDeviceConfigOverride.enableWidevineL1();
    }
    
    @Override
    public boolean isWidevineL3ABTestEnabled() {
        return this.mAccountConfigOverride.enableWidevineL3ABTest();
    }
    
    @Override
    public boolean isWidevineL3Enabled() {
        return this.mDeviceConfigOverride.enableWidevineL3();
    }
    
    public void refreshConfig(final ConfigurationAgentWebCallback configurationAgentWebCallback, final ConfigurationAgent$ConfigAgentListener configurationAgent$ConfigAgentListener) {
        // monitorenter(this)
        Label_0017: {
            if (configurationAgent$ConfigAgentListener == null) {
                break Label_0017;
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
    
    @Override
    public boolean shouldAlertForMissingLocale() {
        return this.mDeviceConfigOverride.shouldAlertForMissingLocale();
    }
    
    @Override
    public boolean shouldDisableVoip() {
        boolean b = true;
        final boolean b2 = !this.mMicrophoneExist || !this.mDeviceConfigOverride.getVoipConfiguration().isEnableVoip() || (this.mDeviceConfigOverride.shouldDisableVoip() && this.mAccountConfigOverride.shouldDisableVoip());
        if (Log.isLoggable()) {
            Log.d("nf_configurationagent", "Microfon exist: " + this.mMicrophoneExist);
            Log.d("nf_configurationagent", "device overide: " + this.mDeviceConfigOverride.shouldDisableVoip());
            Log.d("nf_configurationagent", "account overide: " + this.mAccountConfigOverride.shouldDisableVoip());
            final StringBuilder append = new StringBuilder().append("VOIP configuration overide: ");
            if (this.mDeviceConfigOverride.getVoipConfiguration().isEnableVoip()) {
                b = false;
            }
            Log.d("nf_configurationagent", append.append(b).toString());
            Log.d("nf_configurationagent", "Real disable " + b2);
        }
        return b2;
    }
    
    @Override
    public boolean shouldDisplayVoipDialConfirmationDialog() {
        final int voipConfirmationDialogAllocationPercentage = this.mDeviceConfigOverride.getVoipConfirmationDialogAllocationPercentage();
        if (Log.isLoggable()) {
            Log.d("nf_configurationagent", "shouldDisplayVoipDialConfirmationDialog:: allocation percentage: " + voipConfirmationDialogAllocationPercentage);
        }
        boolean deviceEnabled;
        if (voipConfirmationDialogAllocationPercentage <= 0) {
            Log.d("nf_configurationagent", "Nobody will see confirmation dialog");
            deviceEnabled = false;
        }
        else {
            if (voipConfirmationDialogAllocationPercentage >= 100) {
                Log.d("nf_configurationagent", "Everybody will see confirmation dialog");
                return true;
            }
            final boolean b = deviceEnabled = DeviceUtils.isDeviceEnabled(this.getContext(), 100 - voipConfirmationDialogAllocationPercentage);
            if (Log.isLoggable()) {
                Log.d("nf_configurationagent", "shouldDisplayVoipDialConfirmationDialog:: This device will see confirmation dialog: " + b);
                return b;
            }
        }
        return deviceEnabled;
    }
    
    @Override
    public void userAgentLogoutComplete() {
        this.mNeedLogout = false;
    }
    
    @Override
    public void verifyLoginViaDynecom(final String s, final String s2, final ConfigurationAgentWebCallback configurationAgentWebCallback) {
        this.prepareConfigWebClient();
        this.launchTask(new ConfigurationAgent$VerifyLoginViaDynecomTask(this, s, s2, configurationAgentWebCallback));
    }
}
