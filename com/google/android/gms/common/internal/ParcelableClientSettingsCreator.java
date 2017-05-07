// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ParcelableClientSettingsCreator implements Parcelable$Creator<ClientSettings$ParcelableClientSettings>
{
    static void a(final ClientSettings$ParcelableClientSettings clientSettings$ParcelableClientSettings, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, clientSettings$ParcelableClientSettings.getAccountName(), false);
        b.c(parcel, 1000, clientSettings$ParcelableClientSettings.getVersionCode());
        b.b(parcel, 2, clientSettings$ParcelableClientSettings.getScopes(), false);
        b.c(parcel, 3, clientSettings$ParcelableClientSettings.getGravityForPopups());
        b.a(parcel, 4, clientSettings$ParcelableClientSettings.getRealClientPackageName(), false);
        b.H(parcel, d);
    }
    
    public ClientSettings$ParcelableClientSettings createFromParcel(final Parcel parcel) {
        int g = 0;
        String o = null;
        final int c = a.C(parcel);
        List<String> c2 = null;
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
                    c2 = a.C(parcel, b);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 4: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ClientSettings$ParcelableClientSettings(g2, o2, c2, g, o);
    }
    
    public ClientSettings$ParcelableClientSettings[] newArray(final int n) {
        return new ClientSettings$ParcelableClientSettings[n];
    }
}
