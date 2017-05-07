// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class IrisNotificationSummary$1 implements Parcelable$Creator<IrisNotificationSummary>
{
    public IrisNotificationSummary createFromParcel(final Parcel parcel) {
        return new IrisNotificationSummary(parcel);
    }
    
    public IrisNotificationSummary[] newArray(final int n) {
        return new IrisNotificationSummary[n];
    }
}
