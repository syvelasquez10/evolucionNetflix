// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.drive.query.internal.Operator;
import com.google.android.gms.drive.query.internal.MatchAllFilter;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Query implements SafeParcelable
{
    public static final Parcelable$Creator<Query> CREATOR;
    final LogicalFilter GA;
    final String GB;
    final SortOrder GC;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    Query(final int xh, final LogicalFilter ga, final String gb, final SortOrder gc) {
        this.xH = xh;
        this.GA = ga;
        this.GB = gb;
        this.GC = gc;
    }
    
    Query(final LogicalFilter logicalFilter, final String s, final SortOrder sortOrder) {
        this(1, logicalFilter, s, sortOrder);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public SortOrder fV() {
        return this.GC;
    }
    
    public Filter getFilter() {
        return this.GA;
    }
    
    public String getPageToken() {
        return this.GB;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
    
    public static class Builder
    {
        private String GB;
        private SortOrder GC;
        private final List<Filter> GD;
        
        public Builder() {
            this.GD = new ArrayList<Filter>();
        }
        
        public Builder a(final SortOrder gc) {
            this.GC = gc;
            return this;
        }
        
        public Builder addFilter(final Filter filter) {
            if (!(filter instanceof MatchAllFilter)) {
                this.GD.add(filter);
            }
            return this;
        }
        
        public Query build() {
            return new Query(new LogicalFilter(Operator.GZ, this.GD), this.GB, this.GC);
        }
        
        public Builder setPageToken(final String gb) {
            this.GB = gb;
            return this;
        }
    }
}
