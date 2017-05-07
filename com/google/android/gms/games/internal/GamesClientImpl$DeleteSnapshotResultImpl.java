// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots$DeleteSnapshotResult;

final class GamesClientImpl$DeleteSnapshotResultImpl implements Snapshots$DeleteSnapshotResult
{
    private final Status CM;
    private final String Wx;
    
    GamesClientImpl$DeleteSnapshotResultImpl(final int n, final String wx) {
        this.CM = new Status(n);
        this.Wx = wx;
    }
    
    @Override
    public String getSnapshotId() {
        return this.Wx;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
