// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal.safeparcel;

import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;

public class b
{
    public static int D(final Parcel parcel) {
        return F(parcel, 20293);
    }
    
    private static int F(final Parcel parcel, final int n) {
        parcel.writeInt(0xFFFF0000 | n);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }
    
    private static void G(final Parcel parcel, final int n) {
        final int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(n - 4);
        parcel.writeInt(dataPosition - n);
        parcel.setDataPosition(dataPosition);
    }
    
    public static void H(final Parcel parcel, final int n) {
        G(parcel, n);
    }
    
    public static void a(final Parcel parcel, final int n, final double n2) {
        b(parcel, n, 8);
        parcel.writeDouble(n2);
    }
    
    public static void a(final Parcel parcel, int f, final Parcelable parcelable, final int n, final boolean b) {
        if (parcelable == null) {
            if (b) {
                b(parcel, f, 0);
            }
            return;
        }
        f = F(parcel, f);
        parcelable.writeToParcel(parcel, n);
        G(parcel, f);
    }
    
    public static void a(final Parcel parcel, int f, final String s, final boolean b) {
        if (s == null) {
            if (b) {
                b(parcel, f, 0);
            }
            return;
        }
        f = F(parcel, f);
        parcel.writeString(s);
        G(parcel, f);
    }
    
    public static void a(final Parcel parcel, int n, final boolean b) {
        b(parcel, n, 4);
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
    }
    
    private static <T extends Parcelable> void a(final Parcel parcel, final T t, int dataPosition) {
        final int dataPosition2 = parcel.dataPosition();
        parcel.writeInt(1);
        final int dataPosition3 = parcel.dataPosition();
        t.writeToParcel(parcel, dataPosition);
        dataPosition = parcel.dataPosition();
        parcel.setDataPosition(dataPosition2);
        parcel.writeInt(dataPosition - dataPosition3);
        parcel.setDataPosition(dataPosition);
    }
    
    private static void b(final Parcel parcel, final int n, final int n2) {
        if (n2 >= 65535) {
            parcel.writeInt(0xFFFF0000 | n);
            parcel.writeInt(n2);
            return;
        }
        parcel.writeInt(n2 << 16 | n);
    }
    
    public static void b(final Parcel parcel, int f, final List<String> list, final boolean b) {
        if (list == null) {
            if (b) {
                b(parcel, f, 0);
            }
            return;
        }
        f = F(parcel, f);
        parcel.writeStringList((List)list);
        G(parcel, f);
    }
    
    public static void c(final Parcel parcel, final int n, final int n2) {
        b(parcel, n, 4);
        parcel.writeInt(n2);
    }
    
    public static <T extends Parcelable> void c(final Parcel parcel, int i, final List<T> list, final boolean b) {
        if (list == null) {
            if (b) {
                b(parcel, i, 0);
            }
            return;
        }
        final int f = F(parcel, i);
        final int size = list.size();
        parcel.writeInt(size);
        Parcelable parcelable;
        for (i = 0; i < size; ++i) {
            parcelable = list.get(i);
            if (parcelable == null) {
                parcel.writeInt(0);
            }
            else {
                a(parcel, parcelable, 0);
            }
        }
        G(parcel, f);
    }
}
