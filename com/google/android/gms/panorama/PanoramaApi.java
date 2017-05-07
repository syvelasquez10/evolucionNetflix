// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.panorama;

import android.content.Intent;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.PendingResult;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;

public interface PanoramaApi
{
    PendingResult<PanoramaResult> loadPanoramaInfo(final GoogleApiClient p0, final Uri p1);
    
    PendingResult<PanoramaResult> loadPanoramaInfoAndGrantAccess(final GoogleApiClient p0, final Uri p1);
    
    public interface PanoramaResult extends Result
    {
        Intent getViewerIntent();
    }
    
    public interface a extends PanoramaResult
    {
    }
}
