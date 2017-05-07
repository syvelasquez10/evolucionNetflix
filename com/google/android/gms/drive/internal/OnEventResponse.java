// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.ChangeEvent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnEventResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnEventResponse> CREATOR;
    final int BR;
    final int NS;
    final ChangeEvent Pk;
    final CompletionEvent Pl;
    
    static {
        CREATOR = (Parcelable$Creator)new am();
    }
    
    OnEventResponse(final int br, final int ns, final ChangeEvent pk, final CompletionEvent pl) {
        this.BR = br;
        this.NS = ns;
        this.Pk = pk;
        this.Pl = pl;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public DriveEvent ih() {
        switch (this.NS) {
            default: {
                throw new IllegalStateException("Unexpected event type " + this.NS);
            }
            case 1: {
                return this.Pk;
            }
            case 2: {
                return this.Pl;
            }
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        am.a(this, parcel, n);
    }
}
