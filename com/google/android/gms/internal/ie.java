// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.plus.model.moments.ItemScope;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ie extends ga implements SafeParcelable, Moment
{
    public static final if CREATOR;
    private static final HashMap<String, a<?, ?>> UI;
    private String Rd;
    private final Set<Integer> UJ;
    private ic VE;
    private ic VF;
    private String Vw;
    private String wp;
    private final int xH;
    
    static {
        CREATOR = new if();
        (UI = new HashMap<String, a<?, ?>>()).put("id", a.j("id", 2));
        ie.UI.put("result", a.a("result", 4, (Class<?>)ic.class));
        ie.UI.put("startDate", (a<?, ?>)a.j("startDate", 5));
        ie.UI.put("target", a.a("target", 6, (Class<?>)ic.class));
        ie.UI.put("type", (a<?, ?>)a.j("type", 7));
    }
    
    public ie() {
        this.xH = 1;
        this.UJ = new HashSet<Integer>();
    }
    
    ie(final Set<Integer> uj, final int xh, final String wp, final ic ve, final String vw, final ic vf, final String rd) {
        this.UJ = uj;
        this.xH = xh;
        this.wp = wp;
        this.VE = ve;
        this.Vw = vw;
        this.VF = vf;
        this.Rd = rd;
    }
    
    public ie(final Set<Integer> uj, final String wp, final ic ve, final String vw, final ic vf, final String rd) {
        this.UJ = uj;
        this.xH = 1;
        this.wp = wp;
        this.VE = ve;
        this.Vw = vw;
        this.VF = vf;
        this.Rd = rd;
    }
    
    @Override
    protected boolean a(final a a) {
        return this.UJ.contains(a.ff());
    }
    
    @Override
    protected Object aq(final String s) {
        return null;
    }
    
    @Override
    protected boolean ar(final String s) {
        return false;
    }
    
    @Override
    protected Object b(final a a) {
        switch (a.ff()) {
            default: {
                throw new IllegalStateException("Unknown safe parcelable id=" + a.ff());
            }
            case 2: {
                return this.wp;
            }
            case 4: {
                return this.VE;
            }
            case 5: {
                return this.Vw;
            }
            case 6: {
                return this.VF;
            }
            case 7: {
                return this.Rd;
            }
        }
    }
    
    public int describeContents() {
        final if creator = ie.CREATOR;
        return 0;
    }
    
    @Override
    public HashMap<String, a<?, ?>> eY() {
        return ie.UI;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ie)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ie ie = (ie)o;
        for (final a a : com.google.android.gms.internal.ie.UI.values()) {
            if (this.a((a)a)) {
                if (!ie.a((a)a)) {
                    return false;
                }
                if (!this.b((a)a).equals(ie.b((a)a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ie.a((a)a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public String getId() {
        return this.wp;
    }
    
    @Override
    public ItemScope getResult() {
        return this.VE;
    }
    
    @Override
    public String getStartDate() {
        return this.Vw;
    }
    
    @Override
    public ItemScope getTarget() {
        return this.VF;
    }
    
    @Override
    public String getType() {
        return this.Rd;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public boolean hasId() {
        return this.UJ.contains(2);
    }
    
    @Override
    public boolean hasResult() {
        return this.UJ.contains(4);
    }
    
    @Override
    public boolean hasStartDate() {
        return this.UJ.contains(5);
    }
    
    @Override
    public boolean hasTarget() {
        return this.UJ.contains(6);
    }
    
    @Override
    public boolean hasType() {
        return this.UJ.contains(7);
    }
    
    @Override
    public int hashCode() {
        final Iterator<a<?, ?>> iterator = ie.UI.values().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final a a = (a)iterator.next();
            if (this.a((a)a)) {
                n = this.b((a)a).hashCode() + (n + a.ff());
            }
        }
        return n;
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    Set<Integer> ja() {
        return this.UJ;
    }
    
    ic jr() {
        return this.VE;
    }
    
    ic js() {
        return this.VF;
    }
    
    public ie jt() {
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final if creator = ie.CREATOR;
        if.a(this, parcel, n);
    }
}
