// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class PlayVerifier
{
    public static void verify(final NetflixActivity netflixActivity, final boolean b, final boolean b2, final PlayVerifierVault playVerifierVault) {
        if (netflixActivity.getServiceManager() == null) {
            Log.w("nf_age", "serviceManager is null");
            return;
        }
        Log.v("nf_age", String.format(" isAgeProtected: %b, isAgeVerified: %b", b, netflixActivity.getServiceManager().isUserAgeVerified()));
        if (!b || netflixActivity.getServiceManager().isUserAgeVerified()) {
            verifyPinToContinue(netflixActivity, playVerifierVault);
            return;
        }
        AgeVerifier.getInstance().verify(netflixActivity, playVerifierVault);
    }
    
    public static void verifyPinToContinue(final NetflixActivity netflixActivity, final PlayVerifierVault playVerifierVault) {
        if (playVerifierVault != null && playVerifierVault.getAsset() != null) {
            PinVerifier.getInstance().verify(netflixActivity, playVerifierVault.getAsset().isPinProtected(), playVerifierVault);
            return;
        }
        Log.e("nf_age", "vault or valut.asset null, skipping pin and play");
    }
}
