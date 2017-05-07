// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.snapshot.Snapshots$DeleteSnapshotResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.games.snapshot.SnapshotMetadata;

class SnapshotsImpl$4 extends SnapshotsImpl$DeleteImpl
{
    final /* synthetic */ SnapshotsImpl ZA;
    final /* synthetic */ SnapshotMetadata ZF;
    
    SnapshotsImpl$4(final SnapshotsImpl za, final SnapshotMetadata zf) {
        this.ZA = za;
        this.ZF = zf;
        super((SnapshotsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.j((BaseImplementation$b<Snapshots$DeleteSnapshotResult>)this, this.ZF.getSnapshotId());
    }
}
