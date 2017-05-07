// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.drive.query.internal.Operator;
import com.google.android.gms.drive.query.internal.MatchAllFilter;
import java.util.ArrayList;
import android.os.Parcel;
import java.util.Locale;
import java.util.List;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Query implements SafeParcelable
{
    public static final Parcelable$Creator<Query> CREATOR;
    final int BR;
    final LogicalFilter Qt;
    final String Qu;
    final SortOrder Qv;
    final List<String> Qw;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    Query(final int br, final LogicalFilter qt, final String qu, final SortOrder qv, final List<String> qw) {
        this.BR = br;
        this.Qt = qt;
        this.Qu = qu;
        this.Qv = qv;
        this.Qw = qw;
    }
    
    Query(final LogicalFilter logicalFilter, final String s, final SortOrder sortOrder, final List<String> list) {
        this(1, logicalFilter, s, sortOrder, list);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Filter getFilter() {
        return this.Qt;
    }
    
    public String getPageToken() {
        return this.Qu;
    }
    
    public SortOrder getSortOrder() {
        return this.Qv;
    }
    
    public List<String> iq() {
        return this.Qw;
    }
    
    @Override
    public String toString() {
        return String.format(Locale.US, "Query[%s,%s,PageToken=%s]", this.Qt, this.Qv, this.Qu);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
    
    public static class Builder
    {
        private String Qu;
        private SortOrder Qv;
        private List<String> Qw;
        private final List<Filter> Qx;
        
        public Builder() {
            this.Qx = new ArrayList<Filter>();
        }
        
        public Builder(final Query query) {
            (this.Qx = new ArrayList<Filter>()).add(query.getFilter());
            this.Qu = query.getPageToken();
            this.Qv = query.getSortOrder();
            this.Qw = query.iq();
        }
        
        public Builder addFilter(final Filter filter) {
            if (!(filter instanceof MatchAllFilter)) {
                this.Qx.add(filter);
            }
            return this;
        }
        
        public Query build() {
            return new Query(new LogicalFilter(Operator.QW, this.Qx), this.Qu, this.Qv, this.Qw);
        }
        
        public Builder setPageToken(final String qu) {
            this.Qu = qu;
            return this;
        }
        
        public Builder setSortOrder(final SortOrder qv) {
            this.Qv = qv;
            return this;
        }
    }
}
