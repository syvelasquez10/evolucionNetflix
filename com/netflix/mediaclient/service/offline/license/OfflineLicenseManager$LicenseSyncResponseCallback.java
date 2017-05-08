// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.volley.ClientActionFromLase;
import java.util.Map;

public interface OfflineLicenseManager$LicenseSyncResponseCallback
{
    void onLicenseSyncDone(final Map<String, ClientActionFromLase> p0, final Status p1);
}
