// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.esn;

import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.util.MediaDrmUtils;
import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;
import android.content.Context;

public final class EsnProviderRegistry
{
    protected static final String TAG = "nf_esn";
    
    public static EsnProvider createESN(final Context context, final DrmManager drmManager, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        Log.d("nf_esn", "Create ESN");
        final int androidVersion = AndroidUtils.getAndroidVersion();
        int n;
        if (drmManager.getCryptoProvider() == CryptoProvider.LEGACY) {
            n = 1;
        }
        else {
            n = 0;
        }
        BaseEsnProvider esnCdmProvider;
        if (n != 0) {
            BaseEsnProvider baseEsnProvider;
            if (androidVersion >= 23) {
                baseEsnProvider = new EsnLegacyMPlusProvider();
            }
            else {
                baseEsnProvider = new EsnLegacyProvider();
            }
            esnCdmProvider = baseEsnProvider;
            if (Log.isLoggable()) {
                if (androidVersion >= 18) {
                    Log.d("nf_esn", "JB MR2+ device");
                    if (MediaDrmUtils.isWidewineSupported()) {
                        Log.d("nf_esn", "JB MR2+ device with Widewine support, but failed to initialize or not allowed, return ESN Legacy implementation!");
                        esnCdmProvider = baseEsnProvider;
                    }
                    else {
                        Log.d("nf_esn", "JB MR2+ device without Widewine support, return ESN Legacy implementation!");
                        esnCdmProvider = baseEsnProvider;
                    }
                }
                else {
                    Log.d("nf_esn", "Pre JB MR2 device, return ESN Legacy implementation!");
                    esnCdmProvider = baseEsnProvider;
                }
            }
        }
        else {
            Log.d("nf_esn", "JB MR2+ device with Widewine support, return ESN CDM implementation!");
            if (MediaDrmUtils.isDevicePredeterminedToUseWV()) {
                Log.d("nf_esn", "JB MR2+ device with legacy Widewine support, return ESN CDM Nexus 7 implementation!");
                esnCdmProvider = new EsnCdmNexus7Provider(drmManager);
            }
            else {
                Log.d("nf_esn", "JB MR2+ device with new Widewine support, return ESN CDM implementation!");
                esnCdmProvider = createEsnCdmProvider(context, drmManager, serviceAgent$ConfigurationAgentInterface);
            }
        }
        esnCdmProvider.initialize(context);
        if (Log.isLoggable()) {
            Log.d("nf_esn", "ESN: " + esnCdmProvider.getEsn());
        }
        return esnCdmProvider;
    }
    
    private static EsnCdmProvider createEsnCdmProvider(final Context context, final DrmManager drmManager, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        final DeviceCategory deviceCategory = serviceAgent$ConfigurationAgentInterface.getDeviceCategory();
        final CryptoProvider cryptoProvider = drmManager.getCryptoProvider();
        if (cryptoProvider == CryptoProvider.WIDEVINE_L1 && deviceCategory == DeviceCategory.PHONE) {
            return new WidevineL1PhoneEsnProvider(drmManager);
        }
        if (cryptoProvider == CryptoProvider.WIDEVINE_L1 && deviceCategory == DeviceCategory.TABLET) {
            return new WidevineL1TabletEsnProvider(drmManager);
        }
        if (cryptoProvider == CryptoProvider.WIDEVINE_L3 && deviceCategory == DeviceCategory.PHONE) {
            return new WidevineL3PhoneEsnProvider(drmManager);
        }
        if (cryptoProvider == CryptoProvider.WIDEVINE_L3 && deviceCategory == DeviceCategory.TABLET) {
            return new WidevineL3TabletEsnProvider(drmManager);
        }
        throw new IllegalStateException("Not able to create ESN provider for not supported combination. Device category: " + deviceCategory + ", Crypto provider: " + cryptoProvider);
    }
}
