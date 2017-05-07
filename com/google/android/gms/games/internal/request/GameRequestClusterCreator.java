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
    static void a(final GameRequestCluster gameRequestCluster, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.b(parcel, 1, gameRequestCluster.hz(), false);
        b.c(parcel, 1000, gameRequestCluster.getVersionCode());
        b.F(parcel, p3);
    }
    
    public GameRequestCluster at(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        ArrayList<GameRequestEntity> c = null;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    c = a.c(parcel, n, (android.os.Parcelable$Creator<GameRequestEntity>)GameRequestEntity.CREATOR);
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
        return new GameRequestCluster(g, c);
    }
    
    public GameRequestCluster[] bl(final int n) {
        return new GameRequestCluster[n];
    }
}
