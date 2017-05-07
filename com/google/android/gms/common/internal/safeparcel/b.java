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
    private static int D(final Parcel parcel, final int n) {
        parcel.writeInt(0xFFFF0000 | n);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }
    
    private static void E(final Parcel parcel, final int n) {
        final int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(n - 4);
        parcel.writeInt(dataPosition - n);
        parcel.setDataPosition(dataPosition);
    }
    
    public static void F(final Parcel parcel, final int n) {
        E(parcel, n);
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
    
    public static void a(final Parcel parcel, int d, final Bundle bundle, final boolean b) {
        if (bundle == null) {
            if (b) {
                b(parcel, d, 0);
            }
            return;
        }
        d = D(parcel, d);
        parcel.writeBundle(bundle);
        E(parcel, d);
    }
    
    public static void a(final Parcel parcel, int d, final IBinder binder, final boolean b) {
        if (binder == null) {
            if (b) {
                b(parcel, d, 0);
            }
            return;
        }
        d = D(parcel, d);
        parcel.writeStrongBinder(binder);
        E(parcel, d);
    }
    
    public static void a(final Parcel parcel, int d, final Parcel parcel2, final boolean b) {
        if (parcel2 == null) {
            if (b) {
                b(parcel, d, 0);
            }
            return;
        }
        d = D(parcel, d);
        parcel.appendFrom(parcel2, 0, parcel2.dataSize());
        E(parcel, d);
    }
    
    public static void a(final Parcel parcel, int d, final Parcelable parcelable, final int n, final boolean b) {
        if (parcelable == null) {
            if (b) {
                b(parcel, d, 0);
            }
            return;
        }
        d = D(parcel, d);
        parcelable.writeToParcel(parcel, n);
        E(parcel, d);
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
    
    public static void a(final Parcel parcel, int d, final String s, final boolean b) {
        if (s == null) {
            if (b) {
                b(parcel, d, 0);
            }
            return;
        }
        d = D(parcel, d);
        parcel.writeString(s);
        E(parcel, d);
    }
    
    public static void a(final Parcel parcel, int d, final List<String> list, final boolean b) {
        if (list == null) {
            if (b) {
                b(parcel, d, 0);
            }
            return;
        }
        d = D(parcel, d);
        parcel.writeStringList((List)list);
        E(parcel, d);
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
    
    public static void a(final Parcel parcel, int d, final byte[] array, final boolean b) {
        if (array == null) {
            if (b) {
                b(parcel, d, 0);
            }
            return;
        }
        d = D(parcel, d);
        parcel.writeByteArray(array);
        E(parcel, d);
    }
    
    public static void a(final Parcel parcel, int d, final int[] array, final boolean b) {
        if (array == null) {
            if (b) {
                b(parcel, d, 0);
            }
            return;
        }
        d = D(parcel, d);
        parcel.writeIntArray(array);
        E(parcel, d);
    }
    
    public static <T extends Parcelable> void a(final Parcel parcel, int i, final T[] array, final int n, final boolean b) {
        if (array == null) {
            if (b) {
                b(parcel, i, 0);
            }
            return;
        }
        final int d = D(parcel, i);
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
        E(parcel, d);
    }
    
    public static void a(final Parcel parcel, int d, final String[] array, final boolean b) {
        if (array == null) {
            if (b) {
                b(parcel, d, 0);
            }
            return;
        }
        d = D(parcel, d);
        parcel.writeStringArray(array);
        E(parcel, d);
    }
    
    public static void a(final Parcel parcel, int i, final byte[][] array, final boolean b) {
        final int n = 0;
        if (array == null) {
            if (b) {
                b(parcel, i, 0);
            }
            return;
        }
        final int d = D(parcel, i);
        final int length = array.length;
        parcel.writeInt(length);
        for (i = n; i < length; ++i) {
            parcel.writeByteArray(array[i]);
        }
        E(parcel, d);
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
    
    public static <T extends Parcelable> void b(final Parcel parcel, int i, final List<T> list, final boolean b) {
        if (list == null) {
            if (b) {
                b(parcel, i, 0);
            }
            return;
        }
        final int d = D(parcel, i);
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
        E(parcel, d);
    }
    
    public static void c(final Parcel parcel, final int n, final int n2) {
        b(parcel, n, 4);
        parcel.writeInt(n2);
    }
    
    public static void c(final Parcel parcel, int d, final List list, final boolean b) {
        if (list == null) {
            if (b) {
                b(parcel, d, 0);
            }
            return;
        }
        d = D(parcel, d);
        parcel.writeList(list);
        E(parcel, d);
    }
    
    public static int p(final Parcel parcel) {
        return D(parcel, 20293);
    }
}
