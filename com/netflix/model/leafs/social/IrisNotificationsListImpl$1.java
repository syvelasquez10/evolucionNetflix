// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class IrisNotificationsListImpl$1 implements Parcelable$Creator<IrisNotificationsListImpl>
{
    public IrisNotificationsListImpl createFromParcel(final Parcel parcel) {
        return new IrisNotificationsListImpl(parcel);
    }
    
    public IrisNotificationsListImpl[] newArray(final int n) {
        return new IrisNotificationsListImpl[n];
    }
}
