// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class PlayerEntityCreator implements Parcelable$Creator<PlayerEntity>
{
    static void a(final PlayerEntity playerEntity, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.a(parcel, 1, playerEntity.getPlayerId(), false);
        b.c(parcel, 1000, playerEntity.getVersionCode());
        b.a(parcel, 2, playerEntity.getDisplayName(), false);
        b.a(parcel, 3, (Parcelable)playerEntity.getIconImageUri(), n, false);
        b.a(parcel, 4, (Parcelable)playerEntity.getHiResImageUri(), n, false);
        b.a(parcel, 5, playerEntity.getRetrievedTimestamp());
        b.c(parcel, 6, playerEntity.gh());
        b.a(parcel, 7, playerEntity.getLastPlayedWithTimestamp());
        b.a(parcel, 8, playerEntity.getIconImageUrl(), false);
        b.a(parcel, 9, playerEntity.getHiResImageUrl(), false);
        b.F(parcel, p3);
    }
    
    public PlayerEntity[] aT(final int n) {
        return new PlayerEntity[n];
    }
    
    public PlayerEntity ao(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        String n = null;
        String n2 = null;
        Uri uri = null;
        Uri uri2 = null;
        long i = 0L;
        int g2 = 0;
        long j = 0L;
        String n3 = null;
        String n4 = null;
        while (parcel.dataPosition() < o) {
            final int n5 = a.n(parcel);
            switch (a.R(n5)) {
                default: {
                    a.b(parcel, n5);
                    continue;
                }
                case 1: {
                    n = a.n(parcel, n5);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n5);
                    continue;
                }
                case 2: {
                    n2 = a.n(parcel, n5);
                    continue;
                }
                case 3: {
                    uri = a.a(parcel, n5, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 4: {
                    uri2 = a.a(parcel, n5, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 5: {
                    i = a.i(parcel, n5);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, n5);
                    continue;
                }
                case 7: {
                    j = a.i(parcel, n5);
                    continue;
                }
                case 8: {
                    n3 = a.n(parcel, n5);
                    continue;
                }
                case 9: {
                    n4 = a.n(parcel, n5);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new PlayerEntity(g, n, n2, uri, uri2, i, g2, j, n3, n4);
    }
}
