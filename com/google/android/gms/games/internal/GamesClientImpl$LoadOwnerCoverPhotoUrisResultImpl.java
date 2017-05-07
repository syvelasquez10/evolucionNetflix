// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Players$LoadOwnerCoverPhotoUrisResult;

final class GamesClientImpl$LoadOwnerCoverPhotoUrisResultImpl implements Players$LoadOwnerCoverPhotoUrisResult
{
    private final Status CM;
    private final Bundle MZ;
    
    GamesClientImpl$LoadOwnerCoverPhotoUrisResultImpl(final int n, final Bundle mz) {
        this.CM = new Status(n);
        this.MZ = mz;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
