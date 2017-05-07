// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.quest;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class MilestoneEntityCreator implements Parcelable$Creator<MilestoneEntity>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final MilestoneEntity milestoneEntity, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, milestoneEntity.getMilestoneId(), false);
        b.c(parcel, 1000, milestoneEntity.getVersionCode());
        b.a(parcel, 2, milestoneEntity.getCurrentProgress());
        b.a(parcel, 3, milestoneEntity.getTargetProgress());
        b.a(parcel, 4, milestoneEntity.getCompletionRewardData(), false);
        b.c(parcel, 5, milestoneEntity.getState());
        b.a(parcel, 6, milestoneEntity.getEventId(), false);
        b.H(parcel, d);
    }
    
    public MilestoneEntity createFromParcel(final Parcel parcel) {
        long i = 0L;
        int g = 0;
        String o = null;
        final int c = a.C(parcel);
        byte[] r = null;
        long j = 0L;
        String o2 = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 4: {
                    r = a.r(parcel, b);
                    continue;
                }
                case 5: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 6: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new MilestoneEntity(g2, o2, j, i, r, g, o);
    }
    
    public MilestoneEntity[] newArray(final int n) {
        return new MilestoneEntity[n];
    }
}
