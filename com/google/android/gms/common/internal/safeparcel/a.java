// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal.safeparcel;

import android.os.Parcelable;
import android.os.Parcelable$Creator;
import java.util.ArrayList;
import android.os.Parcel;

public class a
{
    public static int B(final Parcel parcel) {
        return parcel.readInt();
    }
    
    public static int C(final Parcel parcel) {
        final int b = B(parcel);
        final int a = a(parcel, b);
        final int dataPosition = parcel.dataPosition();
        if (aD(b) != 20293) {
            throw new a$a("Expected object header. Got 0x" + Integer.toHexString(b), parcel);
        }
        final int n = dataPosition + a;
        if (n < dataPosition || n > parcel.dataSize()) {
            throw new a$a("Size read is invalid start=" + dataPosition + " end=" + n, parcel);
        }
        return n;
    }
    
    public static ArrayList<String> C(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final ArrayList stringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(a + dataPosition);
        return (ArrayList<String>)stringArrayList;
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
            throw new a$a("Expected size " + n + " got " + a + " (0x" + Integer.toHexString(a) + ")", parcel);
        }
    }
    
    public static int aD(final int n) {
        return 0xFFFF & n;
    }
    
    public static void b(final Parcel parcel, final int n) {
        parcel.setDataPosition(a(parcel, n) + parcel.dataPosition());
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
    
    public static int g(final Parcel parcel, final int n) {
        a(parcel, n, 4);
        return parcel.readInt();
    }
    
    public static double m(final Parcel parcel, final int n) {
        a(parcel, n, 8);
        return parcel.readDouble();
    }
    
    public static String o(final Parcel parcel, int a) {
        a = a(parcel, a);
        final int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        final String string = parcel.readString();
        parcel.setDataPosition(a + dataPosition);
        return string;
    }
}
