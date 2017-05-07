// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.android.app.Status;

public interface DrmManager$DrmReadyCallback
{
    void drmError(final Status p0);
    
    void drmReady();
}
