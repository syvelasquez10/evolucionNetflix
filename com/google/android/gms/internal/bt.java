// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Set;
import java.util.Date;
import com.google.android.gms.ads.mediation.MediationAdRequest;

public final class bt implements MediationAdRequest
{
    private final Date d;
    private final Set<String> f;
    private final boolean g;
    private final int lZ;
    private final int nD;
    
    public bt(final Date d, final int lz, final Set<String> f, final boolean g, final int nd) {
        this.d = d;
        this.lZ = lz;
        this.f = f;
        this.g = g;
        this.nD = nd;
    }
    
    @Override
    public Date getBirthday() {
        return this.d;
    }
    
    @Override
    public int getGender() {
        return this.lZ;
    }
    
    @Override
    public Set<String> getKeywords() {
        return this.f;
    }
    
    @Override
    public boolean isTesting() {
        return this.g;
    }
    
    @Override
    public int taggedForChildDirectedTreatment() {
        return this.nD;
    }
}
