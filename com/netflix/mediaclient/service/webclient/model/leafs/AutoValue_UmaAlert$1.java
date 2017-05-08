// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class AutoValue_UmaAlert$1 implements Parcelable$Creator<AutoValue_UmaAlert>
{
    public AutoValue_UmaAlert createFromParcel(final Parcel parcel) {
        final int int1 = parcel.readInt();
        final int int2 = parcel.readInt();
        String string;
        if (parcel.readInt() == 0) {
            string = parcel.readString();
        }
        else {
            string = null;
        }
        String string2;
        if (parcel.readInt() == 0) {
            string2 = parcel.readString();
        }
        else {
            string2 = null;
        }
        final long long1 = parcel.readLong();
        String string3;
        if (parcel.readInt() == 0) {
            string3 = parcel.readString();
        }
        else {
            string3 = null;
        }
        final boolean b = parcel.readInt() == 1;
        String string4;
        if (parcel.readInt() == 0) {
            string4 = parcel.readString();
        }
        else {
            string4 = null;
        }
        String string5;
        if (parcel.readInt() == 0) {
            string5 = parcel.readString();
        }
        else {
            string5 = null;
        }
        UmaCta umaCta;
        if (parcel.readInt() == 0) {
            umaCta = (UmaCta)parcel.readParcelable(UmaCta.class.getClassLoader());
        }
        else {
            umaCta = null;
        }
        UmaCta umaCta2;
        if (parcel.readInt() == 0) {
            umaCta2 = (UmaCta)parcel.readParcelable(UmaCta.class.getClassLoader());
        }
        else {
            umaCta2 = null;
        }
        return new AutoValue_UmaAlert(int1, int2, string, string2, long1, string3, b, string4, string5, umaCta, umaCta2, parcel.readLong());
    }
    
    public AutoValue_UmaAlert[] newArray(final int n) {
        return new AutoValue_UmaAlert[n];
    }
}
