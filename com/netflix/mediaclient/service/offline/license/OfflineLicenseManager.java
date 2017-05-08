// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import java.util.List;
import com.netflix.mediaclient.service.player.bladerunnerclient.IBladeRunnerClient$OfflineRefreshInvoke;

public interface OfflineLicenseManager
{
    void abortAllRequestsForPlayable(final String p0);
    
    void deleteLicense(final String p0, final byte[] p1, final boolean p2, final String p3, final OfflineLicenseManagerCallback p4);
    
    void destroy();
    
    void downloadCompleteAndActivateLicense(final String p0, final String p1, final OfflineLicenseManager$DownloadCompleteAndActivateCallback p2);
    
    void refreshLicense(final IBladeRunnerClient$OfflineRefreshInvoke p0, final String p1, final byte[] p2, final byte[] p3, final String p4, final OfflineLicenseManagerCallback p5);
    
    void requestNewLicense(final String p0, final byte[] p1, final String p2, final OfflineLicenseManagerCallback p3);
    
    void trySyncActiveLicensesToServer(final List<String> p0);
}
