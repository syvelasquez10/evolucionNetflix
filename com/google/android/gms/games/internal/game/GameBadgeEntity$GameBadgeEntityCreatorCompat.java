// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.internal.c;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import android.net.Uri;
import android.os.Parcel;

final class GameBadgeEntity$GameBadgeEntityCreatorCompat extends GameBadgeEntityCreator
{
    @Override
    public GameBadgeEntity ch(final Parcel parcel) {
        if (GamesDowngradeableSafeParcel.c(c.gP()) || c.aV(GameBadgeEntity.class.getCanonicalName())) {
            return super.ch(parcel);
        }
        final int int1 = parcel.readInt();
        final String string = parcel.readString();
        final String string2 = parcel.readString();
        final String string3 = parcel.readString();
        Uri parse;
        if (string3 == null) {
            parse = null;
        }
        else {
            parse = Uri.parse(string3);
        }
        return new GameBadgeEntity(1, int1, string, string2, parse);
    }
}
