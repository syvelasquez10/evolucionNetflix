// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class PinAndAgeVerifier
{
    public static void verifyAgeAndPinForOfflineDownload(final NetflixActivity netflixActivity, final boolean b, final PlayVerifierVault playVerifierVault, final PinAndAgeVerifier$PinAndAgeVerifyCallback pinAndAgeVerifier$PinAndAgeVerifyCallback) {
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        if (serviceManager == null) {
            Log.w("nf_age", "serviceManager is null");
            return;
        }
        Log.v("nf_age", String.format(" isAgeProtected: %b, isAgeVerified: %b", b, serviceManager.isUserAgeVerified()));
        if (!b || serviceManager.isUserAgeVerified()) {
            verifyPinToContinue(netflixActivity, playVerifierVault, pinAndAgeVerifier$PinAndAgeVerifyCallback);
            return;
        }
        AgeVerifier.getInstance().verify(netflixActivity, playVerifierVault, pinAndAgeVerifier$PinAndAgeVerifyCallback);
    }
    
    public static void verifyAgeAndPinToPlay(final NetflixActivity netflixActivity, final boolean b, final PlayVerifierVault playVerifierVault) {
        if (netflixActivity.getServiceManager() == null) {
            Log.w("nf_age", "serviceManager is null");
            return;
        }
        Log.v("nf_age", String.format(" isAgeProtected: %b, isAgeVerified: %b", b, netflixActivity.getServiceManager().isUserAgeVerified()));
        if (!b || netflixActivity.getServiceManager().isUserAgeVerified()) {
            verifyPinToContinue(netflixActivity, playVerifierVault, netflixActivity);
            return;
        }
        AgeVerifier.getInstance().verify(netflixActivity, playVerifierVault, netflixActivity);
    }
    
    static void verifyPinToContinue(final NetflixActivity netflixActivity, final PlayVerifierVault playVerifierVault, final PinAndAgeVerifier$PinAndAgeVerifyCallback pinAndAgeVerifier$PinAndAgeVerifyCallback) {
        if (playVerifierVault != null && playVerifierVault.getAsset() != null) {
            PinVerifier.getInstance().verify(netflixActivity, playVerifierVault.getAsset().isPinProtected(), playVerifierVault, pinAndAgeVerifier$PinAndAgeVerifyCallback);
            return;
        }
        Log.e("nf_age", "vault or vault.asset null, skipping pin and play");
    }
}
