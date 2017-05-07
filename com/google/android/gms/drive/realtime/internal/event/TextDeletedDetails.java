// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class TextDeletedDetails implements SafeParcelable
{
    public static final Parcelable$Creator<TextDeletedDetails> CREATOR;
    final int BR;
    final int RD;
    final int mIndex;
    
    static {
        CREATOR = (Parcelable$Creator)new e();
    }
    
    TextDeletedDetails(final int br, final int mIndex, final int rd) {
        this.BR = br;
        this.mIndex = mIndex;
        this.RD = rd;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        e.a(this, parcel, n);
    }
}
