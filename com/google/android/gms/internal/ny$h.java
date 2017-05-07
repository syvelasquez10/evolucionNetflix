// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import com.google.android.gms.plus.model.people.Person$Urls;

public final class ny$h extends jj implements Person$Urls
{
    public static final oi CREATOR;
    private static final HashMap<String, ji$a<?, ?>> alQ;
    final int BR;
    int FD;
    final Set<Integer> alR;
    String anw;
    private final int anx;
    String mValue;
    
    static {
        CREATOR = new oi();
        (alQ = new HashMap<String, ji$a<?, ?>>()).put("label", ji$a.l("label", 5));
        ny$h.alQ.put("type", ji$a.a("type", 6, new jf().h("home", 0).h("work", 1).h("blog", 2).h("profile", 3).h("other", 4).h("otherProfile", 5).h("contributor", 6).h("website", 7), false));
        ny$h.alQ.put("value", ji$a.l("value", 4));
    }
    
    public ny$h() {
        this.anx = 4;
        this.BR = 1;
        this.alR = new HashSet<Integer>();
    }
    
    ny$h(final Set<Integer> alR, final int br, final String anw, final int fd, final String mValue, final int n) {
        this.anx = 4;
        this.alR = alR;
        this.BR = br;
        this.anw = anw;
        this.FD = fd;
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
            case 5: {
                return this.anw;
            }
            case 6: {
                return this.FD;
            }
            case 4: {
                return this.mValue;
            }
        }
    }
    
    public int describeContents() {
        final oi creator = ny$h.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ny$h)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ny$h ny$h = (ny$h)o;
        for (final ji$a<?, ?> ji$a : com.google.android.gms.internal.ny$h.alQ.values()) {
            if (this.a(ji$a)) {
                if (!ny$h.a(ji$a)) {
                    return false;
                }
                if (!this.b(ji$a).equals(ny$h.b(ji$a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ny$h.a(ji$a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public String getLabel() {
        return this.anw;
    }
    
    @Override
    public int getType() {
        return this.FD;
    }
    
    @Override
    public String getValue() {
        return this.mValue;
    }
    
    @Override
    public boolean hasLabel() {
        return this.alR.contains(5);
    }
    
    @Override
    public boolean hasType() {
        return this.alR.contains(6);
    }
    
    @Override
    public boolean hasValue() {
        return this.alR.contains(4);
    }
    
    @Override
    public int hashCode() {
        final Iterator<ji$a<?, ?>> iterator = ny$h.alQ.values().iterator();
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
        return ny$h.alQ;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    @Deprecated
    public int nB() {
        return 4;
    }
    
    public ny$h nC() {
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final oi creator = ny$h.CREATOR;
        oi.a(this, parcel, n);
    }
}
