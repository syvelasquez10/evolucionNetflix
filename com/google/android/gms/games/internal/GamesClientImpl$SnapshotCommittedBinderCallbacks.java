// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.snapshot.Snapshots$CommitSnapshotResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$SnapshotCommittedBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<Snapshots$CommitSnapshotResult> Xz;
    
    public GamesClientImpl$SnapshotCommittedBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<Snapshots$CommitSnapshotResult> baseImplementation$b) {
        this.Wr = wr;
        this.Xz = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void J(final DataHolder dataHolder) {
        this.Xz.b(new GamesClientImpl$CommitSnapshotResultImpl(dataHolder));
    }
}
