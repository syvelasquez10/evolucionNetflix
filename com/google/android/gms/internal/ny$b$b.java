// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import com.google.android.gms.plus.model.people.Person$Cover$CoverPhoto;

public final class ny$b$b extends jj implements Person$Cover$CoverPhoto
{
    public static final od CREATOR;
    private static final HashMap<String, ji$a<?, ?>> alQ;
    final int BR;
    final Set<Integer> alR;
    int lf;
    int lg;
    String uR;
    
    static {
        CREATOR = new od();
        (alQ = new HashMap<String, ji$a<?, ?>>()).put("height", ji$a.i("height", 2));
        ny$b$b.alQ.put("url", ji$a.l("url", 3));
        ny$b$b.alQ.put("width", ji$a.i("width", 4));
    }
    
    public ny$b$b() {
        this.BR = 1;
        this.alR = new HashSet<Integer>();
    }
    
    ny$b$b(final Set<Integer> alR, final int br, final int lg, final String ur, final int lf) {
        this.alR = alR;
        this.BR = br;
        this.lg = lg;
        this.uR = ur;
        this.lf = lf;
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
                return this.lg;
            }
            case 3: {
                return this.uR;
            }
            case 4: {
                return this.lf;
            }
        }
    }
    
    public int describeContents() {
        final od creator = ny$b$b.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ny$b$b)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ny$b$b ny$b$b = (ny$b$b)o;
        for (final ji$a<?, ?> ji$a : com.google.android.gms.internal.ny$b$b.alQ.values()) {
            if (this.a(ji$a)) {
                if (!ny$b$b.a(ji$a)) {
                    return false;
                }
                if (!this.b(ji$a).equals(ny$b$b.b(ji$a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ny$b$b.a(ji$a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public int getHeight() {
        return this.lg;
    }
    
    @Override
    public String getUrl() {
        return this.uR;
    }
    
    @Override
    public int getWidth() {
        return this.lf;
    }
    
    @Override
    public boolean hasHeight() {
        return this.alR.contains(2);
    }
    
    @Override
    public boolean hasUrl() {
        return this.alR.contains(3);
    }
    
    @Override
    public boolean hasWidth() {
        return this.alR.contains(4);
    }
    
    @Override
    public int hashCode() {
        final Iterator<ji$a<?, ?>> iterator = ny$b$b.alQ.values().iterator();
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
        return ny$b$b.alQ;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    public ny$b$b nw() {
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final od creator = ny$b$b.CREATOR;
        od.a(this, parcel, n);
    }
}
