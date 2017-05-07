// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.query.Filter;
import android.os.Parcelable$Creator;

public class NotFilter extends AbstractFilter
{
    public static final Parcelable$Creator<NotFilter> CREATOR;
    final int BR;
    final FilterHolder QQ;
    
    static {
        CREATOR = (Parcelable$Creator)new k();
    }
    
    NotFilter(final int br, final FilterHolder qq) {
        this.BR = br;
        this.QQ = qq;
    }
    
    public NotFilter(final Filter filter) {
        this(1, new FilterHolder(filter));
    }
    
    @Override
    public <T> T a(final f<T> f) {
        return f.j(this.QQ.getFilter().a(f));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        k.a(this, parcel, n);
    }
}
