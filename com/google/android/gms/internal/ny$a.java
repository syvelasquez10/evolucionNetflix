// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import com.google.android.gms.plus.model.people.Person$AgeRange;

public final class ny$a extends jj implements Person$AgeRange
{
    public static final oa CREATOR;
    private static final HashMap<String, ji$a<?, ?>> alQ;
    final int BR;
    final Set<Integer> alR;
    int ani;
    int anj;
    
    static {
        CREATOR = new oa();
        (alQ = new HashMap<String, ji$a<?, ?>>()).put("max", ji$a.i("max", 2));
        ny$a.alQ.put("min", ji$a.i("min", 3));
    }
    
    public ny$a() {
        this.BR = 1;
        this.alR = new HashSet<Integer>();
    }
    
    ny$a(final Set<Integer> alR, final int br, final int ani, final int anj) {
        this.alR = alR;
        this.BR = br;
        this.ani = ani;
        this.anj = anj;
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
                return this.ani;
            }
            case 3: {
                return this.anj;
            }
        }
    }
    
    public int describeContents() {
        final oa creator = ny$a.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ny$a)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ny$a ny$a = (ny$a)o;
        for (final ji$a<?, ?> ji$a : com.google.android.gms.internal.ny$a.alQ.values()) {
            if (this.a(ji$a)) {
                if (!ny$a.a(ji$a)) {
                    return false;
                }
                if (!this.b(ji$a).equals(ny$a.b(ji$a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ny$a.a(ji$a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public int getMax() {
        return this.ani;
    }
    
    @Override
    public int getMin() {
        return this.anj;
    }
    
    @Override
    public boolean hasMax() {
        return this.alR.contains(2);
    }
    
    @Override
    public boolean hasMin() {
        return this.alR.contains(3);
    }
    
    @Override
    public int hashCode() {
        final Iterator<ji$a<?, ?>> iterator = ny$a.alQ.values().iterator();
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
        return ny$a.alQ;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    public ny$a nt() {
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final oa creator = ny$a.CREATOR;
        oa.a(this, parcel, n);
    }
}
