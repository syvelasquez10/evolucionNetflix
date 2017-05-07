// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Collections;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class cz implements SafeParcelable
{
    public static final da CREATOR;
    public final int errorCode;
    public final List<String> ne;
    public final List<String> nf;
    public final long ni;
    public final String ol;
    public final int orientation;
    public final String pm;
    public final long pn;
    public final boolean po;
    public final long pp;
    public final List<String> pq;
    public final String pr;
    public final long ps;
    public final String pt;
    public final boolean pu;
    public final String pv;
    public final String pw;
    public final int versionCode;
    
    static {
        CREATOR = new da();
    }
    
    public cz(final int n) {
        this(7, null, null, null, n, null, -1L, false, -1L, null, -1L, -1, null, -1L, null, false, null, null);
    }
    
    public cz(final int n, final long n2) {
        this(7, null, null, null, n, null, -1L, false, -1L, null, n2, -1, null, -1L, null, false, null, null);
    }
    
    cz(final int versionCode, final String ol, final String pm, final List<String> list, final int errorCode, final List<String> list2, final long pn, final boolean po, final long pp, final List<String> list3, final long ni, final int orientation, final String pr, final long ps, final String pt, final boolean pu, final String pv, final String pw) {
        this.versionCode = versionCode;
        this.ol = ol;
        this.pm = pm;
        List<String> unmodifiableList;
        if (list != null) {
            unmodifiableList = Collections.unmodifiableList((List<? extends String>)list);
        }
        else {
            unmodifiableList = null;
        }
        this.ne = unmodifiableList;
        this.errorCode = errorCode;
        List<String> unmodifiableList2;
        if (list2 != null) {
            unmodifiableList2 = Collections.unmodifiableList((List<? extends String>)list2);
        }
        else {
            unmodifiableList2 = null;
        }
        this.nf = unmodifiableList2;
        this.pn = pn;
        this.po = po;
        this.pp = pp;
        List<String> unmodifiableList3;
        if (list3 != null) {
            unmodifiableList3 = Collections.unmodifiableList((List<? extends String>)list3);
        }
        else {
            unmodifiableList3 = null;
        }
        this.pq = unmodifiableList3;
        this.ni = ni;
        this.orientation = orientation;
        this.pr = pr;
        this.ps = ps;
        this.pt = pt;
        this.pu = pu;
        this.pv = pv;
        this.pw = pw;
    }
    
    public cz(final String s, final String s2, final List<String> list, final List<String> list2, final long n, final boolean b, final long n2, final List<String> list3, final long n3, final int n4, final String s3, final long n5, final String s4, final String s5) {
        this(7, s, s2, list, -2, list2, n, b, n2, list3, n3, n4, s3, n5, s4, false, null, s5);
    }
    
    public cz(final String s, final String s2, final List<String> list, final List<String> list2, final long n, final boolean b, final long n2, final List<String> list3, final long n3, final int n4, final String s3, final long n5, final String s4, final boolean b2, final String s5, final String s6) {
        this(7, s, s2, list, -2, list2, n, b, n2, list3, n3, n4, s3, n5, s4, b2, s5, s6);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        da.a(this, parcel, n);
    }
}
