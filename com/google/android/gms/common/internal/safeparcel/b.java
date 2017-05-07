// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal.safeparcel;

import java.util.List;
import android.os.Parcelable;
import android.os.IBinder;
import android.os.Bundle;
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
    
    public static void a(final Parcel parcel, final int n, final byte b) {
        b(parcel, n, 4);
        parcel.writeInt((int)b);
    }
    
    public static void a(final Parcel parcel, final int n, final double n2) {
        b(parcel, n, 8);
        parcel.writeDouble(n2);
    }
    
    public static void a(final Parcel parcel, final int n, final float n2) {
        b(parcel, n, 4);
        parcel.writeFloat(n2);
    }
    
    public static void a(final Parcel parcel, final int n, final long n2) {
        b(parcel, n, 8);
        parcel.writeLong(n2);
    }
    
    public static void a(final Parcel parcel, int f, final Bundle bundle, final boolean b) {
        if (bundle == null) {
            if (b) {
                b(parcel, f, 0);
            }
            return;
        }
        f = F(parcel, f);
        parcel.writeBundle(bundle);
        G(parcel, f);
    }
    
    public static void a(final Parcel parcel, int f, final IBinder binder, final boolean b) {
        if (binder == null) {
            if (b) {
                b(parcel, f, 0);
            }
            return;
        }
        f = F(parcel, f);
        parcel.writeStrongBinder(binder);
        G(parcel, f);
    }
    
    public static void a(final Parcel parcel, int f, final Parcel parcel2, final boolean b) {
        if (parcel2 == null) {
            if (b) {
                b(parcel, f, 0);
            }
            return;
        }
        f = F(parcel, f);
        parcel.appendFrom(parcel2, 0, parcel2.dataSize());
        G(parcel, f);
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
    
    public static void a(final Parcel parcel, int n, final Boolean b, final boolean b2) {
        final int n2 = 0;
        if (b == null) {
            if (b2) {
                b(parcel, n, 0);
            }
            return;
        }
        b(parcel, n, 4);
        n = n2;
        if (b) {
            n = 1;
        }
        parcel.writeInt(n);
    }
    
    public static void a(final Parcel parcel, final int n, final Integer n2, final boolean b) {
        if (n2 == null) {
            if (b) {
                b(parcel, n, 0);
            }
            return;
        }
        b(parcel, n, 4);
        parcel.writeInt((int)n2);
    }
    
    public static void a(final Parcel parcel, final int n, final Long n2, final boolean b) {
        if (n2 == null) {
            if (b) {
                b(parcel, n, 0);
            }
            return;
        }
        b(parcel, n, 8);
        parcel.writeLong((long)n2);
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
    
    public static void a(final Parcel parcel, int i, final List<Integer> list, final boolean b) {
        if (list == null) {
            if (b) {
                b(parcel, i, 0);
            }
            return;
        }
        final int f = F(parcel, i);
        final int size = list.size();
        parcel.writeInt(size);
        for (i = 0; i < size; ++i) {
            parcel.writeInt((int)list.get(i));
        }
        G(parcel, f);
    }
    
    public static void a(final Parcel parcel, final int n, final short n2) {
        b(parcel, n, 4);
        parcel.writeInt((int)n2);
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
    
    public static void a(final Parcel parcel, int f, final byte[] array, final boolean b) {
        if (array == null) {
            if (b) {
                b(parcel, f, 0);
            }
            return;
        }
        f = F(parcel, f);
        parcel.writeByteArray(array);
        G(parcel, f);
    }
    
    public static void a(final Parcel parcel, int f, final int[] array, final boolean b) {
        if (array == null) {
            if (b) {
                b(parcel, f, 0);
            }
            return;
        }
        f = F(parcel, f);
        parcel.writeIntArray(array);
        G(parcel, f);
    }
    
    public static <T extends Parcelable> void a(final Parcel parcel, int i, final T[] array, final int n, final boolean b) {
        if (array == null) {
            if (b) {
                b(parcel, i, 0);
            }
            return;
        }
        final int f = F(parcel, i);
        final int length = array.length;
        parcel.writeInt(length);
        Parcelable parcelable;
        for (i = 0; i < length; ++i) {
            parcelable = array[i];
            if (parcelable == null) {
                parcel.writeInt(0);
            }
            else {
                a(parcel, parcelable, n);
            }
        }
        G(parcel, f);
    }
    
    public static void a(final Parcel parcel, int f, final String[] array, final boolean b) {
        if (array == null) {
            if (b) {
                b(parcel, f, 0);
            }
            return;
        }
        f = F(parcel, f);
        parcel.writeStringArray(array);
        G(parcel, f);
    }
    
    public static void a(final Parcel parcel, int i, final byte[][] array, final boolean b) {
        final int n = 0;
        if (array == null) {
            if (b) {
                b(parcel, i, 0);
            }
            return;
        }
        final int f = F(parcel, i);
        final int length = array.length;
        parcel.writeInt(length);
        for (i = n; i < length; ++i) {
            parcel.writeByteArray(array[i]);
        }
        G(parcel, f);
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
    
    public static void d(final Parcel parcel, int f, final List list, final boolean b) {
        if (list == null) {
            if (b) {
                b(parcel, f, 0);
            }
            return;
        }
        f = F(parcel, f);
        parcel.writeList(list);
        G(parcel, f);
    }
}
