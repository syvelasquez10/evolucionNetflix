// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.event;

import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class EventEntityCreator implements Parcelable$Creator<EventEntity>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final EventEntity eventEntity, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, eventEntity.getEventId(), false);
        b.c(parcel, 1000, eventEntity.getVersionCode());
        b.a(parcel, 2, eventEntity.getName(), false);
        b.a(parcel, 3, eventEntity.getDescription(), false);
        b.a(parcel, 4, (Parcelable)eventEntity.getIconImageUri(), n, false);
        b.a(parcel, 5, eventEntity.getIconImageUrl(), false);
        b.a(parcel, 6, (Parcelable)eventEntity.getPlayer(), n, false);
        b.a(parcel, 7, eventEntity.getValue());
        b.a(parcel, 8, eventEntity.getFormattedValue(), false);
        b.a(parcel, 9, eventEntity.isVisible());
        b.H(parcel, d);
    }
    
    public EventEntity createFromParcel(final Parcel parcel) {
        boolean c = false;
        String o = null;
        final int c2 = a.C(parcel);
        long i = 0L;
        Player player = null;
        String o2 = null;
        Uri uri = null;
        String o3 = null;
        String o4 = null;
        String o5 = null;
        int g = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o5 = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    uri = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 5: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 6: {
                    player = a.a(parcel, b, PlayerEntity.CREATOR);
                    continue;
                }
                case 7: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 8: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 9: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new EventEntity(g, o5, o4, o3, uri, o2, player, i, o, c);
    }
    
    public EventEntity[] newArray(final int n) {
        return new EventEntity[n];
    }
}
