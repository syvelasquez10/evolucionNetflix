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
        final int p3 = b.p(parcel);
        b.c(parcel, 1, gameBadgeEntity.getType());
        b.c(parcel, 1000, gameBadgeEntity.getVersionCode());
        b.a(parcel, 2, gameBadgeEntity.getTitle(), false);
        b.a(parcel, 3, gameBadgeEntity.getDescription(), false);
        b.a(parcel, 4, (Parcelable)gameBadgeEntity.getIconImageUri(), n, false);
        b.F(parcel, p3);
    }
    
    public GameBadgeEntity ar(final Parcel parcel) {
        int g = 0;
        Uri uri = null;
        final int o = a.o(parcel);
        String n = null;
        String n2 = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 3: {
                    n = a.n(parcel, n3);
                    continue;
                }
                case 4: {
                    uri = a.a(parcel, n3, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new GameBadgeEntity(g2, g, n2, n, uri);
    }
    
    public GameBadgeEntity[] bg(final int n) {
        return new GameBadgeEntity[n];
    }
}
