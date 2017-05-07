// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.snapshot.Snapshots$LoadSnapshotsResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$SnapshotsLoadedBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<Snapshots$LoadSnapshotsResult> XB;
    
    public GamesClientImpl$SnapshotsLoadedBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<Snapshots$LoadSnapshotsResult> baseImplementation$b) {
        this.Wr = wr;
        this.XB = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void I(final DataHolder dataHolder) {
        this.XB.b(new GamesClientImpl$LoadSnapshotsResultImpl(dataHolder));
    }
}
