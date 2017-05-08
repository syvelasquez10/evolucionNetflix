// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import java.util.List;
import com.netflix.mediaclient.service.player.bladerunnerclient.IBladeRunnerClient$OfflineRefreshInvoke;

public interface OfflineLicenseManager
{
    void abortAllRequestsForPlayable(final String p0);
    
    void deleteLicense(final String p0, final byte[] p1, final boolean p2, final String p3, final String p4, final String p5, final OfflineLicenseManagerCallback p6);
    
    void destroy();
    
    void refreshLicense(final IBladeRunnerClient$OfflineRefreshInvoke p0, final String p1, final byte[] p2, final byte[] p3, final String p4, final String p5, final String p6, final OfflineLicenseManagerCallback p7);
    
    void requestNewLicense(final String p0, final byte[] p1, final String p2, final String p3, final String p4, final OfflineLicenseManagerCallback p5);
    
    void sendSyncActiveLicensesToServer(final List<String> p0, final OfflineLicenseManager$LicenseSyncResponseCallback p1);
}
