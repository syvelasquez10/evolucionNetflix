// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class SnapshotMetadataChangeCreator implements Parcelable$Creator<SnapshotMetadataChange>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final SnapshotMetadataChange snapshotMetadataChange, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, snapshotMetadataChange.getDescription(), false);
        b.c(parcel, 1000, snapshotMetadataChange.getVersionCode());
        b.a(parcel, 2, snapshotMetadataChange.getPlayedTimeMillis(), false);
        b.a(parcel, 4, (Parcelable)snapshotMetadataChange.getCoverImageUri(), n, false);
        b.a(parcel, 5, (Parcelable)snapshotMetadataChange.lK(), n, false);
        b.H(parcel, d);
    }
    
    public SnapshotMetadataChange createFromParcel(final Parcel parcel) {
        Uri uri = null;
        final int c = a.C(parcel);
        int g = 0;
        com.google.android.gms.common.data.a a = null;
        Long j = null;
        String o = null;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    j = com.google.android.gms.common.internal.safeparcel.a.j(parcel, b);
                    continue;
                }
                case 4: {
                    uri = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 5: {
                    a = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, com.google.android.gms.common.data.a.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new SnapshotMetadataChange(g, o, j, a, uri);
    }
    
    public SnapshotMetadataChange[] newArray(final int n) {
        return new SnapshotMetadataChange[n];
    }
}
