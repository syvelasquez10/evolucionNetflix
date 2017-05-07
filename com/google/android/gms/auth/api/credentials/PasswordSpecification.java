// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Collection;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Random;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class PasswordSpecification implements SafeParcelable
{
    public static final zze CREATOR;
    public static final PasswordSpecification zzSt;
    public static final PasswordSpecification zzSu;
    final int mVersionCode;
    private final int[] zzSA;
    final String zzSv;
    final List<String> zzSw;
    final List<Integer> zzSx;
    final int zzSy;
    final int zzSz;
    private final Random zzts;
    
    static {
        CREATOR = new zze();
        zzSt = new PasswordSpecification$zza().zzg(12, 16).zzbD("abcdefghijkmnopqrstxyzABCDEFGHJKLMNPQRSTXY3456789").zzf("abcdefghijkmnopqrstxyz", 1).zzf("ABCDEFGHJKLMNPQRSTXY", 1).zzf("3456789", 1).zzlK();
        zzSu = new PasswordSpecification$zza().zzg(12, 16).zzbD("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890").zzf("abcdefghijklmnopqrstuvwxyz", 1).zzf("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1).zzf("1234567890", 1).zzlK();
    }
    
    PasswordSpecification(final int mVersionCode, final String zzSv, final List<String> list, final List<Integer> list2, final int zzSy, final int zzSz) {
        this.mVersionCode = mVersionCode;
        this.zzSv = zzSv;
        this.zzSw = Collections.unmodifiableList((List<? extends String>)list);
        this.zzSx = Collections.unmodifiableList((List<? extends Integer>)list2);
        this.zzSy = zzSy;
        this.zzSz = zzSz;
        this.zzSA = this.zzlJ();
        this.zzts = new SecureRandom();
    }
    
    private int zza(final char c) {
        return c - ' ';
    }
    
    private static String zzb(final Collection<Character> collection) {
        final char[] array = new char[collection.size()];
        final Iterator<Character> iterator = collection.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            array[n] = iterator.next();
            ++n;
        }
        return new String(array);
    }
    
    private static boolean zzb(final int n, final int n2, final int n3) {
        return n < n2 || n > n3;
    }
    
    private int[] zzlJ() {
        final int[] array = new int[95];
        Arrays.fill(array, -1);
        final Iterator<String> iterator = this.zzSw.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final char[] charArray = iterator.next().toCharArray();
            for (int length = charArray.length, i = 0; i < length; ++i) {
                array[this.zza(charArray[i])] = n;
            }
            ++n;
        }
        return array;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zze.zza(this, parcel, n);
    }
}
