// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class SocialNotificationSummary$1 implements Parcelable$Creator<SocialNotificationSummary>
{
    public SocialNotificationSummary createFromParcel(final Parcel parcel) {
        return new SocialNotificationSummary(parcel);
    }
    
    public SocialNotificationSummary[] newArray(final int n) {
        return new SocialNotificationSummary[n];
    }
}
