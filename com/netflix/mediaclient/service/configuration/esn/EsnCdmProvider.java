// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.esn;

import android.content.Context;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Build;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;
import com.netflix.mediaclient.util.DeviceCategory;

public abstract class EsnCdmProvider extends BaseEsnProvider
{
    private static final String CATEGORY_TYPE_PHONE = "P-";
    private static final String CATEGORY_TYPE_TABLET = "T-";
    private static final String CRYPTO_PROVIDER_L3 = "L3-";
    private static final String DASH = "-";
    private static final String DEVICE_TYPE_PREFIX = "PRV-";
    private String mCdmModelId;
    private DeviceCategory mDeviceCategory;
    private String mDrmSystemId;
    private String mDrmUniqueDeviceId;
    private boolean mPhone;
    
    EsnCdmProvider(final DrmManager drmManager, final DeviceCategory mDeviceCategory) {
        if (drmManager == null) {
            throw new IllegalArgumentException("DrmManager is null!");
        }
        if (mDeviceCategory == null) {
            throw new IllegalArgumentException("Device category is null!");
        }
        if ((this.mDeviceCategory = mDeviceCategory) == DeviceCategory.PHONE) {
            this.mPhone = true;
        }
        final byte[] deviceId = drmManager.getDeviceId();
        this.mDrmSystemId = drmManager.getDeviceType();
        if (deviceId == null) {
            throw new IllegalArgumentException("MediaDrm.uniqueDeviceId is null! We can not use this ESN implementation!");
        }
        this.mDrmUniqueDeviceId = new String(deviceId);
        this.mCdmModelId = this.findCdmModelId(mDeviceCategory);
    }
    
    private String findCdmModelId(final DeviceCategory deviceCategory) {
        final StringBuilder sb = new StringBuilder("PRV-");
        if (this.mPhone) {
            sb.append("P-");
        }
        else {
            sb.append("T-");
        }
        if (this.getCryptoProvider() == CryptoProvider.WIDEVINE_L3) {
            sb.append("L3-");
        }
        if (Log.isLoggable()) {
            Log.d("ESN", "MANUFACTURER " + Build.MANUFACTURER);
            Log.d("ESN", "Model " + Build.MODEL);
        }
        String s2;
        final String s = s2 = Build.MODEL;
        if (s.length() > 45) {
            final String s3 = s2 = s.substring(0, 45);
            if (Log.isLoggable()) {
                Log.d("ESN", "Model was bigger than: 45. Using first 45 characters: " + s3);
                s2 = s3;
            }
        }
        final String string = BaseEsnProvider.getManufactorer() + StringUtils.replaceWhiteSpace(s2, EsnCdmProvider.DELIM);
        if (Log.isLoggable()) {
            Log.d("ESN", "Model ID: " + string);
        }
        sb.append(BaseEsnProvider.validateChars(string));
        sb.append("-");
        sb.append(this.mDrmSystemId);
        return sb.toString();
    }
    
    @Override
    public void destroy() {
    }
    
    @Override
    protected String findDeviceId(final Context context) {
        return this.mDrmUniqueDeviceId;
    }
    
    @Override
    protected String findModelId() {
        return this.mCdmModelId;
    }
    
    @Override
    protected void generateEsnPrefix() {
        final StringBuilder sb = new StringBuilder(EsnCdmProvider.ESN_PREFIX);
        sb.append("PRV-");
        if (this.mPhone) {
            sb.append("P-");
        }
        else {
            sb.append("T-");
        }
        if (this.getCryptoProvider() == CryptoProvider.WIDEVINE_L3) {
            sb.append("L3-");
        }
        this.mEsnPrefix = sb.toString();
        if (this.mEsnPrefix.endsWith("-")) {
            final int n = this.mEsnPrefix.lastIndexOf("-") + 1;
            if (n > 0) {
                this.mEsnPrefix = this.mEsnPrefix.substring(0, n);
            }
        }
    }
    
    @Override
    public int getCryptoFactoryType() {
        return 2;
    }
    
    protected DeviceCategory getDeviceCategory() {
        return this.mDeviceCategory;
    }
}
