// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import com.google.android.gms.plus.model.people.Person$PlacesLived;

public final class ny$g extends jj implements Person$PlacesLived
{
    public static final oh CREATOR;
    private static final HashMap<String, ji$a<?, ?>> alQ;
    final int BR;
    final Set<Integer> alR;
    boolean anv;
    String mValue;
    
    static {
        CREATOR = new oh();
        (alQ = new HashMap<String, ji$a<?, ?>>()).put("primary", ji$a.k("primary", 2));
        ny$g.alQ.put("value", ji$a.l("value", 3));
    }
    
    public ny$g() {
        this.BR = 1;
        this.alR = new HashSet<Integer>();
    }
    
    ny$g(final Set<Integer> alR, final int br, final boolean anv, final String mValue) {
        this.alR = alR;
        this.BR = br;
        this.anv = anv;
        this.mValue = mValue;
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
                return this.anv;
            }
            case 3: {
                return this.mValue;
            }
        }
    }
    
    public int describeContents() {
        final oh creator = ny$g.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ny$g)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ny$g ny$g = (ny$g)o;
        for (final ji$a<?, ?> ji$a : com.google.android.gms.internal.ny$g.alQ.values()) {
            if (this.a(ji$a)) {
                if (!ny$g.a(ji$a)) {
                    return false;
                }
                if (!this.b(ji$a).equals(ny$g.b(ji$a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ny$g.a(ji$a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public String getValue() {
        return this.mValue;
    }
    
    @Override
    public boolean hasPrimary() {
        return this.alR.contains(2);
    }
    
    @Override
    public boolean hasValue() {
        return this.alR.contains(3);
    }
    
    @Override
    public int hashCode() {
        final Iterator<ji$a<?, ?>> iterator = ny$g.alQ.values().iterator();
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
        return ny$g.alQ;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public boolean isPrimary() {
        return this.anv;
    }
    
    public ny$g nA() {
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final oh creator = ny$g.CREATOR;
        oh.a(this, parcel, n);
    }
}
