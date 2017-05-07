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

public final class fc
{
    private final View AV;
    private final a CT;
    
    public fc(final String s, final Collection<String> collection, final int n, final View av, final String s2) {
        this.CT = new a(s, collection, n, s2);
        this.AV = av;
    }
    
    public String eC() {
        return this.CT.eC();
    }
    
    public int eD() {
        return this.CT.eD();
    }
    
    public List<String> eE() {
        return this.CT.eE();
    }
    
    public String[] eF() {
        return this.CT.eE().toArray(new String[0]);
    }
    
    public String eG() {
        return this.CT.eG();
    }
    
    public View eH() {
        return this.AV;
    }
    
    public String getAccountName() {
        return this.CT.getAccountName();
    }
    
    public static final class a implements SafeParcelable
    {
        public static final fp CREATOR;
        private final int AU;
        private final String AW;
        private final List<String> CU;
        private final String wG;
        private final int xH;
        
        static {
            CREATOR = new fp();
        }
        
        a(final int xh, final String wg, final List<String> list, final int au, final String aw) {
            this.CU = new ArrayList<String>();
            this.xH = xh;
            this.wG = wg;
            this.CU.addAll(list);
            this.AU = au;
            this.AW = aw;
        }
        
        public a(final String s, final Collection<String> collection, final int n, final String s2) {
            this(3, s, new ArrayList<String>(collection), n, s2);
        }
        
        public int describeContents() {
            return 0;
        }
        
        public String eC() {
            if (this.wG != null) {
                return this.wG;
            }
            return "<<default account>>";
        }
        
        public int eD() {
            return this.AU;
        }
        
        public List<String> eE() {
            return new ArrayList<String>(this.CU);
        }
        
        public String eG() {
            return this.AW;
        }
        
        public String getAccountName() {
            return this.wG;
        }
        
        public int getVersionCode() {
            return this.xH;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            fp.a(this, parcel, n);
        }
    }
}
