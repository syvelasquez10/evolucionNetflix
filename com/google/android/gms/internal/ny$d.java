// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import com.google.android.gms.plus.model.people.Person$Name;

public final class ny$d extends jj implements Person$Name
{
    public static final of CREATOR;
    private static final HashMap<String, ji$a<?, ?>> alQ;
    final int BR;
    final Set<Integer> alR;
    String amp;
    String ams;
    String anp;
    String anq;
    String anr;
    String ans;
    
    static {
        CREATOR = new of();
        (alQ = new HashMap<String, ji$a<?, ?>>()).put("familyName", ji$a.l("familyName", 2));
        ny$d.alQ.put("formatted", ji$a.l("formatted", 3));
        ny$d.alQ.put("givenName", ji$a.l("givenName", 4));
        ny$d.alQ.put("honorificPrefix", ji$a.l("honorificPrefix", 5));
        ny$d.alQ.put("honorificSuffix", ji$a.l("honorificSuffix", 6));
        ny$d.alQ.put("middleName", ji$a.l("middleName", 7));
    }
    
    public ny$d() {
        this.BR = 1;
        this.alR = new HashSet<Integer>();
    }
    
    ny$d(final Set<Integer> alR, final int br, final String amp, final String anp, final String ams, final String anq, final String anr, final String ans) {
        this.alR = alR;
        this.BR = br;
        this.amp = amp;
        this.anp = anp;
        this.ams = ams;
        this.anq = anq;
        this.anr = anr;
        this.ans = ans;
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
                return this.amp;
            }
            case 3: {
                return this.anp;
            }
            case 4: {
                return this.ams;
            }
            case 5: {
                return this.anq;
            }
            case 6: {
                return this.anr;
            }
            case 7: {
                return this.ans;
            }
        }
    }
    
    public int describeContents() {
        final of creator = ny$d.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ny$d)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ny$d ny$d = (ny$d)o;
        for (final ji$a<?, ?> ji$a : com.google.android.gms.internal.ny$d.alQ.values()) {
            if (this.a(ji$a)) {
                if (!ny$d.a(ji$a)) {
                    return false;
                }
                if (!this.b(ji$a).equals(ny$d.b(ji$a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ny$d.a(ji$a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public String getFamilyName() {
        return this.amp;
    }
    
    @Override
    public String getFormatted() {
        return this.anp;
    }
    
    @Override
    public String getGivenName() {
        return this.ams;
    }
    
    @Override
    public String getHonorificPrefix() {
        return this.anq;
    }
    
    @Override
    public String getHonorificSuffix() {
        return this.anr;
    }
    
    @Override
    public String getMiddleName() {
        return this.ans;
    }
    
    @Override
    public boolean hasFamilyName() {
        return this.alR.contains(2);
    }
    
    @Override
    public boolean hasFormatted() {
        return this.alR.contains(3);
    }
    
    @Override
    public boolean hasGivenName() {
        return this.alR.contains(4);
    }
    
    @Override
    public boolean hasHonorificPrefix() {
        return this.alR.contains(5);
    }
    
    @Override
    public boolean hasHonorificSuffix() {
        return this.alR.contains(6);
    }
    
    @Override
    public boolean hasMiddleName() {
        return this.alR.contains(7);
    }
    
    @Override
    public int hashCode() {
        final Iterator<ji$a<?, ?>> iterator = ny$d.alQ.values().iterator();
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
        return ny$d.alQ;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    public ny$d ny() {
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final of creator = ny$d.CREATOR;
        of.a(this, parcel, n);
    }
}
