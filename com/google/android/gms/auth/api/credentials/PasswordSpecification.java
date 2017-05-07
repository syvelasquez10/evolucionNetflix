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
    public static final PasswordSpecification zzRo;
    public static final PasswordSpecification zzRp;
    final int mVersionCode;
    final String zzRq;
    final List<String> zzRr;
    final List<Integer> zzRs;
    final int zzRt;
    final int zzRu;
    private final int[] zzRv;
    private final Random zzsT;
    
    static {
        CREATOR = new zze();
        zzRo = new PasswordSpecification$zza().zzh(12, 16).zzbD("abcdefghijkmnopqrstxyzABCDEFGHJKLMNPQRSTXY3456789").zzf("abcdefghijkmnopqrstxyz", 1).zzf("ABCDEFGHJKLMNPQRSTXY", 1).zzf("3456789", 1).zzlx();
        zzRp = new PasswordSpecification$zza().zzh(12, 16).zzbD("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890").zzf("abcdefghijklmnopqrstuvwxyz", 1).zzf("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1).zzf("1234567890", 1).zzlx();
    }
    
    PasswordSpecification(final int mVersionCode, final String zzRq, final List<String> list, final List<Integer> list2, final int zzRt, final int zzRu) {
        this.mVersionCode = mVersionCode;
        this.zzRq = zzRq;
        this.zzRr = Collections.unmodifiableList((List<? extends String>)list);
        this.zzRs = Collections.unmodifiableList((List<? extends Integer>)list2);
        this.zzRt = zzRt;
        this.zzRu = zzRu;
        this.zzRv = this.zzlw();
        this.zzsT = new SecureRandom();
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
    
    private int[] zzlw() {
        final int[] array = new int[95];
        Arrays.fill(array, -1);
        final Iterator<String> iterator = this.zzRr.iterator();
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
