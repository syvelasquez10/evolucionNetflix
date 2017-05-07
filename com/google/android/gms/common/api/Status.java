// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcel;
import android.content.Intent;
import android.app.Activity;
import com.google.android.gms.common.internal.zzt;
import android.app.PendingIntent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Status implements Result, SafeParcelable
{
    public static final Parcelable$Creator<Status> CREATOR;
    public static final Status zzXO;
    public static final Status zzXP;
    public static final Status zzXQ;
    public static final Status zzXR;
    public static final Status zzXS;
    private final PendingIntent mPendingIntent;
    private final int zzCY;
    private final int zzTR;
    private final String zzXT;
    
    static {
        zzXO = new Status(0);
        zzXP = new Status(14);
        zzXQ = new Status(8);
        zzXR = new Status(15);
        zzXS = new Status(16);
        CREATOR = (Parcelable$Creator)new zzk();
    }
    
    public Status(final int n) {
        this(n, null);
    }
    
    Status(final int zzCY, final int zzTR, final String zzXT, final PendingIntent mPendingIntent) {
        this.zzCY = zzCY;
        this.zzTR = zzTR;
        this.zzXT = zzXT;
        this.mPendingIntent = mPendingIntent;
    }
    
    public Status(final int n, final String s) {
        this(1, n, s, null);
    }
    
    public Status(final int n, final String s, final PendingIntent pendingIntent) {
        this(1, n, s, pendingIntent);
    }
    
    private String zzmS() {
        if (this.zzXT != null) {
            return this.zzXT;
        }
        return CommonStatusCodes.getStatusCodeString(this.zzTR);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Status) {
            final Status status = (Status)o;
            if (this.zzCY == status.zzCY && this.zzTR == status.zzTR && zzt.equal(this.zzXT, status.zzXT) && zzt.equal(this.mPendingIntent, status.mPendingIntent)) {
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
        return this.zzTR;
    }
    
    public String getStatusMessage() {
        return this.zzXT;
    }
    
    int getVersionCode() {
        return this.zzCY;
    }
    
    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }
    
    @Override
    public int hashCode() {
        return zzt.hashCode(this.zzCY, this.zzTR, this.zzXT, this.mPendingIntent);
    }
    
    public boolean isSuccess() {
        return this.zzTR <= 0;
    }
    
    public void startResolutionForResult(final Activity activity, final int n) {
        if (!this.hasResolution()) {
            return;
        }
        activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), n, (Intent)null, 0, 0, 0);
    }
    
    @Override
    public String toString() {
        return zzt.zzt(this).zzg("statusCode", this.zzmS()).zzg("resolution", this.mPendingIntent).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzk.zza(this, parcel, n);
    }
    
    PendingIntent zzmR() {
        return this.mPendingIntent;
    }
}
