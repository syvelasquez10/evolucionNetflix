// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import com.google.android.gms.plus.model.people.Person$Organizations;

public final class ny$f extends jj implements Person$Organizations
{
    public static final og CREATOR;
    private static final HashMap<String, ji$a<?, ?>> alQ;
    final int BR;
    int FD;
    String No;
    String Tg;
    final Set<Integer> alR;
    String amE;
    String amo;
    String ant;
    String anu;
    boolean anv;
    String mName;
    
    static {
        CREATOR = new og();
        (alQ = new HashMap<String, ji$a<?, ?>>()).put("department", ji$a.l("department", 2));
        ny$f.alQ.put("description", ji$a.l("description", 3));
        ny$f.alQ.put("endDate", ji$a.l("endDate", 4));
        ny$f.alQ.put("location", ji$a.l("location", 5));
        ny$f.alQ.put("name", ji$a.l("name", 6));
        ny$f.alQ.put("primary", ji$a.k("primary", 7));
        ny$f.alQ.put("startDate", ji$a.l("startDate", 8));
        ny$f.alQ.put("title", ji$a.l("title", 9));
        ny$f.alQ.put("type", ji$a.a("type", 10, new jf().h("work", 0).h("school", 1), false));
    }
    
    public ny$f() {
        this.BR = 1;
        this.alR = new HashSet<Integer>();
    }
    
    ny$f(final Set<Integer> alR, final int br, final String ant, final String tg, final String amo, final String anu, final String mName, final boolean anv, final String amE, final String no, final int fd) {
        this.alR = alR;
        this.BR = br;
        this.ant = ant;
        this.Tg = tg;
        this.amo = amo;
        this.anu = anu;
        this.mName = mName;
        this.anv = anv;
        this.amE = amE;
        this.No = no;
        this.FD = fd;
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
                return this.ant;
            }
            case 3: {
                return this.Tg;
            }
            case 4: {
                return this.amo;
            }
            case 5: {
                return this.anu;
            }
            case 6: {
                return this.mName;
            }
            case 7: {
                return this.anv;
            }
            case 8: {
                return this.amE;
            }
            case 9: {
                return this.No;
            }
            case 10: {
                return this.FD;
            }
        }
    }
    
    public int describeContents() {
        final og creator = ny$f.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ny$f)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ny$f ny$f = (ny$f)o;
        for (final ji$a<?, ?> ji$a : com.google.android.gms.internal.ny$f.alQ.values()) {
            if (this.a(ji$a)) {
                if (!ny$f.a(ji$a)) {
                    return false;
                }
                if (!this.b(ji$a).equals(ny$f.b(ji$a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ny$f.a(ji$a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public String getDepartment() {
        return this.ant;
    }
    
    @Override
    public String getDescription() {
        return this.Tg;
    }
    
    @Override
    public String getEndDate() {
        return this.amo;
    }
    
    @Override
    public String getLocation() {
        return this.anu;
    }
    
    @Override
    public String getName() {
        return this.mName;
    }
    
    @Override
    public String getStartDate() {
        return this.amE;
    }
    
    @Override
    public String getTitle() {
        return this.No;
    }
    
    @Override
    public int getType() {
        return this.FD;
    }
    
    @Override
    public boolean hasDepartment() {
        return this.alR.contains(2);
    }
    
    @Override
    public boolean hasDescription() {
        return this.alR.contains(3);
    }
    
    @Override
    public boolean hasEndDate() {
        return this.alR.contains(4);
    }
    
    @Override
    public boolean hasLocation() {
        return this.alR.contains(5);
    }
    
    @Override
    public boolean hasName() {
        return this.alR.contains(6);
    }
    
    @Override
    public boolean hasPrimary() {
        return this.alR.contains(7);
    }
    
    @Override
    public boolean hasStartDate() {
        return this.alR.contains(8);
    }
    
    @Override
    public boolean hasTitle() {
        return this.alR.contains(9);
    }
    
    @Override
    public boolean hasType() {
        return this.alR.contains(10);
    }
    
    @Override
    public int hashCode() {
        final Iterator<ji$a<?, ?>> iterator = ny$f.alQ.values().iterator();
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
        return ny$f.alQ;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public boolean isPrimary() {
        return this.anv;
    }
    
    public ny$f nz() {
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final og creator = ny$f.CREATOR;
        og.a(this, parcel, n);
    }
}
