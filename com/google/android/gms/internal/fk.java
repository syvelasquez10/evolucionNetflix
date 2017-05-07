// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Collections;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ez
public final class fk implements SafeParcelable
{
    public static final fl CREATOR;
    public final int errorCode;
    public final int orientation;
    public final List<String> qf;
    public final List<String> qg;
    public final long qj;
    public final String rP;
    public final boolean tF;
    public final String tG;
    public final long tH;
    public final boolean tI;
    public final long tJ;
    public final List<String> tK;
    public final String tL;
    public final long tM;
    public final String tN;
    public final boolean tO;
    public final String tP;
    public final String tQ;
    public final boolean tR;
    public final boolean tS;
    public final boolean tT;
    public final int versionCode;
    
    static {
        CREATOR = new fl();
    }
    
    public fk(final int n) {
        this(10, null, null, null, n, null, -1L, false, -1L, null, -1L, -1, null, -1L, null, false, null, null, false, false, false, false);
    }
    
    public fk(final int n, final long n2) {
        this(10, null, null, null, n, null, -1L, false, -1L, null, n2, -1, null, -1L, null, false, null, null, false, false, false, false);
    }
    
    fk(final int versionCode, final String rp, final String tg, final List<String> list, final int errorCode, final List<String> list2, final long th, final boolean ti, final long tj, final List<String> list3, final long qj, final int orientation, final String tl, final long tm, final String tn, final boolean to, final String tp, final String tq, final boolean tr, final boolean ts, final boolean tf, final boolean tt) {
        this.versionCode = versionCode;
        this.rP = rp;
        this.tG = tg;
        List<String> unmodifiableList;
        if (list != null) {
            unmodifiableList = Collections.unmodifiableList((List<? extends String>)list);
        }
        else {
            unmodifiableList = null;
        }
        this.qf = unmodifiableList;
        this.errorCode = errorCode;
        List<String> unmodifiableList2;
        if (list2 != null) {
            unmodifiableList2 = Collections.unmodifiableList((List<? extends String>)list2);
        }
        else {
            unmodifiableList2 = null;
        }
        this.qg = unmodifiableList2;
        this.tH = th;
        this.tI = ti;
        this.tJ = tj;
        List<String> unmodifiableList3;
        if (list3 != null) {
            unmodifiableList3 = Collections.unmodifiableList((List<? extends String>)list3);
        }
        else {
            unmodifiableList3 = null;
        }
        this.tK = unmodifiableList3;
        this.qj = qj;
        this.orientation = orientation;
        this.tL = tl;
        this.tM = tm;
        this.tN = tn;
        this.tO = to;
        this.tP = tp;
        this.tQ = tq;
        this.tR = tr;
        this.tS = ts;
        this.tF = tf;
        this.tT = tt;
    }
    
    public fk(final String s, final String s2, final List<String> list, final List<String> list2, final long n, final boolean b, final long n2, final List<String> list3, final long n3, final int n4, final String s3, final long n5, final String s4, final String s5, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        this(10, s, s2, list, -2, list2, n, b, n2, list3, n3, n4, s3, n5, s4, false, null, s5, b2, b3, b4, b5);
    }
    
    public fk(final String s, final String s2, final List<String> list, final List<String> list2, final long n, final boolean b, final long n2, final List<String> list3, final long n3, final int n4, final String s3, final long n5, final String s4, final boolean b2, final String s5, final String s6, final boolean b3, final boolean b4, final boolean b5, final boolean b6) {
        this(10, s, s2, list, -2, list2, n, b, n2, list3, n3, n4, s3, n5, s4, b2, s5, s6, b3, b4, b5, b6);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        fl.a(this, parcel, n);
    }
}
