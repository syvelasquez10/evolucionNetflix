// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.os.Parcel;
import android.content.Intent;
import android.app.Activity;
import com.google.android.gms.common.internal.zzw;
import android.app.PendingIntent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ConnectionResult implements SafeParcelable
{
    public static final Parcelable$Creator<ConnectionResult> CREATOR;
    public static final ConnectionResult zzYi;
    private final PendingIntent mPendingIntent;
    final int mVersionCode;
    private final int zzWu;
    
    static {
        zzYi = new ConnectionResult(0, null);
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    ConnectionResult(final int mVersionCode, final int zzWu, final PendingIntent mPendingIntent) {
        this.mVersionCode = mVersionCode;
        this.zzWu = zzWu;
        this.mPendingIntent = mPendingIntent;
    }
    
    public ConnectionResult(final int n, final PendingIntent pendingIntent) {
        this(1, n, pendingIntent);
    }
    
    static String getStatusString(final int n) {
        switch (n) {
            default: {
                return "UNKNOWN_ERROR_CODE(" + n + ")";
            }
            case 0: {
                return "SUCCESS";
            }
            case 1: {
                return "SERVICE_MISSING";
            }
            case 2: {
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            }
            case 3: {
                return "SERVICE_DISABLED";
            }
            case 4: {
                return "SIGN_IN_REQUIRED";
            }
            case 5: {
                return "INVALID_ACCOUNT";
            }
            case 6: {
                return "RESOLUTION_REQUIRED";
            }
            case 7: {
                return "NETWORK_ERROR";
            }
            case 8: {
                return "INTERNAL_ERROR";
            }
            case 9: {
                return "SERVICE_INVALID";
            }
            case 10: {
                return "DEVELOPER_ERROR";
            }
            case 11: {
                return "LICENSE_CHECK_FAILED";
            }
            case 13: {
                return "CANCELED";
            }
            case 14: {
                return "TIMEOUT";
            }
            case 15: {
                return "INTERRUPTED";
            }
            case 16: {
                return "API_UNAVAILABLE";
            }
            case 17: {
                return "SIGN_IN_FAILED";
            }
            case 18: {
                return "SERVICE_UPDATING";
            }
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ConnectionResult)) {
                return false;
            }
            final ConnectionResult connectionResult = (ConnectionResult)o;
            if (this.zzWu != connectionResult.zzWu || !zzw.equal(this.mPendingIntent, connectionResult.mPendingIntent)) {
                return false;
            }
        }
        return true;
    }
    
    public int getErrorCode() {
        return this.zzWu;
    }
    
    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }
    
    public boolean hasResolution() {
        return this.zzWu != 0 && this.mPendingIntent != null;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzWu, this.mPendingIntent);
    }
    
    public boolean isSuccess() {
        return this.zzWu == 0;
    }
    
    public void startResolutionForResult(final Activity activity, final int n) {
        if (!this.hasResolution()) {
            return;
        }
        activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), n, (Intent)null, 0, 0, 0);
    }
    
    @Override
    public String toString() {
        return zzw.zzu(this).zzg("statusCode", getStatusString(this.zzWu)).zzg("resolution", this.mPendingIntent).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
