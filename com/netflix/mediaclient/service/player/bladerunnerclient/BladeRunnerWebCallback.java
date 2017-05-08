// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

import org.json.JSONObject;
import com.netflix.mediaclient.android.app.Status;

public interface BladeRunnerWebCallback
{
    void onDownloadComplete(final Status p0, final String p1);
    
    void onLicenseDeactivated(final Status p0, final String p1);
    
    void onLicenseFetched(final JSONObject p0, final Status p1);
    
    void onLinkDone(final JSONObject p0, final Status p1);
    
    void onManifestsFetched(final JSONObject p0, final Status p1);
    
    void onOfflineLicenseFetched(final OfflineLicenseResponse p0, final Status p1);
    
    void onPdsEventSent(final Status p0, final IBladeRunnerClient$PdsEventType p1, final long p2, final JSONObject p3);
    
    void onPdsSessionStart(final Status p0, final long p1, final String p2);
    
    void onSyncDone(final Status p0);
}
