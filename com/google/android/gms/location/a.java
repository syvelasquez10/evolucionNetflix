// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class a implements SafeParcelable
{
    public static final b CREATOR;
    private final int kg;
    private final PendingIntent xr;
    
    static {
        CREATOR = new b();
    }
    
    public a(final int kg, final PendingIntent xr) {
        this.kg = kg;
        this.xr = xr;
    }
    
    public PendingIntent dB() {
        return this.xr;
    }
    
    public int describeContents() {
        final b creator = a.CREATOR;
        return 0;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final b creator = a.CREATOR;
        b.a(this, parcel, n);
    }
}
