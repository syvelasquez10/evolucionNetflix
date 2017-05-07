// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.query.Filter;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class FilterHolder implements SafeParcelable
{
    public static final Parcelable$Creator<FilterHolder> CREATOR;
    final ComparisonFilter<?> GK;
    final FieldOnlyFilter GL;
    final LogicalFilter GM;
    final NotFilter GN;
    final InFilter<?> GO;
    final MatchAllFilter GP;
    private final Filter GQ;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new d();
    }
    
    FilterHolder(final int xh, final ComparisonFilter<?> gk, final FieldOnlyFilter gl, final LogicalFilter gm, final NotFilter gn, final InFilter<?> go, final MatchAllFilter gp) {
        this.xH = xh;
        this.GK = gk;
        this.GL = gl;
        this.GM = gm;
        this.GN = gn;
        this.GO = go;
        this.GP = gp;
        if (this.GK != null) {
            this.GQ = this.GK;
            return;
        }
        if (this.GL != null) {
            this.GQ = this.GL;
            return;
        }
        if (this.GM != null) {
            this.GQ = this.GM;
            return;
        }
        if (this.GN != null) {
            this.GQ = this.GN;
            return;
        }
        if (this.GO != null) {
            this.GQ = this.GO;
            return;
        }
        if (this.GP != null) {
            this.GQ = this.GP;
            return;
        }
        throw new IllegalArgumentException("At least one filter must be set.");
    }
    
    public FilterHolder(final Filter gq) {
        this.xH = 1;
        ComparisonFilter<?> gk;
        if (gq instanceof ComparisonFilter) {
            gk = (ComparisonFilter<?>)gq;
        }
        else {
            gk = null;
        }
        this.GK = gk;
        FieldOnlyFilter gl;
        if (gq instanceof FieldOnlyFilter) {
            gl = (FieldOnlyFilter)gq;
        }
        else {
            gl = null;
        }
        this.GL = gl;
        LogicalFilter gm;
        if (gq instanceof LogicalFilter) {
            gm = (LogicalFilter)gq;
        }
        else {
            gm = null;
        }
        this.GM = gm;
        NotFilter gn;
        if (gq instanceof NotFilter) {
            gn = (NotFilter)gq;
        }
        else {
            gn = null;
        }
        this.GN = gn;
        InFilter<?> go;
        if (gq instanceof InFilter) {
            go = (InFilter<?>)gq;
        }
        else {
            go = null;
        }
        this.GO = go;
        MatchAllFilter gp;
        if (gq instanceof MatchAllFilter) {
            gp = (MatchAllFilter)gq;
        }
        else {
            gp = null;
        }
        this.GP = gp;
        if (this.GK == null && this.GL == null && this.GM == null && this.GN == null && this.GO == null && this.GP == null) {
            throw new IllegalArgumentException("Invalid filter type or null filter.");
        }
        this.GQ = gq;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        d.a(this, parcel, n);
    }
}
