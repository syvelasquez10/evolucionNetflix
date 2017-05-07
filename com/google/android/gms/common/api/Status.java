// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Status implements Result, SafeParcelable
{
    public static final StatusCreator CREATOR;
    public static final Status Jo;
    public static final Status Jp;
    public static final Status Jq;
    public static final Status Jr;
    public static final Status Js;
    private final int BR;
    private final int HF;
    private final String Jt;
    private final PendingIntent mPendingIntent;
    
    static {
        Jo = new Status(0);
        Jp = new Status(14);
        Jq = new Status(8);
        Jr = new Status(15);
        Js = new Status(16);
        CREATOR = new StatusCreator();
    }
    
    public Status(final int n) {
        this(1, n, null, null);
    }
    
    Status(final int br, final int hf, final String jt, final PendingIntent mPendingIntent) {
        this.BR = br;
        this.HF = hf;
        this.Jt = jt;
        this.mPendingIntent = mPendingIntent;
    }
    
    public Status(final int n, final String s, final PendingIntent pendingIntent) {
        this(1, n, s, pendingIntent);
    }
    
    private String fY() {
        if (this.Jt != null) {
            return this.Jt;
        }
        return CommonStatusCodes.getStatusCodeString(this.HF);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Status) {
            final Status status = (Status)o;
            if (this.BR == status.BR && this.HF == status.HF && m.equal(this.Jt, status.Jt) && m.equal(this.mPendingIntent, status.mPendingIntent)) {
                return true;
            }
        }
        return false;
    }
    
    PendingIntent getPendingIntent() {
        return this.mPendingIntent;
    }
    
    @Override
    public Status getStatus() {
        return this;
    }
    
    public int getStatusCode() {
        return this.HF;
    }
    
    public String getStatusMessage() {
        return this.Jt;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.BR, this.HF, this.Jt, this.mPendingIntent);
    }
    
    public boolean isSuccess() {
        return this.HF <= 0;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("statusCode", this.fY()).a("resolution", this.mPendingIntent).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        StatusCreator.a(this, parcel, n);
    }
}
