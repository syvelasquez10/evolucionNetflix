// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.esn;

import com.netflix.mediaclient.service.configuration.drm.WidevineDrmManager;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;
import android.content.Context;

public final class EsnProviderRegistry
{
    protected static final String TAG = "nf_esn";
    
    public static EsnProvider createESN(final Context context, final DrmManager drmManager) {
        Log.d("nf_esn", "Create ESN");
        final int androidVersion = AndroidUtils.getAndroidVersion();
        int n;
        if (drmManager.getDrmType() == 0) {
            n = 1;
        }
        else {
            n = 0;
        }
        BaseEsnProvider baseEsnProvider;
        if (n != 0) {
            if (androidVersion >= 18) {
                Log.d("nf_esn", "JB MR2+ device");
                if (WidevineDrmManager.isWidewineSupported()) {
                    Log.d("nf_esn", "JB MR2+ device with Widewine support, but failed to initialize or not allowed, return ESN Legacy implemenatation!");
                }
                else {
                    Log.d("nf_esn", "JB MR2+ device without Widewine support, return ESN Legacy implemenatation!");
                }
            }
            else {
                Log.d("nf_esn", "Pre JB MR2 device, return ESN Legacy implemenatation!");
            }
            baseEsnProvider = new EsnLegacyProvider();
        }
        else {
            Log.d("nf_esn", "JB MR2+ device with Widewine support, return ESN CDM implemenatation!");
            baseEsnProvider = new EsnCdmProvider(drmManager);
        }
        baseEsnProvider.initialize(context);
        if (Log.isLoggable("nf_esn", 3)) {
            Log.d("nf_esn", "ESN: " + baseEsnProvider.getEsn());
        }
        return baseEsnProvider;
    }
}
