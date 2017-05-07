// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots$DeleteSnapshotResult;

class SnapshotsImpl$DeleteImpl$1 implements Snapshots$DeleteSnapshotResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ SnapshotsImpl$DeleteImpl ZK;
    
    SnapshotsImpl$DeleteImpl$1(final SnapshotsImpl$DeleteImpl zk, final Status cw) {
        this.ZK = zk;
        this.CW = cw;
    }
    
    @Override
    public String getSnapshotId() {
        return null;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
