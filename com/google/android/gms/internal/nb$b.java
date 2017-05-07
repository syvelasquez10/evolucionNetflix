// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.panorama.PanoramaApi$PanoramaResult;

abstract class nb$b extends nb$d<PanoramaApi$PanoramaResult>
{
    protected PanoramaApi$PanoramaResult az(final Status status) {
        return new nd(status, null);
    }
}
