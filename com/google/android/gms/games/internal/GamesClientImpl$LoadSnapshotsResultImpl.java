// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.snapshot.Snapshots$LoadSnapshotsResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$LoadSnapshotsResultImpl extends a implements Snapshots$LoadSnapshotsResult
{
    GamesClientImpl$LoadSnapshotsResultImpl(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    public SnapshotMetadataBuffer getSnapshots() {
        return new SnapshotMetadataBuffer(this.IC);
    }
}
