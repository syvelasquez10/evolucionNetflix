// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcel;
import android.content.IntentSender$SendIntentException;
import android.content.Intent;
import android.app.Activity;
import com.google.android.gms.internal.fo;
import com.google.android.gms.common.ConnectionResult;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Status implements Result, SafeParcelable
{
    public static final Status Bv;
    public static final Status Bw;
    public static final Status Bx;
    public static final Status By;
    public static final Status Bz;
    public static final StatusCreator CREATOR;
    private final int Ah;
    private final String BA;
    private final PendingIntent mPendingIntent;
    private final int xH;
    
    static {
        Bv = new Status(0);
        Bw = new Status(14);
        Bx = new Status(8);
        By = new Status(15);
        Bz = new Status(16);
        CREATOR = new StatusCreator();
    }
    
    public Status(final int n) {
        this(1, n, null, null);
    }
    
    Status(final int xh, final int ah, final String ba, final PendingIntent mPendingIntent) {
        this.xH = xh;
        this.Ah = ah;
        this.BA = ba;
        this.mPendingIntent = mPendingIntent;
    }
    
    public Status(final int n, final String s, final PendingIntent pendingIntent) {
        this(1, n, s, pendingIntent);
    }
    
    private String dW() {
        if (this.BA != null) {
            return this.BA;
        }
        return CommonStatusCodes.getStatusCodeString(this.Ah);
    }
    
    public int describeContents() {
        return 0;
    }
    
    PendingIntent eo() {
        return this.mPendingIntent;
    }
    
    String ep() {
        return this.BA;
    }
    
    @Deprecated
    public ConnectionResult eq() {
        return new ConnectionResult(this.Ah, this.mPendingIntent);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Status) {
            final Status status = (Status)o;
            if (this.xH == status.xH && this.Ah == status.Ah && fo.equal(this.BA, status.BA) && fo.equal(this.mPendingIntent, status.mPendingIntent)) {
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
        return this.Ah;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.xH, this.Ah, this.BA, this.mPendingIntent);
    }
    
    public boolean isCanceled() {
        return this.Ah == 16;
    }
    
    public boolean isInterrupted() {
        return this.Ah == 14;
    }
    
    public boolean isSuccess() {
        return this.Ah <= 0;
    }
    
    public void startResolutionForResult(final Activity activity, final int n) throws IntentSender$SendIntentException {
        if (!this.hasResolution()) {
            return;
        }
        activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), n, (Intent)null, 0, 0, 0);
    }
    
    @Override
    public String toString() {
        return fo.e(this).a("statusCode", this.dW()).a("resolution", this.mPendingIntent).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        StatusCreator.a(this, parcel, n);
    }
}
