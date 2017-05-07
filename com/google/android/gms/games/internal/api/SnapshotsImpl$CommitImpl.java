// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots$CommitSnapshotResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class SnapshotsImpl$CommitImpl extends Games$BaseGamesApiMethodImpl<Snapshots$CommitSnapshotResult>
{
    public Snapshots$CommitSnapshotResult ao(final Status status) {
        return new SnapshotsImpl$CommitImpl$1(this, status);
    }
}
