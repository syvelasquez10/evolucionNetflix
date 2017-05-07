// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcelable$Creator;
import java.util.ArrayList;
import android.os.Parcel;

public class a
{
    public static ArrayList<String> A(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final ArrayList stringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(a + dataPosition);
        return (ArrayList<String>)stringArrayList;
    }
    
    public static Parcel B(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final Parcel obtain = Parcel.obtain();
        obtain.appendFrom(parcel, dataPosition, a);
        parcel.setDataPosition(a + dataPosition);
        return obtain;
    }
    
    public static Parcel[] C(final Parcel parcel, int i) {
        final int a = a(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final Parcel[] array = new Parcel[int1];
        int int2;
        int dataPosition2;
        Parcel obtain;
        for (i = 0; i < int1; ++i) {
            int2 = parcel.readInt();
            if (int2 != 0) {
                dataPosition2 = parcel.dataPosition();
                obtain = Parcel.obtain();
                obtain.appendFrom(parcel, dataPosition2, int2);
                array[i] = obtain;
                parcel.setDataPosition(int2 + dataPosition2);
            }
            else {
                array[i] = null;
            }
        }
        parcel.setDataPosition(dataPosition + a);
        return array;
    }
    
    public static int R(final int n) {
        return 0xFFFF & n;
    }
    
    public static int a(final Parcel parcel, final int n) {
        if ((n & 0xFFFF0000) != 0xFFFF0000) {
            return n >> 16 & 0xFFFF;
        }
        return parcel.readInt();
    }
    
    public static <T extends Parcelable> T a(final Parcel parcel, int a, final Parcelable$Creator<T> parcelable$Creator) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final Parcelable parcelable = (Parcelable)parcelable$Creator.createFromParcel(parcel);
        parcel.setDataPosition(a + dataPosition);
        return (T)parcelable;
    }
    
    private static void a(final Parcel parcel, int a, final int n) {
        a = a(parcel, a);
        if (a != n) {
            throw new a("Expected size " + n + " got " + a + " (0x" + Integer.toHexString(a) + ")", parcel);
        }
    }
    
    private static void a(final Parcel parcel, final int n, final int n2, final int n3) {
        if (n2 != n3) {
            throw new a("Expected size " + n3 + " got " + n2 + " (0x" + Integer.toHexString(n2) + ")", parcel);
        }
    }
    
    public static void a(final Parcel parcel, int a, final List list, final ClassLoader classLoader) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return;
        }
        parcel.readList(list, classLoader);
        parcel.setDataPosition(a + dataPosition);
    }
    
    public static void b(final Parcel parcel, final int n) {
        parcel.setDataPosition(a(parcel, n) + parcel.dataPosition());
    }
    
    public static <T> T[] b(final Parcel parcel, int a, final Parcelable$Creator<T> parcelable$Creator) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final Object[] typedArray = parcel.createTypedArray((Parcelable$Creator)parcelable$Creator);
        parcel.setDataPosition(a + dataPosition);
        return (T[])typedArray;
    }
    
    public static <T> ArrayList<T> c(final Parcel parcel, int a, final Parcelable$Creator<T> parcelable$Creator) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final ArrayList typedArrayList = parcel.createTypedArrayList((Parcelable$Creator)parcelable$Creator);
        parcel.setDataPosition(a + dataPosition);
        return (ArrayList<T>)typedArrayList;
    }
    
    public static boolean c(final Parcel parcel, final int n) {
        a(parcel, n, 4);
        return parcel.readInt() != 0;
    }
    
    public static Boolean d(final Parcel parcel, final int n) {
        final int a = a(parcel, n);
        if (a == 0) {
            return null;
        }
        a(parcel, n, a, 4);
        return parcel.readInt() != 0;
    }
    
    public static byte e(final Parcel parcel, final int n) {
        a(parcel, n, 4);
        return (byte)parcel.readInt();
    }
    
    public static short f(final Parcel parcel, final int n) {
        a(parcel, n, 4);
        return (short)parcel.readInt();
    }
    
    public static int g(final Parcel parcel, final int n) {
        a(parcel, n, 4);
        return parcel.readInt();
    }
    
    public static Integer h(final Parcel parcel, final int n) {
        final int a = a(parcel, n);
        if (a == 0) {
            return null;
        }
        a(parcel, n, a, 4);
        return parcel.readInt();
    }
    
    public static long i(final Parcel parcel, final int n) {
        a(parcel, n, 8);
        return parcel.readLong();
    }
    
    public static BigInteger j(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final byte[] byteArray = parcel.createByteArray();
        parcel.setDataPosition(a + dataPosition);
        return new BigInteger(byteArray);
    }
    
    public static float k(final Parcel parcel, final int n) {
        a(parcel, n, 4);
        return parcel.readFloat();
    }
    
    public static double l(final Parcel parcel, final int n) {
        a(parcel, n, 8);
        return parcel.readDouble();
    }
    
    public static BigDecimal m(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final byte[] byteArray = parcel.createByteArray();
        final int int1 = parcel.readInt();
        parcel.setDataPosition(a + dataPosition);
        return new BigDecimal(new BigInteger(byteArray), int1);
    }
    
    public static int n(final Parcel parcel) {
        return parcel.readInt();
    }
    
    public static String n(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final String string = parcel.readString();
        parcel.setDataPosition(a + dataPosition);
        return string;
    }
    
    public static int o(final Parcel parcel) {
        final int n = n(parcel);
        final int a = a(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (R(n) != 20293) {
            throw new a("Expected object header. Got 0x" + Integer.toHexString(n), parcel);
        }
        final int n2 = dataPosition + a;
        if (n2 < dataPosition || n2 > parcel.dataSize()) {
            throw new a("Size read is invalid start=" + dataPosition + " end=" + n2, parcel);
        }
        return n2;
    }
    
    public static IBinder o(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final IBinder strongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(a + dataPosition);
        return strongBinder;
    }
    
    public static Bundle p(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final Bundle bundle = parcel.readBundle();
        parcel.setDataPosition(a + dataPosition);
        return bundle;
    }
    
    public static byte[] q(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final byte[] byteArray = parcel.createByteArray();
        parcel.setDataPosition(a + dataPosition);
        return byteArray;
    }
    
    public static byte[][] r(final Parcel parcel, int i) {
        final int a = a(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final byte[][] array = new byte[int1][];
        for (i = 0; i < int1; ++i) {
            array[i] = parcel.createByteArray();
        }
        parcel.setDataPosition(dataPosition + a);
        return array;
    }
    
    public static boolean[] s(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final boolean[] booleanArray = parcel.createBooleanArray();
        parcel.setDataPosition(a + dataPosition);
        return booleanArray;
    }
    
    public static int[] t(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final int[] intArray = parcel.createIntArray();
        parcel.setDataPosition(a + dataPosition);
        return intArray;
    }
    
    public static long[] u(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final long[] longArray = parcel.createLongArray();
        parcel.setDataPosition(a + dataPosition);
        return longArray;
    }
    
    public static BigInteger[] v(final Parcel parcel, int i) {
        final int a = a(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final BigInteger[] array = new BigInteger[int1];
        for (i = 0; i < int1; ++i) {
            array[i] = new BigInteger(parcel.createByteArray());
        }
        parcel.setDataPosition(dataPosition + a);
        return array;
    }
    
    public static float[] w(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final float[] floatArray = parcel.createFloatArray();
        parcel.setDataPosition(a + dataPosition);
        return floatArray;
    }
    
    public static double[] x(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final double[] doubleArray = parcel.createDoubleArray();
        parcel.setDataPosition(a + dataPosition);
        return doubleArray;
    }
    
    public static BigDecimal[] y(final Parcel parcel, int i) {
        final int a = a(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final BigDecimal[] array = new BigDecimal[int1];
        for (i = 0; i < int1; ++i) {
            array[i] = new BigDecimal(new BigInteger(parcel.createByteArray()), parcel.readInt());
        }
        parcel.setDataPosition(dataPosition + a);
        return array;
    }
    
    public static String[] z(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final String[] stringArray = parcel.createStringArray();
        parcel.setDataPosition(a + dataPosition);
        return stringArray;
    }
    
    public static class a extends RuntimeException
    {
        public a(final String s, final Parcel parcel) {
            super(s + " Parcel: pos=" + parcel.dataPosition() + " size=" + parcel.dataSize());
        }
    }
}
