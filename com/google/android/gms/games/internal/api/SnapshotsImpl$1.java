// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.snapshot.Snapshots$LoadSnapshotsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class SnapshotsImpl$1 extends SnapshotsImpl$LoadImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ SnapshotsImpl ZA;
    
    SnapshotsImpl$1(final SnapshotsImpl za, final boolean xu) {
        this.ZA = za;
        this.XU = xu;
        super((SnapshotsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.e((BaseImplementation$b<Snapshots$LoadSnapshotsResult>)this, this.XU);
    }
}
