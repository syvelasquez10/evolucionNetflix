// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import com.google.android.gms.plus.model.people.Person$Cover$CoverInfo;

public final class ny$b$a extends jj implements Person$Cover$CoverInfo
{
    public static final oc CREATOR;
    private static final HashMap<String, ji$a<?, ?>> alQ;
    final int BR;
    final Set<Integer> alR;
    int ann;
    int ano;
    
    static {
        CREATOR = new oc();
        (alQ = new HashMap<String, ji$a<?, ?>>()).put("leftImageOffset", ji$a.i("leftImageOffset", 2));
        ny$b$a.alQ.put("topImageOffset", ji$a.i("topImageOffset", 3));
    }
    
    public ny$b$a() {
        this.BR = 1;
        this.alR = new HashSet<Integer>();
    }
    
    ny$b$a(final Set<Integer> alR, final int br, final int ann, final int ano) {
        this.alR = alR;
        this.BR = br;
        this.ann = ann;
        this.ano = ano;
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
                return this.ann;
            }
            case 3: {
                return this.ano;
            }
        }
    }
    
    public int describeContents() {
        final oc creator = ny$b$a.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ny$b$a)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ny$b$a ny$b$a = (ny$b$a)o;
        for (final ji$a<?, ?> ji$a : com.google.android.gms.internal.ny$b$a.alQ.values()) {
            if (this.a(ji$a)) {
                if (!ny$b$a.a(ji$a)) {
                    return false;
                }
                if (!this.b(ji$a).equals(ny$b$a.b(ji$a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ny$b$a.a(ji$a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public int getLeftImageOffset() {
        return this.ann;
    }
    
    @Override
    public int getTopImageOffset() {
        return this.ano;
    }
    
    @Override
    public boolean hasLeftImageOffset() {
        return this.alR.contains(2);
    }
    
    @Override
    public boolean hasTopImageOffset() {
        return this.alR.contains(3);
    }
    
    @Override
    public int hashCode() {
        final Iterator<ji$a<?, ?>> iterator = ny$b$a.alQ.values().iterator();
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
        return ny$b$a.alQ;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    public ny$b$a nv() {
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final oc creator = ny$b$a.CREATOR;
        oc.a(this, parcel, n);
    }
}
