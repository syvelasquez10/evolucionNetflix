// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.crypto;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.MediaDrmUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.INetflixService;
import android.content.Context;

public final class CryptoManagerRegistry
{
    private static final String TAG = "nf_msl";
    private static MSLWidevineCryptoManager sCryptoManager;
    
    public static void createCryptoInstance(final Context context, final INetflixService netflixService, final CryptoManager$DrmReadyCallback cryptoManager$DrmReadyCallback) {
        while (true) {
            final CryptoProvider cryptoProvider;
            Label_0073: {
                synchronized (CryptoManagerRegistry.class) {
                    if (CryptoManagerRegistry.sCryptoManager != null) {
                        Log.e("nf_msl", "CryptoManagerRegistry::createCryptoInstance:  CryptoManager can not be instantiated more than once!");
                    }
                    else {
                        cryptoProvider = MediaDrmUtils.getCryptoProvider(context, netflixService.getConfiguration());
                        if (cryptoProvider != null && cryptoProvider != CryptoProvider.LEGACY) {
                            break Label_0073;
                        }
                        Log.w("nf_msl", "Legacy crypto provider, unable to create Crypto");
                        if (cryptoManager$DrmReadyCallback != null) {
                            cryptoManager$DrmReadyCallback.drmError(CommonStatus.MSL_FAILED_TO_CREATE_CLIENT);
                        }
                    }
                    return;
                }
            }
            if (cryptoProvider == CryptoProvider.WIDEVINE_L1) {
                Log.d("nf_msl", "CryptoManagerRegistry::createCryptoInstance: create CryptoManager Widevine L1 starts...");
                final Context context2;
                CryptoManagerRegistry.sCryptoManager = new MSLWidevineL1CryptoManager(context2, netflixService.getConfiguration(), cryptoManager$DrmReadyCallback);
            }
            else if (cryptoProvider == CryptoProvider.WIDEVINE_L3) {
                Log.d("nf_msl", "CryptoManagerRegistry::createCryptoInstance: create CryptoManager Widevine L3 starts...");
                final Context context2;
                CryptoManagerRegistry.sCryptoManager = new MSLWidevineL3CryptoManager(context2, netflixService.getConfiguration(), cryptoManager$DrmReadyCallback);
            }
            else {
                Log.e("nf_msl", "Not supported crypto: " + cryptoProvider);
                if (cryptoManager$DrmReadyCallback != null) {
                    cryptoManager$DrmReadyCallback.drmError(CommonStatus.MSL_FAILED_TO_CREATE_CLIENT);
                }
                return;
            }
            Log.d("nf_msl", "CryptoManagerRegistry::createCryptoInstance: create CryptoManager init...");
            CryptoManagerRegistry.sCryptoManager.init();
            Log.d("nf_msl", "CryptoManagerRegistry::createCryptoInstance: create CryptoManager done.");
        }
    }
    
    public static CryptoManager getCryptoManager() {
        synchronized (CryptoManagerRegistry.class) {
            return CryptoManagerRegistry.sCryptoManager;
        }
    }
    
    public static CryptoProvider getCryptoProvider() {
        synchronized (CryptoManagerRegistry.class) {
            CryptoProvider cryptoProvider;
            if (CryptoManagerRegistry.sCryptoManager == null) {
                ErrorLoggingManager.logHandledException("CryptoManagerRegistry:: crypto manager is NULL!");
                cryptoProvider = null;
            }
            else {
                cryptoProvider = CryptoManagerRegistry.sCryptoManager.getCryptoProvider();
            }
            return cryptoProvider;
        }
    }
}
