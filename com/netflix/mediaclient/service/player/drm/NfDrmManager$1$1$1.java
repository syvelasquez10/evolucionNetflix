// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.drm;

import org.json.JSONObject;

class NfDrmManager$1$1$1 implements Runnable
{
    final /* synthetic */ NfDrmManager$1$1 this$2;
    final /* synthetic */ JSONObject val$license;
    
    NfDrmManager$1$1$1(final NfDrmManager$1$1 this$2, final JSONObject val$license) {
        this.this$2 = this$2;
        this.val$license = val$license;
    }
    
    @Override
    public void run() {
        final NfDrmSession drmSession = this.this$2.this$1.this$0.getDrmSession(this.this$2.val$sessionId, null);
        if (drmSession != null) {
            final LicenseContext licenseContext = drmSession.getLicenseContext();
            licenseContext.addLicenseReponse(this.val$license);
            if (drmSession.getInUse()) {
                drmSession.provideKeyResponse(licenseContext.getLicenseData());
            }
        }
    }
}
