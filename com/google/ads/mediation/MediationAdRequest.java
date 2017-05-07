// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation;

import java.util.Calendar;
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
    private final Location h;
    
    public MediationAdRequest(final Date d, final AdRequest.Gender e, final Set<String> f, final boolean g, final Location h) {
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
    }
    
    public Integer getAgeInYears() {
        if (this.d != null) {
            final Calendar instance = Calendar.getInstance();
            final Calendar instance2 = Calendar.getInstance();
            instance.setTime(this.d);
            final Integer value = instance2.get(1) - instance.get(1);
            if (instance2.get(2) >= instance.get(2)) {
                Integer value2 = value;
                if (instance2.get(2) != instance.get(2)) {
                    return value2;
                }
                value2 = value;
                if (instance2.get(5) >= instance.get(5)) {
                    return value2;
                }
            }
            return value - 1;
        }
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
        return this.h;
    }
    
    public boolean isTesting() {
        return this.g;
    }
}
