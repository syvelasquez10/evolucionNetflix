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

public class c implements Parcelable$Creator<PlayerEntity>
{
    static void a(final PlayerEntity playerEntity, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.a(parcel, 1, playerEntity.getPlayerId(), false);
        b.c(parcel, 1000, playerEntity.getVersionCode());
        b.a(parcel, 2, playerEntity.getDisplayName(), false);
        b.a(parcel, 3, (Parcelable)playerEntity.getIconImageUri(), n, false);
        b.a(parcel, 4, (Parcelable)playerEntity.getHiResImageUri(), n, false);
        b.a(parcel, 5, playerEntity.getRetrievedTimestamp());
        b.c(parcel, 6, playerEntity.db());
        b.a(parcel, 7, playerEntity.getLastPlayedWithTimestamp());
        b.D(parcel, o);
    }
    
    public PlayerEntity Z(final Parcel parcel) {
        long h = 0L;
        int g = 0;
        Uri uri = null;
        final int n = a.n(parcel);
        long h2 = 0L;
        Uri uri2 = null;
        String m = null;
        String i = null;
        int g2 = 0;
        while (parcel.dataPosition() < n) {
            final int j = a.m(parcel);
            switch (a.M(j)) {
                default: {
                    a.b(parcel, j);
                    continue;
                }
                case 1: {
                    i = a.m(parcel, j);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, j);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, j);
                    continue;
                }
                case 3: {
                    uri2 = a.a(parcel, j, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 4: {
                    uri = a.a(parcel, j, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 5: {
                    h2 = a.h(parcel, j);
                    continue;
                }
                case 6: {
                    g = a.g(parcel, j);
                    continue;
                }
                case 7: {
                    h = a.h(parcel, j);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new PlayerEntity(g2, i, m, uri2, uri, h2, g, h);
    }
    
    public PlayerEntity[] aA(final int n) {
        return new PlayerEntity[n];
    }
}
