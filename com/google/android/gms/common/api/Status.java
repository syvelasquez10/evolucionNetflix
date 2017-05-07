// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcel;
import android.content.Intent;
import android.app.Activity;
import com.google.android.gms.common.internal.zzw;
import android.app.PendingIntent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Status implements Result, SafeParcelable
{
    public static final Parcelable$Creator<Status> CREATOR;
    public static final Status zzaaD;
    public static final Status zzaaE;
    public static final Status zzaaF;
    public static final Status zzaaG;
    public static final Status zzaaH;
    private final PendingIntent mPendingIntent;
    private final int mVersionCode;
    private final int zzWu;
    private final String zzaaI;
    
    static {
        zzaaD = new Status(0);
        zzaaE = new Status(14);
        zzaaF = new Status(8);
        zzaaG = new Status(15);
        zzaaH = new Status(16);
        CREATOR = (Parcelable$Creator)new zzn();
    }
    
    public Status(final int n) {
        this(n, null);
    }
    
    Status(final int mVersionCode, final int zzWu, final String zzaaI, final PendingIntent mPendingIntent) {
        this.mVersionCode = mVersionCode;
        this.zzWu = zzWu;
        this.zzaaI = zzaaI;
        this.mPendingIntent = mPendingIntent;
    }
    
    public Status(final int n, final String s) {
        this(1, n, s, null);
    }
    
    public Status(final int n, final String s, final PendingIntent pendingIntent) {
        this(1, n, s, pendingIntent);
    }
    
    private String zznI() {
        if (this.zzaaI != null) {
            return this.zzaaI;
        }
        return CommonStatusCodes.getStatusCodeString(this.zzWu);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Status) {
            final Status status = (Status)o;
            if (this.mVersionCode == status.mVersionCode && this.zzWu == status.zzWu && zzw.equal(this.zzaaI, status.zzaaI) && zzw.equal(this.mPendingIntent, status.mPendingIntent)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Status getStatus() {
        return this;
    }
    
    public int getStatusCode() {
        return this.zzWu;
    }
    
    public String getStatusMessage() {
        return this.zzaaI;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.mVersionCode, this.zzWu, this.zzaaI, this.mPendingIntent);
    }
    
    public boolean isSuccess() {
        return this.zzWu <= 0;
    }
    
    public void startResolutionForResult(final Activity activity, final int n) {
        if (!this.hasResolution()) {
            return;
        }
        activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), n, (Intent)null, 0, 0, 0);
    }
    
    @Override
    public String toString() {
        return zzw.zzu(this).zzg("statusCode", this.zznI()).zzg("resolution", this.mPendingIntent).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzn.zza(this, parcel, n);
    }
    
    PendingIntent zznH() {
        return this.mPendingIntent;
    }
}
