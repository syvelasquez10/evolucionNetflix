// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class AutoValue_UmaCta extends $AutoValue_UmaCta
{
    public static final Parcelable$Creator<AutoValue_UmaCta> CREATOR;
    
    static {
        CREATOR = (Parcelable$Creator)new AutoValue_UmaCta$1();
    }
    
    AutoValue_UmaCta(final String s, final String s2, final String s3, final String s4, final boolean b, final boolean b2) {
        super(s, s2, s3, s4, b, b2);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        final int n2 = 1;
        if (this.text() == null) {
            parcel.writeInt(1);
        }
        else {
            parcel.writeInt(0);
            parcel.writeString(this.text());
        }
        if (this.action() == null) {
            parcel.writeInt(1);
        }
        else {
            parcel.writeInt(0);
            parcel.writeString(this.action());
        }
        if (this.actionType() == null) {
            parcel.writeInt(1);
        }
        else {
            parcel.writeInt(0);
            parcel.writeString(this.actionType());
        }
        if (this.callback() == null) {
            parcel.writeInt(1);
        }
        else {
            parcel.writeInt(0);
            parcel.writeString(this.callback());
        }
        if (this.selected()) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        if (this.autoLogin()) {
            n = n2;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
    }
}
