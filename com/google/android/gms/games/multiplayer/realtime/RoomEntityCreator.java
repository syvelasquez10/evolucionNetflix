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
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class RoomEntityCreator implements Parcelable$Creator<RoomEntity>
{
    static void a(final RoomEntity roomEntity, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.a(parcel, 1, roomEntity.getRoomId(), false);
        b.c(parcel, 1000, roomEntity.getVersionCode());
        b.a(parcel, 2, roomEntity.getCreatorId(), false);
        b.a(parcel, 3, roomEntity.getCreationTimestamp());
        b.c(parcel, 4, roomEntity.getStatus());
        b.a(parcel, 5, roomEntity.getDescription(), false);
        b.c(parcel, 6, roomEntity.getVariant());
        b.a(parcel, 7, roomEntity.getAutoMatchCriteria(), false);
        b.b(parcel, 8, roomEntity.getParticipants(), false);
        b.c(parcel, 9, roomEntity.getAutoMatchWaitEstimateSeconds());
        b.F(parcel, p3);
    }
    
    public RoomEntity ax(final Parcel parcel) {
        int g = 0;
        ArrayList<ParticipantEntity> c = null;
        final int o = a.o(parcel);
        long i = 0L;
        Bundle p = null;
        int g2 = 0;
        String n = null;
        int g3 = 0;
        String n2 = null;
        String n3 = null;
        int g4 = 0;
        while (parcel.dataPosition() < o) {
            final int n4 = a.n(parcel);
            switch (a.R(n4)) {
                default: {
                    a.b(parcel, n4);
                    continue;
                }
                case 1: {
                    n3 = a.n(parcel, n4);
                    continue;
                }
                case 1000: {
                    g4 = a.g(parcel, n4);
                    continue;
                }
                case 2: {
                    n2 = a.n(parcel, n4);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, n4);
                    continue;
                }
                case 4: {
                    g3 = a.g(parcel, n4);
                    continue;
                }
                case 5: {
                    n = a.n(parcel, n4);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, n4);
                    continue;
                }
                case 7: {
                    p = a.p(parcel, n4);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, n4, ParticipantEntity.CREATOR);
                    continue;
                }
                case 9: {
                    g = a.g(parcel, n4);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new RoomEntity(g4, n3, n2, i, g3, n, g2, p, c, g);
    }
    
    public RoomEntity[] bq(final int n) {
        return new RoomEntity[n];
    }
}
