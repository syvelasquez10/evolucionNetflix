// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.text.TextUtils;
import java.util.Locale;
import com.google.android.gms.drive.query.internal.FieldWithSortOrder;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class SortOrder implements SafeParcelable
{
    public static final Parcelable$Creator<SortOrder> CREATOR;
    final int BR;
    final List<FieldWithSortOrder> QA;
    final boolean QB;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    SortOrder(final int br, final List<FieldWithSortOrder> qa, final boolean qb) {
        this.BR = br;
        this.QA = qa;
        this.QB = qb;
    }
    
    private SortOrder(final List<FieldWithSortOrder> list, final boolean b) {
        this(1, list, b);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String toString() {
        return String.format(Locale.US, "SortOrder[%s, %s]", TextUtils.join((CharSequence)",", (Iterable)this.QA), this.QB);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
