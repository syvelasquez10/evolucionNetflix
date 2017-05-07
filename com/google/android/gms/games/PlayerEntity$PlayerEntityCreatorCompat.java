// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.internal.jv;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.c;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.common.internal.a;
import android.os.Parcelable$Creator;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;
import android.net.Uri;
import android.os.Parcel;

final class PlayerEntity$PlayerEntityCreatorCompat extends PlayerEntityCreator
{
    @Override
    public PlayerEntity ce(final Parcel parcel) {
        if (GamesDowngradeableSafeParcel.c(c.gP()) || c.aV(PlayerEntity.class.getCanonicalName())) {
            return super.ce(parcel);
        }
        final String string = parcel.readString();
        final String string2 = parcel.readString();
        final String string3 = parcel.readString();
        final String string4 = parcel.readString();
        Uri parse;
        if (string3 == null) {
            parse = null;
        }
        else {
            parse = Uri.parse(string3);
        }
        Uri parse2;
        if (string4 == null) {
            parse2 = null;
        }
        else {
            parse2 = Uri.parse(string4);
        }
        return new PlayerEntity(11, string, string2, parse, parse2, parcel.readLong(), -1, -1L, null, null, null, null, null, true);
    }
}
