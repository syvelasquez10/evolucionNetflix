// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class GameBadgeEntityCreator implements Parcelable$Creator<GameBadgeEntity>
{
    static void a(final GameBadgeEntity gameBadgeEntity, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, gameBadgeEntity.getType());
        b.c(parcel, 1000, gameBadgeEntity.getVersionCode());
        b.a(parcel, 2, gameBadgeEntity.getTitle(), false);
        b.a(parcel, 3, gameBadgeEntity.getDescription(), false);
        b.a(parcel, 4, (Parcelable)gameBadgeEntity.getIconImageUri(), n, false);
        b.H(parcel, d);
    }
    
    public GameBadgeEntity ch(final Parcel parcel) {
        int g = 0;
        Uri uri = null;
        final int c = a.C(parcel);
        String o = null;
        String o2 = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    uri = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new GameBadgeEntity(g2, g, o2, o, uri);
    }
    
    public GameBadgeEntity[] dL(final int n) {
        return new GameBadgeEntity[n];
    }
}
