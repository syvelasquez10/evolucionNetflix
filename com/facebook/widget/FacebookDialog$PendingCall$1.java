// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class FacebookDialog$PendingCall$1 implements Parcelable$Creator<FacebookDialog$PendingCall>
{
    public FacebookDialog$PendingCall createFromParcel(final Parcel parcel) {
        return new FacebookDialog$PendingCall(parcel, null);
    }
    
    public FacebookDialog$PendingCall[] newArray(final int n) {
        return new FacebookDialog$PendingCall[n];
    }
}
