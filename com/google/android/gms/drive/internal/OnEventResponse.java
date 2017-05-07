// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.events.ConflictEvent;
import com.google.android.gms.drive.events.ChangeEvent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnEventResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnEventResponse> CREATOR;
    final int ES;
    final ChangeEvent FH;
    final ConflictEvent FI;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new ac();
    }
    
    OnEventResponse(final int xh, final int es, final ChangeEvent fh, final ConflictEvent fi) {
        this.xH = xh;
        this.ES = es;
        this.FH = fh;
        this.FI = fi;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public ChangeEvent fL() {
        return this.FH;
    }
    
    public ConflictEvent fM() {
        return this.FI;
    }
    
    public int getEventType() {
        return this.ES;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ac.a(this, parcel, n);
    }
}
