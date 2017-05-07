// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.service.webclient.model.leafs.DeviceConfigData;
import com.netflix.mediaclient.util.DeviceCategory;
import java.util.Iterator;
import java.lang.reflect.Type;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import com.netflix.mediaclient.util.PreferenceUtils;
import java.util.HashMap;
import com.netflix.mediaclient.net.IpConnectivityPolicy;
import com.netflix.mediaclient.service.webclient.model.leafs.ErrorLoggingSpecification;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.ConsolidatedLoggingSessionSpecification;
import java.util.Map;
import com.netflix.mediaclient.service.webclient.model.leafs.BreadcrumbLoggingSpecification;

public class DeviceConfiguration
{
    private static final boolean DISABLE_MDX_DEF = false;
    private static final boolean DISABLE_WEBSOCKET_DEF = true;
    private static String TAG;
    private int mAppMinimalVersion;
    private int mAppRecommendedVersion;
    private BreadcrumbLoggingSpecification mBreadcrumbLoggingSpecification;
    private Map<String, ConsolidatedLoggingSessionSpecification> mConsolidatedLoggingSpecification;
    private Context mContext;
    private DeviceRepository mDeviceRepository;
    private ErrorLoggingSpecification mErrorLoggingSpecification;
    private ImagePrefRepository mImagePrefRepository;
    private IpConnectivityPolicy mIpConnectivityPolicy;
    private boolean mIsDisableMdx;
    private boolean mIsDisableWebsocket;
    private boolean mIsDisableWidevine;
    private int mPTAggregationSize;
    private int mRateLimitForGcmBrowseEvents;
    private SignUpConfiguration mSignUpConfig;
    private SubtitleConfiguration mSubtitleConfiguration;
    private int mUserSessionDurationInSeconds;
    private int mVideoResolutionOverride;
    
    static {
        DeviceConfiguration.TAG = "nf_configuration_device";
    }
    
    public DeviceConfiguration(final Context mContext) {
        boolean b = true;
        this.mSubtitleConfiguration = SubtitleConfiguration.ENHANCED_XML;
        this.mConsolidatedLoggingSpecification = new HashMap<String, ConsolidatedLoggingSessionSpecification>();
        this.mContext = mContext;
        this.mDeviceRepository = new DeviceRepository(this.mContext);
        this.mImagePrefRepository = new ImagePrefRepository(this.mContext);
        this.mSignUpConfig = new SignUpConfiguration(this.mContext);
        this.mSubtitleConfiguration = SubtitleConfiguration.load(this.mContext);
        this.mConsolidatedLoggingSpecification = this.loadConsolidateLoggingSpecification();
        this.mIpConnectivityPolicy = this.loadIpConnectivityPolicy();
        this.mPTAggregationSize = PreferenceUtils.getIntPref(this.mContext, "pt_aggregation_size", -1);
        this.mAppRecommendedVersion = PreferenceUtils.getIntPref(this.mContext, "config_recommended_version", -1);
        this.mAppMinimalVersion = PreferenceUtils.getIntPref(this.mContext, "config_min_version", -1);
        this.mIsDisableMdx = PreferenceUtils.getBooleanPref(this.mContext, "disable_mdx", false);
        this.mIsDisableWebsocket = PreferenceUtils.getBooleanPref(this.mContext, "disable_websocket", true);
        final Context mContext2 = this.mContext;
        if (DrmManagerRegistry.isDevicePredeterminedToUseWV()) {
            b = false;
        }
        this.mIsDisableWidevine = PreferenceUtils.getBooleanPref(mContext2, "disable_widevine", b);
        this.mUserSessionDurationInSeconds = this.loadUserSessionTimeoutDuration();
        this.mBreadcrumbLoggingSpecification = BreadcrumbLoggingSpecification.loadFromPreferences(mContext);
        this.mErrorLoggingSpecification = ErrorLoggingSpecification.loadFromPreferences(mContext);
        this.mVideoResolutionOverride = PreferenceUtils.getIntPref(this.mContext, "video_resolution_override", Integer.MAX_VALUE);
        this.mRateLimitForGcmBrowseEvents = PreferenceUtils.getIntPref(this.mContext, "gcm_browse_rate_limit", 0);
    }
    
    private Map<String, ConsolidatedLoggingSessionSpecification> loadConsolidateLoggingSpecification() {
        final List<ConsolidatedLoggingSessionSpecification> list = null;
        final String stringPref = PreferenceUtils.getStringPref(this.mContext, "cl_configuration", null);
        if (StringUtils.isEmpty(stringPref)) {
            Log.d(DeviceConfiguration.TAG, "CL specification not found in file system");
            return new HashMap<String, ConsolidatedLoggingSessionSpecification>();
        }
        List<ConsolidatedLoggingSessionSpecification> list2 = list;
        try {
            final Type type = new TypeToken<List<ConsolidatedLoggingSessionSpecification>>() {}.getType();
            list2 = list;
            final List<ConsolidatedLoggingSessionSpecification> list3 = list2 = (List<ConsolidatedLoggingSessionSpecification>)FalcorParseUtils.getGson().fromJson(stringPref, type);
            Log.d(DeviceConfiguration.TAG, "CL specification loaded from file system");
            list2 = list3;
            return toMap(list2);
        }
        catch (Throwable t) {
            Log.e(DeviceConfiguration.TAG, "Failed to load CL specification from file system", t);
            return toMap(list2);
        }
    }
    
    private IpConnectivityPolicy loadIpConnectivityPolicy() {
        return IpConnectivityPolicy.from(PreferenceUtils.getIntPref(this.mContext, "ip_connectivity_policy_overide", Integer.MIN_VALUE));
    }
    
    private int loadUserSessionTimeoutDuration() {
        return PreferenceUtils.getIntPref(this.mContext, "apm_user_session_timeout_duration_override", Integer.MIN_VALUE);
    }
    
    private static Map<String, ConsolidatedLoggingSessionSpecification> toMap(final List<ConsolidatedLoggingSessionSpecification> list) {
        final HashMap<String, ConsolidatedLoggingSessionSpecification> hashMap = new HashMap<String, ConsolidatedLoggingSessionSpecification>();
        if (list != null) {
            for (final ConsolidatedLoggingSessionSpecification consolidatedLoggingSessionSpecification : list) {
                if (consolidatedLoggingSessionSpecification != null && consolidatedLoggingSessionSpecification.getSession() != null) {
                    hashMap.put(consolidatedLoggingSessionSpecification.getSession(), consolidatedLoggingSessionSpecification);
                }
            }
        }
        return hashMap;
    }
    
    private void updateConsolidatedLoggingSpecification(final List<ConsolidatedLoggingSessionSpecification> list) {
        this.mConsolidatedLoggingSpecification = toMap(list);
        if (list == null || list.size() < 1) {
            PreferenceUtils.removePref(this.mContext, "cl_configuration");
            return;
        }
        PreferenceUtils.putStringPref(this.mContext, "cl_configuration", FalcorParseUtils.getGson().toJson(list));
    }
    
    private void updateDeviceConfigFlag(final boolean b) {
        Log.d(DeviceConfiguration.TAG, "setting DeviceConfig preferences inCache= " + b);
        PreferenceUtils.putBooleanPref(this.mContext, "nf_device_config_cached", b);
    }
    
    private void updateDisableMdxFlag(final String s) {
        final boolean mIsDisableMdx = StringUtils.isNotEmpty(s) && Boolean.parseBoolean(s);
        PreferenceUtils.putBooleanPref(this.mContext, "disable_mdx", mIsDisableMdx);
        this.mIsDisableMdx = mIsDisableMdx;
    }
    
    private void updateDisableWebsocketFlag(final String s) {
        final boolean mIsDisableWebsocket = !StringUtils.isNotEmpty(s) || Boolean.parseBoolean(s);
        PreferenceUtils.putBooleanPref(this.mContext, "disable_websocket", mIsDisableWebsocket);
        this.mIsDisableWebsocket = mIsDisableWebsocket;
    }
    
    private void updateDisableWidevineFlag(final String s) {
        boolean boolean1;
        if (StringUtils.isNotEmpty(s)) {
            boolean1 = Boolean.parseBoolean(s);
        }
        else {
            boolean1 = !DrmManagerRegistry.isDevicePredeterminedToUseWV();
        }
        PreferenceUtils.putBooleanPref(this.mContext, "disable_widevine", boolean1);
        this.mIsDisableWidevine = boolean1;
    }
    
    public void clear() {
        this.mDeviceRepository = null;
        this.mImagePrefRepository = null;
        this.mSignUpConfig = null;
    }
    
    public int getAppMinimalVersion() {
        return this.mAppMinimalVersion;
    }
    
    public int getAppRecommendedVersion() {
        return this.mAppRecommendedVersion;
    }
    
    public BreadcrumbLoggingSpecification getBreadcrumbLoggingSpecification() {
        return this.mBreadcrumbLoggingSpecification;
    }
    
    public DeviceCategory getCategory() {
        return this.mDeviceRepository.getCategory();
    }
    
    public ConsolidatedLoggingSessionSpecification getConsolidatedLoggingSessionSpecification(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return this.mConsolidatedLoggingSpecification.get(s);
    }
    
    public ErrorLoggingSpecification getErrorLoggingSpecification() {
        return this.mErrorLoggingSpecification;
    }
    
    public ImagePrefRepository getImageRepository() {
        return this.mImagePrefRepository;
    }
    
    public IpConnectivityPolicy getIpConnectivityPolicy() {
        return this.mIpConnectivityPolicy;
    }
    
    public int getPTAggregationSize() {
        return this.mPTAggregationSize;
    }
    
    public int getRateLimitForGcmBrowseEvents() {
        return this.mRateLimitForGcmBrowseEvents;
    }
    
    public SignUpConfiguration getSignUpConfiguration() {
        return this.mSignUpConfig;
    }
    
    public SubtitleConfiguration getSubtitleConfiguration() {
        return this.mSubtitleConfiguration;
    }
    
    public int getUserSessionDurationInSeconds() {
        return this.mUserSessionDurationInSeconds;
    }
    
    public int getVideoResolutionOverride() {
        return this.mVideoResolutionOverride;
    }
    
    public boolean isDeviceConfigInCache() {
        return PreferenceUtils.getBooleanPref(this.mContext, "nf_device_config_cached", false);
    }
    
    public boolean isDisableMdx() {
        return this.mIsDisableMdx;
    }
    
    public boolean isDisableWebsocket() {
        return this.mIsDisableWebsocket;
    }
    
    public boolean isDisableWidevine() {
        return this.mIsDisableWidevine;
    }
    
    public void persistDeviceConfigOverride(final DeviceConfigData deviceConfigData) {
        final int n = -1;
        if (deviceConfigData != null) {
            if (Log.isLoggable(DeviceConfiguration.TAG, 3)) {
                Log.d(DeviceConfiguration.TAG, String.format("writing configData to storage %s", deviceConfigData.toString()));
            }
            PlayerTypeFactory.updateDevicePlayerType(this.mContext, deviceConfigData.getPlayerType());
            this.mDeviceRepository.update(this.mContext, deviceConfigData.getDeviceCategory());
            BitrateRangeFactory.updateDeviceBitrateCap(this.mContext, deviceConfigData.getBitrateCap());
            this.mImagePrefRepository.update(this.mContext, deviceConfigData.getImagePref());
            this.mSignUpConfig.update(this.mContext, deviceConfigData.getSignUpEnabled(), deviceConfigData.getSignUpTimeout());
            this.updateDisableWidevineFlag(deviceConfigData.getWidevineDisabled());
            this.updateDisableWebsocketFlag(deviceConfigData.getWebsocketDisabled());
            this.updateDisableMdxFlag(deviceConfigData.getMdxDisabled());
            this.updateConsolidatedLoggingSpecification(deviceConfigData.getConsolidatedloggingSpecification());
            this.mSubtitleConfiguration = SubtitleConfiguration.update(this.mContext, deviceConfigData.getSubtitleConfiguration());
            int int1;
            if (deviceConfigData.getAppMinVresion() != null) {
                int1 = Integer.parseInt(deviceConfigData.getAppMinVresion());
            }
            else {
                int1 = -1;
            }
            this.mAppMinimalVersion = int1;
            PreferenceUtils.putIntPref(this.mContext, "config_min_version", this.mAppMinimalVersion);
            int int2;
            if (deviceConfigData.getAppRecommendedVresion() != null) {
                int2 = Integer.parseInt(deviceConfigData.getAppRecommendedVresion());
            }
            else {
                int2 = -1;
            }
            this.mAppRecommendedVersion = int2;
            PreferenceUtils.putIntPref(this.mContext, "config_recommended_version", this.mAppRecommendedVersion);
            int int3 = n;
            if (StringUtils.isNotEmpty(deviceConfigData.getPTAggregationSize())) {
                int3 = Integer.parseInt(deviceConfigData.getPTAggregationSize());
            }
            this.mPTAggregationSize = int3;
            PreferenceUtils.putIntPref(this.mContext, "pt_aggregation_size", this.mPTAggregationSize);
            final int ipConnectivityPolicy = deviceConfigData.getIpConnectivityPolicy();
            this.mIpConnectivityPolicy = IpConnectivityPolicy.from(ipConnectivityPolicy);
            PreferenceUtils.putIntPref(this.mContext, "ip_connectivity_policy_overide", ipConnectivityPolicy);
            this.mUserSessionDurationInSeconds = deviceConfigData.getUserSessionTimeoutDuration();
            PreferenceUtils.putIntPref(this.mContext, "apm_user_session_timeout_duration_override", this.mUserSessionDurationInSeconds);
            this.mBreadcrumbLoggingSpecification = BreadcrumbLoggingSpecification.saveToPreferences(this.mContext, deviceConfigData.getBreadcrumbLoggingSpecification());
            this.mErrorLoggingSpecification = ErrorLoggingSpecification.saveToPreferences(this.mContext, deviceConfigData.getErrorLoggingSpecification());
            this.mVideoResolutionOverride = deviceConfigData.getVideoResolutionOverride();
            PreferenceUtils.putIntPref(this.mContext, "video_resolution_override", deviceConfigData.getVideoResolutionOverride());
            this.mRateLimitForGcmBrowseEvents = deviceConfigData.getRateLimitForGcmBrowseEvents();
            PreferenceUtils.putIntPref(this.mContext, "gcm_browse_rate_limit", deviceConfigData.getRateLimitForGcmBrowseEvents());
            if (!this.isDeviceConfigInCache()) {
                this.updateDeviceConfigFlag(true);
            }
        }
    }
}
