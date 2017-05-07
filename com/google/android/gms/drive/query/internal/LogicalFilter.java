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
    private List<Filter> GD;
    final Operator GG;
    final List<FilterHolder> GS;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new g();
    }
    
    LogicalFilter(final int xh, final Operator gg, final List<FilterHolder> gs) {
        this.xH = xh;
        this.GG = gg;
        this.GS = gs;
    }
    
    public LogicalFilter(final Operator gg, final Filter filter, final Filter... array) {
        this.xH = 1;
        this.GG = gg;
        (this.GS = new ArrayList<FilterHolder>(array.length + 1)).add(new FilterHolder(filter));
        (this.GD = new ArrayList<Filter>(array.length + 1)).add(filter);
        for (int length = array.length, i = 0; i < length; ++i) {
            final Filter filter2 = array[i];
            this.GS.add(new FilterHolder(filter2));
            this.GD.add(filter2);
        }
    }
    
    public LogicalFilter(final Operator gg, final Iterable<Filter> iterable) {
        this.xH = 1;
        this.GG = gg;
        this.GD = new ArrayList<Filter>();
        this.GS = new ArrayList<FilterHolder>();
        for (final Filter filter : iterable) {
            this.GD.add(filter);
            this.GS.add(new FilterHolder(filter));
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        g.a(this, parcel, n);
    }
}
