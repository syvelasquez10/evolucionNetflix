// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class SocialNotificationsListImpl$1 implements Parcelable$Creator<SocialNotificationsListImpl>
{
    public SocialNotificationsListImpl createFromParcel(final Parcel parcel) {
        return new SocialNotificationsListImpl(parcel);
    }
    
    public SocialNotificationsListImpl[] newArray(final int n) {
        return new SocialNotificationsListImpl[n];
    }
}
