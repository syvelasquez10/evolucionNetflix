// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.panorama;

import com.google.android.gms.common.api.PendingResult;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;

public interface PanoramaApi
{
    PendingResult<PanoramaApi$PanoramaResult> loadPanoramaInfo(final GoogleApiClient p0, final Uri p1);
    
    PendingResult<PanoramaApi$PanoramaResult> loadPanoramaInfoAndGrantAccess(final GoogleApiClient p0, final Uri p1);
}
