// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class IrisNotificationsListSummary$1 implements Parcelable$Creator<IrisNotificationsListSummary>
{
    public IrisNotificationsListSummary createFromParcel(final Parcel parcel) {
        return new IrisNotificationsListSummary(parcel);
    }
    
    public IrisNotificationsListSummary[] newArray(final int n) {
        return new IrisNotificationsListSummary[n];
    }
}
