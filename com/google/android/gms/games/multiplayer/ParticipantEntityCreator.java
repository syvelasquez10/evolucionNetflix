// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.internal.safeparcel.a$a;
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
        final int d = b.D(parcel);
        b.a(parcel, 1, participantEntity.getParticipantId(), false);
        b.c(parcel, 1000, participantEntity.getVersionCode());
        b.a(parcel, 2, participantEntity.getDisplayName(), false);
        b.a(parcel, 3, (Parcelable)participantEntity.getIconImageUri(), n, false);
        b.a(parcel, 4, (Parcelable)participantEntity.getHiResImageUri(), n, false);
        b.c(parcel, 5, participantEntity.getStatus());
        b.a(parcel, 6, participantEntity.jU(), false);
        b.a(parcel, 7, participantEntity.isConnectedToRoom());
        b.a(parcel, 8, (Parcelable)participantEntity.getPlayer(), n, false);
        b.c(parcel, 9, participantEntity.getCapabilities());
        b.a(parcel, 10, (Parcelable)participantEntity.getResult(), n, false);
        b.a(parcel, 11, participantEntity.getIconImageUrl(), false);
        b.a(parcel, 12, participantEntity.getHiResImageUrl(), false);
        b.H(parcel, d);
    }
    
    public ParticipantEntity cm(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        String o2 = null;
        Uri uri = null;
        Uri uri2 = null;
        int g2 = 0;
        String o3 = null;
        boolean c2 = false;
        PlayerEntity playerEntity = null;
        int g3 = 0;
        ParticipantResult participantResult = null;
        String o4 = null;
        String o5 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    uri = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 4: {
                    uri2 = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 5: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 6: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 8: {
                    playerEntity = a.a(parcel, b, PlayerEntity.CREATOR);
                    continue;
                }
                case 9: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 10: {
                    participantResult = a.a(parcel, b, (android.os.Parcelable$Creator<ParticipantResult>)ParticipantResult.CREATOR);
                    continue;
                }
                case 11: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 12: {
                    o5 = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ParticipantEntity(g, o, o2, uri, uri2, g2, o3, c2, playerEntity, g3, participantResult, o4, o5);
    }
    
    public ParticipantEntity[] dT(final int n) {
        return new ParticipantEntity[n];
    }
}
