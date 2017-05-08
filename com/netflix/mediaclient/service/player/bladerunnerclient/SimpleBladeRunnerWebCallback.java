// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

import com.netflix.mediaclient.service.player.bladerunnerclient.volley.ClientActionFromLase;
import java.util.Map;
import org.json.JSONObject;
import com.netflix.mediaclient.android.app.Status;

public abstract class SimpleBladeRunnerWebCallback implements BladeRunnerWebCallback
{
    @Override
    public void onDownloadComplete(final Status status, final String s) {
    }
    
    @Override
    public void onLicenseDeactivated(final Status status, final String s) {
    }
    
    @Override
    public void onLicenseFetched(final JSONObject jsonObject, final Status status) {
    }
    
    @Override
    public void onLinkDone(final JSONObject jsonObject, final Status status) {
    }
    
    @Override
    public void onManifestsFetched(final JSONObject jsonObject, final Status status) {
    }
    
    @Override
    public void onOfflineLicenseFetched(final OfflineLicenseResponse offlineLicenseResponse, final Status status) {
    }
    
    @Override
    public void onPdsEventSent(final Status status, final IBladeRunnerClient$PdsEventType bladeRunnerClient$PdsEventType, final long n, final JSONObject jsonObject) {
    }
    
    @Override
    public void onPdsSessionStart(final Status status, final long n, final String s) {
    }
    
    @Override
    public void onSyncLicenseDone(final Map<String, ClientActionFromLase> map, final Status status) {
    }
}
