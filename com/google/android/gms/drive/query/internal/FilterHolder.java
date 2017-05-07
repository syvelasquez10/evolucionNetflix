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
    final int BR;
    final ComparisonFilter<?> QG;
    final FieldOnlyFilter QH;
    final LogicalFilter QI;
    final NotFilter QJ;
    final InFilter<?> QK;
    final MatchAllFilter QL;
    final HasFilter QM;
    private final Filter QN;
    
    static {
        CREATOR = (Parcelable$Creator)new d();
    }
    
    FilterHolder(final int br, final ComparisonFilter<?> qg, final FieldOnlyFilter qh, final LogicalFilter qi, final NotFilter qj, final InFilter<?> qk, final MatchAllFilter ql, final HasFilter<?> qm) {
        this.BR = br;
        this.QG = qg;
        this.QH = qh;
        this.QI = qi;
        this.QJ = qj;
        this.QK = qk;
        this.QL = ql;
        this.QM = qm;
        if (this.QG != null) {
            this.QN = this.QG;
            return;
        }
        if (this.QH != null) {
            this.QN = this.QH;
            return;
        }
        if (this.QI != null) {
            this.QN = this.QI;
            return;
        }
        if (this.QJ != null) {
            this.QN = this.QJ;
            return;
        }
        if (this.QK != null) {
            this.QN = this.QK;
            return;
        }
        if (this.QL != null) {
            this.QN = this.QL;
            return;
        }
        if (this.QM != null) {
            this.QN = this.QM;
            return;
        }
        throw new IllegalArgumentException("At least one filter must be set.");
    }
    
    public FilterHolder(final Filter qn) {
        this.BR = 2;
        ComparisonFilter<?> qg;
        if (qn instanceof ComparisonFilter) {
            qg = (ComparisonFilter<?>)qn;
        }
        else {
            qg = null;
        }
        this.QG = qg;
        FieldOnlyFilter qh;
        if (qn instanceof FieldOnlyFilter) {
            qh = (FieldOnlyFilter)qn;
        }
        else {
            qh = null;
        }
        this.QH = qh;
        LogicalFilter qi;
        if (qn instanceof LogicalFilter) {
            qi = (LogicalFilter)qn;
        }
        else {
            qi = null;
        }
        this.QI = qi;
        NotFilter qj;
        if (qn instanceof NotFilter) {
            qj = (NotFilter)qn;
        }
        else {
            qj = null;
        }
        this.QJ = qj;
        InFilter<?> qk;
        if (qn instanceof InFilter) {
            qk = (InFilter<?>)qn;
        }
        else {
            qk = null;
        }
        this.QK = qk;
        MatchAllFilter ql;
        if (qn instanceof MatchAllFilter) {
            ql = (MatchAllFilter)qn;
        }
        else {
            ql = null;
        }
        this.QL = ql;
        HasFilter qm;
        if (qn instanceof HasFilter) {
            qm = (HasFilter)qn;
        }
        else {
            qm = null;
        }
        this.QM = qm;
        if (this.QG == null && this.QH == null && this.QI == null && this.QJ == null && this.QK == null && this.QL == null && this.QM == null) {
            throw new IllegalArgumentException("Invalid filter type or null filter.");
        }
        this.QN = qn;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Filter getFilter() {
        return this.QN;
    }
    
    @Override
    public String toString() {
        return String.format("FilterHolder[%s]", this.QN);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        d.a(this, parcel, n);
    }
}
