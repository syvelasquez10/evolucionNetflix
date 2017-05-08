// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.OfflineLicenseResponse;

public interface OfflineLicenseManagerCallback
{
    void onOfflineLicenseRequestDone(final String p0, final OfflineLicenseResponse p1, final Status p2);
}
