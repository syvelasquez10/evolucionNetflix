// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation;

import android.location.Location;
import java.util.Set;
import com.google.ads.AdRequest;
import java.util.Date;

@Deprecated
public final class MediationAdRequest
{
    private final Date d;
    private final AdRequest.Gender e;
    private final Set<String> f;
    private final boolean g;
    
    public MediationAdRequest(final Date d, final AdRequest.Gender e, final Set<String> f, final boolean g) {
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    public Integer getAgeInYears() {
        return null;
    }
    
    public Date getBirthday() {
        return this.d;
    }
    
    public AdRequest.Gender getGender() {
        return this.e;
    }
    
    public Set<String> getKeywords() {
        return this.f;
    }
    
    public Location getLocation() {
        return null;
    }
    
    public boolean isTesting() {
        return this.g;
    }
}
