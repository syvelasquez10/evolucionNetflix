// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface Snapshots$LoadSnapshotsResult extends Releasable, Result
{
    SnapshotMetadataBuffer getSnapshots();
}
