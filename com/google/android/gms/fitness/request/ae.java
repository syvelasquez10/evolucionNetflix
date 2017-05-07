// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.n;
import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.fitness.data.Subscription;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ae implements SafeParcelable
{
    public static final Parcelable$Creator<ae> CREATOR;
    private final int BR;
    private final Subscription UH;
    private final boolean UI;
    
    static {
        CREATOR = (Parcelable$Creator)new af();
    }
    
    ae(final int br, final Subscription uh, final boolean ui) {
        this.BR = br;
        this.UH = uh;
        this.UI = ui;
    }
    
    private ae(final ae$a ae$a) {
        this.BR = 1;
        this.UH = ae$a.UH;
        this.UI = ae$a.UI;
    }
    
    public int describeContents() {
        return 0;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public Subscription jB() {
        return this.UH;
    }
    
    public boolean jC() {
        return this.UI;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("subscription", this.UH).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        af.a(this, parcel, n);
    }
}
