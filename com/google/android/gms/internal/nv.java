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

public final class nv extends jj implements Moment
{
    public static final nw CREATOR;
    private static final HashMap<String, ji$a<?, ?>> alQ;
    String BL;
    final int BR;
    final Set<Integer> alR;
    String amE;
    nt amM;
    nt amN;
    String uO;
    
    static {
        CREATOR = new nw();
        (alQ = new HashMap<String, ji$a<?, ?>>()).put("id", ji$a.l("id", 2));
        nv.alQ.put("result", ji$a.a("result", 4, (Class<?>)nt.class));
        nv.alQ.put("startDate", ji$a.l("startDate", 5));
        nv.alQ.put("target", ji$a.a("target", 6, (Class<?>)nt.class));
        nv.alQ.put("type", ji$a.l("type", 7));
    }
    
    public nv() {
        this.BR = 1;
        this.alR = new HashSet<Integer>();
    }
    
    nv(final Set<Integer> alR, final int br, final String bl, final nt amM, final String amE, final nt amN, final String uo) {
        this.alR = alR;
        this.BR = br;
        this.BL = bl;
        this.amM = amM;
        this.amE = amE;
        this.amN = amN;
        this.uO = uo;
    }
    
    public nv(final Set<Integer> alR, final String bl, final nt amM, final String amE, final nt amN, final String uo) {
        this.alR = alR;
        this.BR = 1;
        this.BL = bl;
        this.amM = amM;
        this.amE = amE;
        this.amN = amN;
        this.uO = uo;
    }
    
    @Override
    protected boolean a(final ji$a ji$a) {
        return this.alR.contains(ji$a.hm());
    }
    
    @Override
    protected Object b(final ji$a ji$a) {
        switch (ji$a.hm()) {
            default: {
                throw new IllegalStateException("Unknown safe parcelable id=" + ji$a.hm());
            }
            case 2: {
                return this.BL;
            }
            case 4: {
                return this.amM;
            }
            case 5: {
                return this.amE;
            }
            case 6: {
                return this.amN;
            }
            case 7: {
                return this.uO;
            }
        }
    }
    
    public int describeContents() {
        final nw creator = nv.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof nv)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final nv nv = (nv)o;
        for (final ji$a<?, ?> ji$a : com.google.android.gms.internal.nv.alQ.values()) {
            if (this.a(ji$a)) {
                if (!nv.a(ji$a)) {
                    return false;
                }
                if (!this.b(ji$a).equals(nv.b(ji$a))) {
                    return false;
                }
                continue;
            }
            else {
                if (nv.a(ji$a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public String getId() {
        return this.BL;
    }
    
    @Override
    public ItemScope getResult() {
        return this.amM;
    }
    
    @Override
    public String getStartDate() {
        return this.amE;
    }
    
    @Override
    public ItemScope getTarget() {
        return this.amN;
    }
    
    @Override
    public String getType() {
        return this.uO;
    }
    
    @Override
    public boolean hasId() {
        return this.alR.contains(2);
    }
    
    @Override
    public boolean hasResult() {
        return this.alR.contains(4);
    }
    
    @Override
    public boolean hasStartDate() {
        return this.alR.contains(5);
    }
    
    @Override
    public boolean hasTarget() {
        return this.alR.contains(6);
    }
    
    @Override
    public boolean hasType() {
        return this.alR.contains(7);
    }
    
    @Override
    public int hashCode() {
        final Iterator<ji$a<?, ?>> iterator = nv.alQ.values().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final ji$a<?, ?> ji$a = iterator.next();
            if (this.a(ji$a)) {
                n = this.b(ji$a).hashCode() + (n + ji$a.hm());
            }
        }
        return n;
    }
    
    @Override
    public HashMap<String, ji$a<?, ?>> hf() {
        return nv.alQ;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    public nv nq() {
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final nw creator = nv.CREATOR;
        nw.a(this, parcel, n);
    }
}
