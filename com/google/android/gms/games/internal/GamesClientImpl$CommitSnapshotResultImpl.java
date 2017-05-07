// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.Snapshots$CommitSnapshotResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$CommitSnapshotResultImpl extends a implements Snapshots$CommitSnapshotResult
{
    private final SnapshotMetadata Ww;
    
    GamesClientImpl$CommitSnapshotResultImpl(DataHolder dataHolder) {
        super(dataHolder);
        dataHolder = (DataHolder)new SnapshotMetadataBuffer(dataHolder);
        try {
            if (((DataBuffer)dataHolder).getCount() > 0) {
                this.Ww = new SnapshotMetadataEntity(((SnapshotMetadataBuffer)dataHolder).get(0));
            }
            else {
                this.Ww = null;
            }
        }
        finally {
            ((DataBuffer)dataHolder).release();
        }
    }
    
    @Override
    public SnapshotMetadata getSnapshotMetadata() {
        return this.Ww;
    }
}
