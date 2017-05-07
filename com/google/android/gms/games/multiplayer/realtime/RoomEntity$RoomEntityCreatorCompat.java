// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import java.util.Collection;
import com.google.android.gms.internal.jv;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.c;
import com.google.android.gms.games.Player;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.games.multiplayer.Participant;
import android.os.Parcelable$Creator;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;
import android.os.Parcel;

final class RoomEntity$RoomEntityCreatorCompat extends RoomEntityCreator
{
    @Override
    public RoomEntity co(final Parcel parcel) {
        if (GamesDowngradeableSafeParcel.c(c.gP()) || c.aV(RoomEntity.class.getCanonicalName())) {
            return super.co(parcel);
        }
        final String string = parcel.readString();
        final String string2 = parcel.readString();
        final long long1 = parcel.readLong();
        final int int1 = parcel.readInt();
        final String string3 = parcel.readString();
        final int int2 = parcel.readInt();
        final Bundle bundle = parcel.readBundle();
        final int int3 = parcel.readInt();
        final ArrayList list = new ArrayList<ParticipantEntity>(int3);
        for (int i = 0; i < int3; ++i) {
            list.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
        }
        return new RoomEntity(2, string, string2, long1, int1, string3, int2, bundle, (ArrayList<ParticipantEntity>)list, -1);
    }
}
