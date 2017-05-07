// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class LogicalFilter implements SafeParcelable, Filter
{
    public static final Parcelable$Creator<LogicalFilter> CREATOR;
    final int kg;
    private List<Filter> rQ;
    final Operator rR;
    final List<FilterHolder> sb;
    
    static {
        CREATOR = (Parcelable$Creator)new f();
    }
    
    LogicalFilter(final int kg, final Operator rr, final List<FilterHolder> sb) {
        this.kg = kg;
        this.rR = rr;
        this.sb = sb;
    }
    
    public LogicalFilter(final Operator rr, final Filter filter, final Filter... array) {
        this.kg = 1;
        this.rR = rr;
        (this.sb = new ArrayList<FilterHolder>(array.length + 1)).add(new FilterHolder(filter));
        (this.rQ = new ArrayList<Filter>(array.length + 1)).add(filter);
        for (int length = array.length, i = 0; i < length; ++i) {
            final Filter filter2 = array[i];
            this.sb.add(new FilterHolder(filter2));
            this.rQ.add(filter2);
        }
    }
    
    public LogicalFilter(final Operator rr, final List<Filter> rq) {
        this.kg = 1;
        this.rR = rr;
        this.rQ = rq;
        this.sb = new ArrayList<FilterHolder>(rq.size());
        final Iterator<Filter> iterator = rq.iterator();
        while (iterator.hasNext()) {
            this.sb.add(new FilterHolder(iterator.next()));
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
}
