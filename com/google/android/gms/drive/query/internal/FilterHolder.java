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
    final int kg;
    final ComparisonFilter<?> rU;
    final FieldOnlyFilter rV;
    final LogicalFilter rW;
    final NotFilter rX;
    final InFilter<?> rY;
    private final Filter rZ;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    FilterHolder(final int kg, final ComparisonFilter<?> ru, final FieldOnlyFilter rv, final LogicalFilter rw, final NotFilter rx, final InFilter<?> ry) {
        this.kg = kg;
        this.rU = ru;
        this.rV = rv;
        this.rW = rw;
        this.rX = rx;
        this.rY = ry;
        if (this.rU != null) {
            this.rZ = this.rU;
            return;
        }
        if (this.rV != null) {
            this.rZ = this.rV;
            return;
        }
        if (this.rW != null) {
            this.rZ = this.rW;
            return;
        }
        if (this.rX != null) {
            this.rZ = this.rX;
            return;
        }
        if (this.rY != null) {
            this.rZ = this.rY;
            return;
        }
        throw new IllegalArgumentException("At least one filter must be set.");
    }
    
    public FilterHolder(final Filter rz) {
        this.kg = 1;
        ComparisonFilter<?> ru;
        if (rz instanceof ComparisonFilter) {
            ru = (ComparisonFilter<?>)rz;
        }
        else {
            ru = null;
        }
        this.rU = ru;
        FieldOnlyFilter rv;
        if (rz instanceof FieldOnlyFilter) {
            rv = (FieldOnlyFilter)rz;
        }
        else {
            rv = null;
        }
        this.rV = rv;
        LogicalFilter rw;
        if (rz instanceof LogicalFilter) {
            rw = (LogicalFilter)rz;
        }
        else {
            rw = null;
        }
        this.rW = rw;
        NotFilter rx;
        if (rz instanceof NotFilter) {
            rx = (NotFilter)rz;
        }
        else {
            rx = null;
        }
        this.rX = rx;
        InFilter<?> ry;
        if (rz instanceof InFilter) {
            ry = (InFilter<?>)rz;
        }
        else {
            ry = null;
        }
        this.rY = ry;
        if (this.rU == null && this.rV == null && this.rW == null && this.rX == null && this.rY == null) {
            throw new IllegalArgumentException("Invalid filter type or null filter.");
        }
        this.rZ = rz;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
