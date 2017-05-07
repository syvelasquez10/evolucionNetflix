// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.snapshot.Snapshots$LoadSnapshotsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class SnapshotsImpl$7 extends SnapshotsImpl$LoadImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ String XW;
    final /* synthetic */ String XX;
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.c((BaseImplementation$b<Snapshots$LoadSnapshotsResult>)this, this.XW, this.XX, this.XU);
    }
}
