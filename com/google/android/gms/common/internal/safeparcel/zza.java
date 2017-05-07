// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.Parcelable$Creator;
import java.util.ArrayList;
import android.os.Parcel;

public class zza
{
    public static String[] zzA(final Parcel parcel, int zza) {
        zza = zza(parcel, zza);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final String[] stringArray = parcel.createStringArray();
        parcel.setDataPosition(zza + dataPosition);
        return stringArray;
    }
    
    public static ArrayList<Integer> zzB(final Parcel parcel, int i) {
        final int zza = zza(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final ArrayList<Integer> list = new ArrayList<Integer>();
        int int1;
        for (int1 = parcel.readInt(), i = 0; i < int1; ++i) {
            list.add(parcel.readInt());
        }
        parcel.setDataPosition(dataPosition + zza);
        return list;
    }
    
    public static ArrayList<String> zzC(final Parcel parcel, int zza) {
        zza = zza(parcel, zza);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final ArrayList stringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(zza + dataPosition);
        return (ArrayList<String>)stringArrayList;
    }
    
    public static int zza(final Parcel parcel, final int n) {
        if ((n & 0xFFFF0000) != 0xFFFF0000) {
            return n >> 16 & 0xFFFF;
        }
        return parcel.readInt();
    }
    
    public static <T extends Parcelable> T zza(final Parcel parcel, int zza, final Parcelable$Creator<T> parcelable$Creator) {
        zza = zza(parcel, zza);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final Parcelable parcelable = (Parcelable)parcelable$Creator.createFromParcel(parcel);
        parcel.setDataPosition(zza + dataPosition);
        return (T)parcelable;
    }
    
    private static void zza(final Parcel parcel, int zza, final int n) {
        zza = zza(parcel, zza);
        if (zza != n) {
            throw new zza$zza("Expected size " + n + " got " + zza + " (0x" + Integer.toHexString(zza) + ")", parcel);
        }
    }
    
    public static int zzai(final Parcel parcel) {
        return parcel.readInt();
    }
    
    public static int zzaj(final Parcel parcel) {
        final int zzai = zzai(parcel);
        final int zza = zza(parcel, zzai);
        final int dataPosition = parcel.dataPosition();
        if (zzbH(zzai) != 20293) {
            throw new zza$zza("Expected object header. Got 0x" + Integer.toHexString(zzai), parcel);
        }
        final int n = dataPosition + zza;
        if (n < dataPosition || n > parcel.dataSize()) {
            throw new zza$zza("Size read is invalid start=" + dataPosition + " end=" + n, parcel);
        }
        return n;
    }
    
    public static void zzb(final Parcel parcel, final int n) {
        parcel.setDataPosition(zza(parcel, n) + parcel.dataPosition());
    }
    
    public static <T> T[] zzb(final Parcel parcel, int zza, final Parcelable$Creator<T> parcelable$Creator) {
        zza = zza(parcel, zza);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final Object[] typedArray = parcel.createTypedArray((Parcelable$Creator)parcelable$Creator);
        parcel.setDataPosition(zza + dataPosition);
        return (T[])typedArray;
    }
    
    public static int zzbH(final int n) {
        return 0xFFFF & n;
    }
    
    public static <T> ArrayList<T> zzc(final Parcel parcel, int zza, final Parcelable$Creator<T> parcelable$Creator) {
        zza = zza(parcel, zza);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final ArrayList typedArrayList = parcel.createTypedArrayList((Parcelable$Creator)parcelable$Creator);
        parcel.setDataPosition(zza + dataPosition);
        return (ArrayList<T>)typedArrayList;
    }
    
    public static boolean zzc(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return parcel.readInt() != 0;
    }
    
    public static int zzg(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return parcel.readInt();
    }
    
    public static long zzi(final Parcel parcel, final int n) {
        zza(parcel, n, 8);
        return parcel.readLong();
    }
    
    public static float zzl(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return parcel.readFloat();
    }
    
    public static double zzm(final Parcel parcel, final int n) {
        zza(parcel, n, 8);
        return parcel.readDouble();
    }
    
    public static String zzo(final Parcel parcel, int zza) {
        zza = zza(parcel, zza);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final String string = parcel.readString();
        parcel.setDataPosition(zza + dataPosition);
        return string;
    }
    
    public static IBinder zzp(final Parcel parcel, int zza) {
        zza = zza(parcel, zza);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final IBinder strongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(zza + dataPosition);
        return strongBinder;
    }
    
    public static Bundle zzq(final Parcel parcel, int zza) {
        zza = zza(parcel, zza);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final Bundle bundle = parcel.readBundle();
        parcel.setDataPosition(zza + dataPosition);
        return bundle;
    }
    
    public static byte[] zzr(final Parcel parcel, int zza) {
        zza = zza(parcel, zza);
        final int dataPosition = parcel.dataPosition();
        if (zza == 0) {
            return null;
        }
        final byte[] byteArray = parcel.createByteArray();
        parcel.setDataPosition(zza + dataPosition);
        return byteArray;
    }
}
