// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;
import java.util.Collection;
import android.view.View;

public final class ClientSettings
{
    private final View IG;
    private final ParcelableClientSettings Lk;
    
    public ClientSettings(final String s, final Collection<String> collection, final int n, final View ig, final String s2) {
        this.Lk = new ParcelableClientSettings(s, collection, n, s2);
        this.IG = ig;
    }
    
    public String getAccountName() {
        return this.Lk.getAccountName();
    }
    
    public String getAccountNameOrDefault() {
        return this.Lk.getAccountNameOrDefault();
    }
    
    public int getGravityForPopups() {
        return this.Lk.getGravityForPopups();
    }
    
    public ParcelableClientSettings getParcelableClientSettings() {
        return this.Lk;
    }
    
    public String getRealClientPackageName() {
        return this.Lk.getRealClientPackageName();
    }
    
    public List<String> getScopes() {
        return this.Lk.getScopes();
    }
    
    public String[] getScopesArray() {
        return this.Lk.getScopes().toArray(new String[0]);
    }
    
    public View getViewForPopups() {
        return this.IG;
    }
    
    public static final class ParcelableClientSettings implements SafeParcelable
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
        
        ParcelableClientSettings(final int br, final String dd, final List<String> list, final int if1, final String ih) {
            this.Jd = new ArrayList<String>();
            this.BR = br;
            this.Dd = dd;
            this.Jd.addAll(list);
            this.IF = if1;
            this.IH = ih;
        }
        
        public ParcelableClientSettings(final String s, final Collection<String> collection, final int n, final String s2) {
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
}
