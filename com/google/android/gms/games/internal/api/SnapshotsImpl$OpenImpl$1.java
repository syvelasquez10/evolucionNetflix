// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots$OpenSnapshotResult;

class SnapshotsImpl$OpenImpl$1 implements Snapshots$OpenSnapshotResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ SnapshotsImpl$OpenImpl ZM;
    
    SnapshotsImpl$OpenImpl$1(final SnapshotsImpl$OpenImpl zm, final Status cw) {
        this.ZM = zm;
        this.CW = cw;
    }
    
    @Override
    public String getConflictId() {
        return null;
    }
    
    @Override
    public Snapshot getConflictingSnapshot() {
        return null;
    }
    
    @Override
    public Contents getResolutionContents() {
        return null;
    }
    
    @Override
    public SnapshotContents getResolutionSnapshotContents() {
        return null;
    }
    
    @Override
    public Snapshot getSnapshot() {
        return null;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
