// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.Snapshots$OpenSnapshotResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$OpenSnapshotResultImpl extends a implements Snapshots$OpenSnapshotResult
{
    private final Snapshot Xb;
    private final String Xc;
    private final Snapshot Xd;
    private final Contents Xe;
    private final SnapshotContents Xf;
    
    GamesClientImpl$OpenSnapshotResultImpl(final DataHolder dataHolder, final Contents contents) {
        this(dataHolder, null, contents, null, null);
    }
    
    GamesClientImpl$OpenSnapshotResultImpl(final DataHolder dataHolder, final String xc, final Contents contents, final Contents contents2, final Contents xe) {
    Label_0036_Outer:
        while (true) {
            boolean b = true;
            super(dataHolder);
            final SnapshotMetadataBuffer snapshotMetadataBuffer = new SnapshotMetadataBuffer(dataHolder);
            while (true) {
                Label_0144: {
                    while (true) {
                        Label_0138: {
                            try {
                                if (snapshotMetadataBuffer.getCount() == 0) {
                                    this.Xb = null;
                                    this.Xd = null;
                                }
                                else {
                                    if (snapshotMetadataBuffer.getCount() != 1) {
                                        break Label_0144;
                                    }
                                    if (dataHolder.getStatusCode() == 4004) {
                                        break Label_0138;
                                    }
                                    com.google.android.gms.common.internal.a.I(b);
                                    this.Xb = new SnapshotEntity(new SnapshotMetadataEntity(snapshotMetadataBuffer.get(0)), new SnapshotContents(contents));
                                    this.Xd = null;
                                }
                                snapshotMetadataBuffer.release();
                                this.Xc = xc;
                                this.Xe = xe;
                                this.Xf = new SnapshotContents(xe);
                                return;
                            }
                            finally {
                                snapshotMetadataBuffer.release();
                            }
                        }
                        b = false;
                        continue Label_0036_Outer;
                    }
                }
                this.Xb = new SnapshotEntity(new SnapshotMetadataEntity(snapshotMetadataBuffer.get(0)), new SnapshotContents(contents));
                this.Xd = new SnapshotEntity(new SnapshotMetadataEntity(snapshotMetadataBuffer.get(1)), new SnapshotContents(contents2));
                continue;
            }
        }
    }
    
    @Override
    public String getConflictId() {
        return this.Xc;
    }
    
    @Override
    public Snapshot getConflictingSnapshot() {
        return this.Xd;
    }
    
    @Deprecated
    @Override
    public Contents getResolutionContents() {
        return this.Xe;
    }
    
    @Override
    public SnapshotContents getResolutionSnapshotContents() {
        return this.Xf;
    }
    
    @Override
    public Snapshot getSnapshot() {
        return this.Xb;
    }
}
