// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.crypto;

import com.netflix.mediaclient.android.app.Status;

public interface CryptoManager$DrmReadyCallback
{
    void drmError(final Status p0);
    
    void drmReady();
    
    void drmResoureReclaimed();
}
