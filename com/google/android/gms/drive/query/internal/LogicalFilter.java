// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.android.gms.drive.query.Filter;
import java.util.List;
import android.os.Parcelable$Creator;

public class LogicalFilter extends AbstractFilter
{
    public static final Parcelable$Creator<LogicalFilter> CREATOR;
    final int BR;
    final Operator QC;
    final List<FilterHolder> QP;
    private List<Filter> Qx;
    
    static {
        CREATOR = (Parcelable$Creator)new i();
    }
    
    LogicalFilter(final int br, final Operator qc, final List<FilterHolder> qp) {
        this.BR = br;
        this.QC = qc;
        this.QP = qp;
    }
    
    public LogicalFilter(final Operator qc, final Filter filter, final Filter... array) {
        this.BR = 1;
        this.QC = qc;
        (this.QP = new ArrayList<FilterHolder>(array.length + 1)).add(new FilterHolder(filter));
        (this.Qx = new ArrayList<Filter>(array.length + 1)).add(filter);
        for (int length = array.length, i = 0; i < length; ++i) {
            final Filter filter2 = array[i];
            this.QP.add(new FilterHolder(filter2));
            this.Qx.add(filter2);
        }
    }
    
    public LogicalFilter(final Operator qc, final Iterable<Filter> iterable) {
        this.BR = 1;
        this.QC = qc;
        this.Qx = new ArrayList<Filter>();
        this.QP = new ArrayList<FilterHolder>();
        for (final Filter filter : iterable) {
            this.Qx.add(filter);
            this.QP.add(new FilterHolder(filter));
        }
    }
    
    @Override
    public <T> T a(final f<T> f) {
        final ArrayList<T> list = new ArrayList<T>();
        final Iterator<FilterHolder> iterator = this.QP.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().getFilter().a(f));
        }
        return f.b(this.QC, list);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        i.a(this, parcel, n);
    }
}
