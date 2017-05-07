// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.internal.eg;
import android.text.TextUtils;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class NotifyTransactionStatusRequest implements SafeParcelable
{
    public static final Parcelable$Creator<NotifyTransactionStatusRequest> CREATOR;
    String GV;
    String Gn;
    final int kg;
    int status;
    
    static {
        CREATOR = (Parcelable$Creator)new j();
    }
    
    NotifyTransactionStatusRequest() {
        this.kg = 1;
    }
    
    NotifyTransactionStatusRequest(final int kg, final String gn, final int status, final String gv) {
        this.kg = kg;
        this.Gn = gn;
        this.status = status;
        this.GV = gv;
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
        return this.GV;
    }
    
    public String getGoogleTransactionId() {
        return this.Gn;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        j.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public NotifyTransactionStatusRequest build() {
            final boolean b = true;
            eg.b(!TextUtils.isEmpty((CharSequence)NotifyTransactionStatusRequest.this.Gn), "googleTransactionId is required");
            eg.b(NotifyTransactionStatusRequest.this.status >= 1 && NotifyTransactionStatusRequest.this.status <= 8 && b, "status is an unrecognized value");
            return NotifyTransactionStatusRequest.this;
        }
        
        public Builder setDetailedReason(final String gv) {
            NotifyTransactionStatusRequest.this.GV = gv;
            return this;
        }
        
        public Builder setGoogleTransactionId(final String gn) {
            NotifyTransactionStatusRequest.this.Gn = gn;
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
