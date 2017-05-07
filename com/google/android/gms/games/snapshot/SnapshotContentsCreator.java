// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class SnapshotContentsCreator implements Parcelable$Creator<SnapshotContents>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final SnapshotContents snapshotContents, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)snapshotContents.getContents(), n, false);
        b.c(parcel, 1000, snapshotContents.getVersionCode());
        b.H(parcel, d);
    }
    
    public SnapshotContents createFromParcel(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        Contents contents = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    contents = a.a(parcel, b, Contents.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new SnapshotContents(g, contents);
    }
    
    public SnapshotContents[] newArray(final int n) {
        return new SnapshotContents[n];
    }
}
