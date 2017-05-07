// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class FriendForRecommendation$1 implements Parcelable$Creator<FriendForRecommendation>
{
    public FriendForRecommendation createFromParcel(final Parcel parcel) {
        return new FriendForRecommendation(parcel);
    }
    
    public FriendForRecommendation[] newArray(final int n) {
        return new FriendForRecommendation[n];
    }
}
