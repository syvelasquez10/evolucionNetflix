// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class SocialNotificationsListSummary$1 implements Parcelable$Creator<SocialNotificationsListSummary>
{
    public SocialNotificationsListSummary createFromParcel(final Parcel parcel) {
        return new SocialNotificationsListSummary(parcel);
    }
    
    public SocialNotificationsListSummary[] newArray(final int n) {
        return new SocialNotificationsListSummary[n];
    }
}
