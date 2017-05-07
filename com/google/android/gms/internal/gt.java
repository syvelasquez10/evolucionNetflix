// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Collection;
import java.util.HashSet;
import java.util.Collections;
import java.util.Set;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class gt implements SafeParcelable
{
    public static final gu CREATOR;
    final int kg;
    private final int yf;
    final List<gx> yg;
    private final String yh;
    private final String yi;
    private final boolean yj;
    private final Set<gx> yk;
    
    static {
        CREATOR = new gu();
    }
    
    gt(final int kg, final int yf, final List<gx> list, final String s, final String s2, final boolean yj) {
        this.kg = kg;
        this.yf = yf;
        List<gx> yg;
        if (list == null) {
            yg = Collections.emptyList();
        }
        else {
            yg = Collections.unmodifiableList((List<? extends gx>)list);
        }
        this.yg = yg;
        String yh = s;
        if (s == null) {
            yh = "";
        }
        this.yh = yh;
        String yi;
        if ((yi = s2) == null) {
            yi = "";
        }
        this.yi = yi;
        this.yj = yj;
        if (this.yg.isEmpty()) {
            this.yk = Collections.emptySet();
            return;
        }
        this.yk = Collections.unmodifiableSet((Set<? extends gx>)new HashSet<gx>(this.yg));
    }
    
    public int dO() {
        return this.yf;
    }
    
    public String dP() {
        return this.yh;
    }
    
    public String dQ() {
        return this.yi;
    }
    
    public boolean dR() {
        return this.yj;
    }
    
    public int describeContents() {
        final gu creator = gt.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof gt)) {
                return false;
            }
            final gt gt = (gt)o;
            if (this.yf != gt.yf || !this.yk.equals(gt.yk) || this.yh != gt.yh || this.yi != gt.yi || this.yj != gt.yj) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.yf, this.yk, this.yh, this.yi, this.yj);
    }
    
    @Override
    public String toString() {
        return ee.e(this).a("maxResults", this.yf).a("types", this.yk).a("nameQuery", this.yh).a("textQuery", this.yi).a("isOpenNowRequired", this.yj).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final gu creator = gt.CREATOR;
        gu.a(this, parcel, n);
    }
}
