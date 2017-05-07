// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import java.util.HashMap;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ji$a<I, O> implements SafeParcelable
{
    public static final jk CREATOR;
    private final int BR;
    protected final int Mq;
    protected final boolean Mr;
    protected final int Ms;
    protected final boolean Mt;
    protected final String Mu;
    protected final int Mv;
    protected final Class<? extends ji> Mw;
    protected final String Mx;
    private jm My;
    private ji$b<I, O> Mz;
    
    static {
        CREATOR = new jk();
    }
    
    ji$a(final int br, final int mq, final boolean mr, final int ms, final boolean mt, final String mu, final int mv, final String mx, final jd jd) {
        this.BR = br;
        this.Mq = mq;
        this.Mr = mr;
        this.Ms = ms;
        this.Mt = mt;
        this.Mu = mu;
        this.Mv = mv;
        if (mx == null) {
            this.Mw = null;
            this.Mx = null;
        }
        else {
            this.Mw = jp.class;
            this.Mx = mx;
        }
        if (jd == null) {
            this.Mz = null;
            return;
        }
        this.Mz = (ji$b<I, O>)jd.hb();
    }
    
    protected ji$a(final int mq, final boolean mr, final int ms, final boolean mt, final String mu, final int mv, final Class<? extends ji> mw, final ji$b<I, O> mz) {
        this.BR = 1;
        this.Mq = mq;
        this.Mr = mr;
        this.Ms = ms;
        this.Mt = mt;
        this.Mu = mu;
        this.Mv = mv;
        this.Mw = mw;
        if (mw == null) {
            this.Mx = null;
        }
        else {
            this.Mx = mw.getCanonicalName();
        }
        this.Mz = mz;
    }
    
    public static ji$a a(final String s, final int n, final ji$b<?, ?> ji$b, final boolean b) {
        return new ji$a(ji$b.hd(), b, ji$b.he(), false, s, n, null, (ji$b<I, O>)ji$b);
    }
    
    public static <T extends ji> ji$a<T, T> a(final String s, final int n, final Class<T> clazz) {
        return new ji$a<T, T>(11, false, 11, false, s, n, clazz, null);
    }
    
    public static <T extends ji> ji$a<ArrayList<T>, ArrayList<T>> b(final String s, final int n, final Class<T> clazz) {
        return new ji$a<ArrayList<T>, ArrayList<T>>(11, true, 11, true, s, n, clazz, null);
    }
    
    public static ji$a<Integer, Integer> i(final String s, final int n) {
        return new ji$a<Integer, Integer>(0, false, 0, false, s, n, null, null);
    }
    
    public static ji$a<Double, Double> j(final String s, final int n) {
        return new ji$a<Double, Double>(4, false, 4, false, s, n, null, null);
    }
    
    public static ji$a<Boolean, Boolean> k(final String s, final int n) {
        return new ji$a<Boolean, Boolean>(6, false, 6, false, s, n, null, null);
    }
    
    public static ji$a<String, String> l(final String s, final int n) {
        return new ji$a<String, String>(7, false, 7, false, s, n, null, null);
    }
    
    public static ji$a<ArrayList<String>, ArrayList<String>> m(final String s, final int n) {
        return new ji$a<ArrayList<String>, ArrayList<String>>(7, true, 7, true, s, n, null, null);
    }
    
    public void a(final jm my) {
        this.My = my;
    }
    
    public I convertBack(final O o) {
        return this.Mz.convertBack(o);
    }
    
    public int describeContents() {
        final jk creator = ji$a.CREATOR;
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public int hd() {
        return this.Mq;
    }
    
    public int he() {
        return this.Ms;
    }
    
    public ji$a<I, O> hi() {
        return new ji$a<I, O>(this.BR, this.Mq, this.Mr, this.Ms, this.Mt, this.Mu, this.Mv, this.Mx, this.hq());
    }
    
    public boolean hj() {
        return this.Mr;
    }
    
    public boolean hk() {
        return this.Mt;
    }
    
    public String hl() {
        return this.Mu;
    }
    
    public int hm() {
        return this.Mv;
    }
    
    public Class<? extends ji> hn() {
        return this.Mw;
    }
    
    String ho() {
        if (this.Mx == null) {
            return null;
        }
        return this.Mx;
    }
    
    public boolean hp() {
        return this.Mz != null;
    }
    
    jd hq() {
        if (this.Mz == null) {
            return null;
        }
        return jd.a(this.Mz);
    }
    
    public HashMap<String, ji$a<?, ?>> hr() {
        n.i(this.Mx);
        n.i(this.My);
        return this.My.be(this.Mx);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Field\n");
        sb.append("            versionCode=").append(this.BR).append('\n');
        sb.append("                 typeIn=").append(this.Mq).append('\n');
        sb.append("            typeInArray=").append(this.Mr).append('\n');
        sb.append("                typeOut=").append(this.Ms).append('\n');
        sb.append("           typeOutArray=").append(this.Mt).append('\n');
        sb.append("        outputFieldName=").append(this.Mu).append('\n');
        sb.append("      safeParcelFieldId=").append(this.Mv).append('\n');
        sb.append("       concreteTypeName=").append(this.ho()).append('\n');
        if (this.hn() != null) {
            sb.append("     concreteType.class=").append(this.hn().getCanonicalName()).append('\n');
        }
        final StringBuilder append = sb.append("          converterName=");
        String canonicalName;
        if (this.Mz == null) {
            canonicalName = "null";
        }
        else {
            canonicalName = this.Mz.getClass().getCanonicalName();
        }
        append.append(canonicalName).append('\n');
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final jk creator = ji$a.CREATOR;
        jk.a(this, parcel, n);
    }
}
