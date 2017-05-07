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

public class ParticipantEntityCreator implements Parcelable$Creator<ParticipantEntity>
{
    static void a(final ParticipantEntity participantEntity, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.a(parcel, 1, participantEntity.getParticipantId(), false);
        b.a(parcel, 2, participantEntity.getDisplayName(), false);
        b.a(parcel, 3, (Parcelable)participantEntity.getIconImageUri(), n, false);
        b.a(parcel, 4, (Parcelable)participantEntity.getHiResImageUri(), n, false);
        b.c(parcel, 5, participantEntity.getStatus());
        b.a(parcel, 6, participantEntity.gi(), false);
        b.a(parcel, 7, participantEntity.isConnectedToRoom());
        b.a(parcel, 8, (Parcelable)participantEntity.getPlayer(), n, false);
        b.c(parcel, 9, participantEntity.getCapabilities());
        b.a(parcel, 10, (Parcelable)participantEntity.getResult(), n, false);
        b.a(parcel, 11, participantEntity.getIconImageUrl(), false);
        b.a(parcel, 12, participantEntity.getHiResImageUrl(), false);
        b.c(parcel, 1000, participantEntity.getVersionCode());
        b.F(parcel, p3);
    }
    
    public ParticipantEntity av(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        String n = null;
        String n2 = null;
        Uri uri = null;
        Uri uri2 = null;
        int g2 = 0;
        String n3 = null;
        boolean c = false;
        PlayerEntity playerEntity = null;
        int g3 = 0;
        ParticipantResult participantResult = null;
        String n4 = null;
        String n5 = null;
        while (parcel.dataPosition() < o) {
            final int n6 = a.n(parcel);
            switch (a.R(n6)) {
                default: {
                    a.b(parcel, n6);
                    continue;
                }
                case 1: {
                    n = a.n(parcel, n6);
                    continue;
                }
                case 2: {
                    n2 = a.n(parcel, n6);
                    continue;
                }
                case 3: {
                    uri = a.a(parcel, n6, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 4: {
                    uri2 = a.a(parcel, n6, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 5: {
                    g2 = a.g(parcel, n6);
                    continue;
                }
                case 6: {
                    n3 = a.n(parcel, n6);
                    continue;
                }
                case 7: {
                    c = a.c(parcel, n6);
                    continue;
                }
                case 8: {
                    playerEntity = a.a(parcel, n6, PlayerEntity.CREATOR);
                    continue;
                }
                case 9: {
                    g3 = a.g(parcel, n6);
                    continue;
                }
                case 10: {
                    participantResult = a.a(parcel, n6, (android.os.Parcelable$Creator<ParticipantResult>)ParticipantResult.CREATOR);
                    continue;
                }
                case 11: {
                    n4 = a.n(parcel, n6);
                    continue;
                }
                case 12: {
                    n5 = a.n(parcel, n6);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n6);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ParticipantEntity(g, n, n2, uri, uri2, g2, n3, c, playerEntity, g3, participantResult, n4, n5);
    }
    
    public ParticipantEntity[] bo(final int n) {
        return new ParticipantEntity[n];
    }
}
