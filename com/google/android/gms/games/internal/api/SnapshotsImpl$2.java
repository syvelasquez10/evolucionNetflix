// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.snapshot.Snapshots$OpenSnapshotResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class SnapshotsImpl$2 extends SnapshotsImpl$OpenImpl
{
    final /* synthetic */ SnapshotsImpl ZA;
    final /* synthetic */ String ZB;
    final /* synthetic */ boolean ZC;
    
    SnapshotsImpl$2(final SnapshotsImpl za, final String zb, final boolean zc) {
        this.ZA = za;
        this.ZB = zb;
        this.ZC = zc;
        super((SnapshotsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.b((BaseImplementation$b<Snapshots$OpenSnapshotResult>)this, this.ZB, this.ZC);
    }
}
