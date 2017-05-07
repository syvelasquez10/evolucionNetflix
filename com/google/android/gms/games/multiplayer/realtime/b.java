// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import java.util.ArrayList;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.multiplayer.Participant;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<RoomEntity>
{
    static void a(final RoomEntity roomEntity, final Parcel parcel, int o) {
        o = com.google.android.gms.common.internal.safeparcel.b.o(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, roomEntity.getRoomId(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, roomEntity.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, roomEntity.getCreatorId(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, roomEntity.getCreationTimestamp());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 4, roomEntity.getStatus());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, roomEntity.getDescription(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 6, roomEntity.getVariant());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, roomEntity.getAutoMatchCriteria(), false);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 8, roomEntity.getParticipants(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 9, roomEntity.getAutoMatchWaitEstimateSeconds());
        com.google.android.gms.common.internal.safeparcel.b.D(parcel, o);
    }
    
    public RoomEntity[] aL(final int n) {
        return new RoomEntity[n];
    }
    
    public RoomEntity ad(final Parcel parcel) {
        int g = 0;
        ArrayList<ParticipantEntity> c = null;
        final int n = a.n(parcel);
        long h = 0L;
        Bundle o = null;
        int g2 = 0;
        String m = null;
        int g3 = 0;
        String i = null;
        String j = null;
        int g4 = 0;
        while (parcel.dataPosition() < n) {
            final int k = a.m(parcel);
            switch (a.M(k)) {
                default: {
                    a.b(parcel, k);
                    continue;
                }
                case 1: {
                    j = a.m(parcel, k);
                    continue;
                }
                case 1000: {
                    g4 = a.g(parcel, k);
                    continue;
                }
                case 2: {
                    i = a.m(parcel, k);
                    continue;
                }
                case 3: {
                    h = a.h(parcel, k);
                    continue;
                }
                case 4: {
                    g3 = a.g(parcel, k);
                    continue;
                }
                case 5: {
                    m = a.m(parcel, k);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, k);
                    continue;
                }
                case 7: {
                    o = a.o(parcel, k);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, k, ParticipantEntity.CREATOR);
                    continue;
                }
                case 9: {
                    g = a.g(parcel, k);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new RoomEntity(g4, j, i, h, g3, m, g2, o, c, g);
    }
}
