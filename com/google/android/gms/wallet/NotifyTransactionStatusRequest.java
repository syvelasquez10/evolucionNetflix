// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.n;
import android.text.TextUtils;
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
    
    public static Builder newBuilder() {
        final NotifyTransactionStatusRequest notifyTransactionStatusRequest = new NotifyTransactionStatusRequest();
        notifyTransactionStatusRequest.getClass();
        return new Builder();
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
    
    public final class Builder
    {
        public NotifyTransactionStatusRequest build() {
            final boolean b = true;
            n.b(!TextUtils.isEmpty((CharSequence)NotifyTransactionStatusRequest.this.asq), (Object)"googleTransactionId is required");
            n.b(NotifyTransactionStatusRequest.this.status >= 1 && NotifyTransactionStatusRequest.this.status <= 8 && b, (Object)"status is an unrecognized value");
            return NotifyTransactionStatusRequest.this;
        }
        
        public Builder setDetailedReason(final String atq) {
            NotifyTransactionStatusRequest.this.atq = atq;
            return this;
        }
        
        public Builder setGoogleTransactionId(final String asq) {
            NotifyTransactionStatusRequest.this.asq = asq;
            return this;
        }
        
        public Builder setStatus(final int status) {
            NotifyTransactionStatusRequest.this.status = status;
            return this;
        }
    }
    
    public interface Status
    {
        public static final int SUCCESS = 1;
        
        public interface Error
        {
            public static final int AVS_DECLINE = 7;
            public static final int BAD_CARD = 4;
            public static final int BAD_CVC = 3;
            public static final int DECLINED = 5;
            public static final int FRAUD_DECLINE = 8;
            public static final int OTHER = 6;
            public static final int UNKNOWN = 2;
        }
    }
}
