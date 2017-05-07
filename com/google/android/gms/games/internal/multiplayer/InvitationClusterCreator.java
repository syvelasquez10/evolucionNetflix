// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.multiplayer;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.multiplayer.Invitation;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class InvitationClusterCreator implements Parcelable$Creator<ZInvitationCluster>
{
    static void a(final ZInvitationCluster zInvitationCluster, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, zInvitationCluster.lh(), false);
        b.c(parcel, 1000, zInvitationCluster.getVersionCode());
        b.H(parcel, d);
    }
    
    public ZInvitationCluster ci(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        ArrayList<InvitationEntity> c2 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    c2 = a.c(parcel, b, InvitationEntity.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ZInvitationCluster(g, c2);
    }
    
    public ZInvitationCluster[] dN(final int n) {
        return new ZInvitationCluster[n];
    }
}
