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
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)gameRequestEntity.getGame(), n, false);
        b.c(parcel, 1000, gameRequestEntity.getVersionCode());
        b.a(parcel, 2, (Parcelable)gameRequestEntity.getSender(), n, false);
        b.a(parcel, 3, gameRequestEntity.getData(), false);
        b.a(parcel, 4, gameRequestEntity.getRequestId(), false);
        b.c(parcel, 5, gameRequestEntity.getRecipients(), false);
        b.c(parcel, 7, gameRequestEntity.getType());
        b.a(parcel, 9, gameRequestEntity.getCreationTimestamp());
        b.a(parcel, 10, gameRequestEntity.getExpirationTimestamp());
        b.a(parcel, 11, gameRequestEntity.lJ(), false);
        b.c(parcel, 12, gameRequestEntity.getStatus());
        b.H(parcel, d);
    }
    
    public GameRequestEntity createFromParcel(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        GameEntity gameEntity = null;
        PlayerEntity playerEntity = null;
        byte[] r = null;
        String o = null;
        ArrayList<PlayerEntity> c2 = null;
        int g2 = 0;
        long i = 0L;
        long j = 0L;
        Bundle q = null;
        int g3 = 0;
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
                    r = a.r(parcel, b);
                    continue;
                }
                case 4: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    c2 = a.c(parcel, b, PlayerEntity.CREATOR);
                    continue;
                }
                case 7: {
                    g2 = a.g(parcel, b);
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
                    q = a.q(parcel, b);
                    continue;
                }
                case 12: {
                    g3 = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new GameRequestEntity(g, gameEntity, playerEntity, r, o, c2, g2, i, j, q, g3);
    }
    
    public GameRequestEntity[] newArray(final int n) {
        return new GameRequestEntity[n];
    }
}
