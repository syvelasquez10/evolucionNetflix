// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ClientSettings$ParcelableClientSettings implements SafeParcelable
{
    public static final ParcelableClientSettingsCreator CREATOR;
    private final int BR;
    private final String Dd;
    private final int IF;
    private final String IH;
    private final List<String> Jd;
    
    static {
        CREATOR = new ParcelableClientSettingsCreator();
    }
    
    ClientSettings$ParcelableClientSettings(final int br, final String dd, final List<String> list, final int if1, final String ih) {
        this.Jd = new ArrayList<String>();
        this.BR = br;
        this.Dd = dd;
        this.Jd.addAll(list);
        this.IF = if1;
        this.IH = ih;
    }
    
    public ClientSettings$ParcelableClientSettings(final String s, final Collection<String> collection, final int n, final String s2) {
        this(3, s, new ArrayList<String>(collection), n, s2);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAccountName() {
        return this.Dd;
    }
    
    public String getAccountNameOrDefault() {
        if (this.Dd != null) {
            return this.Dd;
        }
        return "<<default account>>";
    }
    
    public int getGravityForPopups() {
        return this.IF;
    }
    
    public String getRealClientPackageName() {
        return this.IH;
    }
    
    public List<String> getScopes() {
        return new ArrayList<String>(this.Jd);
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ParcelableClientSettingsCreator.a(this, parcel, n);
    }
}
