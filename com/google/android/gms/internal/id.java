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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class id extends es implements SafeParcelable, Moment
{
    public static final ie CREATOR;
    private static final HashMap<String, a<?, ?>> Ep;
    private String AI;
    private final Set<Integer> Eq;
    private String Fe;
    private ib Fm;
    private ib Fn;
    private final int kg;
    private String uS;
    
    static {
        CREATOR = new ie();
        (Ep = new HashMap<String, a<?, ?>>()).put("id", a.g("id", 2));
        id.Ep.put("result", a.a("result", 4, (Class<?>)ib.class));
        id.Ep.put("startDate", (a<?, ?>)a.g("startDate", 5));
        id.Ep.put("target", a.a("target", 6, (Class<?>)ib.class));
        id.Ep.put("type", (a<?, ?>)a.g("type", 7));
    }
    
    public id() {
        this.kg = 1;
        this.Eq = new HashSet<Integer>();
    }
    
    id(final Set<Integer> eq, final int kg, final String us, final ib fm, final String fe, final ib fn, final String ai) {
        this.Eq = eq;
        this.kg = kg;
        this.uS = us;
        this.Fm = fm;
        this.Fe = fe;
        this.Fn = fn;
        this.AI = ai;
    }
    
    public id(final Set<Integer> eq, final String us, final ib fm, final String fe, final ib fn, final String ai) {
        this.Eq = eq;
        this.kg = 1;
        this.uS = us;
        this.Fm = fm;
        this.Fe = fe;
        this.Fn = fn;
        this.AI = ai;
    }
    
    @Override
    protected Object V(final String s) {
        return null;
    }
    
    @Override
    protected boolean W(final String s) {
        return false;
    }
    
    @Override
    protected boolean a(final a a) {
        return this.Eq.contains(a.cq());
    }
    
    @Override
    protected Object b(final a a) {
        switch (a.cq()) {
            default: {
                throw new IllegalStateException("Unknown safe parcelable id=" + a.cq());
            }
            case 2: {
                return this.uS;
            }
            case 4: {
                return this.Fm;
            }
            case 5: {
                return this.Fe;
            }
            case 6: {
                return this.Fn;
            }
            case 7: {
                return this.AI;
            }
        }
    }
    
    @Override
    public HashMap<String, a<?, ?>> cj() {
        return id.Ep;
    }
    
    public int describeContents() {
        final ie creator = id.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof id)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final id id = (id)o;
        for (final a a : com.google.android.gms.internal.id.Ep.values()) {
            if (this.a((a)a)) {
                if (!id.a((a)a)) {
                    return false;
                }
                if (!this.b((a)a).equals(id.b((a)a))) {
                    return false;
                }
                continue;
            }
            else {
                if (id.a((a)a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    Set<Integer> fa() {
        return this.Eq;
    }
    
    ib fr() {
        return this.Fm;
    }
    
    ib fs() {
        return this.Fn;
    }
    
    public id ft() {
        return this;
    }
    
    @Override
    public String getId() {
        return this.uS;
    }
    
    @Override
    public ItemScope getResult() {
        return this.Fm;
    }
    
    @Override
    public String getStartDate() {
        return this.Fe;
    }
    
    @Override
    public ItemScope getTarget() {
        return this.Fn;
    }
    
    @Override
    public String getType() {
        return this.AI;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public boolean hasId() {
        return this.Eq.contains(2);
    }
    
    @Override
    public boolean hasResult() {
        return this.Eq.contains(4);
    }
    
    @Override
    public boolean hasStartDate() {
        return this.Eq.contains(5);
    }
    
    @Override
    public boolean hasTarget() {
        return this.Eq.contains(6);
    }
    
    @Override
    public boolean hasType() {
        return this.Eq.contains(7);
    }
    
    @Override
    public int hashCode() {
        final Iterator<a<?, ?>> iterator = id.Ep.values().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final a a = (a)iterator.next();
            if (this.a((a)a)) {
                n = this.b((a)a).hashCode() + (n + a.cq());
            }
        }
        return n;
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final ie creator = id.CREATOR;
        ie.a(this, parcel, n);
    }
}
