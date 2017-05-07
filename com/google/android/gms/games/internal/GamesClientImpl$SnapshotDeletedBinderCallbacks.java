// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.snapshot.Snapshots$DeleteSnapshotResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$SnapshotDeletedBinderCallbacks extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Snapshots$DeleteSnapshotResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    public GamesClientImpl$SnapshotDeletedBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<Snapshots$DeleteSnapshotResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void i(final int n, final String s) {
        this.De.b(new GamesClientImpl$DeleteSnapshotResultImpl(n, s));
    }
}
