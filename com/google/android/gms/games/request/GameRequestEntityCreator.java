// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.request;

import android.os.Bundle;
import java.util.ArrayList;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class GameRequestEntityCreator implements Parcelable$Creator<GameRequestEntity>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final GameRequestEntity gameRequestEntity, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.a(parcel, 1, (Parcelable)gameRequestEntity.getGame(), n, false);
        b.c(parcel, 1000, gameRequestEntity.getVersionCode());
        b.a(parcel, 2, (Parcelable)gameRequestEntity.getSender(), n, false);
        b.a(parcel, 3, gameRequestEntity.getData(), false);
        b.a(parcel, 4, gameRequestEntity.getRequestId(), false);
        b.b(parcel, 5, gameRequestEntity.getRecipients(), false);
        b.c(parcel, 7, gameRequestEntity.getType());
        b.a(parcel, 9, gameRequestEntity.getCreationTimestamp());
        b.a(parcel, 10, gameRequestEntity.getExpirationTimestamp());
        b.a(parcel, 11, gameRequestEntity.hK(), false);
        b.c(parcel, 12, gameRequestEntity.getStatus());
        b.F(parcel, p3);
    }
    
    public GameRequestEntity createFromParcel(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        GameEntity gameEntity = null;
        PlayerEntity playerEntity = null;
        byte[] q = null;
        String n = null;
        ArrayList<PlayerEntity> c = null;
        int g2 = 0;
        long i = 0L;
        long j = 0L;
        Bundle p = null;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    gameEntity = a.a(parcel, n2, GameEntity.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    playerEntity = a.a(parcel, n2, PlayerEntity.CREATOR);
                    continue;
                }
                case 3: {
                    q = a.q(parcel, n2);
                    continue;
                }
                case 4: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, n2, PlayerEntity.CREATOR);
                    continue;
                }
                case 7: {
                    g2 = a.g(parcel, n2);
                    continue;
                }
                case 9: {
                    i = a.i(parcel, n2);
                    continue;
                }
                case 10: {
                    j = a.i(parcel, n2);
                    continue;
                }
                case 11: {
                    p = a.p(parcel, n2);
                    continue;
                }
                case 12: {
                    g3 = a.g(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new GameRequestEntity(g, gameEntity, playerEntity, q, n, c, g2, i, j, p, g3);
    }
    
    public GameRequestEntity[] newArray(final int n) {
        return new GameRequestEntity[n];
    }
}
