// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

final class AutoValue_UmaAlert extends $AutoValue_UmaAlert
{
    public static final Parcelable$Creator<AutoValue_UmaAlert> CREATOR;
    
    static {
        CREATOR = (Parcelable$Creator)new AutoValue_UmaAlert$1();
    }
    
    AutoValue_UmaAlert(final int n, final int n2, final String s, final String s2, final long n3, final String s3, final boolean b, final String s4, final String s5, final UmaCta umaCta, final UmaCta umaCta2, final long n4) {
        super(n, n2, s, s2, n3, s3, b, s4, s5, umaCta, umaCta2, n4);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.abTestCell());
        parcel.writeInt(this.abTestId());
        if (this.locale() == null) {
            parcel.writeInt(1);
        }
        else {
            parcel.writeInt(0);
            parcel.writeString(this.locale());
        }
        if (this.messageName() == null) {
            parcel.writeInt(1);
        }
        else {
            parcel.writeInt(0);
            parcel.writeString(this.messageName());
        }
        parcel.writeLong(this.messageId());
        if (this.viewType() == null) {
            parcel.writeInt(1);
        }
        else {
            parcel.writeInt(0);
            parcel.writeString(this.viewType());
        }
        int n2;
        if (this.blocking()) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        parcel.writeInt(n2);
        if (this.title() == null) {
            parcel.writeInt(1);
        }
        else {
            parcel.writeInt(0);
            parcel.writeString(this.title());
        }
        if (this.body() == null) {
            parcel.writeInt(1);
        }
        else {
            parcel.writeInt(0);
            parcel.writeString(this.body());
        }
        if (this.cta1() == null) {
            parcel.writeInt(1);
        }
        else {
            parcel.writeInt(0);
            parcel.writeParcelable((Parcelable)this.cta1(), n);
        }
        if (this.cta2() == null) {
            parcel.writeInt(1);
        }
        else {
            parcel.writeInt(0);
            parcel.writeParcelable((Parcelable)this.cta2(), n);
        }
        parcel.writeLong(this.timestamp());
    }
}
