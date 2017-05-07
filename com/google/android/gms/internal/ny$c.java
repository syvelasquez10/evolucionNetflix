// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import com.google.android.gms.plus.model.people.Person$Image;

public final class ny$c extends jj implements Person$Image
{
    public static final oe CREATOR;
    private static final HashMap<String, ji$a<?, ?>> alQ;
    final int BR;
    final Set<Integer> alR;
    String uR;
    
    static {
        CREATOR = new oe();
        (alQ = new HashMap<String, ji$a<?, ?>>()).put("url", ji$a.l("url", 2));
    }
    
    public ny$c() {
        this.BR = 1;
        this.alR = new HashSet<Integer>();
    }
    
    public ny$c(final String ur) {
        this.alR = new HashSet<Integer>();
        this.BR = 1;
        this.uR = ur;
        this.alR.add(2);
    }
    
    ny$c(final Set<Integer> alR, final int br, final String ur) {
        this.alR = alR;
        this.BR = br;
        this.uR = ur;
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
                return this.uR;
            }
        }
    }
    
    public int describeContents() {
        final oe creator = ny$c.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ny$c)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ny$c ny$c = (ny$c)o;
        for (final ji$a<?, ?> ji$a : com.google.android.gms.internal.ny$c.alQ.values()) {
            if (this.a(ji$a)) {
                if (!ny$c.a(ji$a)) {
                    return false;
                }
                if (!this.b(ji$a).equals(ny$c.b(ji$a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ny$c.a(ji$a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public String getUrl() {
        return this.uR;
    }
    
    @Override
    public boolean hasUrl() {
        return this.alR.contains(2);
    }
    
    @Override
    public int hashCode() {
        final Iterator<ji$a<?, ?>> iterator = ny$c.alQ.values().iterator();
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
        return ny$c.alQ;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    public ny$c nx() {
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final oe creator = ny$c.CREATOR;
        oe.a(this, parcel, n);
    }
}
