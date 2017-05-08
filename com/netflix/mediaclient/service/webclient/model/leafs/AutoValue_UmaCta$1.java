// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class AutoValue_UmaCta$1 implements Parcelable$Creator<AutoValue_UmaCta>
{
    public AutoValue_UmaCta createFromParcel(final Parcel parcel) {
        String string = null;
        boolean b = true;
        String string2;
        if (parcel.readInt() == 0) {
            string2 = parcel.readString();
        }
        else {
            string2 = null;
        }
        String string3;
        if (parcel.readInt() == 0) {
            string3 = parcel.readString();
        }
        else {
            string3 = null;
        }
        String string4;
        if (parcel.readInt() == 0) {
            string4 = parcel.readString();
        }
        else {
            string4 = null;
        }
        if (parcel.readInt() == 0) {
            string = parcel.readString();
        }
        final boolean b2 = parcel.readInt() == 1;
        if (parcel.readInt() != 1) {
            b = false;
        }
        return new AutoValue_UmaCta(string2, string3, string4, string, b2, b);
    }
    
    public AutoValue_UmaCta[] newArray(final int n) {
        return new AutoValue_UmaCta[n];
    }
}
