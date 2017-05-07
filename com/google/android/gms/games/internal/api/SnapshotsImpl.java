// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.drive.Contents;
import android.os.Bundle;
import android.content.Intent;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.snapshot.Snapshots;

public final class SnapshotsImpl implements Snapshots
{
    @Override
    public PendingResult<CommitSnapshotResult> commitAndClose(final GoogleApiClient googleApiClient, final Snapshot snapshot, final SnapshotMetadataChange snapshotMetadataChange) {
        return googleApiClient.b((PendingResult<CommitSnapshotResult>)new CommitImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<CommitSnapshotResult>)this, snapshot, snapshotMetadataChange);
            }
        });
    }
    
    @Override
    public PendingResult<DeleteSnapshotResult> delete(final GoogleApiClient googleApiClient, final SnapshotMetadata snapshotMetadata) {
        return googleApiClient.b((PendingResult<DeleteSnapshotResult>)new DeleteImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.j((BaseImplementation.b<DeleteSnapshotResult>)this, snapshotMetadata.getSnapshotId());
            }
        });
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
    public PendingResult<LoadSnapshotsResult> load(final GoogleApiClient googleApiClient, final boolean b) {
        return googleApiClient.a((PendingResult<LoadSnapshotsResult>)new LoadImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.e((BaseImplementation.b<LoadSnapshotsResult>)this, b);
            }
        });
    }
    
    @Override
    public PendingResult<OpenSnapshotResult> open(final GoogleApiClient googleApiClient, final SnapshotMetadata snapshotMetadata) {
        return this.open(googleApiClient, snapshotMetadata.getUniqueName(), false);
    }
    
    @Override
    public PendingResult<OpenSnapshotResult> open(final GoogleApiClient googleApiClient, final String s, final boolean b) {
        return googleApiClient.b((PendingResult<OpenSnapshotResult>)new OpenImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((BaseImplementation.b<OpenSnapshotResult>)this, s, b);
            }
        });
    }
    
    @Override
    public PendingResult<OpenSnapshotResult> resolveConflict(final GoogleApiClient googleApiClient, final String s, final Snapshot snapshot) {
        final SnapshotMetadata metadata = snapshot.getMetadata();
        return this.resolveConflict(googleApiClient, s, metadata.getSnapshotId(), new SnapshotMetadataChange.Builder().fromMetadata(metadata).build(), snapshot.getSnapshotContents());
    }
    
    @Override
    public PendingResult<OpenSnapshotResult> resolveConflict(final GoogleApiClient googleApiClient, final String s, final String s2, final SnapshotMetadataChange snapshotMetadataChange, final Contents contents) {
        return googleApiClient.b((PendingResult<OpenSnapshotResult>)new OpenImpl() {
            final /* synthetic */ SnapshotContents ZI = new SnapshotContents(contents);
            
            protected void a(final GamesClientImpl gamesClientImpl) throws RemoteException {
                gamesClientImpl.a((BaseImplementation.b<OpenSnapshotResult>)this, s, s2, snapshotMetadataChange, this.ZI);
            }
        });
    }
    
    @Override
    public PendingResult<OpenSnapshotResult> resolveConflict(final GoogleApiClient googleApiClient, final String s, final String s2, final SnapshotMetadataChange snapshotMetadataChange, final SnapshotContents snapshotContents) {
        return googleApiClient.b((PendingResult<OpenSnapshotResult>)new OpenImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) throws RemoteException {
                gamesClientImpl.a((BaseImplementation.b<OpenSnapshotResult>)this, s, s2, snapshotMetadataChange, snapshotContents);
            }
        });
    }
    
    private abstract static class CommitImpl extends BaseGamesApiMethodImpl<CommitSnapshotResult>
    {
        public CommitSnapshotResult ao(final Status status) {
            return new CommitSnapshotResult() {
                @Override
                public SnapshotMetadata getSnapshotMetadata() {
                    return null;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class DeleteImpl extends BaseGamesApiMethodImpl<DeleteSnapshotResult>
    {
        public DeleteSnapshotResult ap(final Status status) {
            return new DeleteSnapshotResult() {
                @Override
                public String getSnapshotId() {
                    return null;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class LoadImpl extends BaseGamesApiMethodImpl<LoadSnapshotsResult>
    {
        public LoadSnapshotsResult aq(final Status status) {
            return new LoadSnapshotsResult() {
                @Override
                public SnapshotMetadataBuffer getSnapshots() {
                    return new SnapshotMetadataBuffer(DataHolder.as(14));
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public void release() {
                }
            };
        }
    }
    
    private abstract static class OpenImpl extends BaseGamesApiMethodImpl<OpenSnapshotResult>
    {
        public OpenSnapshotResult ar(final Status status) {
            return new OpenSnapshotResult() {
                @Override
                public String getConflictId() {
                    return null;
                }
                
                @Override
                public Snapshot getConflictingSnapshot() {
                    return null;
                }
                
                @Override
                public Contents getResolutionContents() {
                    return null;
                }
                
                @Override
                public SnapshotContents getResolutionSnapshotContents() {
                    return null;
                }
                
                @Override
                public Snapshot getSnapshot() {
                    return null;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
}
