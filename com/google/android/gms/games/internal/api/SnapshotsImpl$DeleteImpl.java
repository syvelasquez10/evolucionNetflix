// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots$DeleteSnapshotResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class SnapshotsImpl$DeleteImpl extends Games$BaseGamesApiMethodImpl<Snapshots$DeleteSnapshotResult>
{
    public Snapshots$DeleteSnapshotResult ap(final Status status) {
        return new SnapshotsImpl$DeleteImpl$1(this, status);
    }
}
