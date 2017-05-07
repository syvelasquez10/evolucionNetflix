// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.snapshot;

import android.net.Uri;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class SnapshotMetadataEntityCreator implements Parcelable$Creator<SnapshotMetadataEntity>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final SnapshotMetadataEntity snapshotMetadataEntity, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)snapshotMetadataEntity.getGame(), n, false);
        b.c(parcel, 1000, snapshotMetadataEntity.getVersionCode());
        b.a(parcel, 2, (Parcelable)snapshotMetadataEntity.getOwner(), n, false);
        b.a(parcel, 3, snapshotMetadataEntity.getSnapshotId(), false);
        b.a(parcel, 5, (Parcelable)snapshotMetadataEntity.getCoverImageUri(), n, false);
        b.a(parcel, 6, snapshotMetadataEntity.getCoverImageUrl(), false);
        b.a(parcel, 7, snapshotMetadataEntity.getTitle(), false);
        b.a(parcel, 8, snapshotMetadataEntity.getDescription(), false);
        b.a(parcel, 9, snapshotMetadataEntity.getLastModifiedTimestamp());
        b.a(parcel, 10, snapshotMetadataEntity.getPlayedTime());
        b.a(parcel, 11, snapshotMetadataEntity.getCoverImageAspectRatio());
        b.a(parcel, 12, snapshotMetadataEntity.getUniqueName(), false);
        b.H(parcel, d);
    }
    
    public SnapshotMetadataEntity createFromParcel(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        GameEntity gameEntity = null;
        PlayerEntity playerEntity = null;
        String o = null;
        Uri uri = null;
        String o2 = null;
        String o3 = null;
        String o4 = null;
        long i = 0L;
        long j = 0L;
        float l = 0.0f;
        String o5 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    gameEntity = a.a(parcel, b, GameEntity.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    playerEntity = a.a(parcel, b, PlayerEntity.CREATOR);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    uri = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 6: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 8: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 9: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 10: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 11: {
                    l = a.l(parcel, b);
                    continue;
                }
                case 12: {
                    o5 = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new SnapshotMetadataEntity(g, gameEntity, playerEntity, o, uri, o2, o3, o4, i, j, l, o5);
    }
    
    public SnapshotMetadataEntity[] newArray(final int n) {
        return new SnapshotMetadataEntity[n];
    }
}
