// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.snapshot.Snapshots$CommitSnapshotResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshot;

class SnapshotsImpl$3 extends SnapshotsImpl$CommitImpl
{
    final /* synthetic */ SnapshotsImpl ZA;
    final /* synthetic */ Snapshot ZD;
    final /* synthetic */ SnapshotMetadataChange ZE;
    
    SnapshotsImpl$3(final SnapshotsImpl za, final Snapshot zd, final SnapshotMetadataChange ze) {
        this.ZA = za;
        this.ZD = zd;
        this.ZE = ze;
        super((SnapshotsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Snapshots$CommitSnapshotResult>)this, this.ZD, this.ZE);
    }
}
