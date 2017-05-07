// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal.safeparcel;

import java.util.List;
import android.os.Parcelable;
import android.os.IBinder;
import android.os.Bundle;
import android.os.Parcel;

public class zzb
{
    private static int zzG(final Parcel parcel, final int n) {
        parcel.writeInt(0xFFFF0000 | n);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }
    
    private static void zzH(final Parcel parcel, final int n) {
        final int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(n - 4);
        parcel.writeInt(dataPosition - n);
        parcel.setDataPosition(dataPosition);
    }
    
    public static void zzI(final Parcel parcel, final int n) {
        zzH(parcel, n);
    }
    
    public static void zza(final Parcel parcel, final int n, final double n2) {
        zzb(parcel, n, 8);
        parcel.writeDouble(n2);
    }
    
    public static void zza(final Parcel parcel, final int n, final float n2) {
        zzb(parcel, n, 4);
        parcel.writeFloat(n2);
    }
    
    public static void zza(final Parcel parcel, final int n, final long n2) {
        zzb(parcel, n, 8);
        parcel.writeLong(n2);
    }
    
    public static void zza(final Parcel parcel, int zzG, final Bundle bundle, final boolean b) {
        if (bundle == null) {
            if (b) {
                zzb(parcel, zzG, 0);
            }
            return;
        }
        zzG = zzG(parcel, zzG);
        parcel.writeBundle(bundle);
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, int zzG, final IBinder binder, final boolean b) {
        if (binder == null) {
            if (b) {
                zzb(parcel, zzG, 0);
            }
            return;
        }
        zzG = zzG(parcel, zzG);
        parcel.writeStrongBinder(binder);
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, int zzG, final Parcelable parcelable, final int n, final boolean b) {
        if (parcelable == null) {
            if (b) {
                zzb(parcel, zzG, 0);
            }
            return;
        }
        zzG = zzG(parcel, zzG);
        parcelable.writeToParcel(parcel, n);
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, int zzG, final String s, final boolean b) {
        if (s == null) {
            if (b) {
                zzb(parcel, zzG, 0);
            }
            return;
        }
        zzG = zzG(parcel, zzG);
        parcel.writeString(s);
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, int i, final List<Integer> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, i);
        final int size = list.size();
        parcel.writeInt(size);
        for (i = 0; i < size; ++i) {
            parcel.writeInt((int)list.get(i));
        }
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, int n, final boolean b) {
        zzb(parcel, n, 4);
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
    }
    
    public static void zza(final Parcel parcel, int zzG, final byte[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, zzG, 0);
            }
            return;
        }
        zzG = zzG(parcel, zzG);
        parcel.writeByteArray(array);
        zzH(parcel, zzG);
    }
    
    public static <T extends Parcelable> void zza(final Parcel parcel, int i, final T[] array, final int n, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, i);
        final int length = array.length;
        parcel.writeInt(length);
        Parcelable parcelable;
        for (i = 0; i < length; ++i) {
            parcelable = array[i];
            if (parcelable == null) {
                parcel.writeInt(0);
            }
            else {
                zza(parcel, parcelable, n);
            }
        }
        zzH(parcel, zzG);
    }
    
    public static void zza(final Parcel parcel, int zzG, final String[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, zzG, 0);
            }
            return;
        }
        zzG = zzG(parcel, zzG);
        parcel.writeStringArray(array);
        zzH(parcel, zzG);
    }
    
    private static <T extends Parcelable> void zza(final Parcel parcel, final T t, int dataPosition) {
        final int dataPosition2 = parcel.dataPosition();
        parcel.writeInt(1);
        final int dataPosition3 = parcel.dataPosition();
        t.writeToParcel(parcel, dataPosition);
        dataPosition = parcel.dataPosition();
        parcel.setDataPosition(dataPosition2);
        parcel.writeInt(dataPosition - dataPosition3);
        parcel.setDataPosition(dataPosition);
    }
    
    public static int zzaq(final Parcel parcel) {
        return zzG(parcel, 20293);
    }
    
    private static void zzb(final Parcel parcel, final int n, final int n2) {
        if (n2 >= 65535) {
            parcel.writeInt(0xFFFF0000 | n);
            parcel.writeInt(n2);
            return;
        }
        parcel.writeInt(n2 << 16 | n);
    }
    
    public static void zzb(final Parcel parcel, int zzG, final List<String> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, zzG, 0);
            }
            return;
        }
        zzG = zzG(parcel, zzG);
        parcel.writeStringList((List)list);
        zzH(parcel, zzG);
    }
    
    public static void zzc(final Parcel parcel, final int n, final int n2) {
        zzb(parcel, n, 4);
        parcel.writeInt(n2);
    }
    
    public static <T extends Parcelable> void zzc(final Parcel parcel, int i, final List<T> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zzG = zzG(parcel, i);
        final int size = list.size();
        parcel.writeInt(size);
        Parcelable parcelable;
        for (i = 0; i < size; ++i) {
            parcelable = list.get(i);
            if (parcelable == null) {
                parcel.writeInt(0);
            }
            else {
                zza(parcel, parcelable, 0);
            }
        }
        zzH(parcel, zzG);
    }
}
