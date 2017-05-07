// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import java.util.ArrayList;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ExtendedGameEntityCreator implements Parcelable$Creator<ExtendedGameEntity>
{
    static void a(final ExtendedGameEntity extendedGameEntity, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.a(parcel, 1, (Parcelable)extendedGameEntity.hf(), n, false);
        b.c(parcel, 1000, extendedGameEntity.getVersionCode());
        b.c(parcel, 2, extendedGameEntity.gX());
        b.a(parcel, 3, extendedGameEntity.gY());
        b.c(parcel, 4, extendedGameEntity.gZ());
        b.a(parcel, 5, extendedGameEntity.ha());
        b.a(parcel, 6, extendedGameEntity.hb());
        b.a(parcel, 7, extendedGameEntity.hc(), false);
        b.a(parcel, 8, extendedGameEntity.hd());
        b.a(parcel, 9, extendedGameEntity.he(), false);
        b.b(parcel, 10, extendedGameEntity.gW(), false);
        b.F(parcel, p3);
    }
    
    public ExtendedGameEntity aq(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        GameEntity gameEntity = null;
        int g2 = 0;
        boolean c = false;
        int g3 = 0;
        long i = 0L;
        long j = 0L;
        String n = null;
        long k = 0L;
        String n2 = null;
        ArrayList<GameBadgeEntity> c2 = null;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    gameEntity = a.a(parcel, n3, GameEntity.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    g2 = a.g(parcel, n3);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, n3);
                    continue;
                }
                case 4: {
                    g3 = a.g(parcel, n3);
                    continue;
                }
                case 5: {
                    i = a.i(parcel, n3);
                    continue;
                }
                case 6: {
                    j = a.i(parcel, n3);
                    continue;
                }
                case 7: {
                    n = a.n(parcel, n3);
                    continue;
                }
                case 8: {
                    k = a.i(parcel, n3);
                    continue;
                }
                case 9: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 10: {
                    c2 = a.c(parcel, n3, (android.os.Parcelable$Creator<GameBadgeEntity>)GameBadgeEntity.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ExtendedGameEntity(g, gameEntity, g2, c, g3, i, j, n, k, n2, c2);
    }
    
    public ExtendedGameEntity[] be(final int n) {
        return new ExtendedGameEntity[n];
    }
}
