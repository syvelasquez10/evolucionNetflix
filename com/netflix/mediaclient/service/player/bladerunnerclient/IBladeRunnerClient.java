// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

import java.util.List;
import com.netflix.mediaclient.service.player.manifest.NfManifest;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadVideoQuality;
import com.netflix.mediaclient.service.player.drm.BaseLicenseContext;

public interface IBladeRunnerClient
{
    public static final int BLADERUNNER_VERSION = 2;
    
    void deactivateOfflineLicense(final String p0, final String p1, final boolean p2, final BladeRunnerWebCallback p3);
    
    void downloadComplete(final String p0, final BladeRunnerWebCallback p1);
    
    void fetchLicense(final BaseLicenseContext p0, final BladeRunnerWebCallback p1);
    
    void fetchManifests(final String[] p0, final IBladeRunnerClient$ManifestRequestFlavor p1, final BladeRunnerWebCallback p2);
    
    void fetchOfflineManifest(final String p0, final String p1, final String p2, final DownloadVideoQuality p3, final BladeRunnerWebCallback p4);
    
    void refreshOfflineLicense(final IBladeRunnerClient$OfflineRefreshInvoke p0, final String p1, final String p2, final BladeRunnerWebCallback p3);
    
    void refreshOfflineManifest(final String p0, final String p1, final String p2, final DownloadVideoQuality p3, final NfManifest p4, final BladeRunnerWebCallback p5);
    
    void syncActiveLicensesToServer(final List<String> p0, final BladeRunnerWebCallback p1);
}
