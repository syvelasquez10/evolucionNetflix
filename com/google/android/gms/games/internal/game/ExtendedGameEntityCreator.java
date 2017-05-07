// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
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
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)extendedGameEntity.kY(), n, false);
        b.c(parcel, 1000, extendedGameEntity.getVersionCode());
        b.c(parcel, 2, extendedGameEntity.kP());
        b.a(parcel, 3, extendedGameEntity.kQ());
        b.c(parcel, 4, extendedGameEntity.kR());
        b.a(parcel, 5, extendedGameEntity.kS());
        b.a(parcel, 6, extendedGameEntity.kT());
        b.a(parcel, 7, extendedGameEntity.kU(), false);
        b.a(parcel, 8, extendedGameEntity.kV());
        b.a(parcel, 9, extendedGameEntity.kW(), false);
        b.c(parcel, 10, extendedGameEntity.kO(), false);
        b.a(parcel, 11, (Parcelable)extendedGameEntity.kX(), n, false);
        b.H(parcel, d);
    }
    
    public ExtendedGameEntity cg(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        GameEntity gameEntity = null;
        int g2 = 0;
        boolean c2 = false;
        int g3 = 0;
        long i = 0L;
        long j = 0L;
        String o = null;
        long k = 0L;
        String o2 = null;
        ArrayList<GameBadgeEntity> c3 = null;
        SnapshotMetadataEntity snapshotMetadataEntity = null;
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
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 4: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 6: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 7: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 8: {
                    k = a.i(parcel, b);
                    continue;
                }
                case 9: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 10: {
                    c3 = a.c(parcel, b, (android.os.Parcelable$Creator<GameBadgeEntity>)GameBadgeEntity.CREATOR);
                    continue;
                }
                case 11: {
                    snapshotMetadataEntity = a.a(parcel, b, (android.os.Parcelable$Creator<SnapshotMetadataEntity>)SnapshotMetadataEntity.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ExtendedGameEntity(g, gameEntity, g2, c2, g3, i, j, o, k, o2, c3, snapshotMetadataEntity);
    }
    
    public ExtendedGameEntity[] dJ(final int n) {
        return new ExtendedGameEntity[n];
    }
}
