// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBuffer;

public final class SnapshotMetadataBuffer extends DataBuffer<SnapshotMetadata>
{
    public SnapshotMetadataBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    public SnapshotMetadata get(final int n) {
        return new SnapshotMetadataRef(this.IC, n);
    }
}
