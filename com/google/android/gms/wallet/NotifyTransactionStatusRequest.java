// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.internal.fq;
import android.text.TextUtils;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class NotifyTransactionStatusRequest implements SafeParcelable
{
    public static final Parcelable$Creator<NotifyTransactionStatusRequest> CREATOR;
    String abh;
    String ach;
    int status;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new m();
    }
    
    NotifyTransactionStatusRequest() {
        this.xH = 1;
    }
    
    NotifyTransactionStatusRequest(final int xh, final String abh, final int status, final String ach) {
        this.xH = xh;
        this.abh = abh;
        this.status = status;
        this.ach = ach;
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
        return this.ach;
    }
    
    public String getGoogleTransactionId() {
        return this.abh;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        m.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public NotifyTransactionStatusRequest build() {
            final boolean b = true;
            fq.b(!TextUtils.isEmpty((CharSequence)NotifyTransactionStatusRequest.this.abh), "googleTransactionId is required");
            fq.b(NotifyTransactionStatusRequest.this.status >= 1 && NotifyTransactionStatusRequest.this.status <= 8 && b, "status is an unrecognized value");
            return NotifyTransactionStatusRequest.this;
        }
        
        public Builder setDetailedReason(final String ach) {
            NotifyTransactionStatusRequest.this.ach = ach;
            return this;
        }
        
        public Builder setGoogleTransactionId(final String abh) {
            NotifyTransactionStatusRequest.this.abh = abh;
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
