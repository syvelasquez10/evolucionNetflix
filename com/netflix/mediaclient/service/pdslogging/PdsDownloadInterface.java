// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import org.json.JSONObject;

public interface PdsDownloadInterface
{
    void onDownloadOfFirstTimeOfflineManifest(final String p0, final String p1, final String p2, final DownloadContext p3, final JSONObject p4);
}
