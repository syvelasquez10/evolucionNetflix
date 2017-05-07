// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.snapshot.Snapshots$OpenSnapshotResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$SnapshotOpenedBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<Snapshots$OpenSnapshotResult> XA;
    
    public GamesClientImpl$SnapshotOpenedBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<Snapshots$OpenSnapshotResult> baseImplementation$b) {
        this.Wr = wr;
        this.XA = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void a(final DataHolder dataHolder, final Contents contents) {
        this.XA.b(new GamesClientImpl$OpenSnapshotResultImpl(dataHolder, contents));
    }
    
    @Override
    public void a(final DataHolder dataHolder, final String s, final Contents contents, final Contents contents2, final Contents contents3) {
        this.XA.b(new GamesClientImpl$OpenSnapshotResultImpl(dataHolder, s, contents, contents2, contents3));
    }
}
