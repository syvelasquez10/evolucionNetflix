// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange$Builder;
import com.google.android.gms.games.snapshot.Snapshots$OpenSnapshotResult;
import com.google.android.gms.games.snapshot.Snapshots$LoadSnapshotsResult;
import android.os.Bundle;
import android.content.Intent;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshots$DeleteSnapshotResult;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.Snapshots$CommitSnapshotResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.snapshot.Snapshots;

public final class SnapshotsImpl implements Snapshots
{
    @Override
    public PendingResult<Snapshots$CommitSnapshotResult> commitAndClose(final GoogleApiClient googleApiClient, final Snapshot snapshot, final SnapshotMetadataChange snapshotMetadataChange) {
        return googleApiClient.b((PendingResult<Snapshots$CommitSnapshotResult>)new SnapshotsImpl$3(this, snapshot, snapshotMetadataChange));
    }
    
    @Override
    public PendingResult<Snapshots$DeleteSnapshotResult> delete(final GoogleApiClient googleApiClient, final SnapshotMetadata snapshotMetadata) {
        return googleApiClient.b((PendingResult<Snapshots$DeleteSnapshotResult>)new SnapshotsImpl$4(this, snapshotMetadata));
    }
    
    @Override
    public void discardAndClose(final GoogleApiClient googleApiClient, final Snapshot snapshot) {
        Games.c(googleApiClient).a(snapshot);
    }
    
    @Override
    public int getMaxCoverImageSize(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).ks();
    }
    
    @Override
    public int getMaxDataSize(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).kr();
    }
    
    @Override
    public Intent getSelectSnapshotIntent(final GoogleApiClient googleApiClient, final String s, final boolean b, final boolean b2, final int n) {
        return Games.c(googleApiClient).a(s, b, b2, n);
    }
    
    @Override
    public SnapshotMetadata getSnapshotFromBundle(final Bundle bundle) {
        if (bundle == null || !bundle.containsKey("com.google.android.gms.games.SNAPSHOT_METADATA")) {
            return null;
        }
        return (SnapshotMetadata)bundle.getParcelable("com.google.android.gms.games.SNAPSHOT_METADATA");
    }
    
    @Override
    public PendingResult<Snapshots$LoadSnapshotsResult> load(final GoogleApiClient googleApiClient, final boolean b) {
        return googleApiClient.a((PendingResult<Snapshots$LoadSnapshotsResult>)new SnapshotsImpl$1(this, b));
    }
    
    @Override
    public PendingResult<Snapshots$OpenSnapshotResult> open(final GoogleApiClient googleApiClient, final SnapshotMetadata snapshotMetadata) {
        return this.open(googleApiClient, snapshotMetadata.getUniqueName(), false);
    }
    
    @Override
    public PendingResult<Snapshots$OpenSnapshotResult> open(final GoogleApiClient googleApiClient, final String s, final boolean b) {
        return googleApiClient.b((PendingResult<Snapshots$OpenSnapshotResult>)new SnapshotsImpl$2(this, s, b));
    }
    
    @Override
    public PendingResult<Snapshots$OpenSnapshotResult> resolveConflict(final GoogleApiClient googleApiClient, final String s, final Snapshot snapshot) {
        final SnapshotMetadata metadata = snapshot.getMetadata();
        return this.resolveConflict(googleApiClient, s, metadata.getSnapshotId(), new SnapshotMetadataChange$Builder().fromMetadata(metadata).build(), snapshot.getSnapshotContents());
    }
    
    @Override
    public PendingResult<Snapshots$OpenSnapshotResult> resolveConflict(final GoogleApiClient googleApiClient, final String s, final String s2, final SnapshotMetadataChange snapshotMetadataChange, final Contents contents) {
        return googleApiClient.b((PendingResult<Snapshots$OpenSnapshotResult>)new SnapshotsImpl$5(this, s, s2, snapshotMetadataChange, new SnapshotContents(contents)));
    }
    
    @Override
    public PendingResult<Snapshots$OpenSnapshotResult> resolveConflict(final GoogleApiClient googleApiClient, final String s, final String s2, final SnapshotMetadataChange snapshotMetadataChange, final SnapshotContents snapshotContents) {
        return googleApiClient.b((PendingResult<Snapshots$OpenSnapshotResult>)new SnapshotsImpl$6(this, s, s2, snapshotMetadataChange, snapshotContents));
    }
}
