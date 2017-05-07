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
    
    static void a(final ParticipantResult participantResult, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.a(parcel, 1, participantResult.getParticipantId(), false);
        b.c(parcel, 1000, participantResult.getVersionCode());
        b.c(parcel, 2, participantResult.getResult());
        b.c(parcel, 3, participantResult.getPlacing());
        b.D(parcel, o);
    }
    
    public ParticipantResult createFromParcel(final Parcel parcel) {
        int g = 0;
        final int n = a.n(parcel);
        String m = null;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    m = a.m(parcel, i);
                    continue;
                }
                case 1000: {
                    g3 = a.g(parcel, i);
                    continue;
                }
                case 2: {
                    g2 = a.g(parcel, i);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, i);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ParticipantResult(g3, m, g2, g);
    }
    
    public ParticipantResult[] newArray(final int n) {
        return new ParticipantResult[n];
    }
}
