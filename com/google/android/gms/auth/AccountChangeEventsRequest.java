// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountChangeEventsRequest implements SafeParcelable
{
    public static final AccountChangeEventsRequestCreator CREATOR;
    String Dd;
    final int Di;
    int Dl;
    
    static {
        CREATOR = new AccountChangeEventsRequestCreator();
    }
    
    public AccountChangeEventsRequest() {
        this.Di = 1;
    }
    
    AccountChangeEventsRequest(final int di, final int dl, final String dd) {
        this.Di = di;
        this.Dl = dl;
        this.Dd = dd;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAccountName() {
        return this.Dd;
    }
    
    public int getEventIndex() {
        return this.Dl;
    }
    
    public AccountChangeEventsRequest setAccountName(final String dd) {
        this.Dd = dd;
        return this;
    }
    
    public AccountChangeEventsRequest setEventIndex(final int dl) {
        this.Dl = dl;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        AccountChangeEventsRequestCreator.a(this, parcel, n);
    }
}
