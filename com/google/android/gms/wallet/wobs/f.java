// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class f implements SafeParcelable
{
    public static final Parcelable$Creator<f> CREATOR;
    private final int BR;
    l asR;
    g aur;
    String label;
    String type;
    
    static {
        CREATOR = (Parcelable$Creator)new i();
    }
    
    f() {
        this.BR = 1;
    }
    
    f(final int br, final String label, final g aur, final String type, final l asR) {
        this.BR = br;
        this.label = label;
        this.aur = aur;
        this.type = type;
        this.asR = asR;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        i.a(this, parcel, n);
    }
}
