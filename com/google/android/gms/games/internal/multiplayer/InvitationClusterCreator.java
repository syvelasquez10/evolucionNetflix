// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.multiplayer;

import java.util.ArrayList;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.multiplayer.Invitation;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class InvitationClusterCreator implements Parcelable$Creator<ZInvitationCluster>
{
    static void a(final ZInvitationCluster zInvitationCluster, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.b(parcel, 1, zInvitationCluster.ho(), false);
        b.c(parcel, 1000, zInvitationCluster.getVersionCode());
        b.F(parcel, p3);
    }
    
    public ZInvitationCluster as(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        ArrayList<InvitationEntity> c = null;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    c = a.c(parcel, n, InvitationEntity.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ZInvitationCluster(g, c);
    }
    
    public ZInvitationCluster[] bi(final int n) {
        return new ZInvitationCluster[n];
    }
}
