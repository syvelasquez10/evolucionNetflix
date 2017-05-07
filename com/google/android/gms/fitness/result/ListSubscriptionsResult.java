// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.result;

import android.os.Parcel;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.n;
import java.util.Collections;
import com.google.android.gms.fitness.data.Subscription;
import java.util.List;
import com.google.android.gms.common.api.Status;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.api.Result;

public class ListSubscriptionsResult implements Result, SafeParcelable
{
    public static final Parcelable$Creator<ListSubscriptionsResult> CREATOR;
    private final int BR;
    private final Status CM;
    private final List<Subscription> UN;
    
    static {
        CREATOR = (Parcelable$Creator)new e();
    }
    
    ListSubscriptionsResult(final int br, final List<Subscription> un, final Status cm) {
        this.BR = br;
        this.UN = un;
        this.CM = cm;
    }
    
    public ListSubscriptionsResult(final List<Subscription> list, final Status status) {
        this.BR = 3;
        this.UN = Collections.unmodifiableList((List<? extends Subscription>)list);
        this.CM = n.b(status, "status");
    }
    
    public static ListSubscriptionsResult G(final Status status) {
        return new ListSubscriptionsResult(Collections.emptyList(), status);
    }
    
    private boolean b(final ListSubscriptionsResult listSubscriptionsResult) {
        return this.CM.equals(listSubscriptionsResult.CM) && m.equal(this.UN, listSubscriptionsResult.UN);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof ListSubscriptionsResult && this.b((ListSubscriptionsResult)o));
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
    
    public List<Subscription> getSubscriptions() {
        return this.UN;
    }
    
    public List<Subscription> getSubscriptions(final DataType dataType) {
        final ArrayList<Subscription> list = new ArrayList<Subscription>();
        for (final Subscription subscription : list) {
            if (subscription.getDataType().equals(dataType)) {
                list.add(subscription);
            }
        }
        return (List<Subscription>)Collections.unmodifiableList((List<?>)list);
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.CM, this.UN);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("status", this.CM).a("dataSets", this.UN).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        e.a(this, parcel, n);
    }
}
