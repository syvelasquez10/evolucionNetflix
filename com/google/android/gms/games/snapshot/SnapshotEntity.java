// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import java.io.IOException;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class SnapshotEntity implements SafeParcelable, Snapshot
{
    public static final SnapshotEntityCreator CREATOR;
    private final int BR;
    private final SnapshotMetadataEntity acW;
    private final SnapshotContents acX;
    
    static {
        CREATOR = new SnapshotEntityCreator();
    }
    
    SnapshotEntity(final int br, final SnapshotMetadata snapshotMetadata, final SnapshotContents acX) {
        this.BR = br;
        this.acW = new SnapshotMetadataEntity(snapshotMetadata);
        this.acX = acX;
    }
    
    public SnapshotEntity(final SnapshotMetadata snapshotMetadata, final SnapshotContents snapshotContents) {
        this(2, snapshotMetadata, snapshotContents);
    }
    
    static boolean a(final Snapshot snapshot, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof Snapshot)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (snapshot != o) {
                final Snapshot snapshot2 = (Snapshot)o;
                if (m.equal(snapshot2.getMetadata(), snapshot.getMetadata())) {
                    b2 = b;
                    if (m.equal(snapshot2.getSnapshotContents(), snapshot.getSnapshotContents())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static int b(final Snapshot snapshot) {
        return m.hashCode(snapshot.getMetadata(), snapshot.getSnapshotContents());
    }
    
    static String c(final Snapshot snapshot) {
        return m.h(snapshot).a("Metadata", snapshot.getMetadata()).a("HasContents", snapshot.getSnapshotContents() != null).toString();
    }
    
    private boolean isClosed() {
        return this.acX.isClosed();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public Snapshot freeze() {
        return this;
    }
    
    @Override
    public Contents getContents() {
        if (this.isClosed()) {
            return null;
        }
        return this.acX.getContents();
    }
    
    @Override
    public SnapshotMetadata getMetadata() {
        return this.acW;
    }
    
    @Override
    public SnapshotContents getSnapshotContents() {
        if (this.isClosed()) {
            return null;
        }
        return this.acX;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return b(this);
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public boolean modifyBytes(final int n, final byte[] array, final int n2, final int n3) {
        return this.acX.modifyBytes(n, array, n2, n3);
    }
    
    @Override
    public byte[] readFully() {
        try {
            return this.acX.readFully();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public String toString() {
        return c(this);
    }
    
    @Override
    public boolean writeBytes(final byte[] array) {
        return this.acX.writeBytes(array);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        SnapshotEntityCreator.a(this, parcel, n);
    }
}
