// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;
import java.util.Collection;
import android.view.View;

public final class dt
{
    private final View nu;
    private final a oX;
    
    public dt(final String s, final Collection<String> collection, final int n, final View nu, final String s2) {
        this.oX = new a(s, collection, n, s2);
        this.nu = nu;
    }
    
    public String bF() {
        return this.oX.bF();
    }
    
    public int bG() {
        return this.oX.bG();
    }
    
    public List<String> bH() {
        return this.oX.bH();
    }
    
    public String[] bI() {
        return this.oX.bH().toArray(new String[0]);
    }
    
    public String bJ() {
        return this.oX.bJ();
    }
    
    public View bK() {
        return this.nu;
    }
    
    public String getAccountName() {
        return this.oX.getAccountName();
    }
    
    public static final class a implements SafeParcelable
    {
        public static final ef CREATOR;
        private final String jG;
        private final int kg;
        private final int nt;
        private final String nv;
        private final List<String> oY;
        
        static {
            CREATOR = new ef();
        }
        
        a(final int kg, final String jg, final List<String> list, final int nt, final String nv) {
            this.oY = new ArrayList<String>();
            this.kg = kg;
            this.jG = jg;
            this.oY.addAll(list);
            this.nt = nt;
            this.nv = nv;
        }
        
        public a(final String s, final Collection<String> collection, final int n, final String s2) {
            this(3, s, new ArrayList<String>(collection), n, s2);
        }
        
        public String bF() {
            if (this.jG != null) {
                return this.jG;
            }
            return "<<default account>>";
        }
        
        public int bG() {
            return this.nt;
        }
        
        public List<String> bH() {
            return new ArrayList<String>(this.oY);
        }
        
        public String bJ() {
            return this.nv;
        }
        
        public int describeContents() {
            return 0;
        }
        
        public String getAccountName() {
            return this.jG;
        }
        
        public int getVersionCode() {
            return this.kg;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            ef.a(this, parcel, n);
        }
    }
}
