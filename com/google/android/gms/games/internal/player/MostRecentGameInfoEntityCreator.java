// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.player;

import com.google.android.gms.common.internal.safeparcel.a$a;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class MostRecentGameInfoEntityCreator implements Parcelable$Creator<MostRecentGameInfoEntity>
{
    static void a(final MostRecentGameInfoEntity mostRecentGameInfoEntity, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, mostRecentGameInfoEntity.ln(), false);
        b.c(parcel, 1000, mostRecentGameInfoEntity.getVersionCode());
        b.a(parcel, 2, mostRecentGameInfoEntity.lo(), false);
        b.a(parcel, 3, mostRecentGameInfoEntity.lp());
        b.a(parcel, 4, (Parcelable)mostRecentGameInfoEntity.lq(), n, false);
        b.a(parcel, 5, (Parcelable)mostRecentGameInfoEntity.lr(), n, false);
        b.a(parcel, 6, (Parcelable)mostRecentGameInfoEntity.ls(), n, false);
        b.H(parcel, d);
    }
    
    public MostRecentGameInfoEntity cj(final Parcel parcel) {
        Uri uri = null;
        final int c = a.C(parcel);
        int g = 0;
        long i = 0L;
        Uri uri2 = null;
        Uri uri3 = null;
        String o = null;
        String o2 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 4: {
                    uri3 = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 5: {
                    uri2 = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 6: {
                    uri = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new MostRecentGameInfoEntity(g, o2, o, i, uri3, uri2, uri);
    }
    
    public MostRecentGameInfoEntity[] dP(final int n) {
        return new MostRecentGameInfoEntity[n];
    }
}
