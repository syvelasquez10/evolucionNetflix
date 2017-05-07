// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots$OpenSnapshotResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class SnapshotsImpl$OpenImpl extends Games$BaseGamesApiMethodImpl<Snapshots$OpenSnapshotResult>
{
    public Snapshots$OpenSnapshotResult ar(final Status status) {
        return new SnapshotsImpl$OpenImpl$1(this, status);
    }
}
