// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import java.io.Serializable;
import android.os.Parcelable$Creator;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ah implements SafeParcelable, MessageEvent
{
    public static final Parcelable$Creator<ah> CREATOR;
    final int BR;
    private final byte[] acw;
    private final String avw;
    private final String avx;
    private final int uQ;
    
    static {
        CREATOR = (Parcelable$Creator)new ai();
    }
    
    ah(final int br, final int uq, final String avw, final byte[] acw, final String avx) {
        this.BR = br;
        this.uQ = uq;
        this.avw = avw;
        this.acw = acw;
        this.avx = avx;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public byte[] getData() {
        return this.acw;
    }
    
    @Override
    public String getPath() {
        return this.avw;
    }
    
    @Override
    public int getRequestId() {
        return this.uQ;
    }
    
    @Override
    public String getSourceNodeId() {
        return this.avx;
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder().append("MessageEventParcelable[").append(this.uQ).append(",").append(this.avw).append(", size=");
        Serializable value;
        if (this.acw == null) {
            value = "null";
        }
        else {
            value = this.acw.length;
        }
        return append.append(value).append("]").toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ai.a(this, parcel, n);
    }
}
