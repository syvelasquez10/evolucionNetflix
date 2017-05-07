// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ParticipantResultCreator implements Parcelable$Creator<ParticipantResult>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final ParticipantResult participantResult, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, participantResult.getParticipantId(), false);
        b.c(parcel, 1000, participantResult.getVersionCode());
        b.c(parcel, 2, participantResult.getResult());
        b.c(parcel, 3, participantResult.getPlacing());
        b.H(parcel, d);
    }
    
    public ParticipantResult createFromParcel(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        String o = null;
        int g2 = 0;
        int g3 = 0;
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
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new ParticipantResult(g3, o, g2, g);
    }
    
    public ParticipantResult[] newArray(final int n) {
        return new ParticipantResult[n];
    }
}
