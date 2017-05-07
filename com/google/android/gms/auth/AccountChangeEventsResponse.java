// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountChangeEventsResponse implements SafeParcelable
{
    public static final AccountChangeEventsResponseCreator CREATOR;
    final int Di;
    final List<AccountChangeEvent> me;
    
    static {
        CREATOR = new AccountChangeEventsResponseCreator();
    }
    
    AccountChangeEventsResponse(final int di, final List<AccountChangeEvent> list) {
        this.Di = di;
        this.me = n.i(list);
    }
    
    public AccountChangeEventsResponse(final List<AccountChangeEvent> list) {
        this.Di = 1;
        this.me = n.i(list);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public List<AccountChangeEvent> getEvents() {
        return this.me;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        AccountChangeEventsResponseCreator.a(this, parcel, n);
    }
}
