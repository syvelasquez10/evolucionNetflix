// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.snapshot.Snapshots$OpenSnapshotResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;

class SnapshotsImpl$5 extends SnapshotsImpl$OpenImpl
{
    final /* synthetic */ SnapshotsImpl ZA;
    final /* synthetic */ SnapshotMetadataChange ZE;
    final /* synthetic */ String ZG;
    final /* synthetic */ String ZH;
    final /* synthetic */ SnapshotContents ZI;
    
    SnapshotsImpl$5(final SnapshotsImpl za, final String zg, final String zh, final SnapshotMetadataChange ze, final SnapshotContents zi) {
        this.ZA = za;
        this.ZG = zg;
        this.ZH = zh;
        this.ZE = ze;
        this.ZI = zi;
        super((SnapshotsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Snapshots$OpenSnapshotResult>)this, this.ZG, this.ZH, this.ZE, this.ZI);
    }
}
