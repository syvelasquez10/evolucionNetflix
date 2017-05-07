// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.drive.query.internal.Operator;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Query implements SafeParcelable
{
    public static final Parcelable$Creator<Query> CREATOR;
    final int kg;
    LogicalFilter rO;
    String rP;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    Query(final int kg, final LogicalFilter ro, final String rp) {
        this.kg = kg;
        this.rO = ro;
        this.rP = rp;
    }
    
    Query(final LogicalFilter logicalFilter, final String s) {
        this(1, logicalFilter, s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Filter getFilter() {
        return this.rO;
    }
    
    public String getPageToken() {
        return this.rP;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
    
    public static class Builder
    {
        private String rP;
        private final List<Filter> rQ;
        
        public Builder() {
            this.rQ = new ArrayList<Filter>();
        }
        
        public Builder addFilter(final Filter filter) {
            this.rQ.add(filter);
            return this;
        }
        
        public Query build() {
            return new Query(new LogicalFilter(Operator.si, this.rQ), this.rP);
        }
        
        public Builder setPageToken(final String rp) {
            this.rP = rp;
            return this;
        }
    }
}
