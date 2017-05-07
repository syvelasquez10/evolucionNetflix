// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ms implements SafeParcelable
{
    public static final mt CREATOR;
    public static final ms ahR;
    public static final ms ahS;
    public static final ms ahT;
    public static final Set<ms> ahU;
    final int BR;
    final int ahV;
    final String uO;
    
    static {
        ahR = y("test_type", 1);
        ahS = y("trellis_store", 2);
        ahT = y("labeled_place", 6);
        ahU = Collections.unmodifiableSet((Set<? extends ms>)new HashSet<ms>(Arrays.asList(ms.ahR, ms.ahS, ms.ahT)));
        CREATOR = new mt();
    }
    
    ms(final int br, final String uo, final int ahV) {
        n.aZ(uo);
        this.BR = br;
        this.uO = uo;
        this.ahV = ahV;
    }
    
    private static ms y(final String s, final int n) {
        return new ms(0, s, n);
    }
    
    public int describeContents() {
        final mt creator = ms.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof ms)) {
                return false;
            }
            final ms ms = (ms)o;
            if (!this.uO.equals(ms.uO) || this.ahV != ms.ahV) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.uO.hashCode();
    }
    
    @Override
    public String toString() {
        return this.uO;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final mt creator = ms.CREATOR;
        mt.a(this, parcel, n);
    }
}
