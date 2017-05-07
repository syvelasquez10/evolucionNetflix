// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.multiplayer.Participant;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class RoomEntityCreator implements Parcelable$Creator<RoomEntity>
{
    static void a(final RoomEntity roomEntity, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, roomEntity.getRoomId(), false);
        b.c(parcel, 1000, roomEntity.getVersionCode());
        b.a(parcel, 2, roomEntity.getCreatorId(), false);
        b.a(parcel, 3, roomEntity.getCreationTimestamp());
        b.c(parcel, 4, roomEntity.getStatus());
        b.a(parcel, 5, roomEntity.getDescription(), false);
        b.c(parcel, 6, roomEntity.getVariant());
        b.a(parcel, 7, roomEntity.getAutoMatchCriteria(), false);
        b.c(parcel, 8, roomEntity.getParticipants(), false);
        b.c(parcel, 9, roomEntity.getAutoMatchWaitEstimateSeconds());
        b.H(parcel, d);
    }
    
    public RoomEntity co(final Parcel parcel) {
        int g = 0;
        ArrayList<ParticipantEntity> c = null;
        final int c2 = a.C(parcel);
        long i = 0L;
        Bundle q = null;
        int g2 = 0;
        String o = null;
        int g3 = 0;
        String o2 = null;
        String o3 = null;
        int g4 = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g4 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 4: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 7: {
                    q = a.q(parcel, b);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, b, ParticipantEntity.CREATOR);
                    continue;
                }
                case 9: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new RoomEntity(g4, o3, o2, i, g3, o, g2, q, c, g);
    }
    
    public RoomEntity[] dV(final int n) {
        return new RoomEntity[n];
    }
}
