// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.api.Result;

public interface Snapshots$OpenSnapshotResult extends Result
{
    String getConflictId();
    
    Snapshot getConflictingSnapshot();
    
    @Deprecated
    Contents getResolutionContents();
    
    SnapshotContents getResolutionSnapshotContents();
    
    Snapshot getSnapshot();
}
