// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.service.webclient.model.leafs.DeviceConfigData;
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import com.netflix.mediaclient.util.DeviceCategory;
import java.util.Iterator;
import java.lang.reflect.Type;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ConfigData;
import java.io.IOException;
import com.netflix.mediaclient.service.configuration.volley.FetchConfigDataRequest;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.ConsolidatedLoggingSessionSpecification;
import java.util.Map;

public class DeviceConfiguration
{
    private static String TAG;
    private Map<String, ConsolidatedLoggingSessionSpecification> mConsolidatedLoggingSpecification;
    private Context mContext;
    private DeviceRepository mDeviceRepository;
    private ImagePrefRepository mImagePrefRepository;
    private SignUpConfiguration mSignUpConfig;
    private SubtitleConfiguration mSubtitleConfiguration;
    
    static {
        DeviceConfiguration.TAG = "nf_configuration_device";
    }
    
    public DeviceConfiguration(final Context mContext) {
        this.mSubtitleConfiguration = SubtitleConfiguration.ENHANCED_XML;
        this.mConsolidatedLoggingSpecification = new HashMap<String, ConsolidatedLoggingSessionSpecification>();
        this.mContext = mContext;
        this.mDeviceRepository = new DeviceRepository(mContext);
        this.mImagePrefRepository = new ImagePrefRepository(mContext);
        this.mSignUpConfig = new SignUpConfiguration(mContext);
        this.mSubtitleConfiguration = SubtitleConfiguration.load(mContext);
        this.mConsolidatedLoggingSpecification = this.loadConsolidateLoggingSpecification();
    }
    
    private int fetchDeviceConfigSynchronously(String remoteDataAsString) {
        Log.d(DeviceConfiguration.TAG, "Need to fetchdeviceConfig synchronously ");
        ConfigData configString;
        try {
            Log.d(DeviceConfiguration.TAG, String.format("configurationUrl %s", remoteDataAsString));
            remoteDataAsString = StringUtils.getRemoteDataAsString(remoteDataAsString);
            Log.d(DeviceConfiguration.TAG, String.format("Device config data=%s", remoteDataAsString));
            configString = FetchConfigDataRequest.parseConfigString(remoteDataAsString);
            if (configString.deviceConfig == null) {
                throw new IOException();
            }
        }
        catch (Exception ex) {
            Log.e(DeviceConfiguration.TAG, "Could not fetch configuration! ", ex);
            return -3;
        }
        this.persistDeviceConfigOverride(configString.getDeviceConfig());
        return 0;
    }
    
    public static int getPTAggregationSize(final Context context) {
        return PreferenceUtils.getIntPref(context, "pt_aggregation_size", -1);
    }
    
    private boolean isDeviceConfigInCache() {
        return PreferenceUtils.getBooleanPref(this.mContext, "nf_device_config_cached", false);
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
        PreferenceUtils.putBooleanPref(this.mContext, "disable_mdx", Boolean.valueOf(StringUtils.isNotEmpty(s) && Boolean.parseBoolean(s)));
    }
    
    private void updateDisableWebsocketFlag(final String s) {
        PreferenceUtils.putBooleanPref(this.mContext, "disable_websocket", Boolean.valueOf(StringUtils.isNotEmpty(s) && Boolean.parseBoolean(s)));
    }
    
    private void updateDisableWidevineFlag(final String s) {
        PreferenceUtils.putBooleanPref(this.mContext, "disable_widevine", Boolean.valueOf(StringUtils.isNotEmpty(s) && Boolean.parseBoolean(s)));
    }
    
    public void clear() {
        this.mDeviceRepository = null;
        this.mImagePrefRepository = null;
        this.mSignUpConfig = null;
    }
    
    public int getAppMinimalVersion() {
        return PreferenceUtils.getIntPref(this.mContext, "config_min_version", -1);
    }
    
    public int getAppRecommendedVersion() {
        return PreferenceUtils.getIntPref(this.mContext, "config_recommended_version", -1);
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
    
    public ImagePrefRepository getImageRepository() {
        return this.mImagePrefRepository;
    }
    
    public SignUpConfiguration getSignUpConfiguration() {
        return this.mSignUpConfig;
    }
    
    public SubtitleConfiguration getSubtitleConfiguration() {
        return this.mSubtitleConfiguration;
    }
    
    public boolean isDisableMdxFlagSet() {
        return PreferenceUtils.getBooleanPref(this.mContext, "disable_mdx", false);
    }
    
    public boolean isDisableWebsocketFlagSet() {
        return PreferenceUtils.getBooleanPref(this.mContext, "disable_websocket", false);
    }
    
    public boolean isDisableWidevineFlagSet() {
        return PreferenceUtils.getBooleanPref(this.mContext, "disable_widevine", !DrmManagerRegistry.isDevicePredeterminedToUseWV());
    }
    
    public int loadDeviceConfigOverrides(final String s) {
        if (!this.isDeviceConfigInCache()) {
            return this.fetchDeviceConfigSynchronously(s);
        }
        Log.d(DeviceConfiguration.TAG, "DeviceConfig in cache... proceed!");
        return 0;
    }
    
    public void persistDeviceConfigOverride(final DeviceConfigData deviceConfigData) {
        if (deviceConfigData != null) {
            Log.d(DeviceConfiguration.TAG, String.format("writing configData to storage %s", deviceConfigData.toString()));
            PlayerTypeFactory.updateDevicePlayerType(this.mContext, deviceConfigData.getPlayerType());
            this.mDeviceRepository.update(this.mContext, deviceConfigData.getDeviceCatgory());
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
            PreferenceUtils.putIntPref(this.mContext, "config_min_version", int1);
            int int2;
            if (deviceConfigData.getAppRecommendedVresion() != null) {
                int2 = Integer.parseInt(deviceConfigData.getAppRecommendedVresion());
            }
            else {
                int2 = -1;
            }
            PreferenceUtils.putIntPref(this.mContext, "config_recommended_version", int2);
            int int3;
            if (StringUtils.isNotEmpty(deviceConfigData.getPTAggregationSize())) {
                int3 = Integer.parseInt(deviceConfigData.getPTAggregationSize());
            }
            else {
                int3 = -1;
            }
            PreferenceUtils.putIntPref(this.mContext, "pt_aggregation_size", int3);
            if (!this.isDeviceConfigInCache()) {
                this.updateDeviceConfigFlag(true);
            }
        }
    }
}
