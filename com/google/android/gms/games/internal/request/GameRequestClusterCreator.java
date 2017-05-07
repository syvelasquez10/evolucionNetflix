// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.request;

import java.util.ArrayList;
import com.google.android.gms.games.request.GameRequestEntity;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.request.GameRequest;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class GameRequestClusterCreator implements Parcelable$Creator<GameRequestCluster>
{
    static void a(final GameRequestCluster gameRequestCluster, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, gameRequestCluster.lu(), false);
        b.c(parcel, 1000, gameRequestCluster.getVersionCode());
        b.H(parcel, d);
    }
    
    public GameRequestCluster ck(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        ArrayList<GameRequestEntity> c2 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    c2 = a.c(parcel, b, (android.os.Parcelable$Creator<GameRequestEntity>)GameRequestEntity.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new GameRequestCluster(g, c2);
    }
    
    public GameRequestCluster[] dQ(final int n) {
        return new GameRequestCluster[n];
    }
}
