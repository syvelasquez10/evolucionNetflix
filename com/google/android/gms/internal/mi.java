// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.Collection;
import java.util.HashSet;
import java.util.Collections;
import java.util.Set;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class mi implements SafeParcelable
{
    public static final mj CREATOR;
    final int BR;
    final List<mo> afg;
    private final String afh;
    private final boolean afi;
    final List<ms> afj;
    final List<String> afk;
    private final Set<mo> afl;
    private final Set<ms> afm;
    private final Set<String> afn;
    
    static {
        CREATOR = new mj();
    }
    
    mi(final int br, final List<mo> list, final String s, final boolean afi, final List<ms> list2, final List<String> list3) {
        this.BR = br;
        List<mo> afg;
        if (list == null) {
            afg = Collections.emptyList();
        }
        else {
            afg = Collections.unmodifiableList((List<? extends mo>)list);
        }
        this.afg = afg;
        String afh = s;
        if (s == null) {
            afh = "";
        }
        this.afh = afh;
        this.afi = afi;
        List<ms> afj;
        if (list2 == null) {
            afj = Collections.emptyList();
        }
        else {
            afj = Collections.unmodifiableList((List<? extends ms>)list2);
        }
        this.afj = afj;
        List<String> afk;
        if (list3 == null) {
            afk = Collections.emptyList();
        }
        else {
            afk = Collections.unmodifiableList((List<? extends String>)list3);
        }
        this.afk = afk;
        this.afl = f(this.afg);
        this.afm = f(this.afj);
        this.afn = f(this.afk);
    }
    
    private static <E> Set<E> f(final List<E> list) {
        if (list.isEmpty()) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet((Set<? extends E>)new HashSet<E>((Collection<? extends E>)list));
    }
    
    public int describeContents() {
        final mj creator = mi.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof mi)) {
                return false;
            }
            final mi mi = (mi)o;
            if (!this.afl.equals(mi.afl) || this.afi != mi.afi || !this.afm.equals(mi.afm) || !this.afn.equals(mi.afn)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.afl, this.afi, this.afm, this.afn);
    }
    
    @Deprecated
    public String mg() {
        return this.afh;
    }
    
    public boolean mh() {
        return this.afi;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("types", this.afl).a("placeIds", this.afn).a("requireOpenNow", this.afi).a("requestedUserDataTypes", this.afm).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final mj creator = mi.CREATOR;
        mj.a(this, parcel, n);
    }
}
