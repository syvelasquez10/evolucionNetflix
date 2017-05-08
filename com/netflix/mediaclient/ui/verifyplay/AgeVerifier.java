// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;

public class AgeVerifier
{
    public static final boolean FORCE_AGE_VERIFY = false;
    public static final String TAG = "nf_age";
    private static AgeVerifier sAgeVerifyInstance;
    
    static {
        AgeVerifier.sAgeVerifyInstance = new AgeVerifier();
    }
    
    public static AgeVerifier getInstance() {
        return AgeVerifier.sAgeVerifyInstance;
    }
    
    private boolean shouldHandleNewRequest(final PlayVerifierVault playVerifierVault) {
        if (PlayVerifierVault$RequestedBy.MDX.getValue().equals(playVerifierVault.getInvokeLocation())) {
            if (Log.isLoggable()) {
                Log.d("nf_age", String.format("Dismissing new request new one Invoked from: %s", playVerifierVault.getInvokeLocation()));
            }
            return false;
        }
        return true;
    }
    
    public void verify(final NetflixActivity netflixActivity, final PlayVerifierVault playVerifierVault, final PinAndAgeVerifier$PinAndAgeVerifyCallback ageVerifierCallback) {
        if (Log.isLoggable()) {
            Log.d("nf_age", String.format("verifyAge invokeLoc:%s, vault:%s", playVerifierVault.getInvokeLocation(), playVerifierVault));
        }
        if (!this.shouldHandleNewRequest(playVerifierVault)) {
            return;
        }
        final AgeDialog ageDialog = AgeDialog.createAgeDialog(playVerifierVault);
        ageDialog.setAgeVerifierCallback(ageVerifierCallback);
        ageDialog.show(netflixActivity.getFragmentManager(), "frag_dialog");
    }
}
