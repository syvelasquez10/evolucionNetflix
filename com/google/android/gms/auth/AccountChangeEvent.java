// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountChangeEvent implements SafeParcelable
{
    public static final AccountChangeEventCreator CREATOR;
    final String Dd;
    final int Di;
    final long Dj;
    final int Dk;
    final int Dl;
    final String Dm;
    
    static {
        CREATOR = new AccountChangeEventCreator();
    }
    
    AccountChangeEvent(final int di, final long dj, final String s, final int dk, final int dl, final String dm) {
        this.Di = di;
        this.Dj = dj;
        this.Dd = n.i(s);
        this.Dk = dk;
        this.Dl = dl;
        this.Dm = dm;
    }
    
    public AccountChangeEvent(final long dj, final String s, final int dk, final int dl, final String dm) {
        this.Di = 1;
        this.Dj = dj;
        this.Dd = n.i(s);
        this.Dk = dk;
        this.Dl = dl;
        this.Dm = dm;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof AccountChangeEvent)) {
                return false;
            }
            final AccountChangeEvent accountChangeEvent = (AccountChangeEvent)o;
            if (this.Di != accountChangeEvent.Di || this.Dj != accountChangeEvent.Dj || !m.equal(this.Dd, accountChangeEvent.Dd) || this.Dk != accountChangeEvent.Dk || this.Dl != accountChangeEvent.Dl || !m.equal(this.Dm, accountChangeEvent.Dm)) {
                return false;
            }
        }
        return true;
    }
    
    public String getAccountName() {
        return this.Dd;
    }
    
    public String getChangeData() {
        return this.Dm;
    }
    
    public int getChangeType() {
        return this.Dk;
    }
    
    public int getEventIndex() {
        return this.Dl;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Di, this.Dj, this.Dd, this.Dk, this.Dl, this.Dm);
    }
    
    @Override
    public String toString() {
        String s = "UNKNOWN";
        switch (this.Dk) {
            case 1: {
                s = "ADDED";
                break;
            }
            case 2: {
                s = "REMOVED";
                break;
            }
            case 4: {
                s = "RENAMED_TO";
                break;
            }
            case 3: {
                s = "RENAMED_FROM";
                break;
            }
        }
        return "AccountChangeEvent {accountName = " + this.Dd + ", changeType = " + s + ", changeData = " + this.Dm + ", eventIndex = " + this.Dl + "}";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        AccountChangeEventCreator.a(this, parcel, n);
    }
}
