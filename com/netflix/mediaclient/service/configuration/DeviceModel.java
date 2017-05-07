// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.configuration.esn.BaseEsnProvider;
import android.os.Build;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceCategory;

public class DeviceModel
{
    private static final String APP_NAME = "samurai";
    int apiLevel;
    String appType;
    String appVersion;
    int appVersionCode;
    String buildPropBoard;
    String buildPropDisplay;
    DeviceCategory category;
    String device;
    String esnModelId;
    String fingerprint;
    String manufacturer;
    String model;
    
    public DeviceModel(final int appVersionCode, final Context context) {
        this.appType = "";
        this.model = "";
        this.esnModelId = "";
        this.manufacturer = "";
        this.fingerprint = "";
        this.device = "";
        this.buildPropBoard = "";
        this.buildPropDisplay = "";
        this.appType = "samurai";
        if (Build.MANUFACTURER != null) {
            this.manufacturer = Build.MANUFACTURER.trim();
        }
        if (Build.MODEL != null) {
            this.model = Build.MODEL.trim();
        }
        this.esnModelId = BaseEsnProvider.buildFesnModelId();
        if (Build.FINGERPRINT != null) {
            this.fingerprint = Build.FINGERPRINT.trim();
        }
        this.apiLevel = AndroidUtils.getAndroidVersion();
        this.appVersionCode = appVersionCode;
        this.appVersion = AndroidManifestUtils.getVersion(context);
        if (DeviceUtils.isTabletByContext(context)) {
            this.category = DeviceCategory.TABLET;
        }
        else {
            this.category = DeviceCategory.PHONE;
        }
        if (Build.DEVICE != null) {
            this.device = Build.DEVICE;
        }
        if (Build.BOARD != null) {
            this.buildPropBoard = Build.BOARD;
        }
        if (Build.DISPLAY != null) {
            this.buildPropDisplay = Build.DISPLAY;
        }
    }
    
    public int getApiLevel() {
        return this.apiLevel;
    }
    
    public String getAppType() {
        return this.appType;
    }
    
    public String getAppVersion() {
        return this.appVersion;
    }
    
    public int getAppVersionCode() {
        return this.appVersionCode;
    }
    
    public String getBuildPropBoard() {
        return this.buildPropBoard;
    }
    
    public String getBuildPropDevice() {
        return this.device;
    }
    
    public String getBuildPropDisplay() {
        return this.buildPropDisplay;
    }
    
    public String getEsnModelId() {
        return this.esnModelId;
    }
    
    public String getFingerprint() {
        return this.fingerprint;
    }
    
    public String getFormFactor() {
        return this.category.getValue();
    }
    
    public String getManufacturer() {
        return this.manufacturer;
    }
    
    public String getModel() {
        return this.model;
    }
}
