// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots$CommitSnapshotResult;

class SnapshotsImpl$CommitImpl$1 implements Snapshots$CommitSnapshotResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ SnapshotsImpl$CommitImpl ZJ;
    
    SnapshotsImpl$CommitImpl$1(final SnapshotsImpl$CommitImpl zj, final Status cw) {
        this.ZJ = zj;
        this.CW = cw;
    }
    
    @Override
    public SnapshotMetadata getSnapshotMetadata() {
        return null;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
