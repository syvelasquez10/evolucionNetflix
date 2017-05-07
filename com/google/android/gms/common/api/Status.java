// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcel;
import android.content.IntentSender$SendIntentException;
import android.content.Intent;
import android.app.Activity;
import com.google.android.gms.internal.ee;
import com.google.android.gms.common.ConnectionResult;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Status implements Result, SafeParcelable
{
    public static final StatusCreator CREATOR;
    public static final Status nA;
    public static final Status nB;
    public static final Status nC;
    private final int kg;
    private final int mC;
    private final PendingIntent mPendingIntent;
    private final String nD;
    
    static {
        nA = new Status(0, null, null);
        nB = new Status(14, null, null);
        nC = new Status(15, null, null);
        CREATOR = new StatusCreator();
    }
    
    public Status(final int n) {
        this(1, n, null, null);
    }
    
    Status(final int kg, final int mc, final String nd, final PendingIntent mPendingIntent) {
        this.kg = kg;
        this.mC = mc;
        this.nD = nd;
        this.mPendingIntent = mPendingIntent;
    }
    
    public Status(final int n, final String s, final PendingIntent pendingIntent) {
        this(1, n, s, pendingIntent);
    }
    
    private String bh() {
        if (this.nD != null) {
            return this.nD;
        }
        return CommonStatusCodes.getStatusCodeString(this.mC);
    }
    
    PendingIntent bs() {
        return this.mPendingIntent;
    }
    
    String bt() {
        return this.nD;
    }
    
    @Deprecated
    public ConnectionResult bu() {
        return new ConnectionResult(this.mC, this.mPendingIntent);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Status) {
            final Status status = (Status)o;
            if (this.kg == status.kg && this.mC == status.mC && ee.equal(this.nD, status.nD) && ee.equal(this.mPendingIntent, status.mPendingIntent)) {
                return true;
            }
        }
        return false;
    }
    
    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }
    
    @Override
    public Status getStatus() {
        return this;
    }
    
    public int getStatusCode() {
        return this.mC;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.kg, this.mC, this.nD, this.mPendingIntent);
    }
    
    public boolean isInterrupted() {
        return this.mC == 14;
    }
    
    public boolean isSuccess() {
        return this.mC <= 0;
    }
    
    public void startResolutionForResult(final Activity activity, final int n) throws IntentSender$SendIntentException {
        if (!this.hasResolution()) {
            return;
        }
        activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), n, (Intent)null, 0, 0, 0);
    }
    
    @Override
    public String toString() {
        return ee.e(this).a("statusCode", this.bh()).a("resolution", this.mPendingIntent).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        StatusCreator.a(this, parcel, n);
    }
}
