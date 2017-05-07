// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots$LoadSnapshotsResult;

class SnapshotsImpl$LoadImpl$1 implements Snapshots$LoadSnapshotsResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ SnapshotsImpl$LoadImpl ZL;
    
    SnapshotsImpl$LoadImpl$1(final SnapshotsImpl$LoadImpl zl, final Status cw) {
        this.ZL = zl;
        this.CW = cw;
    }
    
    @Override
    public SnapshotMetadataBuffer getSnapshots() {
        return new SnapshotMetadataBuffer(DataHolder.as(14));
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
