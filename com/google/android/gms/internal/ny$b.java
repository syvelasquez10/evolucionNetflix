// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.plus.model.people.Person$Cover$CoverPhoto;
import com.google.android.gms.plus.model.people.Person$Cover$CoverInfo;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import com.google.android.gms.plus.model.people.Person$Cover;

public final class ny$b extends jj implements Person$Cover
{
    public static final ob CREATOR;
    private static final HashMap<String, ji$a<?, ?>> alQ;
    final int BR;
    final Set<Integer> alR;
    ny$b$a ank;
    ny$b$b anl;
    int anm;
    
    static {
        CREATOR = new ob();
        (alQ = new HashMap<String, ji$a<?, ?>>()).put("coverInfo", ji$a.a("coverInfo", 2, (Class<?>)ny$b$a.class));
        ny$b.alQ.put("coverPhoto", ji$a.a("coverPhoto", 3, (Class<?>)ny$b$b.class));
        ny$b.alQ.put("layout", ji$a.a("layout", 4, new jf().h("banner", 0), false));
    }
    
    public ny$b() {
        this.BR = 1;
        this.alR = new HashSet<Integer>();
    }
    
    ny$b(final Set<Integer> alR, final int br, final ny$b$a ank, final ny$b$b anl, final int anm) {
        this.alR = alR;
        this.BR = br;
        this.ank = ank;
        this.anl = anl;
        this.anm = anm;
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
                return this.ank;
            }
            case 3: {
                return this.anl;
            }
            case 4: {
                return this.anm;
            }
        }
    }
    
    public int describeContents() {
        final ob creator = ny$b.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ny$b)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ny$b ny$b = (ny$b)o;
        for (final ji$a<?, ?> ji$a : com.google.android.gms.internal.ny$b.alQ.values()) {
            if (this.a(ji$a)) {
                if (!ny$b.a(ji$a)) {
                    return false;
                }
                if (!this.b(ji$a).equals(ny$b.b(ji$a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ny$b.a(ji$a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public Person$Cover$CoverInfo getCoverInfo() {
        return this.ank;
    }
    
    @Override
    public Person$Cover$CoverPhoto getCoverPhoto() {
        return this.anl;
    }
    
    @Override
    public int getLayout() {
        return this.anm;
    }
    
    @Override
    public boolean hasCoverInfo() {
        return this.alR.contains(2);
    }
    
    @Override
    public boolean hasCoverPhoto() {
        return this.alR.contains(3);
    }
    
    @Override
    public boolean hasLayout() {
        return this.alR.contains(4);
    }
    
    @Override
    public int hashCode() {
        final Iterator<ji$a<?, ?>> iterator = ny$b.alQ.values().iterator();
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
        return ny$b.alQ;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    public ny$b nu() {
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final ob creator = ny$b.CREATOR;
        ob.a(this, parcel, n);
    }
}
