// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class SnapshotEntityCreator implements Parcelable$Creator<SnapshotEntity>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final SnapshotEntity snapshotEntity, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)snapshotEntity.getMetadata(), n, false);
        b.c(parcel, 1000, snapshotEntity.getVersionCode());
        b.a(parcel, 3, (Parcelable)snapshotEntity.getSnapshotContents(), n, false);
        b.H(parcel, d);
    }
    
    public SnapshotEntity createFromParcel(final Parcel parcel) {
        SnapshotContents snapshotContents = null;
        final int c = a.C(parcel);
        int g = 0;
        SnapshotMetadata snapshotMetadata = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    snapshotMetadata = a.a(parcel, b, (android.os.Parcelable$Creator<SnapshotMetadataEntity>)SnapshotMetadataEntity.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    snapshotContents = a.a(parcel, b, (android.os.Parcelable$Creator<SnapshotContents>)SnapshotContents.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new SnapshotEntity(g, snapshotMetadata, snapshotContents);
    }
    
    public SnapshotEntity[] newArray(final int n) {
        return new SnapshotEntity[n];
    }
}
