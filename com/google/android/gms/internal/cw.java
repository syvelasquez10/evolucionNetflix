// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.location.Location;
import java.util.Set;
import java.util.Date;
import com.google.android.gms.ads.mediation.MediationAdRequest;

@ez
public final class cw implements MediationAdRequest
{
    private final Date d;
    private final Set<String> f;
    private final boolean g;
    private final Location h;
    private final int om;
    private final int qD;
    
    public cw(final Date d, final int om, final Set<String> f, final Location h, final boolean g, final int qd) {
        this.d = d;
        this.om = om;
        this.f = f;
        this.h = h;
        this.g = g;
        this.qD = qd;
    }
    
    @Override
    public Date getBirthday() {
        return this.d;
    }
    
    @Override
    public int getGender() {
        return this.om;
    }
    
    @Override
    public Set<String> getKeywords() {
        return this.f;
    }
    
    @Override
    public Location getLocation() {
        return this.h;
    }
    
    @Override
    public boolean isTesting() {
        return this.g;
    }
    
    @Override
    public int taggedForChildDirectedTreatment() {
        return this.qD;
    }
}
