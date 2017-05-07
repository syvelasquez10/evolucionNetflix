// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class NotifyTransactionStatusRequest implements SafeParcelable
{
    public static final Parcelable$Creator<NotifyTransactionStatusRequest> CREATOR;
    final int BR;
    String asq;
    String atq;
    int status;
    
    static {
        CREATOR = (Parcelable$Creator)new m();
    }
    
    NotifyTransactionStatusRequest() {
        this.BR = 1;
    }
    
    NotifyTransactionStatusRequest(final int br, final String asq, final int status, final String atq) {
        this.BR = br;
        this.asq = asq;
        this.status = status;
        this.atq = atq;
    }
    
    public static NotifyTransactionStatusRequest$Builder newBuilder() {
        final NotifyTransactionStatusRequest notifyTransactionStatusRequest = new NotifyTransactionStatusRequest();
        notifyTransactionStatusRequest.getClass();
        return new NotifyTransactionStatusRequest$Builder(notifyTransactionStatusRequest, null);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getDetailedReason() {
        return this.atq;
    }
    
    public String getGoogleTransactionId() {
        return this.asq;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        m.a(this, parcel, n);
    }
}
