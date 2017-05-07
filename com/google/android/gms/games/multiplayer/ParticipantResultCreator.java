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
    
    static void a(final ParticipantResult participantResult, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.a(parcel, 1, participantResult.getParticipantId(), false);
        b.c(parcel, 1000, participantResult.getVersionCode());
        b.c(parcel, 2, participantResult.getResult());
        b.c(parcel, 3, participantResult.getPlacing());
        b.F(parcel, p3);
    }
    
    public ParticipantResult createFromParcel(final Parcel parcel) {
        int g = 0;
        final int o = a.o(parcel);
        String n = null;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 1000: {
                    g3 = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    g2 = a.g(parcel, n2);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ParticipantResult(g3, n, g2, g);
    }
    
    public ParticipantResult[] newArray(final int n) {
        return new ParticipantResult[n];
    }
}
