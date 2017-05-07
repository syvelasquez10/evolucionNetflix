// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<ParticipantEntity>
{
    static void a(final ParticipantEntity participantEntity, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.a(parcel, 1, participantEntity.getParticipantId(), false);
        b.c(parcel, 1000, participantEntity.getVersionCode());
        b.a(parcel, 2, participantEntity.getDisplayName(), false);
        b.a(parcel, 3, (Parcelable)participantEntity.getIconImageUri(), n, false);
        b.a(parcel, 4, (Parcelable)participantEntity.getHiResImageUri(), n, false);
        b.c(parcel, 5, participantEntity.getStatus());
        b.a(parcel, 6, participantEntity.dy(), false);
        b.a(parcel, 7, participantEntity.isConnectedToRoom());
        b.a(parcel, 8, (Parcelable)participantEntity.getPlayer(), n, false);
        b.c(parcel, 9, participantEntity.getCapabilities());
        b.a(parcel, 10, (Parcelable)participantEntity.getResult(), n, false);
        b.D(parcel, o);
    }
    
    public ParticipantEntity[] aJ(final int n) {
        return new ParticipantEntity[n];
    }
    
    public ParticipantEntity ab(final Parcel parcel) {
        int g = 0;
        ParticipantResult participantResult = null;
        final int n = a.n(parcel);
        PlayerEntity playerEntity = null;
        boolean c = false;
        String m = null;
        int g2 = 0;
        Uri uri = null;
        Uri uri2 = null;
        String i = null;
        String j = null;
        int g3 = 0;
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
                    g3 = a.g(parcel, k);
                    continue;
                }
                case 2: {
                    i = a.m(parcel, k);
                    continue;
                }
                case 3: {
                    uri2 = a.a(parcel, k, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 4: {
                    uri = a.a(parcel, k, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 5: {
                    g2 = a.g(parcel, k);
                    continue;
                }
                case 6: {
                    m = a.m(parcel, k);
                    continue;
                }
                case 7: {
                    c = a.c(parcel, k);
                    continue;
                }
                case 8: {
                    playerEntity = a.a(parcel, k, PlayerEntity.CREATOR);
                    continue;
                }
                case 9: {
                    g = a.g(parcel, k);
                    continue;
                }
                case 10: {
                    participantResult = a.a(parcel, k, (android.os.Parcelable$Creator<ParticipantResult>)ParticipantResult.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ParticipantEntity(g3, j, i, uri2, uri, g2, m, c, playerEntity, g, participantResult);
    }
}
