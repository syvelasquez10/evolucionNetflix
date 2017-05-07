// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.Contents;
import android.os.Bundle;
import android.content.Intent;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface Snapshots
{
    public static final int DISPLAY_LIMIT_NONE = -1;
    public static final String EXTRA_SNAPSHOT_METADATA = "com.google.android.gms.games.SNAPSHOT_METADATA";
    public static final String EXTRA_SNAPSHOT_NEW = "com.google.android.gms.games.SNAPSHOT_NEW";
    
    PendingResult<CommitSnapshotResult> commitAndClose(final GoogleApiClient p0, final Snapshot p1, final SnapshotMetadataChange p2);
    
    PendingResult<DeleteSnapshotResult> delete(final GoogleApiClient p0, final SnapshotMetadata p1);
    
    void discardAndClose(final GoogleApiClient p0, final Snapshot p1);
    
    int getMaxCoverImageSize(final GoogleApiClient p0);
    
    int getMaxDataSize(final GoogleApiClient p0);
    
    Intent getSelectSnapshotIntent(final GoogleApiClient p0, final String p1, final boolean p2, final boolean p3, final int p4);
    
    SnapshotMetadata getSnapshotFromBundle(final Bundle p0);
    
    PendingResult<LoadSnapshotsResult> load(final GoogleApiClient p0, final boolean p1);
    
    PendingResult<OpenSnapshotResult> open(final GoogleApiClient p0, final SnapshotMetadata p1);
    
    PendingResult<OpenSnapshotResult> open(final GoogleApiClient p0, final String p1, final boolean p2);
    
    PendingResult<OpenSnapshotResult> resolveConflict(final GoogleApiClient p0, final String p1, final Snapshot p2);
    
    @Deprecated
    PendingResult<OpenSnapshotResult> resolveConflict(final GoogleApiClient p0, final String p1, final String p2, final SnapshotMetadataChange p3, final Contents p4);
    
    PendingResult<OpenSnapshotResult> resolveConflict(final GoogleApiClient p0, final String p1, final String p2, final SnapshotMetadataChange p3, final SnapshotContents p4);
    
    public interface CommitSnapshotResult extends Result
    {
        SnapshotMetadata getSnapshotMetadata();
    }
    
    public interface DeleteSnapshotResult extends Result
    {
        String getSnapshotId();
    }
    
    public interface LoadSnapshotsResult extends Releasable, Result
    {
        SnapshotMetadataBuffer getSnapshots();
    }
    
    public interface OpenSnapshotResult extends Result
    {
        String getConflictId();
        
        Snapshot getConflictingSnapshot();
        
        @Deprecated
        Contents getResolutionContents();
        
        SnapshotContents getResolutionSnapshotContents();
        
        Snapshot getSnapshot();
    }
}
